package common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.Properties;


public final class PropertyFile {                
    private static String INI_FILE_EXT = ".properties";
    
    public static Object getProperty(String linkName, String propertyName) {        
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(new File(linkName.concat(INI_FILE_EXT))));
        } catch (IOException e) { }        
        
        return props.getProperty(propertyName);
    }
    
    public static Properties getProperties(String linkName) {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(new File(linkName.concat(INI_FILE_EXT))));
        } catch (IOException e) {}
        
        return props;
    }
}
