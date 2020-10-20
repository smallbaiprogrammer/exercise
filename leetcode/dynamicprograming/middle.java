package com.company.leetcode.dynamicprograming;

import java.util.Arrays;

public class middle {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
        for (int i=0;i<dp.length;i++){
            for (int j=0;j<dp[i].length;j++){
                if (obstacleGrid[i][j]==1){
                    dp[i][j]=0;
                }
                else{
                    if(i==0&&j!=0){
                        dp[i][j]=dp[i][j-1];
                    }
                    else if(i!=0&&j==0){
                        dp[i][j]=dp[i-1][j];
                    }
                    else if(i==0&&j==0){
                        dp[i][j]=1;
                    }else{
                        dp[i][j]=dp[i-1][j]+dp[i][j-1];
                    }
                }
            }
        }
        return dp[obstacleGrid.length-1][obstacleGrid[0].length-1];
    }
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for(int i=0;i<dp.length;i++){
            for(int j=0;j<dp[i].length;j++){
                if(i==0||j==0){
                    dp[i][j]=1;
                }else {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                }
            }
        }
        return dp[m-1][n-1];
    }
    public int numDecodings(String s) {
        if(s.length()==0){
            return 0;
        }
        int[] dp = new int[s.length()+1];
        dp[0]=1;
        dp[1]=s.charAt(0)=='0'?0:1;
        for(int i=1;i<s.length();i++){
            if(s.charAt(i-1)=='1'||s.charAt(i-1)=='2'&&s.charAt(i)-'0'<7){
                if(s.charAt(i)=='0'){
                    dp[i+1]=dp[i-1];
                }else{
                    dp[i+1]=dp[i-1]+dp[i];
                }
                // 这里是重点，若后两位不是在10到26之间的，后一位等于0，那么肯定不存在这样的组合，所以直接返回零
            }else if(s.charAt(i)=='0'){
                return 0;
            }else{
                dp[i+1]=dp[i];
            }
        }
        return dp[s.length()];
    }
    // 数组分割
    public static boolean canPartition(int[] nums) {
            int len = nums.length;
            if (len < 2) {
                return false;
            }
            int sum = 0, maxNum = 0;
            for (int num : nums) {
                sum += num;
                maxNum = Math.max(maxNum, num);
            }
            if (sum % 2 != 0) {
                return false;
            }
            int target = sum / 2;
            if (maxNum > target) {
                return false;
            }
            boolean[][] dp = new boolean[len][target + 1];
            for (int i = 0; i < len; i++) {
                dp[i][0] = true;
            }
            for (int i = 1; i < len; i++) {
                int num = nums[i];
                for (int j = 1; j <= target; j++) {
                    if (j >= num) {
                        dp[i][j] = dp[i - 1][j] | dp[i - 1][j - num];
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
            return dp[len - 1][target];
    }
    public static String longestPalindrome(String s) {
        int len = s.length();

        if (len<2){
            return s;
        }
        int max=1;
        int temp=0;
        boolean[][] dp = new boolean[len][len];
        char[] chars=s.toCharArray();

        for (int j=1;j<len;j++){
            for (int i=0;i<j;i++){
                if (chars[i]!=chars[j]){
                    dp[i][j]=false;
                }else {
                    if (j-i<3){
                        dp[i][j]=true;
                    }else {
                        dp[i][j]=dp[i+1][j-1];
                    }
                }
                if (dp[i][j]&&j-i+1>max){
                    max=j-i+1;
                    temp=i;
                }
            }
        }
        return s.substring(temp,temp+max);
    }
    public static int coinChange(int[] coins, int amount) {
        int len = coins.length;
        int[] dp = new int[amount+1];
        Arrays.fill(dp,amount+1);
            dp[0]=0;
        for (int i=1;i<amount+1;i++){
            int min=Integer.MAX_VALUE;
            for (int j=0;j<len;j++){
                if (i-coins[j]>=0) {
                    dp[i] = Math.min(dp[i],dp[i-coins[j]]+1);
                }
            }

        }
        if (dp[amount]>amount){
            return -1;
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = {186,419,83,408};
        System.out.println(Integer.MIN_VALUE);
        System.out.println(coinChange(coins,6249));
    }
}
