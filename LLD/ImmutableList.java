import java.util.*;
public class ImmutableList {
    public static void main(String[] args) {
        List<String> list = List.of("one", "two", "three");
        System.out.println(list);
        // list.add("four"); // Will throw UnsupportedOperationException
    }
}