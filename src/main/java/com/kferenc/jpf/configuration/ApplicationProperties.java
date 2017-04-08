package com.kferenc.jpf.configuration;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ApplicationProperties {

    Properties properties;
    String propertiesPath;

    public ApplicationProperties() {
        properties = new Properties();
    }

    public void setLocation(String location) {
        propertiesPath = this.getClass().getResource(location).getFile();
    }

    public void loadConfig() throws IOException {
        if (propertiesPath == null) {
            return;
        }
        FileInputStream inputStream = new FileInputStream(propertiesPath);
        properties.load(inputStream);
    }

    public void storeConfig() throws IOException {
        if (propertiesPath == null) {
            return;
        }
        FileOutputStream outputStream = new FileOutputStream(propertiesPath);
        properties.store(outputStream, "Penguin Forum configuration");
    }

    public Properties getProperties() {
        return properties;
    }

    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }

}
