package prodarraywithoutcurrelem;
import java.util.ArrayList;


public class ProdArrWithoutCurrElem {
    public static ArrayList<Integer> product_array_without_current_element(ArrayList<Integer> nums) {
        int n = nums.size();
        ArrayList<Integer> res = new ArrayList<>();
        // Initialize result array with 1s
        for (int i = 0; i < n; i++) {
            res.add(1);
        }
        // Populate the output with the running left product.
        for (int i = 1; i < n; i++) {
            res.set(i, res.get(i - 1) * nums.get(i - 1));
        }
        // Multiply the output with the running right product, from right to left.
        int rightProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            res.set(i, res.get(i) * rightProduct);
            rightProduct *= nums.get(i);
        }
        return res;
    }
}
