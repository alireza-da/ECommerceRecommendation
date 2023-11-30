package main.java.models.factory;

import com.esotericsoftware.yamlbeans.YamlException;
import main.java.config.Config;
import main.java.logger.Logger;
import main.java.models.ECommerceController;
import main.java.models.extsystems.adaptors.IItemInput;
import main.java.models.extsystems.adaptors.ItemInputAdaptor1;
import main.java.models.policies.IRecommendationPolicy;

import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;

public class Factory {
    private static IItemInput itemInputAdaptor;
    private static IRecommendationPolicy recommendationPolicy;
    private static ECommerceController eCommerceController;
    private static Config config;
    private static Logger logger;
    public static IItemInput getItemInputAdaptor() throws YamlException, FileNotFoundException, ClassNotFoundException {
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
    public static IRecommendationPolicy getRecommendationPolicy(){
        return recommendationPolicy;
    }
    public static ECommerceController getECommerceController(){
        return eCommerceController;
    }
    public static Config getConfig() throws YamlException, FileNotFoundException {
        if(config == null){
            config = new Config();
            config.load();
        }
        return config;
    }
    public static Logger getLogger(){
        if(logger == null){
            logger = new Logger();
        }
        return logger;
    }
}
