import java.util.HashMap;
import java.util.Map;

public class SortByKeyAndVal {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("b", 2);
        map.put("a", 3);
        map.put("c", 1);

// By Key
        map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(System.out::println);
// By Value
        map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(System.out::println);
    }
}
