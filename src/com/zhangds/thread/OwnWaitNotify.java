package com.zhangds.thread;

public class OwnWaitNotify {
    MonitorObject monitorObject = new MonitorObject();
    boolean wasSignalled = false;

    public void doWaited(){
        synchronized (monitorObject){
            while (!wasSignalled){      //锁自旋
                try {
                    monitorObject.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            wasSignalled = false;
        }
    }

    public void doNotify(){
        synchronized (monitorObject){
            wasSignalled = true;
            monitorObject.notify();
        }
    }
}

class MonitorObject{

}