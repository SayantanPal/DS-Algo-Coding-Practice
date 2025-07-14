import java.util.ArrayList;

public class UnionAndIntersectionOfTwoSortedSetsContainingDuplicates {

    // NOTE: Arrays are sorted
    public static ArrayList<Integer> findUnionForSortedArrays(int a[], int b[]) {
        // add your code here

        int i = 0;
        int j = 0;
        int n1 = a.length;
        int n2 = b.length;

        ArrayList<Integer> result = new ArrayList<>();

        while(i < n1 && j < n2){

            // check for which array element is smaller for inserting in sorted order
            if(a[i] < b[j]){
                // at the beginning, result might be empty and there might not be any to reference the last inserted element
                // if result contains at least one element, then check if the current element is duplicate
                if(result.isEmpty() || a[i] != result.get(result.size() - 1)){
                    result.add(a[i]);
                }
                i++;
            } else {
                // at the beginning, result might be empty and there might not be any to reference the last inserted element
                // if result contains at least one element, then check if the current element is duplicate
                if(result.isEmpty() || b[j] != result.get(result.size() - 1)){
                    result.add(b[j]);
                }
                j++;
            }
        }

        // when second array traversal is complete but first array still contains some elements
        while(i < n1){
            if(result.isEmpty() || a[i] != result.get(result.size() - 1)){
                result.add(a[i]);
            }
            i++;
        }

        // when first array traversal is complete but second array still contains some elements
        while(j < n2){
            if(result.isEmpty() || b[j] != result.get(result.size() - 1)){
                result.add(b[j]);
            }
            j++;
        }

        return result;
    }

    static ArrayList<Integer> intersection(int arr1[], int arr2[]) {
        // add your code here

        ArrayList<Integer> result = new ArrayList<>();

        int i = 0;
        int j = 0;

        int n1 = arr1.length;
        int n2 = arr2.length;

        while(i < n1 && j < n2){
            if(arr1[i] == arr2[j] && (result.isEmpty() || arr1[i] != result.get(result.size() - 1))){
                result.add(arr1[i]);
                i++;
                j++;
            } else if(arr1[i] < arr2[j]){
                i++;
            } else{
                j++;
            }
        }
        return result;
    }
}
