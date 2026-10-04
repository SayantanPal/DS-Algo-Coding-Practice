public class GCDAndLCM {

    // Efficiently calculates GCDAndLCM using the Euclidean algorithm
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return Math.abs(a);
    }

    public static int gcdRec(int a, int b) {
        if(a == 0) return b;
        return gcdRec(b%a, a);
    }

    public static long lcm(int a, int b) {
        return ((long)a * b)/ GCDAndLCM.gcd(a, b);
    }
}
