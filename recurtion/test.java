package com.company.recurtion;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class test {
    // 案例一
    // 求一个数的阶乘，用递归
    public int fact(int n){
        if (n==0){
            return 1;
        }
        return n*fact(n-1);
    }

    // 最大公约数，采用辗转相除法

    public int gcd(int a,int b){
//        int x = a%b;
//        if (x==0){
//            return b;
//        }
//        a=b;
//        b=x;
//        return gcd(a,b);
        // 精简 优化一下
        if (b==0){
            return a;
        }
        return gcd(b,a%b);
    }
    // 案例三
    // 斐波那契数列 走楼梯问题 没有优化

    public int fib(int n){
        if (n==1){
            return 1;
        }
        if (n==2){
            return 2;
        }
        return fib(n-1)+fib(n-2);
    }

    // 案例四
    // 归并排序
    // 简化问题 的 一个标准 模式
    public void mergeSort(int[] nums){
        int len = nums.length;
        int[] tempNum = new int[len];
        sort(nums,tempNum,0,len-1);
    }
    public void sort(int[] nums,int[] tempNum,int i,int j){
        if (i<j){
            int mid = i+(j-i)/2;
            sort(nums,tempNum,0,mid);
            sort(nums,tempNum,mid+1,j);
            mergeSort(nums,tempNum,i,mid,j);
        }
    }
    public void mergeSort(int[] nums,int[] tempNum,int i,int mid,int j){
        int posleft = i;
        int posright = mid+1;
        int leftEnd = mid;
        int rightEnd = j;
        int pos = i;
        int len = rightEnd - posleft + 1;
        while (posleft<=leftEnd&&posright<=rightEnd){
            if (nums[posleft]<nums[posright]){
                tempNum[pos++] = nums[posleft++];
            }else {
                tempNum[pos++] = nums[posright++];
            }
        }
        while (posleft<=leftEnd){
            tempNum[pos++] = nums[posleft++];
        }
        while (posright<=rightEnd){
            tempNum[pos++] = nums[posright++];
        }
        pos--;
        for (int n=0;n<len;n++,pos--){

            nums[pos]=tempNum[pos];
        }
    }
    // 快速排序运用的递归
    public void quickSort(List<Integer> nums){
        if (nums.size()>0){
            List<Integer> smaller = new LinkedList<>();
            List<Integer> normal = new LinkedList<>();
            List<Integer> larger = new LinkedList<>();
            int choice = nums.get(nums.size()/2);
            for (int i:nums){
                if (i>choice){
                    larger.add(i);
                }else if (i<choice){
                    smaller.add(i);
                }else {
                    normal.add(i);
                }
            }
            quickSort(smaller);
            quickSort(larger);
            nums.clear();
            nums.addAll(smaller);
            nums.addAll(normal);
            nums.addAll(larger);
        }
    }

    /**
     *  二叉树
     */

    //  求二叉树的高度
    public int length(TreeNode root){
        if (root==null){
            return 0;
        }
        return Math.max(length(root.left),length(root.right))+1;
    }
    //  二叉树的 镜像
    // 这种题一般都会使用两种做法，一种是递归的方法，另一种是采用一种其他的数据结构来辅助这样做，一般采用栈或者队列
    // 这里我们只采用递归的形式，后面学习采用栈或者队列的形式
    public TreeNode mirrorTree(TreeNode root) {
        // 简单 容易理解
        if (root==null){
            return root;
        }
        TreeNode temp = root.left;
        root.left = mirrorTree(root.left);
        root.right = mirrorTree(root.right);
        return root;
    }
    // 对称的二叉树
    public boolean isSymmetric(TreeNode root) {
        if (root==null){
            return true;
        }
        return isSymmetric(root.right,root.left);
    }
    public boolean isSymmetric(TreeNode root0,TreeNode root1) {
        if (root0==null&&root1==null){
            return true;
        }else if (root0!=null||root1!=null){
            return false;
        }else {
            return isSymmetric(root0.right,root1.left)&&isSymmetric(root0.left,root1.right);
        }
    }
    // 二叉搜索书的第K大节点
    int k;
    int res;
    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        return res;
    }
    public void dfs(TreeNode root){
        // 如果当前节点为空，返回寻找下一个有效节点
        if (root==null){
            return;
        }
        // 从右儿子开始寻找
        dfs(root.right);
        // 找到一个有效节点，k减小1，因为找到的是比较大的节点，
        k--;
        // 当k值为0时，找到了对应的节点，将值赋给res，并返回
        if (k==0){
            res = root.val;
            return;
        }
        // 找完右孩子找左孩子
        dfs(root.left);
    }
    // 平衡二叉树
    public boolean isBalanced(TreeNode root) {
        if (root==null){
            return true;
        }
        if ((Math.abs(length(root.left)-length(root.right)))>1){
            return false;
        }
        return isBalanced(root.right)&&isBalanced(root.left);
    }
    // 中序遍历
    public void midprint(TreeNode root){
        if (root==null){
            return;
        }
        System.out.println(root.val);
        midprint(root.left);
        midprint(root.right);
    }

    // 二叉树路径为K的分支
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>>  ans = new LinkedList<>();
        return ans;
    }
    public void dfs(TreeNode root,List<List<Integer>> ans){

    }
    // 二叉搜索树的最近公共祖先
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val>root.val&&q.val>root.val){
            return lowestCommonAncestor1(root.right,p,q);
        }else if (p.val<root.val&&q.val<root.val){
            return lowestCommonAncestor1(root.left,p,q);
        }else {
            return root;
        }
    }
    // 二叉树的最近公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (contain(root.right,p)&&contain(root.right,q)){
            return lowestCommonAncestor(root.right,p,q);
        }else if (contain(root.left,p)&&contain(root.left,q)){
            return lowestCommonAncestor(root.left,p,q);
        }else {
            return root;
        }
    }
    public boolean contain(TreeNode root,TreeNode p){
        if (root==null){
            return false;
        }
        if (root==p){
            return true;
        }
        return contain(root.left,p)||contain(root.right,p);
    }
    List<Integer> ans = new LinkedList<>();
    public int[] reversePrint(ListNode head){
        reversePrint1(head);
        int len  = ans.size();
        int[] nums = new int[len];
        for (int i=0;i<len;i++){
            nums[i]=ans.get(i);
        }
        return nums;
    }
    public void reversePrint1(ListNode head){
        if (head==null){
            return;
        }
        reversePrint1(head.next);
        ans.add(head.val);
    }
    public static void main(String[] args) {
        test t = new test();
        int[] nums = new int[]{1,4,7,2,5,8,3,6,9,0};
        TreeNode f = new TreeNode(-1);
        TreeNode th = new TreeNode(2);
        TreeNode se = new TreeNode(3);
        TreeNode fo = new TreeNode(4);
        TreeNode ff = new TreeNode(5);
        TreeNode sx = new TreeNode(6);
        TreeNode sv = new TreeNode(7);
        f.left = th;
        f.right = se;
        th.left = fo;
        th.right = ff ;
        se.left = sx;
        se.right = sv;
        System.out.println(t.contain(f,se));
//        List<Integer> nums1 = new LinkedList<>();
//        for (int i:nums) {
//            nums1.add(i);
//        }
//        t.quickSort(nums1);
//        System.out.println(Arrays.toString(nums1.toArray()));
    }
    static class TreeNode{
        int val;
        TreeNode left ;
        TreeNode right ;
        public TreeNode(int val){
            this.val = val;
        }
    }
    static class ListNode{
        int val;
        ListNode next;
        public ListNode(int val){
            this.val =val;
        }
    }

}
