package main.java;

import com.esotericsoftware.yamlbeans.YamlException;
import main.java.config.Config;
import main.java.logger.Logger;
import main.java.models.ECommerceController;
import main.java.models.extsystems.adaptors.IItemInput;
import main.java.models.extsystems.adaptors.ItemInputAdaptor1;
import main.java.models.factory.Factory;
import main.java.models.policies.IRecommendationPolicy;

import java.io.FileNotFoundException;
import java.util.logging.Level;

public class Main {
    public static void main(String[] args) throws YamlException, FileNotFoundException {
        Logger logger = Factory.getLogger();
        logger.log(Level.INFO, "----- APPLICATION STARTED -----");
        factoryInitiator(logger);
    }
    private static void factoryInitiator(Logger logger) throws YamlException, FileNotFoundException {

        logger.logp(Level.INFO, "main", "factoryInitiator", "Factory Initiation Started ⌛");

        // load config
        try{
            Config config = Factory.getConfig();
            logger.log(Level.INFO, "Config loaded: " + config.getFilePath());

        }catch (YamlException | FileNotFoundException ex) {
            logger.log(Level.WARNING, "Config not loaded properly");
        }
        // load controller
        try {
             ECommerceController eCommerceController = Factory.getECommerceController();
             logger.log(Level.INFO, "Controller loaded");
        }catch (Exception e){
            logger.log(Level.WARNING, "Controller not loaded properly");
        }
        // load ItemInputAdaptor
        try {
            IItemInput itemInputAdaptor = Factory.getItemInputAdaptor();
            logger.log(Level.INFO, "ItemInputAdaptor loaded " + itemInputAdaptor);
        }catch (Exception e){
            e.printStackTrace();
            logger.log(Level.WARNING, "ItemInputAdaptor not loaded properly: " + e);
        }
        // load Recommendation Policy
        try {
            IRecommendationPolicy iRecommendationPolicy = Factory.getRecommendationPolicy();
            logger.log(Level.INFO, "Recommendation Policy loaded");
        }catch (Exception e){
            logger.log(Level.WARNING, "Recommendation Policy not loaded properly");
        }
        logger.log(Level.INFO, "Factory Check Done ✅");

    }
}