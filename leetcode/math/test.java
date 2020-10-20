package com.company.leetcode.math;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test {
    public int cuttingRope(int n) {
        if(n<4){
            return n-1;
        }
        if (n==4){
            return 4;
        }
        int sum=1;
        while (n>4){
            sum*=3;
            sum=sum%1000000007;
            n-=3;
        }
        return (int)(sum*n%1000000007);
    }
    // 2的幂
    public boolean isPowerOfTwo(int n) {
        return (n>0)&&((n&(n-1))==0);
    }
    public boolean isPowerOfTwo1(int n) {
        return (n>0)&&(1073741824%2==0);
    }
    //3的幂
    // 1162261467是Integer类型中最大的3的幂
    public boolean isPowerOfThree(int n) {
        return n>0&&(1162261467%n==0);
    }
    // 将数组转为字符串，然后采用正则表达式来表示
    public boolean isPowerOfThree1(int n) {
        return Integer.toString(n,3).matches("^10*$");
    }
    // 4的幂
    //同样的有好多种方法，位运算，字符串，暴力法
    public boolean isPowerOfFour1(int num) {
        //第一种转成字符串，然后正则表达式匹配
        return Integer.toString(num,4).matches("^10*$");
    }
    public boolean isPowerOfFour(int num) {
        //第二种位运算
        return (num>0)&&(1073741824%num==0)&&((num&0x55555555)!=0);
    }

    // 二分查找
    public static int mySqrt(int x) {
        int i=0;
        int j=x;
        int ans=-1;
        while (i<=j){
            int mid=(i+j)/2;
            if ((long)mid*mid<=x){
                ans=mid;
                i=mid+1;
            }else {
                j=mid-1;
            }
        }
        return ans;
    }
    // 杨辉三角
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<>();
        int[][] arr = new int[numRows][numRows];
        for (int i = 0; i < numRows; i++) {
            List<Integer> subList = new ArrayList<>();
            for (int j = 0; j <=i ; j++) {
                if(j==0 || j==i){
                    arr[i][j] = 1;
                }else{
                    arr[i][j] = arr[i-1][j-1]+arr[i-1][j];
                }
                subList.add(arr[i][j]);
            }
            list.add(subList);
        }
        return list;
    }
    public static int trailingZeroes(int n) {
        int res=0;
        for (int i=5;i<=n;i+=5){
            int temp=i;
            while (temp%5==0){
                res++;
                temp/=5;
            }
        }
        return res;
    }
    public static double myPow(double x, int n) {
        if(x == 0.0f) {
            return 0.0d;
        }
        long b = n;
        double res = 1.0;
        if(b < 0) {
            x = 1 / x;
            b = -b;
        }
        while(b > 0) {
            if((b & 1) == 1) {
                res *= x;
            }
            x *= x;
            b >>= 1;
        }
        return res;
    }
    public int hammingWeight(int n) {
        int ans=0;
        while (n!=0){
            if ((n&1)==1){
                ans++;
            }
            n=n>>>1;
        }
        return ans;
    }
    // 除法的实现
    public int divide(int dividend, int divisor) {
        int negtive=0;
        int flag=0;
        if((dividend^divisor) <0) {
            negtive=-1;
        }
        if(dividend==-2147483648 && divisor==1) {
            return -2147483648;
        }
        if(dividend==-2147483648 && divisor==-1) {
            return 2147483647;
        }
        if(dividend==-2147483648){
            dividend=2147483647;
            flag=1;
        }
        if(dividend<0) {
            dividend=-dividend;
        }
        if(divisor<0) {
            divisor=-divisor;
        }
        int ret=0;
        int count=1;
        int tempdivisor=divisor;
        while(dividend-divisor>=0){
            ret+=count;
            dividend=dividend-tempdivisor;
            if(dividend>tempdivisor+tempdivisor){
                count +=count;
                tempdivisor+=tempdivisor;
            }else{
                count=1;
                tempdivisor=divisor;
            }
        }
        if(flag==1){
            ret=ret+((dividend+1==divisor)?1:0);
        }
        return negtive==0?ret:-ret;
    }
    public static void rotate(int[][] matrix) {
        int len = matrix.length;
        for (int i=0;i<len;i++){
            for (int j=i;j<len;j++){
                int tmp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = tmp;
            }
        }
        for (int i=0;i<len;i++){
            for (int j=0;j<len/2;j++){
                swap(matrix,i,j, i,matrix[i].length-1-j);
            }
        }
    }
    public static void swap(int[][] nums,int i,int j,int m,int n){
        nums[i][j]=nums[i][j]^nums[m][n];
        nums[m][n]=nums[i][j]^nums[m][n];
        nums[i][j]=nums[i][j]^nums[m][n];
    }



    public static void main(String[] args) {
//        int[][] nums = {{1,2,3},{4,5,6},{7,8,9}};
//        for (int i=0;i<nums.length;i++){
//            System.out.println(Arrays.toString(nums[i]));
//        }
//        rotate(nums);
//
//
//        for (int i=0;i<nums.length;i++){
//            System.out.println(Arrays.toString(nums[i]));
//        }
           int a=41;
//        int b=41;
        a=a^a;
       a=a^a;
        a=a^a;
         System.out.println(a+" "+1);
        //System.out.println(myPow(2.0,10));
    }
}
