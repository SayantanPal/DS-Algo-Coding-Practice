import java.util.Arrays;

public class ShortestJobFirstCPUScheduling {

    public static int getAvgWaitTime(int[] processBurstTimes) {
        // code here
        Arrays.sort(processBurstTimes);
        int n = processBurstTimes.length;

        int sum = 0;
        int totalWaitTime = 0;

        // not adding first timestamp t0 = 0 because adding with 0 results same number
        // t0 = 0
        // other processes wait time w0 for executing P0 = burstTime(t1) of P0 = t0 + t1
        // other processes wait time w1 for executing P1 = burstTime(t1) of P0 + burstTime(t2) of P1 = t0 + t1 + t2
        // other processes wait time w|(n-1) for executing P|(n - 1) = burstTime of P0 + burstTime of P1 + ... + burstTime of P|n-1 = t0 + t1 + t2 + .. + t|(n-1)
        // no process need to wait while executing P|n since all remaining processes were served by that timestamp. So ignore burst time of last process
        // total wait time = w0 + w1 + ... + w|(n-1)
        for(int i = 0; i < n - 1; i++){
            sum += processBurstTimes[i];
            totalWaitTime += sum;
        }

        return totalWaitTime/n;
    }
}
