package com.company.leetcode;

/**
 * 二叉树
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(){
    }
    public TreeNode(int val){
        this.val=val;
    }
    public TreeNode(int val,TreeNode left,TreeNode right){
        this.val=val;
        this.left=left;
        this.right=right;
    }
    // 判断两个二叉树是否相同
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null&&q==null){
            return true;
        }else if (p==null||q==null){
            return false;
        }else if (p.val!=q.val){
            return false;
        }else {
            return isSameTree(p.left,q.left)&&isSameTree(p.right,q.right);
        }
    }
    // 判断二叉树是不是对称的
    public boolean isSymmetric(TreeNode root) {
        return check(root, root);
    }

    public boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
    }

}
