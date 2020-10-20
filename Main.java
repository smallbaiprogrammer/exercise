package com.company;

import java.util.Arrays;

public class Main {
    public double[] twoSum(int n) {
        int[][] dp = new int[n+1][6*n+1];
        // 第一行全部填充为1
        for(int i=1;i<=6;i++) {
            dp[1][i] = 1;
        }
        // 对角线全部填充为2
        for(int i=2;i<=n;i++) {
            dp[i][i] = 1;
        }
        for(int i=2;i<=n;i++){
            for(int j=i+1;j<=6*i;j++){
                // 对dp数组进行填充
                if(j<=i+5) {
                    // 在过了中间点时候
                    dp[i][j] = dp[i][j-1]+dp[i-1][j-1];
                } else {
                    // 在数组右边的
                    dp[i][j] = dp[i][j-1]+dp[i-1][j-1]-dp[i-1][j-7];
                }
            }
        }
        double sum = 0;
        for(int i=n;i<=6*n;i++){
            sum += dp[n][i];
        }
        double[] res = new double[5*n+1];
        for(int i=n;i<=6*n;i++){
            res[i-n] = dp[n][i]/sum;
        }
        return res;
    }




}
