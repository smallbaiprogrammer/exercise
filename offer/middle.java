package com.company.offer;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.sun.org.apache.regexp.internal.RE;

import java.util.*;

public class middle{
    /**
    * 二叉树
     */
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(){}
        public TreeNode(int val){
            this.val = val;
        }
    }

    /**
     * 单链表
     */
    class ListNode{
        int val;
        ListNode next;
        public ListNode(){}
        public ListNode(int val){
            this.val = val;
        }
    }

    /**
     * 复杂链表
     */
    class Node{
        int val;
        Node next;
        Node random;
        public Node(int val){
            this.val = val;
        }
    }



    // 剑指offer 59-II
    // 队列的最大值
    // 维护一个有序的双端队列

    class MaxQueue {
        Queue<Integer> queue;
        Deque<Integer> deque;
        public MaxQueue() {
            queue = new LinkedList<>();
            deque = new LinkedList<>();
        }

        public int max_value() {
            if (deque.isEmpty()){
                return -1;
            }
            return deque.peekFirst();
        }

        public void push_back(int value) {
            while (!deque.isEmpty() && deque.peekLast() < value){
                deque.pollLast();
            }
            deque.offerLast(value);
            queue.offer(value);
        }

        public int pop_front() {
            if (queue.isEmpty()){
                return -1;
            }
            int ans = queue.peek();
            if (ans == deque.peekFirst()){
                deque.remove();
            }
            return queue.remove();
        }
    }

    // 剑指offer 07
    // 重建二叉树
    int[] preorder;
    Map<Integer,Integer> map;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder =  preorder ;
        map = new HashMap<>();
        for (int i = 0 ;i < inorder.length ; i++ ){
            map.put(inorder[i],i);
        }
        return recur(0,0,inorder.length-1);
    }
    TreeNode recur(int root,int left,int right){
        if (left > right){
            return null;
        }
        TreeNode treeNode = new TreeNode(preorder[root]);
        int temp = map.get(preorder[root]);
        treeNode.left = recur(root+1,left,temp-1);
        treeNode.right = recur(root + temp-left+1,temp+1,right);
        return treeNode;
    }

    // 剑指offer 20
    // 表示数值的字符串
    // 根据三个符号出现的次数来判断该字符串是否可以表示字符串
    public boolean isNumber(String s) {
        s = s.trim();
        if (s.length() == 0){
            return false;
        }
        boolean isNum = false;
        boolean isPoint = false;
        boolean isEore = false;
        char[] chars = s.toCharArray();
        for (int i = 0 ; i < chars.length ; i++){
            if (chars[i] >= '0' && chars[i] <= '9'){
                isNum = true;
            }else if (chars[i] == '.'){
                if (isEore || isPoint){
                    return false;
                }
                isPoint = true;
            }else if (chars[i] == 'e' || chars[i] == 'E'){
                if (isEore || !isNum){
                    return false;
                }
                isNum = false;
                isEore = true;
            }else if (chars[i] == '+' || chars[i] == '-'){
                if (i > 0 && chars[i-1] != 'E' && chars[i-1] != 'e'){
                    return false;
                }
            }else {
                return false;
            }
        }
        return isNum;
    }

    // 剑指offer 63
    // 股票的最大利润
    // 递推
    public int maxProfit(int[] prices) {
        if (prices.length<2){
            return 0;
        }
        int max = 0;
        int min = prices[0];
        for (int i = 1 ; i < prices.length ; i++){
            max = Math.max(max,prices[i]-min);
            min = Math.min(min,prices[i]);
        }
        return max;
    }

    // 剑指offer 47
    // 礼物的最大价值
    // 一般的动态规划 ，递推
    public int maxValue(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        int len1 = grid.length;
        int len2 = grid[0].length;
        int[][] dp = new int[len1][len2];
        for (int i = 0 ;i < len1 ;i++ ){
            for (int j = 0 ; j < len2 ; j++ ){
                if (i == 0 && j == 0){
                    dp[i][j] = grid[i][j];
                }else if (i == 0){
                    dp[i][j] = dp[i][j-1] + grid[i][j];
                }else if ( j== 0){
                    dp[i][j] = dp[i-1][j] + grid[i][j];
                }else {
                    dp[i][j] = grid[i][j] + Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[len1-1][len2-1];
    }

    // 剑指offer 12
    // 矩阵中的路径
    // 经典深度优先搜素

    char[][] board;
    char[] chars;
    public boolean exist(char[][] board, String word) {
        this.board = board;
        chars = word.toCharArray();
        for (int i = 0 ;i <board.length;i++ ){
            for (int j = 0 ;j <board[i].length ; j++ ){
                if (dfs(0,i,j)){
                    return true;
                }
            }
        }
        return false;
    }
    boolean dfs(int k,int i,int j){
        if (i < 0 || i >= board.length || j >= board[0].length || j < 0){
            return false;
        }
        if (board[i][j] == chars[k]){
            if (k == chars.length - 1){
                return true;
            }
            char temp = board[i][j];
            board[i][j] = '/';
            boolean ans = dfs(k+1,i+1,j) || dfs(k+1,i-1,j) || dfs(k+1,i,j+1) || dfs(k+1,i,j-1);
            board[i][j] = temp;
            return ans;
        }
        return false;
    }

    // 剑指offer 64
    // 求1 + 2 + 。。。 + n
    // 不能用乘除以及 关键词

    public int sumNums(int n) {
        boolean b =  n > 1 && (n += sumNums(n - 1 ) )> 0 ;
        return n;
    }

    // 剑指offer 26
    // 树的子结构

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null){
            return false;
        }
        return isSubStructure(A.left,B) || isSubStructure(A.right,B) || isEqual(A,B);
    }
    boolean isEqual(TreeNode A,TreeNode B){
        if ( A== null && B == null){
            return true;
        }else if (A == null && B != null){
            return false;
        }else if (A!=null && B == null){
            return true;
        }else {
            return A.val == B.val && isEqual(A.right,B.right) && isEqual(A.left,B.left);
        }
    }

    // 剑指offer 31
    // 栈的压入、弹出序列
    // 模拟栈
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for (int k : pushed) {
            stack.push(k);
            while (!stack.isEmpty() && stack.peek() == popped[j]) {
                j++;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    // 剑指offer 66
    // 构建乘积数组
    // 没啥好说的，数组操作
    public int[] constructArr(int[] a) {
        int[] nums1 = new int[a.length];
        Arrays.fill(nums1,1);
        int temp = 1;
        for (int i = 1 ;i < a.length ; i++){
            temp *= a[i-1];
            nums1[i] = temp;
        }
        temp = 1;
        for (int i = a.length-2 ; i >= 0 ; i--){
            temp *= a[i+1];
            nums1[i] *= temp;
        }
        return nums1;
    }

    // 剑指offer 13
    // 机器人的运动范围

    public int movingCount(int m, int n, int k) {
        boolean[][] dp = new boolean[m][n];
        dp[0][0] = true;
        int res = 0;
        for (int i = 0 ; i < m ; i++){
            for (int j = 0 ; j < n ; j ++ ){
                if (count(i) + count(j) <= k ){
                    if (i > 0){
                        dp[i][j] |= dp[i-1][j];
                    }
                    if (j > 0){
                        dp[i][j] |= dp[i][j-1];
                    }
                }
                if (dp[i][j] ){
                    res++;
                }
            }
        }
        return res;
    }
    int count(int x){
        int res = 0 ;
        while (x!=0){
            res += x%10;
            x /= 10;
        }
        return res;
    }

    // 剑指offer 48
    // 最长的不含重复字符的字符串
    // 利用Set 很简单的做法
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        if (len == 0){
            return 0;
        }
        char[] chars = s.toCharArray();
        Set<Character> set = new HashSet<>();
        int res = 0;
        int j = 0;
        for(int i = 0; i < len ; i++){
            while (set.contains(chars[i])){
                set.remove(chars[j++]);
            }
            set.add(chars[i]);
            res = Math.max(res,i-j+1);
        }
        return res;
    }

    // 剑指offer 56-I
    // 数组中数字出现的次数

    // 位运算
    public int[] singleNumbers(int[] nums) {
        int[] res =new int[2];
        int x = 0;
        for (int num:nums){
            x ^= num;
        }
        int temp = 1;
        while ((temp & x) == 0){
            temp = temp << 1;
        }
        for (int num:nums){
            if ((num &temp ) == 0 ){
                res[0] ^=num;
            }else {
                res[1] ^=num;
            }
        }
        return res;
    }

    // 剑指offer 56- II
    // 数组中数字出现的次数II

    // 长度为32 的数组计数，每位模3得到这就是
    public int singleNumber(int[] nums) {
        int[] byt = new int[32];
        for (int num:nums){
            for (int i = 0 ;i < 32 ;i++ ){
                byt[i] += num&1 ;
                num >>>= 1;
            }
        }
        int res=0;
        for (int i = 31 ; i >= 0 ; i--){
            res <<= 1 ;
            res |= byt[i]%3;
        }
        return res;
    }

    // 剑指offer 44
    // 数字序列中某一位的数字
    public int findNthDigit(int n) {
        int digit = 1;
        long start = 1;
        long count = 9;
        while (n > count){
            n -= count;
            digit ++;
            start *= 10;
            count = digit*start*9;
        }
        long num = start + (n-1)/digit;
        return Long.toString(num).charAt((n-1)%digit) - '0';
    }

    // 剑指offer 16
    // 数值的整数次方
    // 最大幂法
    public double myPow(double x, int n) {
        long m = n;
        if (n < 0){
            x = 1 / x;
            m = -m;
        }
        double res = 1;
        while (m > 0){
            if (m % 2 == 1){
                res *= x;
            }
            x = x*x;
            m/=2;
        }
        return res;
    }

    // 剑指offer 45
    // 把数组排成最小的数
    // 采用lamaba 表达式
    public String minNumber0(int[] nums) {
        String[] strings = new String[nums.length];
        for (int i = 0 ; i < nums.length ; i++ ){
            strings[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strings,(x,y)->(x+y).compareTo(y+x));
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : strings){
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }

    // 手动写快速排序
    public String minNumber(int[] nums) {
        List<String> strings = new ArrayList<>();
        for (int num:nums){
            strings.add(String.valueOf(num));
        }
        quickSort(strings);
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : strings){
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }
    public void  quickSort(List<String> list){
        if (list.size()>1){
            List<String> small = new ArrayList<>();
            List<String> normal = new ArrayList<>();
            List<String> larger = new ArrayList<>();
            String temp = list.get(0);
            for (String str:list){
                if (str.equals(temp)){
                    normal.add(str);
                }else if ((str+temp).compareTo(temp+str) > 0){
                    larger.add(str);
                }else {
                    small.add(str);
                }
            }
            quickSort(small);
            quickSort(larger);
            list.clear();
            list.addAll(small);
            list.addAll(normal);
            list.addAll(larger);
        }
    }

    // 剑指offer 67
    // 把字符串转换成整数
    // 一般方法，没有特别的方法
    public int strToInt(String str) {
        char[] chars = str.trim().toCharArray();
        if (chars.length == 0){
            return 0;
        }
        int res = 0;
        int bndry = Integer.MAX_VALUE / 10;
        int i= 1;
        boolean sign = true;
        if (chars[0] == '-'){
            sign = false;
        }
        if (chars[i] != '+'){
            i = 0;
        }
        for (int j = i ; j < chars.length ; j ++){
            if (chars[j] < '0' || chars[j] >'9'){
                break;
            }
            if (res > bndry || res ==bndry&&chars[j]>'7'){
                return sign ? Integer.MAX_VALUE:Integer.MIN_VALUE;
            }
            res = res * 10 + chars[j] -'0';
        }
        return sign?res:-res;
    }

    // 剑指 offer 38
    // 字符串排列
    // 深度优先搜索
    List<String> ans;
    char[] c;
    public String[] permutation(String s) {
        ans = new ArrayList<>();
        c = s.toCharArray();
        dfs(0);
        return ans.toArray(new String[ans.size()]);
    }
    void dfs(int x){
        if (x == c.length - 1 ){
            ans.add(new String(c));
            return;
        }
        Set<Character> set = new HashSet<>();
        for (int i = x ; i < c.length ; i++ ){
            if (set.contains(c[i])){
                continue;
            }
            set.add(c[i]);
            swap(c,x,i);
            dfs(x+1);
            swap(c,x,i);
        }
    }
    void swap(char[] c,int i,int j){
        char temp = c[i];
        c[i] = c[j];
        c[j] = temp;
    }

    // 剑指offer 35
    // 复杂链表的复制
    // 采用HashMap表方法，可以 ， 但是有点简单
    public Node copyRandomList0(Node head) {
        if (head == null){
            return null;
        }
        Node pos = head;
        Map<Node,Node> map = new HashMap<>();
        while (pos != null){
            map.put(pos,new Node(pos.val));
            pos = pos.next;
        }
        pos = head;
        while (pos != null){
            map.get(pos).next = map.get(pos.next);
            map.get(pos).random = map.get(pos.random);
            pos = pos.next;
        }
        return map.get(head);
    }
    // 指针操作.
    // 将每一个链表在结点的位置复制一下。链表相当于翻倍
    // 然后再对链表进行拆分
    public Node copyRandomList(Node head) {
        if (head == null){
            return null;
        }
        Node pos = head;
        // 将链表的当前节点，进行翻倍
        while (pos != null){
            Node temp = new Node(pos.val);
            temp.next = pos.next;
            pos.next = temp;
            pos = temp.next;
        }
        // 复制每个新节点的random指向
        pos = head;
        while (pos != null){
            if (pos.random != null){
                pos.next.random = pos.random.next;
            }
            pos = pos.next.next;
        }
        // 拆分链表
        pos = head.next;
        Node pre = head;
        Node res = head.next;
        while (pos.next != null){
            pre.next = pre.next.next;
            pos.next = pos.next.next;
            pre = pre.next;
            pos = pos.next;
        }
        pre.next = null;
        return res;
    }


    // 剑指offer 14 -II
    // 剪绳子
    // 数学 往三靠
    public int cuttingRope(int n) {
        if (n == 2){
            return 1;
        }
        if (n == 3){
            return 2;
        }
        if (n == 4){
            return 4;
        }
        long res = 1;
        int mod = 1000000007;
        while (n > 4){
            res = (res*3)%mod;
            n -=3;
        }
        return (int)(n*res%mod);
    }

    // 剑指offer  14 -I
    // 剪绳子
    // 数学往三上靠
    public int cuttingRope1(int n) {
        if (n == 2){
            return 1;
        }
        if (n == 3){
            return 2;
        }
        if (n == 4){
            return 4;
        }
        int res = 1;
        while (n > 4){
            n-=3;
            res *= 3;
        }
        return res;
    }

    // 剑指offer 32-I
    // 从上到下打印二叉树
    // 层次遍历二叉树

    public int[] levelOrder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null){
            return new int[]{};
        }
        queue.add(root);
        while (!queue.isEmpty()){
            root = queue.poll();
            list.add(root.val);
            if (root.left != null){
                queue.add(root.left);
            }
            if (root.right != null){
                queue.add(root.right);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size() ;i ++) {
            res[i] = list.get(i);
        }
        return res;
    }

    // 剑指offer 32-III
    // 从上到下打印二叉树III
    // 采用双向队列
    public List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
       Queue<TreeNode> queue = new ArrayDeque<>();
        if (root != null){
            queue.add(root);
        }
        while (!queue.isEmpty()){
            int len = queue.size();
            LinkedList<Integer> temp = new LinkedList<>();
            for (int i = 0 ; i < len ; i++){
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

    //面试题 4
    // 二维数组中的查找
    // 正常查找
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0){
            return false;
        }
        int i = 0;
        int j = matrix[0].length-1;
        while (i >= 0 && i < matrix.length &&j >=0 && j <matrix[0].length){
            if (matrix[i][j] == target){
                return true;
            }else if (matrix[i][j] > target){
                j--;
            }else {
                i++;
            }
        }
        return false;
    }
    // 剑指offer 34
    // 二叉树中和为某一值的路径
    // 递归
    List<List<Integer>> res;
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        res = new ArrayList<>();
        recur(root,sum,new ArrayList<>());
        return res;
    }
    void recur(TreeNode root,int sum,List<Integer> list){
        if (root == null){
            return;
        }
        List<Integer> sublist = new ArrayList<>(list);
        sublist.add(root.val);
        if (root.right == null && root.left == null){
            if (root.val == sum){
                res.add(sublist);
            }
            return;
        }
        recur(root.right,sum-root.val,sublist);
        recur(root.left,sum-root.val,sublist);
    }

    // 剑指offer 33
    // 二叉搜索树的后续遍历序列
    int[] postorder;
    public boolean verifyPostorder(int[] postorder) {
        this.postorder = postorder;
        return solu(0,postorder.length-1);
    }
    boolean solu(int left,int right){
        if (left > right){
            return false;
        }
        int p = left;
        while (postorder[p]>postorder[right]){
            p++;
        }
        int pos = p;
        while (postorder[pos] < postorder[right]){
            pos ++ ;
        }
        return pos == right && solu(left,p-1) && solu(p,right-1);
    }

    // 剑指offer 36
    // 二叉搜索树与双向链表
    // 中序遍历指针操作
    TreeNode pre,head;
    public TreeNode treeToDoublyList(TreeNode root) {
        if (root == null ){
            return null;
        }
        dfs(root);
        head.left = pre;
        pre.right = head;
        return head;
    }
    void dfs(TreeNode cur){
        if (cur == null){
            return;
        }
        dfs(cur.left);
        if (pre != null){
            pre.right = cur;
        }else {
            head = cur;
        }
        cur.left = pre;
        pre = cur;
        dfs(cur.right);
    }

    // 剑指offer 49
    // 丑数
    public int nthUglyNumber(int n) {
        int a = 0;
        int b = 0;
        int c = 0;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1 ;i < n ; i++ ){
            int n2 = dp[a]*2;
            int n3 = dp[b]*3;
            int n5 = dp[c]*5;
            dp[i] = Math.min(n2,Math.min(n3,n5));
            if (dp[i] == n2){
                a++;
            }
            if (dp[i] == n3){
                b++;
            }
            if (dp[i] == n5){
                c++;
            }
        }
        return dp[n-1];
    }

    // 面试题 60
    // N个骰子的点数
    // 动态规划

    public double[] dicesProbability(int n) {
        double[] pre = {1/6f,1/6f,1/6f,1/6f,1/6f,1/6f};
        for (int i = 2 ;i <= n ; i++){
            double[] temp = new double[i*5+1];
            for (int j = 0 ; j < pre.length ; j++ ){
                for (int m = 0; m < 6 ;m++){
                    temp[j + m] += pre[j]/6;
                }
            }
            pre = temp;
        }
        return pre;
    }






}