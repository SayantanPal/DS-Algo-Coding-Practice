package DS;

class CustomHashMap2<K, V> {

    private Entry<K, V>[] table;
    private int size;
    private static final int INITIAL_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    public CustomHashMap2() {
        table = new Entry[INITIAL_CAPACITY];
        size = 0;
    }

    /*
    * This is the class that holds each key-value pair. If a collision occurs, the next pointer of the entry will point to the next entry in the same bucket.
    * */
    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    // This computes the hash code and maps it to an index in the table using the modulus operation.
    private int hash(K key) {
        return key == null ? 0 : key.hashCode() % table.length;
    }

    // This is used to insert key-value pairs. If the key already exists, it updates the value. If there's a collision, the new entry is added to the linked list at the corresponding bucket.
    public void put(K key, V value) {
        int index = hash(key);
        Entry<K, V> newEntry = new Entry<>(key, value);

        if (table[index] == null) {
            table[index] = newEntry;
        } else {
            Entry<K, V> current = table[index];
            while (current != null) {
                if (current.key.equals(key)) {
                    current.value = value;  // Update existing value
                    return;
                }
                current = current.next;
            }
            newEntry.next = table[index];  // Handle collision by chaining
            table[index] = newEntry;
        }

        size++;
        if (size > table.length * LOAD_FACTOR) {
            resize();
        }
    }

    // This retrieves the value for a given key. If the key exists, it returns the value; otherwise, it returns null
    public V get(K key) {
        int index = hash(key);
        Entry<K, V> current = table[index];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;  // Key not found
    }

    /*
    * This is triggered when the size of the map exceeds the load factor multiplied by the current table size.
    * It doubles the array size and rehashes all existing entries.
     * */
    private void resize() {
        Entry<K, V>[] oldTable = table;
        table = new Entry[table.length * 2];
        size = 0;

        // Rehash all entries
        for (Entry<K, V> entry : oldTable) {
            while (entry != null) {
                put(entry.key, entry.value);
                entry = entry.next;
            }
        }
    }

    public int size() {
        return size;
    }
    /*
    * This method removes a key-value pair from the map.
    * */

    public boolean remove(K key) {
        int index = hash(key);
        Entry<K, V> current = table[index];
        Entry<K, V> prev = null;

        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;  // Key not found
    }
}
