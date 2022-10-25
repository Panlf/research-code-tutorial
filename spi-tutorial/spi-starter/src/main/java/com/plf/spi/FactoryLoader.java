package com.plf.spi;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

/**
 * @author panlf
 * @date 2022/10/25
 */
public class FactoryLoader {
    private static Map<String, List<String>> classMap;

    public static Class<?>[] getFactoryClass(String className) throws Exception {
        if(classMap == null){
            initMap();
        }
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        List<String>  cns = classMap.get(className);
        List<Class<?>> classes = new ArrayList<>();
        for(String cn : cns){
            classes.add(classLoader.loadClass(cn));
        }
        return classes.toArray(new Class[0]);
    }

    private static void initMap() throws IOException {
        classMap = new HashMap<>();
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        Enumeration<URL> resources = classLoader.getResources("META-INF/spring.factories");
        while (resources.hasMoreElements()){
            Properties properties = new Properties();
            URL url = resources.nextElement();
            InputStream inputStream = url.openStream();
            properties.load(inputStream);
            Set<String> stringSet = properties.stringPropertyNames();
            for(String s:stringSet){
                List<String> cl = classMap.get(s);
                if(cl == null){
                    String[] classNames = properties.getProperty(s).split(",");
                    List<String> cll = new ArrayList<>(Arrays.asList(classNames));
                    classMap.put(s,cll);
                }else{
                    String[] classNames = properties.getProperty(s).split(",");
                    cl.addAll(Arrays.asList(classNames));
                }
            }
            inputStream.close();
        }
        
        for(String key:classMap.keySet()){
            List<String> oldList = classMap.get(key);
            List<String> newList = new ArrayList<>(new HashSet<>(oldList));
            classMap.replace(key,oldList,newList);
        }
    }
}
