package com.ledgefarm.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadConfigFile {

   public InputStream input = null;
   public static Properties prop = null;

   public ReadConfigFile() {
       try {
           input =  new FileInputStream(System.getProperty("user.dir") + "\\config.properties");
           prop = new Properties();
           prop.load(input);
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

   public static String getObject(String data) throws IOException
   {
       ReadConfigFile readConfigFile=new ReadConfigFile();
       String getData=prop.getProperty(data);
       return getData;
   }
}