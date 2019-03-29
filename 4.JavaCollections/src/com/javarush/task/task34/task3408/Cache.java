package com.javarush.task.task34.task3408;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<>();   //TODO add your code here

    public V getByKey(K key, Class<V> clazz) throws Exception {
        //TODO add your code here
        V temp;
        while ((temp = cache.get(key)) == null)
            put((V)Class.forName(clazz.getName()).getConstructor(new Class[]{key.getClass()}).newInstance(key));
        return temp;
    }

    public boolean put(V obj) {
        //TODO add your code here
        try {
            Method method = Class.forName(obj.getClass().getName()).getDeclaredMethod ("getKey", null);
            method.setAccessible(true);
            cache.put((K)method.invoke(obj, null), obj);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public int size() {
        return cache.size();
    }
}
