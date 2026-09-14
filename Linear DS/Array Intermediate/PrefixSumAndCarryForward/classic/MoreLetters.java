package classic;

/*
* More letters
Problem Description:
Given a string A of length N consisting of lowercase letters, and Q queries given by the 2D array B of size Q2. Each query consists of two integers B[i][0] and B[i][1].
For every query, the task is to find the count of consonants and count of vowels in the substring A[B[i][0] … B[i][1]], Return an array of strings of length Q, where ith string is ”Vowel” if the count of vowels is greater than or equal to the count of consonants, else it is “Consonant”.

Problem Constraints:
1 <= N <= 105
1 <= Q <= 105
0 <= B[i][0] <= B[i][1] < N

Input Format
First argument A is a string.
Second argument B is a 2D array of integers.

Output Format
Return an array of strings.

Example Input
Input 1:
A = "developer"
B = [[0, 3],
     [2, 5],
     [1, 3]]

Input 2:
A = "example"
B = [[1, 5],
     [1, 4],
     [5, 6]]

Example Output:
Output 1: [“Vowel”, “Vowel”, “Vowel”]
Output 2: [“Consonant”, “Consonant”, “Vowel”]

Example Explanation:
Explanation 1:
For the first query [0, 3], the substring is "deve". The count of vowels and consonants in this substring is both 2, so ans is “Vowel”.
For the second query [2, 5], the substring is "velo". The count of vowels and consonants in this substring is both 2, so ans is “Vowel”.
For the third query [1, 3], the substring is "eve". The count of vowels (2) greater than the count of consonants(1) in this substring, so ans is “Vowel”.

Explanation 2:
For the first query [1, 5], the substring is "xampl". The count of consonants (4) is greater than the count of vowels(1) in this substring, so ans is “Consonant”.
For the second query [1, 4], the substring is "xamp". The count of consonants (3) is greater than the count of vowels(1) in this substring, so ans is “Consonant”.
For the third query [5, 6], the substring is "le". The count of vowels and consonants in this substring is both 1, so ans is “Vowel”.
*
* */
public class MoreLetters {

    public String[] solve(String A, int[][] B) {
        int n = A.length();
        int[] vowels = new int[n];
        int[] consonants = new int[n];

        int vowelCnt = 0, consonantCnt = 0;
        int j = 0;
        for(char c: A.toCharArray()){
            if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'){
                vowelCnt++;
            }else{
                consonantCnt++;
            }
            vowels[j] = vowelCnt;
            consonants[j] = consonantCnt;
            j++;
        }

        int q = B.length;
        String[] answer = new String[q];
        int k = 0;
        for(int i = 0; i < q; i++){
            int l = B[i][0];
            int r = B[i][1];
            int vowelsRangeCnt = 0;
            int consonantRangeCnt = 0;
            if(l == 0){
                vowelsRangeCnt = vowels[r];
                consonantRangeCnt = consonants[r];
            }else{
                vowelsRangeCnt = vowels[r] - vowels[l - 1];
                consonantRangeCnt = consonants[r] - consonants[l - 1];
            }

            answer[k++] = (vowelsRangeCnt >= consonantRangeCnt) ? "Vowel" : "Consonant";
        }
        return answer;
    }
}
