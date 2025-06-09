package org.comcast.crm.generic.fileUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 *
 * Utility class for reading data from property files.
 * <p>
 * This class loads a `.properties` file and provides methods to fetch key-value pairs.
 * </p>
 * <p>
 * @author Siddharth Malviya
 * </p>
 */
public class PropReader {

    private final Properties props;

    /**
     * Loads the properties file from the given file path.
     *
     * @param filePath the absolute or relative path to the .properties file
     * @throws IOException if the file cannot be read
     */
    public PropReader(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        props = new Properties();
        props.load(fis);
    }
    /**
     * Returns the value associated with the given property key.
     *
     * @param key the name of the property
     * @return the value corresponding to the key, or {@code null} if the key doesn't exist
     */
    public String get(String key) {
        if(!containsKey(key)) {
            throw new KeyNotPresentException(key + " is not present in property file");
        }
        return props.getProperty(key);
    }


    /**
     * Checks whether a property with the given key exists.
     *
     * @param key the name of the property
     * @return {@code true} if the key exists, {@code false} otherwise
     */
    public boolean containsKey(String key)
    {
        return props.containsKey(key);
    }


    /**
     * Returns a map containing all key-value pairs from the loaded properties file.
     * <p>
     * Each key and its corresponding value from the {@code Properties} object is inserted
     * into a {@code HashMap<String, String>} and returned to the caller.
     * </p>
     *
     * @return a map of all key-value pairs present in the properties file
     */
    public Map<String, String> loadAsMap()
    {
        Map<String, String> res = new HashMap<>();
        Set<String> keys = props.stringPropertyNames();
        for (String key: keys)
        {
            res.put(key, props.getProperty(key));
        }

        return res;
    }
}


class KeyNotPresentException extends RuntimeException {
    KeyNotPresentException(String message) {
        super(message);
    }
}