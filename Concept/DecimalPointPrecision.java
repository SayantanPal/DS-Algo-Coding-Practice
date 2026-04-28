import java.math.BigDecimal;

public class DecimalPointPrecision {

    public static boolean isEqual(float a, float b) {
        float epsilon = 1e-6f; // float — 32-bit, ~6-7 decimal digits precision → 1e-6 is appropriate
        return Math.abs(a - b) < epsilon;
    }

    public static boolean isEqual(double a, double b) {
        double epsilon = 1e-9; // double — 64-bit, ~15-16 decimal digits precision → 1e-9 is safe
        return Math.abs(a - b) < epsilon;
    }

    public static void main(String[] args) {

        // CASE STUDY - all numbers except 1/2, 1/(2^2), 1/(2^3), 1/(2^n), will have decimal point precision issue.
        double a = 0.1, b = 0.2, sum = a + b;
        if (sum == 0.3) { // 0.3 will have default decimal datatype as double
            System.out.println(true);
        } else {
            System.out.println(false); // answer is false since neither 0.1 nor 0.2 is not a denominator in the multiple or power of 2
        }

        // REMEDY 1 -
        if (isEqual(sum, 0.3)) {
            System.out.println(true); // answer is true
        } else {
            System.out.println(false);
        }

        // REMEDY 2 - widely used in financial systems
        BigDecimal aBD = new BigDecimal(String.valueOf(a)); // Use new BigDecimal("<decimal_no>") (string constructor) — NOT new BigDecimal(<decimal_no>) directly which captures the imprecise double representation
        BigDecimal bBD = new BigDecimal(String.valueOf(b));
        BigDecimal target = new BigDecimal("0.3");

        if (aBD.add(bBD).compareTo(target) == 0) { // compareTo instead of equals — because equals also checks scale (0.8 vs 0.80 would be false with equals)
            System.out.println(true); // answer is true
        } else {
            System.out.println(false);
        }
    }
}
