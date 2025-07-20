

public class Driver {

    public static void main(String[] args) {

        LRUCacheUsingDLL cache = new LRUCacheUsingDLL(2);
        //[[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
        // [[2],[2,1],[1,1],[2,3],[4,1],[1],[2]]
//        cache.put(1, 1); // tracker = [LRU/head]|1|[MRU/tail]
//        cache.display();
//        cache.put(2, 2); // tracker = [LRU/head]| 1, 2 |[MRU/tail]
//        cache.display();
//        System.out.println("\n\nCache get 1: " + cache.get(1)); // tracker = [LRU/head]| 2, 1 |[MRU/tail]
//        cache.display();
//
//        cache.put(3, 3);
//        cache.display();
//
//        System.out.println("\n\nCache get 2: " + cache.get(2));
//        cache.display();
//
//        cache.put(4, 4);
//        cache.display();
//        System.out.println("\n\nCache get 1: " + cache.get(1));
//        cache.display();
//        System.out.println("\n\nCache get 3: " + cache.get(3));
//        cache.display();
//        System.out.println("\n\nCache get 4: " + cache.get(4));
//        cache.display();

//        [[2],[2,1],[1,1],[2,3],[4,1],[1],[2]]

        cache.put(2, 1);
        cache.display();
        cache.put(1, 1);
        cache.display();
        cache.put(2, 3);
        cache.display();
        cache.put(4, 1);
        cache.display();
        cache.get(1);
        cache.display();
        cache.get(2);
        cache.display();
    }
}
