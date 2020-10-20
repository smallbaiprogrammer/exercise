package com.company.offer;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.omg.CORBA.INTERNAL;

import java.awt.geom.Rectangle2D;
import java.util.*;

public class middle {
    class ListNode{
        int val;
        ListNode next;
        public ListNode(int val){
            this.val = val;
        }
    }
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val){
            this.val=val;
        }
    }

    class Node{
        int val;
        Node next;
        Node random;
        public Node(int val){
            this.val = val;
            next = null;
            random = null;
        }
    }

    /**
     * 重建二叉树
     */

    // 剑指offer 07
    // 根据二叉树的前序遍历结果和中序遍历结果重建二叉树
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return new TreeNode(-1);
    }

    /**
     * ********************************************************************
     */

    // 剑指offer12
    // 矩阵中的最短路径
    public boolean exist(char[][] board, String word) {
        return false;
    }

    /**
     ************************************************************************
     */

    // 剑指offer 13
    // 机器人的运动范围
    public int movingCount(int m, int n, int k) {
        return -1;
    }

    /**
     ************************************************************************
     */

    // 剑指offer 14 -I
    // 剪绳子
    // 这个数学问题，尽量多剪出来 长度为3 的绳子 ，排除特殊情况

    public int cuttingRope1(int n) {
        if (n==2){
            return 1;
        }
        if (n==3){
            return 2;
        }
        if (n==4){
            return 4;
        }
        int res=1;
        while (n>4){
            n-=3;
            res*=3;
        }
        return res*n;
    }
    // 同时这个题还可以采用动态规划来做，dp一维数组，每个值等于当前值减去然后乘以dp【i-j】
    // 这样的时间复杂度是On2，有一点得不偿失，所以采用数学方法是最好的、
    public int cuttingRope(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp,1);
        for (int i=2;i<n+1;i++){
            for (int j=1;j<i;j++){
                dp[i]= Math.max(Math.max(dp[i],j*(i-j)),j*dp[i-j]);
            }
        }
        return dp[n];
    }
    // 剑指offer 14-II
    // 减绳子 II
    // 与上题相比，n的范围变大，导致可能会溢出
    // 所以一直取模就好了
    public int cuttingRope3(int n) {
        if(n<4){
            return n-1;
        }
        if (n==4){
            return 4;
        }
        long sum=1;
        while (n>4){
            sum*=3;
            sum=sum%1000000007;
            n-=3;
        }
        return (int)(sum*n%1000000007);
    }

    /**
     * ****************************************************
     */

    //剑指offer 16
    // 数值的整数次方
    // 快速幂 目前为止最好的解法
    public double myPow(double x, int n) {
        long b=n;
        if (b<0){
            b*=-1;
            x=1/x;
        }
        double sum=1;
        while (b>0){
            double temp = x;
            if (b%2==1){
                sum*=temp;
            }
            x=x*x;
            b/=2;
        }
        return sum;
    }

    /**
     ***************************************************
     */

    // 剑指offer 17
    // 打印从1到最大的n位数
    // 常规做法
    public int[] printNumbers(int n) {
        int sum = 1;
        for (int i=0;i<n;i++){
            sum*=10;
        }
        int[] res = new int[sum-1];
        for (int i=1;i<sum;i++){
            res[i-1]=i;
        }
        return res;
    }

    /**
    ******************************************************************
     */

    //剑指offer 20
    // 表示数值的字符串
    public boolean isNumber(String s) {
        char[] chars = s.toCharArray();
        int sign = 1;
        int spot = 0;
        for (int i=1;i<chars.length;i++){
            if (chars[i]=='e'||chars[i]=='E'){
                sign=1;
            }else if (chars[i]=='+'||chars[i]=='-'){
                return false;
            }
        }
        return true;
    }

    /**
     ******************************************************************
     */

    //剑指offer 26
    // 树的子结构

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (B==null){
            return false;
        }
        return true;
    }

    /**
     ***************************************************
     */

    // 剑指offer
    // 栈的压入、弹出序列
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        return true;
    }

    /**
     ***************************************************
     */

    // 剑指offer 32-I
    // 从上到下打印二叉树
    // 层次遍历二叉树
    // 这是采用迭代的形式，还可以采用递归的形式
    public int[] levelOrder0(TreeNode root) {
        if (root==null){
            return new int[]{};
        }
        List<Integer> tem = new LinkedList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()){
            root = queue.poll();
            tem.add(root.val);
            if (root.left!=null){
                queue.add(root.left);
            }
            if (root.right!=null){
                queue.add(root.right);
            }
        }
        int[] ans = new int[tem.size()];
        for (int i=0;i<tem.size();i++){
            ans[i]=tem.get(i);
        }
        return ans;
    }
    // 剑指offer 32-III
    // 从上打印二叉树-III
    // 按照Z的型的方式进行打印
    // 采用双向队列，判断奇偶
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        List<List<Integer>> ans = new LinkedList<>();
        if (root!=null){
            queue.add(root);
        }
        while (!queue.isEmpty()){
            LinkedList<Integer> temp = new LinkedList<>();
            for (int i=queue.size();i>0;i--){
                root = queue.poll();
                if (ans.size()%2==0){
                    temp.addLast(root.val);
                }else {
                    temp.addFirst(root.val);
                }
                if (root.left!=null){
                    queue.add(root.left);
                }
                if (root.right!=null){
                    queue.add(root.right);
                }
            }
            ans.add(temp);
        }
        return ans;
    }

    /**
     ***************************************************
     */

    // 剑指offer 33
    // 判断二叉搜索树的后续遍历序列
    public boolean verifyPostorder(int[] postorder) {
        return false;
    }

    /**
     *  ***************************************************
     */

    // 剑指offer 34
    // 二叉树中和为某一值的路径
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        return null;
    }

    /**
     *  ***************************************************
     */

    // 剑指offer 35
    // 复杂链表的复制
    public Node copyRandomList(Node head) {
        Node root = new Node(-1);

        Node first = null;
        root.next=first;
        Node pos1 = first;
        Node pos = head;
        while (pos!=null){

        }
        return root.next;
    }

    // 剑指offer 14
    // 礼物的最大价值
    // 二维动态规划
    public int maxValue(int[][] grid) {
        int[][] dp = grid;
        for (int i=0;i<dp.length;i++){
            for (int j=0;j<dp[i].length;j++){
                if (i==0&&j==0){
                    continue;
                }else if (i==0){
                    dp[i][j] +=dp[i][j-1];
                }else if (j==0){
                    dp[i][j] +=dp[i-1][j];
                }else {
                dp[i][j] += Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[grid.length-1][grid[0].length-1];
    }

    /**
     *  ***************************************************
     */



    /**
     *  ***************************************************
     */



    /**
     *  ***************************************************
     */

    /**
     *  ***************************************************
     */


    /**
     * ***********************************************************************************
     */



    /**
     * *************************************************************
     */



    /**
     * *************************************************************
     */





















    // 剑指offer 56 -I
    // 数组中数字出现的次数
    // 空间复杂度为O（1） 时间复杂度是O（N）
    // NB的位运算
    public int[] singleNumbers(int[] nums) {
        int sum  = 0;
        for (int i:nums){
            sum ^= i;
        }
        // 得到两个不同数的异或值
        int temp = 1;
        // 找出这个异或值为 1 的一位，随便一位都可以，但是这个找出的最右边为一的一位
        while ((temp&sum)==0){
            temp<<=1;
        }
        int a=0;int b=0;
        // 根据这一位是否为一，对数组进行分组，一共分为两组，一组是含有第一个单一的数，第二组是第二个单一的数
        // 每一组异或，将重复的数清零，得到a ， b 两个数firs
        for (int i:nums){
            if ((i&temp)==1){
                a^=i;
            }else {
                b^=i;
            }
        }
        return new int[]{a,b};
    }
}
