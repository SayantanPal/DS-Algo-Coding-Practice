package minimizethemaximumlimit;

/*
* Problem Description

Given an array of integers arr of size N and an integer k.
The College library has N books. The ith book has arr[i] number of pages.

You have to allocate books to k number of students so that the maximum number of pages allocated to a student is minimum.

A book will be allocated to exactly one student.
Each student has to be allocated at least one book.
Allotment should be in contiguous order, for example: A student cannot be allocated book 1 and book 3, skipping book 2.
Calculate and return that minimum possible number.

NOTE: Return -1 if a valid assignment is not possible.
*
* Problem Constraints:
1 <= N <= 105
1 <= A[i], B <= 105
*
*
Input Format:
The first argument given is the integer array A.
The second argument given is the integer B.

Output Format:
Return that minimum possible number.

Example Input
Input 1:
A = [12, 34, 67, 90]
B = 2
*
* Output 1: 113
*
* Explanation 1:

There are two students. Books can be distributed in following fashion :
1)  [12] and [34, 67, 90]
    Max number of pages is allocated to student 2 with 34 + 67 + 90 = 191 pages
2)  [12, 34] and [67, 90]
    Max number of pages is allocated to student 2 with 67 + 90 = 157 pages
3)  [12, 34, 67] and [90]
    Max number of pages is allocated to student 1 with 12 + 34 + 67 = 113 pages
    Of the 3 cases, Option 3 has the minimum pages = 113.

Input 2:
A = [12, 15, 78]
B = 4

Output 2: -1
*
* Explanation 2:
Each student has to be allocated at least one book.
But the Total number of books is less than the number of students.
Thus each student cannot be allocated to atleast one book.

Therefore, the result is -1.
*
* */

/*
* Given an integer array nums and an integer k, split nums into k non-empty subarrays such that the largest sum of any subarray is minimized.
  Return the minimized largest sum of the split.
* */

// Link: https://www.geeksforgeeks.org/problems/allocate-minimum-number-of-pages0937/1
// Link: https://leetcode.com/problems/split-array-largest-sum/description/
public class AllocateMinimumPages {

    public boolean checkIfTaskCanBeDistributed(int[] pagesInBook, long maxPagesLimitEachStudentCanBeAllocated, long maxNoOfStudentsLimitNeededToBeDistributed){
        long totalPagesAllocatedToEachStudent = 0L;
        long minNoOfStudentsNeeded = 1L;
        for(int i = 0; i < pagesInBook.length; i++){
            totalPagesAllocatedToEachStudent += pagesInBook[i];
            if(totalPagesAllocatedToEachStudent > maxPagesLimitEachStudentCanBeAllocated){
                minNoOfStudentsNeeded++;
                totalPagesAllocatedToEachStudent = pagesInBook[i];
            }
            if(minNoOfStudentsNeeded > maxNoOfStudentsLimitNeededToBeDistributed) return false;
        }
        // if(minSupply <= givenInputSupply) return true;
        return true;
    }

    public int findPages(int[] arr, int k) {
        // code here
        if(k > arr.length) return -1; // if no of students is more than no of books, then one student will not get at least one
        // so no of students should be less than or equal to no of books
        // so that each student receives one or more books
        long maxElem = 0L; // when N readers gets ith book
        long sum = 0L; // when 1 reader gets 1 book
        for(int i = 0; i < arr.length; i++){
            sum += arr[i];
            maxElem = Math.max(maxElem, arr[i]);
        }

        // solution search space ranges from max array weight to sum of all array weight
        long maxAllocated = -1;
        long l = maxElem, r = sum;
        while(l <= r){
            long mid = l + (r - l)/2;
            if(checkIfTaskCanBeDistributed(arr, mid, k)){
                maxAllocated = mid;
                r = mid - 1;
            }else{
                l = mid + 1;
            }
        }
        return (int) maxAllocated;
    }
}
