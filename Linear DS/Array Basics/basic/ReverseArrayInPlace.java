package basic;

public class ReverseArrayInPlace {

    public void reverseArray(int arr[]) {
        // code here
        if(arr.length < 2) return;
        int l = 0;
        int r = arr.length - 1;
        while(l < r){
            if(arr[l] != arr[r]){
                int temp = arr[l];
                arr[l] = arr[r];
                arr[r] = temp;
            }
            l++;
            r--;
        }
    }

}
