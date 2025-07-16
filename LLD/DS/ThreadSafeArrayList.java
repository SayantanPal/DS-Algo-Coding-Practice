package DS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ThreadSafeArrayList {

    public static void main(String[] args) {
        List<String> list = Collections.synchronizedList(new ArrayList<>());
    }
}
