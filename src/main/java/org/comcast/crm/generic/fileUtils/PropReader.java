package org.comcast.crm.generic.fileUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

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
}
