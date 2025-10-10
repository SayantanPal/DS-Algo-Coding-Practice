import java.util.LinkedHashMap;
import java.util.Map;

class LRUCacheUsingLinkedHashMap<K, V> extends LinkedHashMap<K, V> {
    private int initialCapacity;

    public LRUCacheUsingLinkedHashMap(int initialCapacity) {
        super(initialCapacity, 0.75f, true); // accessOrder = true
        this.initialCapacity = initialCapacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return super.size() > this.initialCapacity;
    }
}