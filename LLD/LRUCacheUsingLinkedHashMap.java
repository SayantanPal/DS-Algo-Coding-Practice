import java.util.LinkedHashMap;
import java.util.Map;

class LRUCacheUsingLinkedHashMap<K, V> extends LinkedHashMap<K, V> {
    private int capacity;

    public LRUCacheUsingLinkedHashMap(int capacity) {
        super(capacity, 0.75f, true); // accessOrder = true
        this.capacity = capacity;
    }
    protected boolean removeOldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}