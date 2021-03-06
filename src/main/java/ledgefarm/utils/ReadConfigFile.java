package ledgefarm.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;

public class ReadConfigFile {

   public InputStream input = null;
   public Properties prop = null;

   public ReadConfigFile() {
       try {
           input =  new FileInputStream(Paths.get(System.getProperty("user.dir"),"config.properties").toString());
           prop = new Properties();
           prop.load(input);
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

   public String getObject(String data) throws IOException
   {
       String getData=prop.getProperty(data);
       return getData;
   }
}