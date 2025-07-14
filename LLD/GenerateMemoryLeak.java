import java.util.ArrayList;
import java.util.List;

// Fix by releasing references and using WeakReference where needed.
public class GenerateMemoryLeak {

    static List<Object> list = new ArrayList<>();

    public static void main(String[] args) {
        while (true) list.add(new Object()); // no limit = memory leak
    }
}
