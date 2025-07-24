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
        map.put(5, 100);
        System.out.println("\n\n" + map.get(5) + "\n\n");

        map.put(6, 300);
        System.out.println(map.get(6) + "\n\n");

        map.put(10, 200);
        System.out.println(map.get(10) + "\n\n");

        map.remove(5);

        System.out.println(map.get(10));
    }
}
