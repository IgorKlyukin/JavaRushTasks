package com.javarush.task.task33.task3310.strategy;

public class FileStorageStrategy implements StorageStrategy {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final long DEFAULT_BUCKET_SIZE_LIMIT = 10000L;
    private FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
    private int size;
    private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;
    private long maxBucketSize;

    public int hash(Long k) {
        int h;
        return (k == null) ? 0 : (h = k.hashCode()) ^ (h >>> 16);
    }

    public int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    public Entry getEntry(Long key) {
        int hash = hash(key);
        if (table[indexFor(hash, table.length)] != null)
            for (Entry e = table[indexFor(hash, table.length)].getEntry();
                 e != null;
                 e = e.next) {
                Object k;
                if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                    return e;
            }
        return null;
    }

    public void resize(int newCapacity) {
        FileBucket[] oldTable = table;
        int oldCapacity = oldTable.length;
        if (oldCapacity == 1 << 30) {
            bucketSizeLimit = Integer.MAX_VALUE;
            return;
        }

        FileBucket[] newTable = new FileBucket[newCapacity];
        transfer(newTable);
        for (FileBucket file :
                table) {
            if (file != null)
                file.remove();
        }
        table = newTable;
        bucketSizeLimit = newCapacity;
    }

    public void transfer(FileBucket[] newTable) {
        FileBucket[] src = table;
        int newCapacity = newTable.length;
        for (int j = 0; j < src.length; j++) {
            if (src[j] != null) {
                Entry e = src[j].getEntry();
                if (e != null) {
                    src[j] = null;
                    do {
                        Entry next = e.next;
                        int i = indexFor(e.hash, newCapacity);
                        if (newTable[i] == null)
                            newTable[i] = new FileBucket();
                        e.next = newTable[i].getEntry();
                        newTable[i].putEntry(e);
                        e = next;
                    } while (e != null);
                }
            }
        }
    }

    public void addEntry(int hash, Long key, String value, int bucketIndex) {
        if (table[bucketIndex] == null)
            table[bucketIndex] = new FileBucket();
        Entry e = table[bucketIndex].getEntry();
        table[bucketIndex].putEntry(new Entry(hash, key, value, e));
        size++;
        if (table[bucketIndex].getFileSize() > bucketSizeLimit)
            resize(2 * table.length);
    }

    public void createEntry(int hash, Long key, String value, int bucketIndex) {
        if (table[bucketIndex] == null)
            table[bucketIndex] = new FileBucket();
        Entry e = table[bucketIndex].getEntry();
        table[bucketIndex].putEntry(new Entry(hash, key, value, e));
        size++;
    }

    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value) {
        if (value == null)
            return false;

        FileBucket[] tab = table;
        for (int i = 0; i < tab.length ; i++) {
            if (tab[i] != null)
                for (Entry e = tab[i].getEntry() ; e != null ; e = e.next)
                    if (value.equals(e.value))
                        return true;
        }
        return false;
    }

    @Override
    public void put(Long key, String value) {
        int hash = hash(key);
        int i = indexFor(hash, table.length);
        if (table[i] != null)
            for (Entry e = table[i].getEntry(); e != null; e = e.next) {
                Object k;
                if (e.hash == hash && ((k = e.key) == key || key.equals(k)))
                    e.value = value;
            }

        addEntry(hash, key, value, i);
    }

    @Override
    public Long getKey(String value) {
        if (containsValue(value)) {
            FileBucket[] tab = table;
            for (int i = 0; i < tab.length ; i++)
                if (tab[i] != null)
                    for (Entry e = tab[i].getEntry() ; e != null ; e = e.next)
                        if (value.equals(e.value))
                            return e.key;
        }
        return null;
    }

    @Override
    public String getValue(Long key) {
        if (key == null)
            return null;
        int hash = hash(key);
        if (table[indexFor(hash, table.length)] != null)
            for (Entry e = table[indexFor(hash, table.length)].getEntry();
                 e != null;
                 e = e.next) {
                Object k;
                if (e.hash == hash && ((k = e.key) == key || key.equals(k)))
                    return e.value;
            }
        return null;
    }

    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }
}
