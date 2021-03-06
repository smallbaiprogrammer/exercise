package com.company.leetcode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListNode {
    int val;
    ListNode next=null;
    public ListNode(int val){
        this.val=val;
    }
    public ListNode(int val,ListNode next){
        this.val=val;
        this.next=next;
    }
    // 链表反转
    public static ListNode reverseList(ListNode head){
        ListNode pre = null;
        ListNode cur = head;
        while (cur!=null){
            ListNode temp = cur.next;
            cur.next=pre;
            pre=cur;
            cur=temp;
        }
        return pre;
    }
    // 倒数第k个结点
    public static ListNode fLast(ListNode head,int k){
        ListNode f1 = head;
        ListNode f2 = head;
        while (k>0){
            f2=f2.next;
            k--;
        }
        while (f2!=null){
            f1=f1.next;
            f2=f2.next;
        }
        return f1;
    }
    // 是否存在环
    public static boolean hasCycle1(ListNode head){
        // 使用hashSet
        ListNode root = head;
        Set<ListNode> set = new HashSet<>();
        while(root!=null){
            if (set.contains(root)){
                return true;
            }else {
                set.add(root);
            }
            root=root.next;
        }
        return false;
    }
    public static boolean hasCycle2(ListNode head){
        //快慢指针
        ListNode fast = head;
        ListNode slow = head;
        while (fast!=null&&fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if (fast==slow){
                return true;
            }
        }
        return false;
    }
    // 返回环所在的位置
    // 同样存在两种方法，Set和快慢指针
    public ListNode detectCycle(ListNode head) {
        if (head==null){
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (true){
            if(fast==null||fast.next==null){
                return null;
            }
            fast =fast.next.next;
            slow = slow.next;
            if (fast==slow){
                break;
            }
        }
        fast = head;
        while (fast!=slow){
            fast=fast.next;
            slow=slow.next;
        }
        return fast;
    }
    // 合并链表
    public static ListNode mergeList(ListNode l1,ListNode l2){
        if (l1==null){
            return l2;
        }
        if (l2==null){
            return l1;
        }
        if (l1.val<l2.val){
            l1.next=mergeList(l1.next,l2);
            return l1;
        }else {
            l2.next=mergeList(l2.next,l1);
            return l2;
        }
    }
    // 删除链表中的结点
    public static void delete(ListNode head,int i){
        ListNode root = new ListNode(-1);
        root.next = head;
        ListNode pos0= root;
        ListNode pos1 = head;
        while (i!=0){
            pos0=pos0.next;
            pos1=pos1.next;
            i--;
        }
        pos0.next=pos1.next;
    }
    // 两两交换链表中的节点

    /// 啊啊啊啊啊啊啊啊啊
    public ListNode swapPairs(ListNode head) {
        ListNode root = new ListNode(-1);
        root.next = head;
        ListNode pos = root;
        while (pos.next!=null&&pos.next.next!=null){
            ListNode tem0 = pos.next;
            ListNode tem1 = pos.next.next;
            pos.next =tem1;
            tem0.next = tem1.next;
            tem1.next=tem0;
            pos = tem0;
        }
        return root.next;
    }

    // 两个链表的第一个公共结点
    public static ListNode firstPub(ListNode l1,ListNode l2){
        ListNode head1 = l1;
        ListNode head2 = l2;
        while (head1!=head2){
            head1=head1.next!=null?head1.next:l2;
            head2=head2.next!=null?head2.next:l1;
        }
        return head1;
    }
    // 链表是否存在公共结点
    public static boolean hasPub(ListNode l1,ListNode l2){
        ListNode head1 = l1;
        ListNode head2 = l2;
        while (head1!=null||head2!=null){
            if (head1==head2){
                return true;
            }
            head1=head1.next!=null?head1.next:l2;
            head2=head2.next!=null?head2.next:l1;
        }
        return false;
    }
    // 链表排序
    // 链表求和
    public static void show(ListNode head){
        ListNode root=head;
        while (root!=null){
            System.out.print(root.val+" ");
            root=root.next;
        }
    }
    public ListNode deleteDuplicates(ListNode head) {
        ListNode current = head;
        while (current != null && current.next != null) {
            if (current.next.val == current.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }




    public static void main(String[] args) {
        ListNode f = new ListNode(5);
        ListNode fo = new ListNode(4);
        ListNode th = new ListNode(3);
        ListNode se = new ListNode(2);
        ListNode fi = new ListNode(1);
        f.next=fo;
        fo.next=th;
        th.next=se;
        se.next=fi;
        ListNode head =new ListNode(-1);
        head.next=th;
        System.out.println(hasPub(f,head));
    }
}
