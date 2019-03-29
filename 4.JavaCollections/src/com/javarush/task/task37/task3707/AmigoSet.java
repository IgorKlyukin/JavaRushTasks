package com.javarush.task.task37.task3707;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class AmigoSet<E> extends AbstractSet<E> implements Serializable, Cloneable, Set<E> {
    private static final Object PRESENT = new Object();
    private transient HashMap<E,Object> map;

    public AmigoSet() {
        map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        map = new HashMap<>(Math.max((int) (collection.size()/.75f) + 1, 16));
        addAll(collection);
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> iterable = map.keySet().iterator();
        return iterable;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return super.contains(o);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean remove(Object o) {
        return super.remove(o);
    }

    public Object clone() throws InternalError {
        try {
            AmigoSet copy = (AmigoSet)super.clone();
            copy.map = (HashMap) map.clone();
            return copy;
        } catch (Exception e) {
            throw new InternalError(e);
        }
    }

    public boolean add(E e) {
        return map.put(e, PRESENT)==null;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();

        objectOutputStream.writeInt(map.size());

        for (E e : map.keySet())
            objectOutputStream.writeObject(e);

        objectOutputStream.writeInt(HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
        objectOutputStream.writeFloat(HashMapReflectionHelper.callHiddenMethod(map, "loadFactor"));
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();

        int size = objectInputStream.readInt();

        List<E> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add((E) objectInputStream.readObject());
        }

        int capacity = objectInputStream.readInt();
        float loadFactory = objectInputStream.readFloat();

        map = new HashMap<>(capacity,loadFactory);

        for (int i = 0; i < size; i++) {
            map.put(list.get(i), PRESENT);
        }
    }
}
