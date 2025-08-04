package hashcodeequalsmethodconcept;

public class MainDriver {

    public static void main(String[] args) {
        Person p1 = new Person("Raunak", 26); // p1 mem addr = 1001
        Person p2 = new Person("Raunak", 26); // p2 mem addr =  1002

        System.out.println("p1 eq p2: " + p1.equals(p2));
        System.out.println("p1 == p2: " + (p1 == p2));

        p2 = p1; // p2 mem addr = 1001

        System.out.println("p1 eq p2: " + p1.equals(p2));
        System.out.println("p1 == p2: " + (p1 == p2));

        String a = new String("2");
        String b = new String("2");

        System.out.println("a == b: " + (a == b)); //false
        System.out.println("a eq b: " + (a.equals(b)));

        // Integer Pool range: - 128 -> +127
        // bcoz most feq used integers lies between -128 and +127 => 1 byte = 2^8 bits cache pool
        Integer x = 127;
        Integer y = 127;
        System.out.println("x == y: " + (x==y));

        Integer m = 128;
        Integer n = 128;
        System.out.println("m == n: " + (m==n));

    }
}
