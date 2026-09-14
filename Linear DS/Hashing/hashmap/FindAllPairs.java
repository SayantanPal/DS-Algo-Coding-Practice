package hashmap;

/*
* Find All Pair
Problem Description

You are given arrays A, B, and C, each of length N.
Your task is to find the count of all pairs of integers (x, y) such that A[x] is equal to B[C[y]] where 1<= x, y <= N.

Problem Constraints
1 <= |A| =|B| =|C| <= 105
1 <= A[i], B[i], C[i] <= |A|

Input Format
First argument A is an integer.
In the second argument, B is an integer.
In the Third argument, C is an integer.

Output Format
Return an integer.

Example Input
Input 1:
A = [1, 2]
B = [2, 1]
C = [2, 2]
Input 2:

A = [2, 3, 3]
B = [1, 3, 3]
C = [1, 1, 1]

Example Output
Output 1: 2
Output 2: 0


Example Explanation
Explanation 1: valid pairs are (1, 1) and (1, 2)

Explanation 2: No valid pair

*
* */
public class FindAllPairs {
    public int solve(int[] A, int[] B, int[] C) {
        int n = A.length;
        int[] visited = new int[n + 1];

        for(int i = 0; i < n; i++){
            visited[A[i]]++;
        }

        int cnt = 0;
        for(int i = 0; i < n; i++){
            // System.out.println(B[C[i] - 1]);
            if(visited[B[C[i] - 1]] > 0) cnt += visited[B[C[i] - 1]];
        }
        return cnt;
    }

}
