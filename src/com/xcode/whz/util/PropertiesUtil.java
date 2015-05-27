package com.xcode.whz.util;

import java.util.ResourceBundle;

public class PropertiesUtil {
      /**
       * 
       * @param ProName 资源文件名不要后缀
       * @param key
       * @return
       */
 public static String getProperties(String ProName,String key) {
	return ResourceBundle.getBundle(ProName).getString(key);
 }
    
}
