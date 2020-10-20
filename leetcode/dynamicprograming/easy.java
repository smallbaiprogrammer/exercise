package com.company.leetcode.dynamicprograming;

public class easy {
    public int maxProfit(int[] prices) {
        int low=Integer.MAX_VALUE;
        int max=0;
        int dp=0;
        for (int i:prices){
            low = Math.min(low,i);
          dp=i-low;
            max = Math.max(max,dp);
        }
        return max;
    }

    /**
     *  斐波那契数列
     *
     */
    public int numWays(int n){
        int a=1;
        int b=1;
        int sum=0;
        for (int i=0;i<n;i++){
            sum=(a+b)%1000000007;
            b=a;
            a=sum;
        }
        return b;
    }

    //  斐波那契数列的改编
    public static int waysToStep(int n) {
        if(n==1){
            return 1;
        }
        if (n==2){
            return 2;
        }
        int mod =1000000007;
        int[] dp = new int[n+1];
        dp[0]=1;
        dp[1]=1;
        dp[2]=2;
        for (int i=3;i<=n;i++){
            dp[i]=(dp[i-1]+dp[i-2]+dp[i-3])%mod;
        }
        return dp[n];
    }
    //  按摩师
    //  动态规划
    public static int massage(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        if(nums.length==1){
            return nums[0];
        }

        int[] dp = new int[nums.length];
        int max=0;
        dp[0]=nums[0];
        dp[1]=Math.max(nums[0],nums[1]);
        if(nums.length==2){
            return dp[1];
        }
        for (int i=2;i<nums.length;i++){
            dp[i] = Math.max(dp[i-2]+nums[i],dp[i-1]);
            max=Math.max(max,dp[i]);
        }
        return max;
    }
}
