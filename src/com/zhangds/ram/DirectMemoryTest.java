package com.zhangds.ram;

import java.nio.ByteBuffer;

/**
 * 直接内存申请慢，访问效率高
 * IO直接操作直接内存（直接内存=>系统调用 =>硬盘/网卡）
 * 非直接内存则需要二次拷贝（堆内存=>直接内存=>系统调用=>硬盘/网卡）
 * @author: zhangds
 * @date: 2021/3/15 11:24
 */
public class DirectMemoryTest {

    public static void main(String[] args) {
        heapAccess();
        directAccess();
        heapAllocate();
        directAllocate();
    }

    public static void heapAccess(){

        long startTime = System.currentTimeMillis();
        //分配内存
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        for (int i=0 ; i<10000; i++){
            for (int j=0; j<200; j++){
                buffer.putInt(j);
            }
            buffer.flip();

            for (int j=0; j<200; j++){
                buffer.getInt(j);
            }
            buffer.clear();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("堆内存访问：" + (endTime-startTime) + "ms");

    }

    /**
     * long size = Math.max(1L, (long)cap + (pa ? ps : 0));
     * Bits.reserveMemory(size, cap);
     *
     * 判断是否有足够的直接内存空间分配,可通过-XX: MaxDirectMemory size-<size>参数指定直接内存最大可分配空间,如果不指定默认为最大堆内存大小
     * 在分配直接内存时如果发现空间不够会显示调用 System.gC()触发一次fu11gc回收掉一部分无用的直接内存的引用对象,同时直接内存也会被释放掉
     * 如果释放完分配空间还是不够会抛出异常java.1ang. OutofMemory Error
     *
     * unsafe.allocateMemory(size);调用 unsafe本地方法分配直接内存
     *
     * Bits.unreserveMemory(size, cap); 分配失败,释放内存
     *
     * 使用 Cleaner机制注册内存回收处理函数,当直接内存引用对象被GC清理掉时
     * 会提前调用这里注册的释放直接内存的Dea11 orator线程对象的run方法
     */
    public static void directAccess(){

        long startTime = System.currentTimeMillis();
        //分配内存
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        for (int i=0 ; i<10000; i++){
            for (int j=0; j<200; j++){
                buffer.putInt(j);
            }
            buffer.flip();

            for (int j=0; j<200; j++){
                buffer.getInt(j);
            }
            buffer.clear();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("直接内存访问：" + (endTime-startTime) + "ms");

    }

    public static void heapAllocate(){
        long startTime = System.currentTimeMillis();
        //分配内存
        for (int i=0 ; i<10000; i++){
            ByteBuffer.allocate(100);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("堆内存申请：" + (endTime-startTime) + "ms");
    }

    public static void directAllocate(){
        long startTime = System.currentTimeMillis();
        //分配内存
        for (int i=0 ; i<10000; i++){
            ByteBuffer.allocateDirect(100);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("直接内存申请：" + (endTime-startTime) + "ms");
    }

}
