package br.edu.ifsp.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    String propFileName;

    public PropertiesReader(String propFileName) {
        this.propFileName = propFileName;
    }

    public String getPropertieValue(String propKey) throws IOException {

        Properties prop = new Properties();

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }

        return prop.getProperty(propKey);

    }
}
