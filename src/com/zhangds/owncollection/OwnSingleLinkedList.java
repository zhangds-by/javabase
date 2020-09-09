package com.zhangds.owncollection;


public class OwnSingleLinkedList<T> {

    private class Node{
        T data;
        Node next;

        public Node() {
        }

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node head;

    private Node tail;

    private int size;

    //指定元素创建单链表
    public OwnSingleLinkedList(T element){
        Node node = new Node(element, null);
        head = tail;
        size++;
    }

    public OwnSingleLinkedList() {
    }

    public int length(){
        return size;
    }

    public T get(int index){
        return getNode(index).data;
    }

    //通过index获取指定的节点
    private Node getNode(int index){
        checkIndex(index);
        Node cur = head;
        for (int i=0; i<size && cur!=null; i++, cur = cur.next){
            if (i == index){
                return cur;
            }
        }
        return null;
    }

    private void checkIndex(int index){
        if (index<0 || index>size-1){
            throw new IndexOutOfBoundsException("越界");
        }
    }

    //获取指定元素的索引
    private int locate(T element){
        Node cur = head;
        for (int i=0; i<size && cur!=null; i++,cur = cur.next){
            if (cur.data.equals(element)){
                return i;
            }
        }
        return -1;
    }

    //向指定位置插入元素
    public void insert(T element, int index){
        checkIndex(index);
        if (head == null){
            addTail(element);
        }else {
            if (index == 0){
                addHead(element);
            }else {
                Node prev = getNode(index-1);
                prev.next = new Node(element, prev.next);
                size++;
            }
        }
    }


    //头插法添加节点
    public void addTail(T element){
        //创建新的节点并指向head，将新节点作为新的Head
        head = new Node(element, head);
        //空链表
        if (tail == null){
            tail = head;
        }
        size++;
    }

    //尾插法添加节点
    public void addHead(T element){
        Node newNode = new Node(element, null);
        //空链表
        if (head == null){
            tail = head = newNode;
        }
        if (head != null){
            //尾节点指向新节点，并将新的节点作为尾节点
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    //删除指定节点的索引
    public T delete(int index){
        checkIndex(index);
        Node del = null;
        //删除的是头节点
        if (index == 0){
            del = head;
            head = head.next;
        }else {
            Node prev = getNode(index);
            del = prev.next;
            prev.next = del.next;
            del.next = null;
        }
        size--;
        return del.data;
    }

    //删除最后一个元素
    public T remove(){
        return delete(size-1);
    }

    //判断链表是否为空
    public boolean empty(){
        return size==0;
    }

    //清空链表
    public void clean(){
        head = tail = null;
        size = 0;
    }

    //遍历打印链表
    public String toString(){
        if (empty()){
            return "[]";
        }else {
            StringBuilder sb = new StringBuilder("[");
            for (Node cur = head; cur!=null; cur=cur.next){
                sb.append(cur.data.toString() + ",");
            }
            int length = sb.length();
            return sb.replace(length-1, length, "]").toString();
        }
    }
}
