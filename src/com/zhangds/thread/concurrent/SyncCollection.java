package com.zhangds.thread.concurrent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Create by zhangds
 * 2020-05-11 10:33
 **/
public class SyncCollection {
    Vector vector = new Vector();

    ArrayList arrayList = new ArrayList();

    CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList(); //读写分离

    Hashtable hashtable = new Hashtable();

    HashMap hashMap = new HashMap();

    ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(); //锁分段

    ConcurrentLinkedQueue concurrentLinkedQueue; //非阻塞队列
}
