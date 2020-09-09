package com.zhangds.arth.leetcode.base;

public class ListNode<T> {
    public T data;
    public ListNode next;

    public ListNode(T data) {
        this.data = data;
    }

    public ListNode(T data, ListNode next) {
        this.data = data;
        this.next = next;
    }

    public ListNode() {
    }

    public ListNode createData(T[] dataArr){
        ListNode[] listNode =new ListNode[dataArr.length];
        listNode[0] = new ListNode(dataArr[0]); //头节点
        for (int i=1; i<dataArr.length; i++){
            listNode[i] = new ListNode(dataArr[i]);
            listNode[i-1].next = listNode[i];
        }
        //返回头节点
        return listNode[0];
    }

    public void print(ListNode listNode){
        if (listNode == null){
            System.out.println("null");
            return;
        }
        StringBuilder sb = new StringBuilder("[");
        ListNode p = listNode;
        while (p != null){
            sb.append(p.data.toString()).append(",");
            p = p.next;
        }
        int len = sb.length();
        System.out.println(sb.replace(len-1, len, "]").toString());
    }
}
