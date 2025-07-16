import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GenerateConcurrentModificationException {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("A"); list.add("B");

        for (String s : list) {
            if (s.equals("A")) list.remove(s); // throws exception
        }

        // Fix using Iterator:
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().equals("A")) it.remove(); // safe
        }

    }
}

