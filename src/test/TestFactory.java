package test;

import com.esotericsoftware.yamlbeans.YamlException;
import main.java.config.Config;
import main.java.logger.Logger;
import main.java.models.ECommerceController;
import main.java.models.extsystems.adaptors.IItemInput;
import main.java.models.factory.Factory;
import main.java.models.policies.IRecommendationPolicy;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.logging.Level;

public class TestFactory {
    @Test
    public void factoryInitiator() throws YamlException, FileNotFoundException {
        Logger logger = Factory.getInstance().getLogger();
        logger.log(Level.INFO, "----- APPLICATION STARTED -----");
        logger.logp(Level.INFO, "main", "factoryInitiator", "Factory Initiation Started ⌛");

        // load config
        try{
            Config config = Factory.getInstance().getConfig();
            logger.log(Level.INFO, "Config loaded: " + config.getFilePath());

        }catch (YamlException | FileNotFoundException ex) {
            logger.log(Level.WARNING, "Config not loaded properly");
        }
        // load controller
        try {
            ECommerceController eCommerceController = Factory.getInstance().getECommerceController();
            logger.log(Level.INFO, "Controller loaded");
        }catch (Exception e){
            logger.log(Level.WARNING, "Controller not loaded properly");
        }
        // load ItemInputAdaptor
        try {
            IItemInput itemInputAdaptor = Factory.getInstance().getItemInputAdaptor();
            logger.log(Level.INFO, "ItemInputAdaptor loaded " + itemInputAdaptor);
        }catch (Exception e){
            logger.log(Level.WARNING, "ItemInputAdaptor not loaded properly: " + e);
        }
        // load Recommendation Policy
        try {
            IRecommendationPolicy iRecommendationPolicy = Factory.getInstance().getRecommendationPolicy();
            logger.log(Level.INFO, "Recommendation Policy loaded: "+iRecommendationPolicy.getClass());
        }catch (Exception e){
            logger.log(Level.WARNING, "Recommendation Policy not loaded properly: "+e);
        }
        logger.log(Level.INFO, "Factory Check Done ✅");

    }
}
