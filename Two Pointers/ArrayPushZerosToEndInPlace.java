public class ArrayPushZerosToEndInPlace {

    public static void pushZerosToEndInPlace(int[] arr) {
        // code here
        int n = arr.length;
        int slow = 0;
        int fast = 0;
        while(fast < arr.length){
            // nonZeroIndex will lag behind and i will move forward only when first 0 is encountered
            // at that stage, non zero will point to latest encountered 0th index always
            if(arr[fast] != 0){
                arr[slow++] = arr[fast];
            }
            fast++;
        }

        // if there was no non-zero element; all elements were 0, then nonZeroIndex = 0 at the end
        // if there exists all non zero elements, then nonZeroIndex = n at the end

        // if at least one non-zero is present and 0 occurs after the series of non 0 element,
        // nonZeroIndex will point to last zero element
        while(slow < n){
            arr[slow++] = 0;
        }
    }
}
