public class DriverMain {

    public static void main(String[] args) {
        MyCustomHashMap map = new MyCustomHashMap();

          // Test Case - 1
//        map.put(5, 100);
//        System.out.println("\n\n" + map.get(5) + "\n\n");
//
//
//
//        map.put(10, 200);
//        System.out.println("\n\n" + map.get(5));
//        System.out.println(map.get(10) + "\n\n");
//
//
//        map.put(6, 300);
//        System.out.println("\n\n" + map.get(5));
//        System.out.println(map.get(10));
//        System.out.println(map.get(6) + "\n\n");
//
//
//        map.put(5, 400);
//        System.out.println("\n\n" + map.get(5));
//        System.out.println(map.get(10));
//        System.out.println(map.get(6) + "\n\n");
//
//        map.put(10, 5);
//        System.out.println("\n\n" + map.get(5));
//        System.out.println(map.get(10));
//        System.out.println(map.get(6) + "\n\n");
//
//        System.out.println("\n\n" + map.remove(5));
//        System.out.println(map.searchIndex(5));
//        System.out.println(map.containsKey(5));
//        System.out.println(map.get(5) + "\n\n");


        // Test Case - 2
//        map.put(5, 100);
//        System.out.println("\n\n" + map.get(5) + "\n\n");
//
//        map.put(6, 300);
//        System.out.println(map.get(6) + "\n\n");
//
//        map.put(10, 200);
//        System.out.println(map.get(10) + "\n\n");
//
//        map.remove(5);
//
//        System.out.println(map.get(10));

        // Test Case - 2
        map.put(5, 500);
        System.out.println("\n\n" + map.get(5) + "\n\n");

        map.put(6, 600);
        System.out.println(map.get(6) + "\n\n");

        map.put(10, 1000);
        System.out.println(map.get(10) + "\n\n");

        System.out.println(map.remove(5));

        System.out.println(map.get(10));

        map.put(7, 700);
        System.out.println("\n\n" + map.get(7) + "\n\n");

        map.put(8, 800);
        System.out.println(map.get(8) + "\n\n");

        map.put(9, 900);
        System.out.println(map.get(9) + "\n\n");

        System.out.println("\n\n" + map.hashTable.length + "\n\n");

        map.put(1, 100);
        System.out.println("\n\n" + map.get(1) + "\n\n");

        map.put(2, 200);
        System.out.println(map.get(2) + "\n\n");

        map.put(3, 300);
        System.out.println(map.get(3) + "\n\n");

        System.out.println("\n\n" + map.hashTable.length + "\n\n");
    }
}
