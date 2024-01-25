package test;

import com.esotericsoftware.yamlbeans.YamlException;
import main.java.models.Item;
import main.java.models.extsystems.adaptors.IItemInput;
import main.java.models.extsystems.adaptors.ItemInputTUIAdaptor;
import main.java.models.policies.IRecommendationPolicy;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

public class TestConfig {
    @Test
    public void readAsMap() throws YamlException, FileNotFoundException {
        main.java.config.Config config = new main.java.config.Config();
        config.load();
        Object itemInputAdaptor = config.readAsObject(IItemInput.class.getSimpleName());
        Object recomPolicy = config.readAsObject(IRecommendationPolicy.class.getSimpleName());
        Assert.assertEquals("main.java.models.extsystems.adaptors.ItemInputTUIAdaptor", itemInputAdaptor);
        Assert.assertEquals("main.java.models.policies.HistoryBasedRecomPolicy", recomPolicy);
    }
}
