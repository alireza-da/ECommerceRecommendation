package main.java.models.factory;

import com.esotericsoftware.yamlbeans.YamlException;
import main.java.config.Config;
import main.java.logger.Logger;
import main.java.models.ECommerceController;
import main.java.models.ItemCat;
import main.java.models.database.IStore;
import main.java.models.extsystems.adaptors.IItemInput;
import main.java.models.extsystems.adaptors.ItemInputTUIAdaptor;
import main.java.models.policies.IRecommendationPolicy;

import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;

public class Factory {
    private static Factory instance;

    public static Factory getInstance() {
        if(instance == null){

            instance = new Factory();
        }
        return instance;
    }

    private  IItemInput itemInputAdaptor;
    private  IRecommendationPolicy recommendationPolicy;
    private  ECommerceController eCommerceController;
    private  Config config;
    private  Logger logger;

    private IStore store;

    public  IItemInput getItemInputAdaptor() throws YamlException, FileNotFoundException, ClassNotFoundException {
        if(itemInputAdaptor == null){
            // TODO: -add adaptor
            String className = (String) config.readAsObject(IItemInput.class.getSimpleName());
            try{
                Class<?> ita = Class.forName(className);
                Constructor<?> constructor = ita.getConstructor();
                itemInputAdaptor = (IItemInput) constructor.newInstance();

            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                logger.logp(Level.WARNING, "Factory", "getItemInputAdaptor", "Failed to instantiate");
            }

        }
        return itemInputAdaptor;
    }
    public IRecommendationPolicy getRecommendationPolicy() throws YamlException, FileNotFoundException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if(recommendationPolicy == null){
            if(config != null) {
                recommendationPolicy = (IRecommendationPolicy)
                        Class.forName(String.valueOf(config.readAsObject(IRecommendationPolicy.class.getSimpleName())))
                                .getConstructor().newInstance();
            }
        }
        return recommendationPolicy;
    }
    public ECommerceController getECommerceController(){
        if(eCommerceController == null){
            eCommerceController = ECommerceController.getInstance();
        }
        return eCommerceController;
    }
    public Config getConfig() throws YamlException, FileNotFoundException {
        if(config == null){
            config = new Config();
            config.load();
        }
        return config;
    }
    public Logger getLogger(){
        if(logger == null){
            logger = new Logger();
        }
        return logger;
    }

    public IStore getStorageUnit() throws YamlException, FileNotFoundException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if(store == null){
            getConfig();
            String className = (String) config.readAsObject(IStore.class.getSimpleName());
            store = (IStore)
                    Class.forName(className).getConstructor().newInstance();
        }
        return store;
    }
}
