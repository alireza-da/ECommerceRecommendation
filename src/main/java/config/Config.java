package main.java.config;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.Objects;

public class Config {
    private String filePath = "./resources/config.yaml";
    private static Map<String, Object> configData;
    private File configFile;
    private Object configObj;
    private YamlReader yamlReader;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public File getFile(String path) {
        this.filePath = path;
        return new File(Objects.requireNonNull(YamlReader.class.getClassLoader().getResource(filePath)).getFile());
    }
    public Object readAsMap(File file) throws FileNotFoundException, YamlException {
        yamlReader = new YamlReader(new FileReader(file));
        return (Map) yamlReader.read();
    }
    public Object readAsObject(String value) throws FileNotFoundException, YamlException {
        yamlReader = new YamlReader(new FileReader(configFile));
        Map read = (Map) yamlReader.read();
        return read.get(value);
    }
    public <T> T read(Class<T> tClass) throws FileNotFoundException, YamlException {
        return yamlReader.read(tClass);
    }
    public void load() throws YamlException, FileNotFoundException {
        configFile = this.getFile(this.filePath);
        readAsMap(configFile);
        configObj = this.readAsMap(this.configFile);
    }

    public static Map<String, Object> getConfigData() {
        return configData;
    }

    public static void setConfigData(Map<String, Object> configData) {
        Config.configData = configData;
    }

    public File getConfigFile() {
        return configFile;
    }

    public void setConfigFile(File configFile) {
        this.configFile = configFile;
    }

    public Object getConfigObj() {
        return configObj;
    }

    public void setConfigObj(Object configObj) {
        this.configObj = configObj;
    }
}
