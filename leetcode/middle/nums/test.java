package com.company.leetcode.middle.nums;

import java.util.*;

public class test {
    public static int longestSubarray(int[] nums) {
        int count = 0;
        int counttemp = 0 ;
        int max = 0;
        for (int i:nums){
            if (i==1){
                // 这里重点，采用两个量来分别对总长度和当前到上一个0的位置进行计数
                // 若出现出现连续的两个零，两个值都清零
                // 若出现两个非连续的零，则第一个值会保存两个0之间的1的个数
                // 备用计数器将清零
                count++;
                counttemp++;
                max = Math.max(max,count);
            }else {
                count = counttemp;
                counttemp=0;
            }
        }
        if (max==nums.length){
            max--;
        }
        return max;
    }
    public static int mySqrt(int x) {
        int i=x;
        while (i!=1){
            if (i*i<=x&&(i+1)*(i+1)>=x){
                return i;
            }else if (x<i*i){
                i--;
            }else {
                i++;
            }
        }
        return i;
    }
        public static void sortColors(int[] nums) {
            int n = nums.length;
            int p0 = 0, p1 = 0;
            for (int i = 0; i < n; ++i) {
                if (nums[i] == 1) {
                    int temp = nums[i];
                    nums[i] = nums[p1];
                    nums[p1] = temp;
                    ++p1;
                } else if (nums[i] == 0) {
                    int temp = nums[i];
                    nums[i] = nums[p0];
                    nums[p0] = temp;
                    if (p0 < p1) {
                        temp = nums[i];
                        nums[i] = nums[p1];
                        nums[p1] = temp;
                    }
                    ++p0;
                    ++p1;
                }
            }
        }
    public static void sort(int[] nums){
        int pos0=0;
        int pos1=nums.length-1;
        for (int i=0;i<nums.length;i++){
            while(nums[i]==2&&i<pos1){
                swap(nums,i,pos1);
                pos1--;
            }
            if (nums[i]==0){
                swap(nums,i,pos0);
                pos0++;
            }
        }
    }
    public static void swap(int[] nums,int i,int j){
        nums[i]=nums[i]^nums[j];
        nums[j]=nums[i]^nums[j];
        nums[i]=nums[i]^nums[j];
    }

    /**
     *  merge
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge(int[] nums1,int m,int[] nums2,int n){
        int pos= nums1.length-1;
        int pos1=m-1;
        int pos2=n-1;
        while (pos1>=0&&pos2>=0){
            if (nums1[pos1]>nums2[pos2]) {
                nums1[pos--]=nums1[pos1--];
            }else {
                nums1[pos--]=nums2[pos2--];
            }
        }
        while (pos2>=0){
            nums1[pos--]=nums2[pos2--];
        }
    }
    public static int[][] restoreMatrix(int[] rowSum,int[] colSum){
        int n = rowSum.length;
        int[][] res = new int[n][n];

        return res;
    }
    public static int[] shuffle(int[] nums,int n){
        for (int i=0;i<2*n;i++){
            int j=i>=n?2*(i-n)+1:2*i;
            nums[j]|=(nums[i]&1023)<<10;
        }
        // 此处采用foreach 不可以，foreach只能表里集合，并不能修改集合
        for (int i=0;i<nums.length;i++){
           nums[i]>>=10;
        }
        return nums;
    }
    public static int[] exchange(int[] nums) {
        int i=0;
        int j=nums.length-1;
        while (i<=j){
            while (i<nums.length&&nums[i]%2!=0){
                i++;
            }
            while(j>=0&&nums[j]%2!=1){
                j--;
            }
            if(i<j) {
                swap(nums,i,j);
            }
        }
        return nums;
    }
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int[] res=new int[nums.length-k+1];
        for(int i=0;i<nums.length-k+1;i++){
            int temp=Integer.MIN_VALUE;
            int j=0;
            while(j<k){
               temp=Math.max(nums[i+j],temp);
               j++;
            }
            res[i]=temp;
        }
        return res;
    }
    // 装水最多的桶的问题
    public class Solution {
        public int maxArea(int[] height) {
            int l = 0, r = height.length - 1;
            int ans = 0;
            while (l < r) {
                int area = Math.min(height[l], height[r]) * (r - l);
                ans = Math.max(ans, area);
                if (height[l] <= height[r]) {
                    ++l;
                }
                else {
                    --r;
                }
            }
            return ans;
        }
    }
    // 二进制求和
    // 加法器
    public static String addBinary(String a, String b) {
        StringBuilder ans = new StringBuilder();
        StringBuilder s1 = new StringBuilder(a);
        StringBuilder s2 = new StringBuilder(b);
        int pos1=s1.length()-1;
        int pos2=s2.length()-1;
        int carry = 0;
        while (pos1>=0||pos2>=0||carry!=0){
            int x = pos1>=0?s1.charAt(pos1)-'0':0;
            int y = pos2>=0?s2.charAt(pos2)-'0':0;
            int sum=x+y+carry;
            ans=ans.append(sum%2);
            carry=sum/2;
            pos1--;
            pos2--;
        }
        ans.reverse();
        return ans.toString();
    }
    // 如果范围要求不是特别大，直接字符串转为数字然后转回去
    public static String addBinary1(String a, String b){
        return Integer.toBinaryString(Integer.parseInt(a,2)+Integer.parseInt(b,2));
    }
    // 二分查找
    public static int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1,-1};
        int ans = BinarySearch(nums,target);
        //System.out.println(ans);
        if (ans==-1){
            return res;
        }else {
            res= new int[]{ans, ans};
            for (int m=ans;m>=0;m--){
                //System.out.println(m);
                if (nums[m]==target){
                    if (m==0){
                        System.out.println(m);
                        res[0]=m;
                        break;
                    }
                    continue;
                }else {
                    res[0]=m+1;
                    break;
                }
            }
            for (int m=ans;m<nums.length;m++){
                //System.out.println(m);
                if (nums[m]==target){
                    if (m==nums.length-1){
                        res[1]=m;
                        break;
                    }
                    continue;
                }else {
                    res[1]=m-1;
                    break;
                }
            }
        }
        return res;
    }
    public static int BinarySearch(int[] nums,int target){
        int res=-1;
        int i=0;
        int j=nums.length-1;
        while (i<=j){
            int mid = (i+j)/2;
            if (nums[mid]==target){
                return mid;
            }else if (nums[mid]>target){
                j=mid-1;
            }else {
                i=mid+1;
            }
        }
        return res;
    }

    public int minCount(int[] coins) {
        int sum=0;
        for (int i=0;i<coins.length;i++){
            sum+=coins[i]/2+coins[i]%2;
        }
        return sum;
    }
    public int[] sortedSquares(int[] A) {
        int len = A.length;
        int[] res = new int[len];
        int left = 0;
        int right = len-1;
        int pos=len-1;
        while (left<right){
            if (A[left]*A[left]<=A[right]*A[right]){
                res[pos--]=A[right]*A[right];
                right--;
            }else {
                res[pos--]=A[left]*A[left];
                left++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1};
        System.out.println(Arrays.toString(searchRange(nums,1)));
    }
}
