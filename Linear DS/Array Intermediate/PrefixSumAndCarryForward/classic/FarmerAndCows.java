package classic;
/*
* Farmer and the cows
*
Problem Description:
Farmer John's N cows, conveniently numbered 1…N, are all standing in a row (they seem to do so often that it now takes very little prompting from Farmer John to line them up). Each cow has a breed ID: 1 for Holsteins and 2 for Jerseys.
Farmer John would like your help counting the number of cows of each breed that lie within certain intervals of the ordering.


Problem Constraints:
1 <= N <= 105
1 <= A[i] <= 2
1 <= Q.size() <= 105
1 <= Q[i][0] <= Q[i][1] <= N, which is the two end points of each Query.


Input Format:
First Argument is an Array A
Second Argument is 2-d array B, which is the Queries.


Output Format:
Return a 2-d array of size Qx2.


Example Input
Input 1:
A = [2, 1, 1, 2, 1]
B = [[1, 5],
[3, 3],
[2, 4]]


Example Output
Output 1:
[[3, 2], [1, 0], [2, 1]]


Example Explanation:-
Explanation 1:
For the first Query (1, 5):
A[1:5] = [2, 1, 1, 2, 1]
Count of 1 : 3
Count of 2 : 2
Thus [3, 2] is the answer for this query.

For the second Query (3, 3):
A[3:3] = [1]
Count of 1 : 1
Count of 2 : 0
Thus [1, 0] is the answer for this query.


For the Third Query (2, 4):
A[2:4] = [1, 1, 2]
Count of 1 : 2
Count of 2 : 1
Thus [2, 1] is the answer for this query.

At last return the answer, [[3, 2], [1, 0], [2, 1]].
*
* */
public class FarmerAndCows {

    public int[][] countCows(int[] A, int[][] B) {
        int n = A.length;
        int[] prefixCountHolstein = new int[n];
        int[] prefixCountJersey = new int[n];
        if(A[0] == 1){
            prefixCountHolstein[0] = 1;
        }else if(A[0] == 2){
            prefixCountJersey[0] = 1;
        }
        for(int i = 1; i < n; i++){
            if(A[i] == 1){
                prefixCountHolstein[i] = prefixCountHolstein[i - 1] + 1;
                prefixCountJersey[i] = prefixCountJersey[i - 1];
            }else if(A[i] == 2){
                prefixCountHolstein[i] = prefixCountHolstein[i - 1];
                prefixCountJersey[i] = prefixCountJersey[i - 1] + 1;
            }
        }
        int q = B.length;
        int[][] result = new int[q][2];
        for(int i = 0; i < q; i++){
            B[i][0]--;
            B[i][1]--;
            int l = B[i][0];
            int r = B[i][1];
            if(l == 0){
                result[i][0] = prefixCountHolstein[r];
                result[i][1] = prefixCountJersey[r];
            }else{
                result[i][0] = prefixCountHolstein[r] - prefixCountHolstein[l - 1];
                result[i][1] = prefixCountJersey[r] - prefixCountJersey[l - 1];
            }
        }
        return result;
    }
}
