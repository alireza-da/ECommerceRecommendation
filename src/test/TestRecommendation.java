package test;

import com.esotericsoftware.yamlbeans.YamlException;
import main.java.config.Config;
import main.java.logger.Logger;
import main.java.models.*;
import main.java.models.extsystems.adaptors.IItemInput;
import main.java.models.factory.Factory;
import main.java.models.states.purchase.PurchaseFinished;
import main.java.models.states.purchase.PurchaseStarted;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;

public class TestRecommendation {
    private final Logger logger = Factory.getInstance().getLogger();
    @Test
    public void testRecommendation() throws Exception { // test recommendation based on an item list
        User user = createMockUser();
        Assert.assertEquals(user.getName(), "John Doe");
        Factory factory = Factory.getInstance();

        ECommerceController eCommerceController = factory.getECommerceController();
        Assert.assertEquals(eCommerceController.getClass(), ECommerceController.class);

        produceMockItems(eCommerceController.getItemCat());
        Assert.assertEquals(eCommerceController.getItemCat().getItemList().size(), 3);
        // start registering items by user input
        user.setPurchaseHistory(new ArrayList<>());
        PurchaseList purchaseList = produceMockPurchaseList(eCommerceController.getItemCat());
        Assert.assertEquals(purchaseList.getState().getClass(), PurchaseStarted.class);
        user.getPurchaseHistory().add(purchaseList);

        Assert.assertEquals(user.getPurchaseHistory().size(), 1);
        Assert.assertEquals(user.getPurchaseHistory().get(0).getItems().size(), 3);

        purchaseList.finishPurchase();
        Assert.assertEquals(purchaseList.getState().getClass(), PurchaseFinished.class);

        PurchaseList newPurchaseList = produceMockPurchaseList(eCommerceController.getItemCat());
        Recommendation recommendation = newPurchaseList.getRecommendation(user);
        Assert.assertFalse(recommendation.getItems().isEmpty());
        checkRecommendation(recommendation);
    }
    private User createMockUser(){
        User user = new User("John Doe");
        user.setReviews(new ArrayList<Review>());
        user.setPurchaseHistory(new ArrayList<PurchaseList>());
        return user;
    }

    private void produceMockItems(ItemCat itemCat) throws YamlException, FileNotFoundException {
        Factory factory = Factory.getInstance();
        Config config = factory.getConfig();
        ArrayList<String> _items = (ArrayList<String>) config.readAsObject("Items");
        for(String s: _items){
            Item item = new Item();
            item.setName(s);
            ArrayList<ItemDesc> itemDescs = new ArrayList<>();
            item.setItemDescs(itemDescs);
            Assert.assertEquals(item.getName(), s);
            itemCat.addItem(item);
        }
    }
    private PurchaseList produceMockPurchaseList(ItemCat itemCat){
        PurchaseList purchaseList = new PurchaseList();
        ArrayList<Item> items = new ArrayList<>();
        int total = 0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        for(Item item: itemCat.getItemList()){
            ItemDesc itemDesc = new ItemDesc();
            itemDesc.setDateAdded(now);
            itemDesc.setDiscount(Math.random());
            itemDesc.setPrice(ThreadLocalRandom.current().nextInt(1, 1000 + 1));
            itemDesc.setQuantity(ThreadLocalRandom.current().nextInt(1, 1000 + 1));
            item.getItemDescs().add(itemDesc);
            items.add(item);
            total += (int) (itemDesc.getPrice() * (1 - itemDesc.getDiscount()));
        }
        purchaseList.setItems(items);
        purchaseList.setTotal(BigInteger.valueOf(total));
        purchaseList.setSubmitDate(now);
        return purchaseList;
    }

    private void checkRecommendation(Recommendation recommendation){
        StringBuilder output = new StringBuilder("Recommendation: ");
        int cnt = 1;
        for(Item item: recommendation.getItems()){
            output.append("\n").append(cnt).append("-").append(item.getName());
            cnt++;
        }
        logger.log(Level.INFO, output.toString());
    }

}
