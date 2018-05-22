package com.simulation.lab.bdc.util;

import java.util.HashMap;
import java.util.Map;

public class Session {

    private static Map<String,Object> map = new HashMap<String,Object>();

    /**
     * 获取session中缓存的对象
     * @param key
     * @return
     */
      public static Object getAttribute(String key){
            return map.get(key);
    }
    /**
     * 移除在session中缓存的对象
     * @param key
     */
    public static void remove(String key){
        map.remove(key);
    }
    /**
     * 向session中添加对象
     * @param key
     * @param value
     * @return
     */
    public static Object put(String key,Object value){
        return map.put(key,value);
    }
}
