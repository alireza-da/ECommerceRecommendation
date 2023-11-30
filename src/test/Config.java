package test;

import com.esotericsoftware.yamlbeans.YamlException;
import main.java.models.Item;
import main.java.models.extsystems.adaptors.IItemInput;
import main.java.models.extsystems.adaptors.ItemInputAdaptor1;
import main.java.models.policies.IRecommendationPolicy;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

public class Config {
    @Test
    public void readAsMap() throws YamlException, FileNotFoundException {
        main.java.config.Config config = new main.java.config.Config();
        config.load();
        Object itemInputAdaptor = config.readAsObject(IItemInput.class.getSimpleName());
        Object recomPolicy = config.readAsObject(IRecommendationPolicy.class.getSimpleName());
        Assert.assertEquals("ItemInputAdaptor1", itemInputAdaptor);
        Assert.assertEquals("HistoryBasedRecomPolicy", recomPolicy);
    }
}
