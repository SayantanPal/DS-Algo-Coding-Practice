package linear_subsequences_or_subsets_constant_transition;

import util.PrintOutputUtils;

import java.util.Arrays;

import static util.PrintOutputUtils.print;

// https://www.naukri.com/code360/problems/ninja-s-training_3621003
public class NinjaTraining {

//    public static void print(int[] arr){
//        for(int i : arr)
//            System.out.print(i + " ");
//        System.out.println();
//    }

    public static int[] ninjaTrainingBottomUpRec(int currDay, int n, int[][] points){
        if(currDay >= n) return new int[]{0, 0, 0};
        if(currDay <= 0) return points[0];

//        print(points[currDay]);
        // int option1ForRunning =  points[currDay][0] + points[currDay - 1][1] + points[currDay - 1][2];
        // int option2ForFighting = points[currDay][1] + points[currDay - 1][0] + points[currDay - 1][2];
        // int option3ForNewMoves = points[currDay][2] + points[currDay - 1][0] + points[currDay - 1][1];


        int option1ForRunning =  points[currDay][0] + Math.max(ninjaTrainingBottomUpRec(currDay - 1, n, points)[1], ninjaTrainingBottomUpRec(currDay - 1, n, points)[2]);
        int option2ForFighting = points[currDay][1] + Math.max(ninjaTrainingBottomUpRec(currDay - 1, n, points)[0], ninjaTrainingBottomUpRec(currDay - 1, n, points)[2]);
        int option3ForNewMoves = points[currDay][2] + Math.max(ninjaTrainingBottomUpRec(currDay - 1, n, points)[0], ninjaTrainingBottomUpRec(currDay - 1, n, points)[1]);

        return new int[]{option1ForRunning, option2ForFighting, option3ForNewMoves};
    }

    public static int ninjaTrainingTopDownRec(int currDay, int lastTask, int[][] points){
        if(currDay < 0) return 0;

        int point = points[currDay][lastTask];
        int maxPoints = 0;
        if(lastTask == 0){
            maxPoints = Math.max(ninjaTrainingTopDownRec(currDay - 1, 1, points), ninjaTrainingTopDownRec(currDay - 1, 2, points));
        } else if(lastTask == 1){
            maxPoints = Math.max(ninjaTrainingTopDownRec(currDay - 1, 0, points), ninjaTrainingTopDownRec(currDay - 1, 2, points));
        } else if(lastTask == 2){
            maxPoints = Math.max(ninjaTrainingTopDownRec(currDay - 1, 0, points), ninjaTrainingTopDownRec(currDay - 1, 1, points));
        }

        return maxPoints + point;
    }

    public static int ninjaTrainingTopDownRec_ver2(int currDay, int lastTask, int[][] points){
        if(currDay < 0) return 0;

        int maxPoints = 0;
        for(int task = 0; task < 3; task++){
            if(task != lastTask)
                maxPoints  = Math.max(maxPoints, ninjaTrainingTopDownRec_ver2(currDay - 1, task, points) + points[currDay][task]);
        }
        return maxPoints;
    }

    public static int ninjaTrainingTopDownRecMemorisation(int currDay, int lastTask, int[][] points, int[][] dp){
        if(currDay < 0) return 0;
        if(dp[currDay][lastTask] != -1) return dp[currDay][lastTask];
        int point = points[currDay][lastTask];

        int maxPoints = 0;
        if(lastTask == 0){
            maxPoints = Math.max(ninjaTrainingTopDownRecMemorisation(currDay - 1, 1, points, dp), ninjaTrainingTopDownRecMemorisation(currDay - 1, 2, points, dp));
        } else if(lastTask == 1){
            maxPoints = Math.max(ninjaTrainingTopDownRecMemorisation(currDay - 1, 0, points, dp), ninjaTrainingTopDownRecMemorisation(currDay - 1, 2, points, dp));
        } else if(lastTask == 2){
            maxPoints = Math.max(ninjaTrainingTopDownRecMemorisation(currDay - 1, 0, points, dp), ninjaTrainingTopDownRecMemorisation(currDay - 1, 1, points, dp));
        }

        return dp[currDay][lastTask] = maxPoints + point;
    }

    public static int ninjaTrainingTopDownRecMemorisation_ver2(int currDay, int lastTask, int[][] points, int[][] dp){
        if(currDay < 0) return 0;
        print(dp);
        if(dp[currDay][lastTask] != -1) return dp[currDay][lastTask];
        int maxPoints = 0;
        for(int task = 0; task < 3; task++){
            if(task != lastTask)
                maxPoints  = Math.max(maxPoints, ninjaTrainingTopDownRecMemorisation_ver2(currDay - 1, task, points, dp) + points[currDay][task]);
        }
        return dp[currDay][lastTask] = maxPoints;
    }

    // Easy to understand
    public static int ninjaTrainingTopDownRec3(int currDay, int lastActivity, int[][] points){
        if(currDay < 0) return 0;

        int takeRunningOption = 0;
        int takeFightingOption = 0;
        int takeMoveOption = 0;

        // if running today, then on other day, consider max of only fighting and moving
        if(lastActivity == 0)
            takeRunningOption = points[currDay][0] + Math.max(ninjaTrainingTopDownRec3(currDay - 1, 1, points), ninjaTrainingTopDownRec3(currDay - 1, 2, points));
        // if fighting today, then on other day, consider max of only running and moving
        else if(lastActivity == 1)
            takeFightingOption = points[currDay][1] + Math.max(ninjaTrainingTopDownRec3(currDay - 1, 0, points), ninjaTrainingTopDownRec3(currDay - 1, 2, points));
        // if moving today, then on other day, consider max of only running and fighting
        else if(lastActivity == 2)
            takeMoveOption = points[currDay][2] + Math.max(ninjaTrainingTopDownRec3(currDay - 1, 0, points), ninjaTrainingTopDownRec3(currDay - 1, 1, points));

        return Math.max(takeRunningOption, Math.max(takeFightingOption, takeMoveOption));
    }



    public static int ninjaTraining(int n, int points[][]) {

        // Write your code here..
        int[][] dp = new int[n][4];
        for(int i = 0; i < dp.length; i++){
            Arrays.fill(dp[i], -1);
        }

//        return ninjaTrainingTopDownRec_ver2(n - 1, 3, points);

        int point = 0;
        // point = Math.max(point, ninjaTrainingTopDownRec(n-1, 0, points));
        // point = Math.max(point, ninjaTrainingTopDownRec(n-1, 1, points));
        // point = Math.max(point, ninjaTrainingTopDownRec(n-1, 2, points));
//        return point;

//         point = Math.max(point, ninjaTrainingTopDownRecMemorisation(n-1, 0, points, dp));
//         point = Math.max(point, ninjaTrainingTopDownRecMemorisation(n-1, 1, points, dp));
//         point = Math.max(point, ninjaTrainingTopDownRecMemorisation(n-1, 2, points, dp));
//        return point;


        ninjaTrainingTopDownRecMemorisation_ver2(n - 1, 3, points, dp);
        System.out.println("print: ");
        print(dp);
        return dp[n - 1][3];

//        return Math.max(ninjaTrainingTopDownRec3(n - 1, 0, points), Math.max(ninjaTrainingTopDownRec3(n - 1, 1, points), ninjaTrainingTopDownRec3(n - 1, 2, points)));
    }


    public static void main(String[] args) {
        int n = 3;
        int[][] points = {{1, 2, 5}, {3, 1, 1}, {3, 3, 3}};
//        System.out.println(ninjaTraining(n, points));
        ninjaTraining(n, points);
    }
}
