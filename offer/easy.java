package com.company.offer;

import sun.reflect.generics.tree.Tree;

import java.util.*;

public class easy {
    /**
     * 链表类
     */
    class ListNode{
        int val;
        ListNode next;
        public ListNode(){

        }
        public ListNode(int val){
            this.val=val;
        }
    }

    /**
     * 二叉树类
     */

    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val){
            this.val=val;
        }
    }

    // 剑指offer 03
    // 数组中重复的数字
    // 采用HashSet 或者 原地置换
    // 第一种方法 采用HashSet
    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i:nums){
            if (set.contains(i)){
                return i;
            }else {
                set.add(i);
            }
        }
        return -1;
    }
    // 采用原地置换的方法，因为数组中元素的范围是0-（n-1），所以如果排序之后，应该每个元素的值等于下标值
    // 这个方法，是速度最快的方法
    public int refindRepeatNumber(int[] nums) {
        for (int i=0;i<nums.length;i++){
            while (nums[i]!=i){
                if (nums[i]==nums[nums[i]]){
                    return nums[i];
                }
                swap(nums,i,nums[i]);
            }
        }
        return -1;
    }
    public void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }

    /*
    *****************************************************************************************
     */

    // 剑指offer 04
    // 二维数组中的查找
    // 二维数组中查找，每一行左右递增，每一列上下递增，时间复杂度O（n）
    // 可以使用暴力法，初级做法
    // 然后是这个高级做法 双边查找，当数组为一维数组时，时间与线性查找一样
    // 所以存在这个一种四分查找，在数组中间作为起点，开始查找，四个区间，每次排除掉一个区间，然后在剩下的三个区间再依次进行查找
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if(matrix.length==0||matrix[0].length==0){
            return false;
        }
        int len = matrix[0].length;
        int i=0;
        int j=len-1;
        while(j<len&&j>=0&&i<matrix.length&&i>=0){
            if(matrix[i][j]==target){
                return true;
            }else if(target>matrix[i][j]){
                i++;
            }else{
                j--;
            }
        }
        return false;
    }

    /*
     ****************************************************************************************
     */

    //剑指offer 05
    // 替换空格
    // 将空格替换为%20
    // 可以直接使用替换函数replac，速度最快，空间最小
    public String replaceSpace1(String s) {
        return s.replace(" ","%20");
    }
    public String replaceSpace(String s){
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = s.toCharArray();
        for (char c:chars){
            if (c!=' '){
                stringBuilder.append(c);
            }else {
                stringBuilder.append("%20");
            }
        }
        return stringBuilder.toString();
    }

    /*
    *************************************************************************
     */

    // 剑指offer 06
    // 从尾到头打印链表
    // 采用递归 可以采用栈来实现，如果链表过长的话，可能出现栈溢出的情况

    // 也可以采用栈来实现
    List<Integer> ans = new LinkedList<>();
    public int[] reversePrint(ListNode head){
        solu(head);
        int[] temp = new int[ans.size()];
        for (int i=0;i<temp.length;i++){
            temp[i]=ans.get(i);
        }
        return temp;
    }
    public void solu(ListNode head){
        if (head==null){
            return;
        }else {
            solu(head.next);
            ans.add(head.val);
        }
    }


    // 剑指offer 09
    /**
     * 采用两个栈实现队列
     */
    class CQueue{
        Stack<Integer> A = new Stack<>();
        Stack<Integer> B = new Stack<>();
        public void appendTail(int value){
            A.push(value);
        }
        public int deleteHead(){
            if (!B.isEmpty()){
                return B.pop();
            }
            if (A.isEmpty()) {
                return -1;
            }
            while (!A.isEmpty()){
                B.add(A.pop());
            }
            return B.pop();
        }
    }

    /**
     * ************************************************************
     */

    // 剑指offer 10-I
    // 斐波那契数列
    // 第一种方法，递推公式，动态规划思想
    public int fib1(int n) {
        if (n==0){
            return 0;
        }
        if (n==1){
            return 1;
        }
        int[] dp = new int[n+1];
        dp[0]=0;
        dp[1]=1;
        int mod = 1000000007;
        for (int i=2;i<=n;i++){
            dp[i]=(dp[i-1]+dp[i-2])%mod;
        }
        return dp[n];
    }
    // 第二种方法
     // 同样是动态规划思想，只不过只存储两个中间值
    public int fib2(int n) {
        if (n==0){
            return 0;
        }
        if (n==1){
            return 1;
        }
        int a = 0;
        int b = 1;
        int temp=0;
        int mod = 1000000007;
        for (int i=2;i<=n;i++){
            temp = (a+b)%mod;
            a=b;
            b=temp;
        }
        return temp;
    }
    // 第三种方法，采用斐波那契数列的通项公式，需要求出来，采用矩阵的形式[[1,1][1,0]] 连乘的情况得出的，只需要带入一个公式就饿可以求出
    // 但是因为会有存在根号5的值，所以存在误差，但是可以直接采用矩阵计算就不会有误差，需要定义矩阵运算，然后乘以n次，取出对于项

    // 剑指offer 10-II
    // 青蛙跳台阶问题，采用了动态规划的思想，同样是斐波那契数列，
    // 也就是跳到第n级台阶的跳法等于第n-1级台阶的跳法和第n-2级台阶的跳法
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

    /**
     * ***********************************************************************************
     */

    // 剑指offer 11
    // 旋转数组的最小数字
    //采用二分法进行查找
    // 当然也可以采用遍历来做，但是会浪费旋转数组的这个条件
    public int minArray(int[] numbers) {
        int len  = numbers.length;
        int i=0;
        int j=len-1;
        while (i<j){
            int mid = i + (i-j)/2;
            if (numbers[mid]>numbers[j]){
                i=mid+1;
            }else if (numbers[mid]<numbers[j]){
                j=mid;
            }else {
                j--;
            }
        }
        return numbers[i];
    }

    /**
     * ************************************************************************
     */

    // 剑指offer 15
    // 一个数二进制中表示一个个数
    // 可以采用位运算，也可以转为字符串
    // 位运算 速度较快
    public int hammingWeight1(int n){
        int sum=0;
        while (n!=0){
            if ((n&1)==1){
                sum++;
            }
            n>>>=1;
        }
        return sum;
    }
    // 字符运输，速度较慢
    public int hammingWeight(int n){
        char[] s = Integer.toBinaryString(n).toCharArray();
        int sum = 0;
        for (int i=0;i<s.length;i++){
            if (s[i]=='1'){
                sum++;
            }
        }
        return sum;
    }

    /**
     * ************************************************************
     */

    // 剑指offer 18
    // 删除链表的某个节点
    public ListNode deleteNode(ListNode head, int val) {
        ListNode root = new ListNode(-1);
        root.next = head ;
        ListNode pos0 = root;
        ListNode pos1 = head;
        while (pos1!=null){
            if (pos1.val==val){
                pos0.next = pos1.next;
                break;
            }
            pos0 = pos0.next;
            pos1 = pos1.next;
        }
        return root.next;
    }

    /**
     * ************************************************************
     */

    // 剑指offer 21
    // 调整数组顺序使奇数位于偶数前面
    // 双指针，交换前后元素，一遍遍历
    public int[] exchange(int[] nums) {
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

    /**
     * ************************************************************
     */

    // 剑指offer 22
    // 链表倒数第k个节点
    // 双指针
    // 前后指针
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode front=head;
        ListNode after=head;
        for (int i=0;i<k;i++){
            after=after.next;
        }
        while (after!=null){
            front=front.next;
            after=after.next;
        }
        return front;
    }

    /**
     * ************************************************************
     */

    // 剑指offer24
    // 反转链表
    // 利用指针对链表进行操作反转
    public ListNode reverseList1(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    // 还可以采用递归进行操作
    public ListNode reverseList(ListNode head){
        if(head==null || head.next == null){
            return head;
        }
        ListNode cur = reverseList(head.next);
        head.next.next = cur;
        head.next = null;
        return cur;
    }

    /**
     * ***************************************************************************
     */

    //剑指offer 25
    // 合并两个有序链表
    // 采用递归是最简单的方法
    public ListNode mergeTwoLists(ListNode l1,ListNode l2){
        if (l1 == null){
            return l2;
        }
        if (l2 == null){
            return l1;
        }
        if (l1.val< l2.val){
            l1.next = mergeTwoLists(l1.next,l2);
            return l1;
        }else {
            l2.next = mergeTwoLists(l2.next,l1);
            return l2;
        }
    }

    /**
     * ***************************************************************************
     */

    // 剑指offer 27
    // 二叉树的镜像
    // 采用递归的方法
    public TreeNode mirrorTree(TreeNode root) {
        if (root==null){
            return root;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        root.left = mirrorTree(root.left);
        root.right = mirrorTree(root.right);
        return root;
    }

    // 剑指offer 28
    // 对称的二叉树
    // 同样是递归做法
    public boolean isSymmetric(TreeNode root) {
        if (root==null){
            return true;
        }
        return isSymmetric(root.right,root.left);
    }
    public boolean isSymmetric(TreeNode root1,TreeNode root2){
        if (root1==null&&root2==null){
            return true;
        }else if (root1==null||root2==null||root1.val!=root2.val){
            return false;
        }else {
            return isSymmetric(root1.right,root2.left)&&isSymmetric(root1.left,root2.right);
        }
    }

    /**
     * ****************************************************************************
     */

    // 剑指offer 29
    // 顺时针打印矩阵
    // 恶心的暴力法，最讨厌的方法，没有之一
        public int[] spiralOrder1(int[][] matrix) {
        if(matrix.length==0||matrix[0].length==0){
            return new int[0];
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0;
        int right = n-1;
        int top = 0;
        int bottom = m-1;
        int[] res = new int[m*n];
        int k=0;
        while(left<=right&&top<=bottom){
            for(int i=left;i<=right;i++){
                res[k++]=matrix[top][i];
            }
            for(int i=top+1;i<=bottom;i++){
                res[k++]=matrix[i][right];
            }
            if(left<right&&top<bottom){
                for(int i=right-1;i>left;i--){
                    res[k++]=matrix[bottom][i];
                }
                for(int i=bottom;i>top;i--){
                    res[k++]=matrix[i][left];
                }

            }
            left++;
            right--;
            top++;
            bottom--;
        }

        return res;
    }



    /**
     * ****************************************************************************
     */

    // 剑指offer 30
    // 包含min函数的栈
    // 采用辅助栈的方法
    class MinStack {
        Stack<Integer> A,B;
        public MinStack() {
            A = new Stack<>();
            B = new Stack<>();
        }

        public void push(int x) {
            A.push(x);
            if (B.isEmpty()||x<=B.peek()){
                B.push(x);
            }
        }

        public void pop() {
            if (A.pop().equals(B.peek())){
                B.pop();
            }
        }

        public int top() {
            return A.peek();
        }

        public int min() {
            return B.peek();
        }
    }

    /**
     * ****************************************************************************
     */

    // 剑指offer32-II
    // 层次遍历二叉树II
    // 采用迭代的方式进行遍历，同时也可以采用递归的形式进行遍历
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> temp = new ArrayDeque<>();
        List<List<Integer>> ans = new LinkedList<>();
        temp.add(root);
        while (!temp.isEmpty()){
            List<Integer> tem = new LinkedList<>();
            for (int i= temp.size();i>0;i--){
                root = temp.poll();
                tem.add(root.val);
                if (root.left!=null){
                    temp.add(root.left);
                }
                if (root.right!=null){
                    temp.add(root.right);
                }
            }
            ans.add(tem);
        }
        return ans;
    }

    /**
     * ****************************************************************************
     */

    // 剑指offer 39
    // 数组中出现次数超过一半的数字
    // 我们采用摩尔技术法,一遍遍历
    public int majorityElement(int[] nums) {
        int x = nums[0];
        int count = 0;
        for (int i:nums){
            if (i==x){
                count++;
                continue;
            }
            if (count==0) {
                x=i;
                count++;
                continue;
            }
            count--;
        }
        return x;
    }
    // 还可以采用哈希表

    /**
     * *****************************************************
     */

    // 剑指offer 40
    // 最小的k的数
    // 第一种方法，对数组进行排序，然后取出后面的k个数
    public int[] getLeastNumbers1(int[] arr, int k) {
        Arrays.sort(arr);
        int[] vec = new int[k];
        for (int i = 0; i <k; ++i) {
            vec[i] = arr[i];
        }
        return vec;
    }
    // 除此之外 ， 还可以采用
    // 经典快排的思想
    // 写完发现也不是最优的解法，最优解法应该是采用堆结构
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0 || arr.length == 0) {
            return new int[0];
        }
        // 最后一个参数表示我们要找的是下标为k-1的数
        return quickSearch(arr, 0, arr.length - 1, k - 1);
    }

    private int[] quickSearch(int[] nums, int lo, int hi, int k) {
        // 每快排切分1次，找到排序后下标为j的元素，如果j恰好等于k就返回j以及j左边所有的数；
        int j = partition(nums, lo, hi);
        if (j == k) {
            return Arrays.copyOf(nums, j + 1);
        }
        // 否则根据下标j与k的大小关系来决定继续切分左段还是右段。
        return j > k? quickSearch(nums, lo, j - 1, k): quickSearch(nums, j + 1, hi, k);
    }

    // 快排切分，返回下标j，使得比nums[j]小的数都在j的左边，比nums[j]大的数都在j的右边。
    private int partition(int[] nums, int lo, int hi) {
        int v = nums[lo];
        int i = lo, j = hi + 1;
        while (true) {
            while (++i <= hi && nums[i] < v) {
                ;
            }
            while (--j >= lo && nums[j] > v) {
                ;
            }
            if (i >= j) {
                break;
            }
            int t = nums[j];
            nums[j] = nums[i];
            nums[i] = t;
        }
        nums[lo] = nums[j];
        nums[j] = v;
        return j;
    }


    // 也可以采用堆
    // 比较好的方式
    // 每次去一个最小值或者一个最大值，时间是logN ，总体的时间复杂度就是KlogN

    /**
     * *****************************************************
     */

    // 剑指offer 42
    // 连续子序数的最大的和
    // 动态处理
    public int maxSubArray(int[] nums) {
        int res = nums[0] ;
        int temp = nums[0];
        for (int i=1;i<nums.length;i++){
            if (temp<0){
                temp=0;
            }
            temp+=nums[i];
            res = Math.max(res,temp);
        }
        return res;
    }

    /**
     * *****************************************************
     */

    // 剑指offer 50
    // 第一个只出现一次的字符
    // 最简单暴力的做法，利用库函数，遍历一遍，但是其实无需遍历一遍
    public char firstUniqChar1(String s) {
        if (s.equals("")){
            return ' ';
        }
        for (char i:s.toCharArray()){
            if (s.indexOf(i)==s.lastIndexOf(i)){
                return i;
            }
        }
        return ' ';
    }
    // 还是可以采用哈希表法来做，有序哈希表非常快就做出来了
    public char firstUniqChar(String s){
        char[] chars = s.toCharArray();
        Map<Character,Boolean> map = new LinkedHashMap<>();
        for (char c:chars){
            map.put(c,!map.containsKey(c));
        }
        for (Character obj:map.keySet()){
            if (map.get(obj)){
                return obj;
            }
        }
        return ' ';
    }

    /**
     * *****************************************************
     */

    // 剑指offer 52
    //两个链表的第一个公共节点
    // 标准做法
    public middle.ListNode getIntersectionNode(middle.ListNode headA, middle.ListNode headB) {
        middle.ListNode l1 = headA;
        middle.ListNode l2 = headB;
        while (l1!=l2){
            l1=l1==null?headB:l1.next;
            l2=l2==null?headA:l2.next;
        }
        return l1;
    }

    /**
     * *****************************************************
     */

    // 剑指offer 53 - I
    // 在排序数组中查找数字出现次数I
    // 二分查找，没啥好说的
    // 利用二分法找两边的边界
    // 最完美的二分查找
    public int search(int[] nums, int target) {
        int i = 0;
        int j = nums.length-1;
        while (i<=j){
            int mid = i+(j-i)/2;
            if (nums[mid]>=target){
                j=mid-1;
            }else {
                i=mid+1;
            }
        }
        int left = i;
        j=nums.length-1;
        while (i<=j){
            int mid = i+(j-i)/2;
            if (nums[mid]>target){
                j=mid-1;
            }else {
                i++;
            }
        }
        int right =j;
        return right-left+1;
    }
    // 当然还可以双指针从两边往中间走，各有个的好处，如果中间重复的数字特别多采用双指针好一点，
    // 特别少的话，可以找到一个数字然后向两边开始搜素
    // 我这里写的应该两种情况下都不错的方法

    /**
     * *****************************************************
     */


    // 剑指offer 53-II
    // 0 ~ n-1中缺失的数字
    // 简单快速的做法
    public int missingNumber1(int[] nums) {
        int len = nums.length;
        int sum = len*(len-1)/2;
        for (int i=0;i<len;i++){
            sum-=nums[i];
        }
        return sum;
    }
    // 二分查找也可以
    public int missingNumber(int[] nums) {
        int i = 0, j = nums.length - 1;
        while(i <= j) {
            int m = (i + j) / 2;
            if(nums[m] == m) {
                i = m + 1;
            } else {
                j = m - 1;
            }
        }
        return i;
    }

    /**
     * *****************************************************
     */

    // 剑指offer 54
    // 二叉搜索树的第K大节点
    // 暴力想法，中序遍历之后得到有效数组，然后返回 第 K大 节点
    // 优化 中序遍历的
    int res, k;
    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return res;
    }
    public void dfs(TreeNode root) {
        if(root == null) {
            return;
        }
        dfs(root.right);
        k--;
        if(k == 0) {
            this.res=res;
            return;
        }
        dfs(root.left);
    }

    /**
     * *****************************************************
     */

    // 剑指offer 55-I
    // 二叉树的深度
    // 标准递归
    public int maxDepth(TreeNode root) {
        if (root==null){
            return 0;
        }
        return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
    }

    /**
     * *****************************************************
     */

    //剑指offer 55-II
    // 平衡二叉树
    // 一般做法，可以采用上面的深度函数
    public boolean isBalanced(TreeNode root) {
        if (root==null){
            return true;
        }
        if (Math.abs(maxDepth(root.right)-maxDepth(root.left))>1){
            return false;
        }
        return isBalanced(root.right)&&isBalanced(root.left);
    }


    /**
     *  ***************************************************
     */

    // 剑指offer 57
    // 和为s的两个数字
    // 双指针
    public int[] twoSum1(int[] nums, int target) {
        if (nums.length==1){
            return new int[]{};
        }
        int i=0;
        int j=nums.length-1;
        while (i<j){
            if (nums[i]+nums[j]==target){
                return new int[]{nums[i],nums[j]};
            }else if (nums[i]+nums[j]>target){
                j--;
            }else {
                i++;
            }
        }
        return new int[]{};
    }
    // 哈希表,没用有序数组这个条件
    public int[] twoSum2(int[] nums, int target) {
        if (nums.length==1){
            return new int[]{};
        }
        Map<Integer,Integer> map = new HashMap<>();
        for (int i =0 ;i<nums.length;i++){
            if (map.containsKey(target-nums[i])){
                return new int[]{target-nums[i],nums[i]};
            }else {
                map.put(nums[i],i);
            }
        }
        return new int[]{};
    }

    /**
     * ******************************************************
     */

    // 剑指offer 57-II
    // 和为s的连续正整数序列
    public int[][] findContinuousSequence1(int target) {
        List<int[]> ans = new LinkedList<>();
        int i=1;
        int j=1;
        int sum=0;
        while(i<=target/2){
            if (sum<target){
                sum+=j;
                j++;
            }else if (j>target){
                sum-=i;
                i++;
            }else {
                int[] tem =new int[j-i];
                for (int m=i;m<j;m++){
                    tem[m-i]=m;
                }
                ans.add(tem);
                sum+=j;
                j++;
            }
        }
        return ans.toArray(new int[ans.size()][]);
    }

    // 超NB的做法
    // 神奇的脑子
    public int[][] findContinuousSequence(int target) {
        List<int[]> result = new ArrayList<>();
        int i = 1;
        while(target>0)
        {
            target -= i++;
            if(target>0 && target%i == 0)
            {
                int[] array = new int[i];
                for(int k = target/i, j = 0; k < target/i+i; k++,j++)
                {
                    array[j] = k;
                }
                result.add(array);
            }
        }
        Collections.reverse(result);
        return result.toArray(new int[0][]);
    }

    /**
     *  ***************************************************
     */

    // 剑指offer 58 -I
    // 反转单词顺序
    //  基本解法，没啥好说
    public String reverseWords(String s) {
        s = s.trim();
        // 删除首尾空格
        int j = s.length() - 1, i = j;
        StringBuilder res = new StringBuilder();
        while(i >= 0) {
            while(i >= 0 && s.charAt(i) != ' ') {
                i--;
                // 搜索首个空格

            }
            res.append(s.substring(i + 1, j + 1) + " ");
            // 添加单词
            while(i >= 0 && s.charAt(i) == ' ') {
                i--;
                // 跳过单词间空格
            }
            j = i;
            // j 指向下个单词的尾字符
        }
        // 转化为字符串并返回
        return res.toString().trim();
    }


    /**
     *  ***************************************************
     */

    // 剑指offer 58--II
    // 左转字符串
    // 最简单的方法
    public String reverseLeftWords1(String s, int n) {
        return s.substring(n)+s.substring(0,n);
    }
    //还可以直接采用字符串拼接，采用StringBuilder 和 StringBuffer

    /**
     *  ***************************************************
     */

    //剑指offer 59-I
    // 滑动窗口的最大值
    // 双指针

    public int[] maxSlidingWindow1(int[] nums, int k) {
        if(nums.length==0){
            return new int[]{};
        }
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
    // 可以用双向队列来做，只需要遍历一遍,但是难度从easy 变成了middle


    /**
     *  ***************************************************
     */

    // 剑指offer 60
    // n个筛子的点数
    // 动态规划 ，虽然为easy ，但是难度很大
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

    /**
     *  ***************************************************
     */

    // 剑指offer 61
    // 扑克牌中的顺子
    // 判断五个数是不是顺子
    // 先排序然后，对有序数组进行处理，大小王的数量代表允许出错的次数

    public static boolean isStraight1(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);
        int count = 0;
        for (int i=0;i<len-1;i++){
            if (nums[i]==0){
                count++;
            }else if (!(nums[i]==nums[i+1]-1)){
                //System.out.println(nums[i]+" "+nums[i+1]+" "+count);
                if (count==0){
                    return false;
                }
                count--;
                nums[i]++;
                i--;
            }
        }
        return true;
    }

    // 这是排序的思路，也可以采用不排序的思路找出数组中的除0之外的最大值和最小值

    public boolean isStraight(int[] nums) {
        int len = nums.length;
        int minNum = 14;
        int maxNum = 0;
        Set<Integer> set = new HashSet<>();
        for (int i=0;i<len;i++){
            if (nums[i]==0){
                continue;
            }else{
                maxNum=Math.max(maxNum,nums[i]);
                minNum=Math.min(minNum,nums[i]);
                if (set.contains(nums[i])){
                    return false;
                }else {
                    set.add(nums[i]);
                }
            }
        }
        return maxNum-minNum<5;
    }

    /**
     *  ***************************************************
     */

    // 剑指offer 62
    // 圈圈中最后剩下数子
    // 经典约瑟夫环的问题
    public int lastRemaining(int n, int m) {
        if(n==1){
            return 0;
        }
        return (lastRemaining(n-1,m)+m)%n;
    }

    /**
     *  ***************************************************
     */
    // 剑指offer 65
    // 不用加减乘除做加法
    // 第一时间想到位运算
    public int add(int a, int b) {
        while (b!=0){
            // c表示进位a+b
            int c = (a&b)<<1;
            // 用a来存储两数之和，不包含进位的
            a^=b;
            // 将进位的值赋给b再进行加法运算，进位为零时，跳出循环
            b=c;
        }
        return a;
    }



    // 剑指offer 68 - I
    // 二叉搜索树的最近共同祖先
    // 递归，很简单
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p,TreeNode q) {
        if (p.val>root.val&&q.val>root.val){
            return lowestCommonAncestor1(root.right,p,q);
        }else if (p.val<root.val&&q.val<root.val){
            return lowestCommonAncestor1(root.left,p,q);
        }else {
            return root;
        }
    }

    /**
     *  ***************************************************
     */

    // 剑指offer 68-II
    //  二叉树的最近公共最先
    public TreeNode lowestCommonAncestor(TreeNode root,TreeNode p, TreeNode q) {
        if (contain(root.right,p)&&contain(root.right,q)){
            return lowestCommonAncestor(root.right,p,q);
        }
        else if (contain(root.left,p)&&contain(root.left,q)){
            return lowestCommonAncestor(root.left,p,q);
        }else{
            return root;
        }

    }
    public boolean contain(TreeNode root,TreeNode p){
        if (root==null){
            return false;
        }
        if (root.val==p.val){
            return true;
        }
        return contain(root.left,p)||contain(root.right,p);
    }
}
