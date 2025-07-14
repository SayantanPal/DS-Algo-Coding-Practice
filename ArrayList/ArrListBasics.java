import java.util.*;

// LinkedHashSet removes duplicates and maintains insertion order.
public class ArrListBasics {
    public static void removeDuplicates(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 2, 3, 4, 4, 5);
        Set<Integer> set = new LinkedHashSet<>(list);
        List<Integer> result = new ArrayList<>(set);
        System.out.println(result);
    }

    public static void merge2ListsAndRemoveDuplicates(String[] args) {
        List<Integer> a = Arrays.asList(1, 2, 3);
        List<Integer> b = Arrays.asList(3, 4, 5);
        Set<Integer> resultSet = new LinkedHashSet<>(a);
        resultSet.addAll(b);
        System.out.println(new ArrayList<>(resultSet));
    }

    public static void FreqOfElemInArrList(String[] args) {
        List<String> list = Arrays.asList("apple", "banana", "apple", "orange", "banana", "banana");
        Map<String, Integer> freq = new HashMap<>();
        for (String item : list) {
            freq.put(item, freq.getOrDefault(item, 0) + 1);
        }
        System.out.println(freq);
    }

    public static void intersectionOf2ArrList(String[] args) {
        List<Integer> a = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<Integer> b = Arrays.asList(3, 4, 5, 6);

        a.retainAll(b);
        System.out.println(a); // [3, 4]
    }

    public static void twoEqListContentIgnoringOrder(String[] args) {
        List<Integer> a = Arrays.asList(1, 2, 3);
        List<Integer> b = Arrays.asList(3, 1, 2);

        System.out.println(new HashSet<>(a).equals(new HashSet<>(b))); // true
    }

    public static void duplicateElemWithCount(String[] args) {
        List<String> list = Arrays.asList("a", "b", "a", "c", "b");

        Map<String, Integer> countMap = new HashMap<>();
        for (String s : list) countMap.put(s, countMap.getOrDefault(s, 0) + 1);
        countMap.entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .forEach(e -> System.out.println(e.getKey() + " -> " + e.getValue()));
    }
}
