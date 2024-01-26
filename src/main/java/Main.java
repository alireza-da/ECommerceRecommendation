package main.java;

import com.esotericsoftware.yamlbeans.YamlException;
import main.java.config.Config;
import main.java.logger.Logger;
import main.java.models.*;
import main.java.models.database.persistancefecade.PersistenceFecade;
import main.java.models.extsystems.adaptors.IItemInput;
import main.java.models.extsystems.adaptors.ItemInputTUIAdaptor;
import main.java.models.factory.Factory;
import main.java.models.policies.IRecommendationPolicy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, URISyntaxException, InterruptedException {
        Factory factory = Factory.getInstance();
        Config config = factory.getConfig();
        Logger logger = factory.getLogger();
        IItemInput ui = factory.getItemInputAdaptor();
        ECommerceController eCommerceController = factory.getECommerceController();
        try{
            User u = generateUser(ui);
            logger.log(Level.INFO, String.format("User with name %s saved into database with id: %s",
                    u.getName(), u.getID()));
            PurchaseList purchaseList = getPurchaseList(ui, eCommerceController, logger);
            logger.log(Level.INFO, String.format("Purchase %s finished with status: %s",
                    purchaseList.hashCode(), purchaseList.getState()));
            // registering purchase
            u.getPurchaseHistory().add(purchaseList);
            logger.log(Level.INFO, "Finished First Purchase. Recommendations are now available for next purchases");
            // submitting another purchase
            purchaseWithRecommendation(u, ui, eCommerceController, logger);
        }catch (Exception e){
            e.printStackTrace();
            logger.log(Level.WARNING, "Something went wrong transitioning to safe state.");
            eCommerceController.state.onError(eCommerceController);
            Thread.sleep(1000);
            restartApplication();
        }
    }

    private static void purchaseWithRecommendation(User u, IItemInput ui, ECommerceController eCommerceController, Logger logger) throws Exception {
        PurchaseList purchaseList = new PurchaseList();
        ArrayList<Item> items = new ArrayList<>();
        purchaseList.setItems(items);
        items = purchaseList.getItems();
        ItemCat itemCat = eCommerceController.getItemCat();
        System.out.println("ENTER ITEMS (FINISH ITEM NAME WITH <<END>> ON EXIT): ");
        String output;
        while (!(output = ui.scanItemName()).endsWith("end")){
            try{
                Item item = itemCat.getItem(output);
                if(item == null){
                    item = createItem(output, ui);
                    logger.log(Level.INFO, String.format("Created Item with name: %s", output));
                }else createDescForItem(item, ui);
                logger.log(Level.INFO, String.format("Item %s found hashcode: %s", output, item.hashCode()));
                itemCat.addItem(item);
                items.add(item);
                Recommendation recommendation = purchaseList.getRecommendation(u);
                int cnt = 1;
                for(Item i: recommendation.getItems()){
                    logger.log(Level.INFO, String.format("Recommendation %s : %s", cnt, i.getName()));
                    cnt++;
                }
            }catch (Exception e){
                e.printStackTrace();
                logger.log(Level.WARNING, String.format("Something went wrong while fetching item %s",
                        output));
            }
        }
        try{
            Item item = itemCat.getItem(output.substring(0, output.length()-4));
            if(item == null){
                item = createItem(output, ui);
                logger.log(Level.INFO, String.format("Created Item with name: %s", output));
            }else createDescForItem(item, ui);
            logger.log(Level.INFO, String.format("Item %s found hashcode: %s", output, item.hashCode()));
            itemCat.addItem(item);
            items.add(item);
        }catch (Exception e){
            e.printStackTrace();
            logger.log(Level.WARNING, String.format("Something went wrong while fetching item %s",
                    output));
        }


        purchaseList.finishPurchase();
    }

    private static PurchaseList getPurchaseList(IItemInput ui, ECommerceController eCommerceController, Logger logger) throws Exception {
        PurchaseList purchaseList = new PurchaseList();
        ArrayList<Item> items = new ArrayList<>();
        ItemCat itemCat = eCommerceController.getItemCat();
        System.out.println("ENTER ITEMS (FINISH ITEM NAME WITH <<END>> ON EXIT): ");
        String output;
        while (!(output = ui.scanItemName()).endsWith("end")){
            try{
                Item item = itemCat.getItem(output);
                if(item == null){
                    item = createItem(output, ui);
                    logger.log(Level.INFO, String.format("Created Item with name: %s", output));
                }else createDescForItem(item, ui);
                logger.log(Level.INFO, String.format("Item %s found hashcode: %s", output, item.hashCode()));
                itemCat.addItem(item);
                items.add(item);
            }catch (Exception e){
                e.printStackTrace();
                logger.log(Level.WARNING, String.format("Something went wrong while fetching item %s",
                        output));
            }
        }
        try{
            Item item = itemCat.getItem(output);
            if(item == null){
                item = createItem(output, ui);
                logger.log(Level.INFO, String.format("Created Item with name: %s", output));
            }else createDescForItem(item, ui);
            logger.log(Level.INFO, String.format("Item %s found hashcode: %s", output, item.hashCode()));
            itemCat.addItem(item);
            items.add(item);
        }catch (Exception e){
            e.printStackTrace();
            logger.log(Level.WARNING, String.format("Something went wrong while fetching item %s",
                    output));
        }
        purchaseList.setItems(items);
        purchaseList.finishPurchase();
        return purchaseList;
    }
    private static Item createItem(String name, IItemInput ui) throws IOException {
        Item item = new Item();
        ArrayList<ItemDesc> itemDescs = new ArrayList<>();
        item.setName(name);
        item.setItemDescs(itemDescs);
        item.getItemDescs().add(createDescForItem(item, ui));
        return item;
    }
    private static ItemDesc createDescForItem(Item item, IItemInput ui) throws IOException {
        ItemDesc itemDesc = new ItemDesc();
        ArrayList<String> keys = new ArrayList<>();
        keys.add("price");
        keys.add("discount");
        keys.add("quantity");
        HashMap<String, String> output = ui.scanMultiArgument(keys);
        itemDesc.setQuantity(Integer.valueOf(output.get("quantity")));
        itemDesc.setDiscount(Double.valueOf(output.get("discount")));
        itemDesc.setPrice(Integer.parseInt(output.get("price")));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        itemDesc.setDateAdded(now);
        return itemDesc;
    }
    private static User generateUser(IItemInput ui) throws Exception {
        PersistenceFecade persistenceFecade = PersistenceFecade.getInstance();
        User user = new User();
        ArrayList<String> keys = new ArrayList<>();
        keys.add("NAME");
        HashMap<String, String> output = ui.scanMultiArgument(keys);
        String name = output.get("NAME");
        user.setName(name);
        persistenceFecade.put(user);
        return user;
    }

    public static void restartApplication() throws URISyntaxException, IOException {
        final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
        final File currentJar = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI());

        /* is it a jar file? */
        if(!currentJar.getName().endsWith(".jar"))
            return;

        /* Build command: java -jar application.jar */
        final ArrayList<String> command = new ArrayList<String>();
        command.add(javaBin);
        command.add("-jar");
        command.add(currentJar.getPath());

        final ProcessBuilder builder = new ProcessBuilder(command);
        builder.start();
        System.exit(0);
    }
}