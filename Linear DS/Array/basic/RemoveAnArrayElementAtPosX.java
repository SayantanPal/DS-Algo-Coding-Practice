package basic;

import java.util.Scanner;

// Practice
public class RemoveAnArrayElementAtPosX {

    public static int[] remove(int[] arr, int X){
        int N = arr.length;
        X--;

        int[] newArr = new int[N - 1];

        int i = 0;
        while(i < X){
            newArr[i] = arr[i];
            i++;
        }
        i++;
        while(i < N){
            newArr[i - 1] = arr[i];
            i++;
        }

        return newArr;
    }

    public static int[] add(int[] arr, int X, int Y){
        int n = arr.length;
        X--;

        int[] newArr = new int[n + 1];

        int  i = 0;
        while(i < X){
            newArr[i]  = arr[i];
            i++;
        }
        newArr[X] = Y;
        while(i < n){
            newArr[i + 1]  = arr[i];
            i++;
        }

        return newArr;
    }

    public static void main(String[] args) {
        // YOUR CODE GOES HERE
        // Please take input and print output to standard input/output (stdin/stdout)
        // DO NOT USE ARGUMENTS FOR INPUTS
        // E.g. 'Scanner' for input & 'System.out' for output
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = sc.nextInt();
        }
        int X = sc.nextInt();

        int[] newArr = remove(arr, X);

        for(int j = 0; j < N - 1; j++){
            System.out.print(newArr[j] + " ");
        }

        int Y = sc.nextInt();
        newArr = add(arr, X, Y);

        for(int j = 0; j < N - 1; j++){
            System.out.print(newArr[j] + " ");
        }
    }
}
