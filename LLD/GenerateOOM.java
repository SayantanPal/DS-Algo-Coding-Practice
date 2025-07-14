import java.util.*;
public class GenerateOOM {
    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<>();
        while (true) {
            list.add(new byte [10 * 1024 * 1024]); // Allocates 10MB
            System.out.println("Allocated 10 MB ");
        }
    }
}
