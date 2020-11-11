package com.company.offer;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(){}
    public TreeNode(int val){
        this.val = val;
    }
}

public class hard {
    // 剑指offer 43
    // 1~n整数中1出现的次数
    // 可以采用动态规划的思想来实现
    // 内存会溢出
    public int countDigitOne0(int n) {
        if (n == 1 || n == 0){
            return n;
        }
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n ; i++ ){
            dp[i] += dp[i/10];
            if (i%10 == 1){
                dp[i]++;
            }
        }
        int res = 0;
        for (int i = 0; i <= n ; i++ ){
            res += dp[i];
        }
        return res;
    }

    // 只能采用数学找规律的方法了
    // 我们发现每个数的出现1的次数与前一位和后一位的大小有关
    // 所以我们按位从前往后走一遍，因为该数的最大范围位2的31次方，
    // 所以是十亿多，也就是十位数，所以经过10次循环就可以得到

    public int countDigitOne(int n) {
       int digit = 1;
       int res = 0 ;
       int high = n / 10;
       int cur = n % 10;
       int low = 0;
       while (high != 0 || cur != 0){
           if (cur == 0){
               res += high * digit;
           }else if (cur == 1){
               res += high*digit+low+1;
           }else{
               res += (high+1)*digit;
           }
           low += cur*digit;
           cur = high%10;
           high /=10;
           digit *=10;
       }
       return res;
    }




    /**
     *  剑指offer 37
     *  序列化二叉树和反序列化二叉树
     */
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null){
                return new String("[]");
            }
            StringBuilder stringBuilder = new StringBuilder("[");
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()){
                root = queue.poll();
                if (root !=null){
                    stringBuilder.append(root.val);
                    //  System.out.println(root.val);
                    queue.add(root.left);
                    queue.add(root.right);
                }else {
                    stringBuilder.append("null");
                }
                stringBuilder.append(",");
            }
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
            stringBuilder.append("]");
            return stringBuilder.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String s = "[]";
            if (s.equals(data)){
                return null;
            }
            String[] strs = data.substring(1,data.length()-1).split(",");
            Queue<TreeNode> queue = new LinkedList<>();
            TreeNode root = new TreeNode(Integer.parseInt(strs[0]));
            queue.add(root);
            int i = 1;
            while (!queue.isEmpty()){
                TreeNode temp = queue.poll();
                if (!strs[i].equals("null")){
                    temp.left = new TreeNode(Integer.parseInt(strs[i]));
                    queue.add(temp.left);
                }
                i++;
                if (!strs[i].equals("null")){
                    temp.right =  new TreeNode(Integer.parseInt(strs[i]));
                    queue.add(temp.right);
                }
                i++;
            }
            return root;
        }
    }

    // 剑指offer 41
    // 数据流中的中位数
    // 采用两个优先队列来实现，一个优先队列存储较大的一半数据，一个反向优先队列存储较小的一半数据
    // 相当于一个大顶堆和一个小顶堆
    // 若两个队列的长度相同的时候，取这两个堆的顶元素的平均数，反之则取堆中的元素
    class MedianFinder {
        // 小顶堆
        Queue<Integer> queue1;
        // 大顶堆
        Queue<Integer> queue2;
        /** initialize your data structure here. */
        public MedianFinder() {
            // 采用优先队列
            // 该队列是采用堆实现的
            queue1 = new PriorityQueue<>();
            queue2 = new PriorityQueue<>((x,y)->(y-x));
        }

        public void addNum(int num) {
            if (queue1.size() != queue2.size()){
                queue1.add(num);
                queue2.add(queue1.poll());
            }else {
                queue2.add(num);
                queue1.add(queue2.poll());
            }
        }

        public double findMedian() {
            return queue1.size() != queue2.size() ?queue1.peek():(queue1.peek()+queue2.peek())/2.0f;
        }
    }

    // 剑指offer 51
    // 数组中的逆序对
    // 第一种思路肯定是暴力算法，发现是超时的，所以时间复杂度是N2的肯定是不合适的
    // 所以可以采用归并排序的想法
    // 典型的分治算法使用，空间换时间，大量占用内存，运算时间变快
    public int reversePairs(int[] nums) {
        int len = nums.length;
        if (len < 2){
            return 0;
        }
        int[] copy = new int[len];
        for (int i = 0 ;i < len ; i++){
            copy[i] = nums[i];
        }
        int[] temp = new int[len];
        return reversePairs(copy,0,len-1,temp);
    }

    private int reversePairs(int[] nums, int left, int right, int[] temp) {
        if (left == right){
            return 0;
        }
        int mid = left + (right - left) / 2 ;
        int leftPair = reversePairs(nums,left,mid,temp);
        int rightPair = reversePairs(nums,mid+1,right,temp);
        if (nums[mid] <= nums[mid+1]){
            return leftPair + rightPair;
        }
        int crossPairs = mergeAndCount(nums,left,mid,right,temp);
        return leftPair + rightPair + crossPairs;
    }

    private int mergeAndCount(int[] nums, int left, int mid, int right, int[] temp) {
        for (int i = left; i <= right; i++) {
            temp[i] = nums[i];
        }

        int i = left;
        int j = mid + 1;

        int count = 0;
        for (int k = left; k <= right; k++) {

            if (i == mid + 1) {
                nums[k] = temp[j];
                j++;
            } else if (j == right + 1) {
                nums[k] = temp[i];
                i++;
            } else if (temp[i] <= temp[j]) {
                nums[k] = temp[i];
                i++;
            } else {
                nums[k] = temp[j];
                j++;
                count += (mid - i + 1);
            }
        }
        return count;
    }

    // 剑指offer
    // 正则表达式匹配
    // 动态规划
    
    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        char[] chars1 = s.toCharArray();
        char[] chars2 = p.toCharArray();
        boolean[][] dp = new boolean[n+1][m+1];
        for (int i = 0 ;i <= n ; i++){
            for (int j = 0; j <= m ; j++){
                if (j == 0){
                    dp[i][j] = i == 0;
                }else {
                    if (chars2[j - 1] != '*'){
                        if (i > 0 && (chars1[i-1] == chars2[j - 1] || chars2[j - 1] == '.')){
                            dp[i][j] = dp[i-1][j-1];
                        }
                    }else {
                        if (j >= 2){
                            dp[i][j] |= dp[i][j-2];
                        }
                        if (i >= 1 && j >= 2 && (chars1[i - 1] == chars2[j-2] || chars2[j-2] == '.')){
                            dp[i][j] |= dp[i-1][j];
                        }
                    }
                }
            }
        }
        return dp[n][m];
    }

}
