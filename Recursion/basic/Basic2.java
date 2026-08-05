package basic;

public class Basic2 {
    public boolean isPalindromeRec(int[] A, int left, int right){
        if(left >= right) return true;
        return (A[left] == A[right])
                && isPalindromeRec(A, left + 1, right - 1);
    }

    public boolean isPalindromeRec(String s, int left, int right){
        if(left >= right) return true;
        return (s.charAt(left) == s.charAt(right))
                && isPalindromeRec(s, left + 1, right - 1);
    }

    // power func

    // factorial

    // fibonacci

    // tower of hanoi
}
