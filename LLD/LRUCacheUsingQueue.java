import java.util.*;

class LRUCacheUsingQueue {

    public final int capacity;
    Queue<Integer> tracker = new LinkedList<>();
    Map<Integer, Integer> map = new HashMap<>();

    public LRUCacheUsingQueue(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if(this.map.containsKey(key)){
            // maintain access in tracker - step a. move from earlier access order only for existing element

            if(tracker.contains(key)){
                removeInBetween(tracker, key);
            }
            // step-b. always add to end/rear for tracking the existing/non-existing element as recently accessed/used
            tracker.offer(key);

            // return value
            return map.get(key); // fetch
        } else { // no need to track
            return -1;
        }
    }

    public void removeInBetween(Queue q, int t){

        // Helper queue to store the elements
        // temporarily.
        Queue<Integer> ref = new LinkedList<>();
        int s = q.size();
        int cnt = 0;

        // Finding the value to be removed
        while (!q.isEmpty() && (Integer)q.peek() != t) {
            ref.add((Integer)q.peek());
            q.remove();
            cnt++;
        }

        // If element is not found
        if (q.isEmpty()) {
            System.out.print("element not found!!" +"\n");
            while (!ref.isEmpty()) {

                // Pushing all the elements back into q
                q.add((Integer)ref.peek());
                ref.remove();
            }
        }

        // If element is found
        else {
            q.remove();
            while (!ref.isEmpty()) {

                // Pushing all the elements back into q
                q.add((Integer)ref.peek());
                ref.remove();
            }
            int k = s - cnt - 1;
            while (k-- >0) {

                // Pushing elements from front of q to its back
                int p = (Integer)q.peek();
                q.remove();
                q.add(p);
            }
        }
    }

    public void put(int key, int value) {
        if(!map.containsKey(key)){ // new elem
            // based on capacity check,
            // if after adding the new elem, it exceeds the capacity
            // note: we havn't added elem yet so far
            if(map.size() + 1 > capacity){
                // do silent eviction
                map.remove((Integer)tracker.remove());
            }
        }

        if(tracker.contains(key)){
            removeInBetween(tracker, key); // - step a. move to recent access order - remove from any position in queue tracker
        }
        tracker.offer(key); // step-b. always add to end/rear for tracking the exsiting/non-existing element as recently accessed/used
        map.put(key, value); // always add the new element with new value/update the existing element with new value
   }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */