import java.util.Arrays;
import java.util.Scanner;

/*
* Take as input N, the size of array.
* Take N more inputs and store that in an array
* Take as inout M, a number
* Write a recursion function which returns the last index at which M is found
* in the array and
* -1 if M is not found anywhere.
* Print the value returned
* Example:
* Enter the search number: 77
* Enter the number of elements M: 7
* Enter the elements one by one: 86 -16 77 65 45 77 28
* Output: 5
* */
public class FindLastElementIndex {

    public static int findLastIndex(int[] arr, int m, int n){

        if(m < 0) return -1;

        if(arr[m] == n) return m;

        return findLastIndex(arr, m - 1, n);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the search number: ");
        int n = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter the number of elements M: ");
        int m = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter the elements one by one: ");
        int[] arr = Arrays.stream(scanner.nextLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.printf("Output: %d", findLastIndex(arr, m - 1, n));
    }
}
