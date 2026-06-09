package basic.subarrays;

public class TotalSumOfAllSubArrays {

    // TC = O(N^3); SC = O(1)
    int sumOfAllSubarraySumsUsingBruteForce(int arr[]) {
        int n = arr.length;
        int totalSum = 0;
        for (int i = 0; i < n; i++) {
            for(int j = i; j < n; j++){
                int currSum = 0;
                for(int k = i; k <=j; k++){
                    currSum+= arr[k];
                }
                totalSum += currSum;
            }
        }
        return totalSum;
    }

    // TC = O(N^2); SC = O(N)
    int sumOfAllSubarraySumsUsingPrefixSum(int arr[]) {
        int n = arr.length;

        int[] prefixSum = new int[n];
        prefixSum[0] = arr[0];
        for(int i = 1; i < n; i++){
            prefixSum[i] = prefixSum[i - 1] + arr[i];
        }

        // Iterate over all elements of the array and compute the sum of all subarrays containing that element
        int totalSum = 0;
        for (int i = 0; i < n; i++) {
            for(int j = i; j < n; j++){
                if(i == 0)
                    totalSum += prefixSum[j] - 0;
                else
                    totalSum += prefixSum[j] - prefixSum[i - 1];
            }
        }

        return totalSum;
    }

    // TC = O(N); SC = O(1)
    // from observation of patterns
    // where with i as starting index, we have (N - i) subarrays which starts with [i, n-1]: [(n - 1) - i + 1] = n - i
    // and this starting index i is repeatedly included more i no of times from previous index 0 to i: [0 to i] = (i + 1) times
    // so, with each index, the total no of subarray is (N - i) * (i + 1)
    // so, with each index, the value of each index as contained in subarray: arr[i] *  (N - i) * (i + 1)
    int sumOfAllSubarraySums(int arr[]) {
        int n = arr.length;
        int totalSum = 0;

        // Iterate over all elements of the array and compute the sum of all subarrays containing that element
        for (int i = 0; i < n; i++) {
            totalSum += arr[i] * (i + 1) * (n - i);
        }

        return totalSum;
    }
}
