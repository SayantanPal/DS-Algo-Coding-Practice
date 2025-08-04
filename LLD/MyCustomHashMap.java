import java.sql.Array;

public class MyCustomHashMap {

    class MapNode{
        public int key, value;
        MapNode(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    private static final int INITIAL_CAPACITY = 5;
    private static final float LOAD_FACTOR = 0.75f;
    private static final int INDEX_OF_KEY_NOT_FOUND = -1;
    private static final int KEY_NOT_FOUND = Integer.MIN_VALUE;

    public MapNode[] hashTable; // array of Map Node object where each obj has a pair (Key, Value)
    private int n; // n = Current No. Of Entries

    public MyCustomHashMap(){
        hashTable = new MapNode[INITIAL_CAPACITY]; // Lazy Loading/Instantiation
    }

    public int get(int key){
       if(this.containsKey(key)){
           return this.hashTable[this.searchIndex(key, this.hashTable.length)].value;
       }
       return KEY_NOT_FOUND;
    }

    public boolean containsKey(int key){
        int index = this.searchIndex(key, this.hashTable.length);
        return (index != -1) && (hashTable[index] != null);
    }

    public int put(int key, int value){
        int N = hashTable.length;

        int index = hash(key, N); // 1. Check initial hashed index

        // 2. keep on searching & retrying insertion until we find empty hash table bucket or a bucket filled with same key
        while(isHashCollision(index, key, this.hashTable)){
            // check every next index as per strategy
            // mod N ensures the scan is on circular fashion of array
            // mod N will never run to infinite loop because always the arrays will be rehashed at 75% load
            index = hashResolutionViaLinearProbing(index, this.hashTable);
        }
        // At this stage, Either index points to empty bucket Or it has a key similar to the incoming key

        // 3. Only increment entries count when an empty bucket is found to be filled
        // (not when same key is to be overridden with new value);
        if(hashTable[index] == null){ // index only point to empty bucket
            n++; // increment entry when index only points to empty bucket
        }

        // now index position is having empty hash table bucket
        // 4. add/put the node with new value to that bucket of empty value or same key
        hashTable[index] = new MapNode(key, value);

        // 5. After inserting new (key, value) pair only,
        // always the arrays will be rehashed at 75% load
        if(checkReHashingNeeded()){
            // double N close to primary number
            reHash();
        }

        // 6.
        return key;
    }

    public boolean remove(int key){
        int index = this.searchIndex(key, this.hashTable.length);
        if(index == -1){
            return false;
        }

        hashTable[index] = null;
        n--;
        return true;
    }

    public int searchIndex(int incomingKey, int N){
        int index = hash(incomingKey, N); // 1. Find first probable hashed index where key might be present

        // 2. if key is present at index of first probable filled bucket, then return index of first bucket
        if (this.hashTable[index] != null && this.hashTable[index].key == incomingKey) {
            return index; // index of first bucket
        }

        // 3. else keep on searching the key finitely for every next possible probe buckets
        // and continue scanning till the time we encounter a filled bucket with the search key present until end of hash table

        // counter check prevents infinite looping
        int counter = N;
        index =  hashResolutionViaLinearProbing(index, this.hashTable);
        counter--;
        while (counter-- > 0 && this.hashTable[index] != null && hashTable[index].key != incomingKey) {
            index = hashResolutionViaLinearProbing(index, this.hashTable);
        }

        // 4. when it is able to find the element in one of the possible next probe bucket
        if (counter != 0) {
            return index;
        }
        // else when counter will be 0 when all the items in the hash table has been scanned and none of them matches the key
        return INDEX_OF_KEY_NOT_FOUND; // after searching whole array, no matching keys present
    }

    /*
    * Resolving Hash Collision strategically
    * Get the next probable bucket index where collision probably is expected not to take place
    * */
    private int hashResolutionViaLinearProbing(int currHashedIndex, MapNode[] hashTable){
        int N = hashTable.length;
        int nextIndex = (currHashedIndex + 1) % N; // mod N ensures the scan is circularly
        return nextIndex;
    }

    /**
     * Checks hash table bucket size empty or not
     * Hash Collision happens when the bucket is filled with different key
     */
    private boolean isHashCollision(int index, int incomingKey, MapNode[] hashTable){
        return hashTable[index] != null // when bucket is filled
                && hashTable[index].key != incomingKey; // and when filled bucket has diff key
    }

    // impl specific

    /**
     *
     * @param key
     * @param hashTableSize
     * @return
     * It outputs the index in Array hash table based on input key elem
     * to figure out at which index the map Node needs to be inserted
     */
    private int hash(int key, int hashTableSize){
        int N = hashTableSize;
        int index =  (key % N);
        return index;
    }

    private boolean checkReHashingNeeded(){
        int N = hashTable.length;
        return ((float)(n/N) > LOAD_FACTOR);
    }

    private void reHash(){
        int oldN = this.hashTable.length;
        int newN = getNewN(oldN);
        MapNode[] newHashTable = new MapNode[newN];
        for(MapNode node: this.hashTable){
            int key = node.key;
            int index = this.hash(key, newN);

            while(isHashCollision(index, key, newHashTable)){
                index = hashResolutionViaLinearProbing(index, newHashTable);
            }
            // always empty bucket
            newHashTable[index] = node;
        }
        this.hashTable = newHashTable;
    }

    private int getNewN(int N){
        return N*2;
    }
}
