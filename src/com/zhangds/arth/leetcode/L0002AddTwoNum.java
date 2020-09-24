package com.zhangds.arth.leetcode;

import com.zhangds.arth.leetcode.base.ListNode;

import java.util.List;

/**
 * 有两个单链表，代表两个非负数，每一个节点代表一个数位，数字是反向存储的，
 * 即第一个结点表示最低位，最后一个结点表示最高位。求两个数的相加和，并且以链表形式返回。
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 *
 * @author zhangds
 * @date 2020/8/5 14:30
 */
public class L0002AddTwoNum {

    public static void main(String[] args) {
        ListNode listNode = new ListNode();
        ListNode link1 = listNode.createData(new Integer[]{4, 7, 4, 3});
        ListNode link2 = listNode.createData(new Integer[]{3, 5, 6, 8});
        ListNode<Integer> res = addTwoNums(link1, link2);
        listNode.print(res);
    }

    /*
    对两个链表都从第一个开始处理，进行相加，结果再除以10求商，作为下一位相加的进位，
    同时记录余数，作为本位的结果，一直处理，直到所有的结点都处理完。
     */
    public static ListNode<Integer> addTwoNums(ListNode<Integer> l1, ListNode<Integer> l2){
        //三个链表指向头节点
        ListNode<Integer> res = new ListNode(0); //头节点

        //需要创建一个游标节点指向链表，遍历操作链表节点
        ListNode<Integer> n1 = l1;
        ListNode<Integer> n2 = l2;
        ListNode<Integer> tmp = res;

        //对两个传入的链表进行相加，商为进位，余为本位
        int sum = 0;
        while (n1 != null || n2 != null){
            if (n1 != null){
                sum += n1.data;
                n1 = n1.next;
            }
            if (n2 != null){
                sum += n2.data;
                n2 = n2.next;
            }

            System.out.println("余" + sum%10 + "进" + sum/10);
            tmp.next = new ListNode(sum%10);
            sum = sum/10;
            tmp = tmp.next; //游标指向下一个节点，操作下一个节点
        }
        // 最后一位有进位
        if (sum != 0){
            tmp.next = new ListNode(1);
        }

        return res.next; //不返回为0的头节点
        /*ListNode<Integer> node = new ListNode(0);
        ListNode<Integer> n1 = l1, n2 = l2, t = node;
        int sum = 0;
        while (n1 != null || n2 != null) {
            sum /= 10;
            if (n1 != null) {
                sum += n1.data;
                n1 = n1.next;
            }
            if (n2 != null) {
                sum += n2.data;
                n2 = n2.next;
            }
            t.next = new ListNode(sum % 10);
            t = t.next;
        }
        if (sum / 10 != 0) t.next = new ListNode(1);
        return node.next;*/
    }
}
