package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        //напишите тут ваш код
        int size = 0;
        for (List<V> list:
        map.values()){
            size += list.size();
        }
        return size;
    }

    @Override
    public V put(K key, V value) {
        //напишите тут ваш код
        if (map.containsKey(key)) {
            List<V> list = map.get(key);
            if (list.size() == repeatCount)
                list.remove(0);
            ArrayList<V> arrayList = new ArrayList<>(list);
            arrayList.add(value);
            map.put(key, arrayList);
            return list.get(list.size() - 1);
        }
        else
            map.put(key, Collections.singletonList(value));
        return null;
    }

    @Override
    public V remove(Object key) {
        //напишите тут ваш код
        if (map.containsKey(key)) {
            List<V> list = map.get(key);
            V remove = list.remove(0);
            if (list.isEmpty())
                map.remove(key);
            return remove;
        }
        return null;
    }

    @Override
    public Set<K> keySet() {
        //напишите тут ваш код
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        //напишите тут ваш код
        ArrayList<V> list = new ArrayList<>();
        for (Entry<K, List<V>> entry :
                map.entrySet()) {
            list.addAll(entry.getValue());
        }
        return list;
    }

    @Override
    public boolean containsKey(Object key) {
        //напишите тут ваш код
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        //напишите тут ваш код
        for (Entry<K, List<V>> entry :
                map.entrySet()) {
            if (entry.getValue().contains(value))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}