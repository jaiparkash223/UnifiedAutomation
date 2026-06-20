package com.unifiedAutomation.utils;

import java.io.File;
import java.io.InputStream;
import java.util.*;
import java.util.Properties;

public class PropertiesLoader {
    public static Map<String, Properties> load() {
        Map<String, Properties> propertiesMap = new HashMap<>();
        File propertiesFolder = new File("src/main/resources/properties");
        File[] fileList = propertiesFolder.listFiles();
        for(File file: fileList) {
            if(file.isFile() && file.getName().endsWith(".properties")) {
                try(InputStream input = PropertiesLoader.class.getClassLoader().getResourceAsStream("properties/" + file.getName())) {
                    if (input == null) {
                        System.out.println("Sorry, unable to find " + file.getName());
                        continue;
                    }
                    Properties properties = new Properties();
                    properties.load(input);
                    String key = file.getName().replace(".properties", "");
                    propertiesMap.put(key, properties);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return propertiesMap;
    }
}
