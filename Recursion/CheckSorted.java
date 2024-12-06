import java.util.*;

/**
 * Take as input N, the size of an integer array.
 * Take one more input, which is a list of N integers separated by a space and store that in an array.
 * Write a recursive function which prints true if the array is sorted, and false otherwise
 */

public class CheckSorted {

    public static boolean isSorted(int[] arr, int n){

        if(n < 1) return true;
        if(arr[n - 1] > arr[n]) return false;
        return isSorted(arr, n - 1);
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of elements: ");
        int n = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter the elements one by one: ");
        int[] arr = Arrays.stream(scanner.nextLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.printf("Output: %b", isSorted(arr, n - 1));
    }
}
