public class MoveZerosToEndInPlaceMaintainingOrder {

    void pushZerosToEnd_v1(int[] arr) {
        // code here
        int n = arr.length;
        int nonZeroIndex = 0;
        for(int i = 0; i < n; i++){
            // whatever is not zero, keep on putting it to array
            if(arr[i] != 0){
                arr[nonZeroIndex] = arr[i];
                nonZeroIndex++;
            }
        }

        // nonZeroIndex will point to last zero element if at least one non-zero is present and 0 occurs after the series of non 0 element

        // when nonZeroIndex == 0, there was no non-zero element
        if(nonZeroIndex > 0 && nonZeroIndex < n){
            for(int i = nonZeroIndex; i < n; i++){
                arr[i] = 0;
            }
        }
    }

    void swap(int[] arr, int a, int b){
        if(a!=b){
            int temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        }
    }

    void pushZerosToEnd_v2(int[] arr) {
        // code here
        int n = arr.length;
        int nonZeroIndex = 0;
        boolean isNonZeroPresent = false;
        for(int i = 0; i < n; i++){
            if(arr[i] != 0){
                swap(arr, nonZeroIndex, i);
                nonZeroIndex++;
            }
        }
    }


}
