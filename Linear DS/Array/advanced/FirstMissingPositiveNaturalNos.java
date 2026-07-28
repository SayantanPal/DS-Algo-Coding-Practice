package advanced;

// Link: https://leetcode.com/problems/first-missing-positive/description/
public class FirstMissingPositiveNaturalNos {
    public int firstMissingPositive(int[] A) {
        int n = A.length;
        for(int i = 0; i < n; i++){
            if(A[i] <= 0) A[i] = n + 2;
        }

        for(int i = 0; i < n; i++){
            int elem = Math.abs(A[i]); // filter out sign which is acting as mark for other element
            if(elem >= 1 && elem <= n){ // elem becomes eligible for first N natural numbers
                int mappedLookUpIndex = elem - 1; // get the mapped key for the element

                // avoid bug by duplicate marking
                if(A[mappedLookUpIndex] > 0){ // in case of duplicate element within first N natural numbers, it should not again unmark by re-multiplying with -1
                    A[mappedLookUpIndex] *= -1; // nark the element as present or visited
                }
            }
        }

        for(int i = 0; i < n; i++){
            if(A[i] > 0){ // whichever is not marked as negative is the missing one
                return (i + 1);
            }
        }

        return n+1;
    }
}
