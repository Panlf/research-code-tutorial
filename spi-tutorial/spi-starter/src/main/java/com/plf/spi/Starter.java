package com.plf.spi;

//import com.plf.data.DataAutoConfiguration;

import java.io.InputStream;
import java.net.URL;
import java.util.*;

/**
 * @author panlf
 * @date 2022/10/24
 */
public class Starter {
    public static void main(String[] args) throws Exception {

      Class<?>[] classes =  FactoryLoader.getFactoryClass("org.springframework.boot.autoconfigure.EnableAutoConfigurtion1");
        for(Class<?> c:classes){
            System.out.println(c);
        }
        /*
        Properties properties = new Properties();

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        Enumeration<URL> resources = classLoader.getResources("META-INF/spring.factories");
        List<String> classes = new ArrayList<>();
        while (resources.hasMoreElements()){
            URL url = resources.nextElement();
            InputStream inputStream = url.openStream();
            properties.load(inputStream);
            String property = properties.getProperty("org.springframework.boot.autoconfigure.EnableAutoConfigurtion");
            String[] classNames = property.split(",");
            classes.addAll(Arrays.asList(classNames));
            inputStream.close();
        }

        for(String s:classes){
          Class<?> loadClass =  classLoader.loadClass(s);
            DataAutoConfiguration dataAutoConfiguration = (DataAutoConfiguration) loadClass.getDeclaredConstructor().newInstance();
            dataAutoConfiguration.autoConfiguration();
        }

         */
    }
}
