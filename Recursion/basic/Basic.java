package basic;

public class Basic {
    public static void main(String[] args) {

        System.out.println("Print Increasing Nos: ");

        printIncreasingNos(5);
        System.out.println();
        printIncreasingNos(1, 5);

        System.out.println("\nPrint Decreasing Nos: ");

        printDecreasingNos(5);
        System.out.println();
        printDecreasingNos2(1, 5);

        System.out.println("\n\nPrint Array Elements: ");

        printArray(new int[]{1, 2, 3, 4, 5}, 5);
        System.out.println();
        printArray(new int[]{1, 2, 3, 4, 5}, 0, 5);
        System.out.println();
        printArray2(new int[]{1, 2, 3, 4, 5}, 0);

        System.out.println("\n\nIndex of Search Element: ");

        int[] A = new int[]{1, 5, 3, 5, 5};
        int[] result;

        result = searchInArray(A, 0, 5, 0, new int[A.length]);
        printArray(result, result.length);

        System.out.println();

        result = searchInArray2(A, 0, 5, 0);
        printArray(result, result.length);
    }

    public static void printIncreasingNos(int n){
        if(n == 0) return;
        printIncreasingNos(n - 1);
        System.out.print(n + " ");
    }

    public static void printIncreasingNos(int i, int n){
        System.out.print(i + " ");
        if(i == n) return;
        printIncreasingNos(i + 1, n);
    }

    public static void printDecreasingNos(int n){
        System.out.print(n + " ");
        if(n == 1) return;
        printDecreasingNos(n - 1);
    }

    public static void printDecreasingNos2(int i, int n){
        if(i == n + 1) return;
        printDecreasingNos2(i + 1, n);
        System.out.print(i + " ");
    }

    public static void printArray(int[] A, int n){
        if(n == 0) return;
        printArray(A, n - 1);
        System.out.print(A[n - 1] + " ");
    }

    public static void printArray(int[] A, int i, int n){
        if(i == n) return;
        System.out.print(A[i] + " ");
        printArray(A, i + 1, n);
    }

    public static void printArray2(int[] A, int i){
        if(i == A.length) return;
        System.out.print(A[i] + " ");
        printArray2(A, i + 1);
    }

    public static int[] searchInArray(int[] A, int i, int searchElem, int count, int[] indicesFound){
        if(i == A.length) return indicesFound;
//        System.out.print(A[i] + " ");
        if(A[i] == searchElem){
            indicesFound[count] = i;
            return searchInArray(A, i + 1, searchElem, count + 1, indicesFound);
        }else{
            return searchInArray(A, i + 1, searchElem, count, indicesFound);
        }
    }

    public static int[] searchInArray2(int[] A, int i, int searchElem, int count){
        if(i == A.length) return new int[count];
//        System.out.print(A[i] + " ");
        int[] indicesFound;
        if(A[i] == searchElem){
            indicesFound = searchInArray2(A, i + 1, searchElem, count + 1);
            indicesFound[count] = i;
        }else{
            indicesFound = searchInArray2(A, i + 1, searchElem, count);
        }
        return indicesFound;
    }
}
