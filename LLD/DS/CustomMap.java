package DS;

import java.util.ArrayList;
import java.util.List;

class CustomMap<K, V> {
    private List<Entry<K, V>> entries = new ArrayList<>();

    private static class Entry<K, V> {
        K key;
        V value;
        Entry(K k, V v) { key = k; value = v; }
    }
    public void put(K key, V value) {
        for (Entry<K, V> e : entries) {
            if (e.key.equals(key)) {
                e.value = value;
                return;
            }
        }
        entries.add(new Entry<>(key, value));
    }
    public V get(K key) {
        for (Entry<K, V> e : entries) {
            if (e.key.equals(key)) return e.value;
        }
        return null;
    }
}