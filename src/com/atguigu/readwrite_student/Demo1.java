package com.atguigu.readwrite_student;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ClassName: Demo1
 * Package: com.atguigu.readwrite_student
 * Description:
 *   演示 读锁 的 锁降级
 * @Author Xu, Luqin
 * @Create 2024/10/9 15:34
 * @Version 1.0
 */
public class Demo1 {
    public static void main(String[] args) {
        ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.WriteLock wl = rwl.writeLock();
        ReentrantReadWriteLock.ReadLock rl = rwl.readLock();

/*        wl.lock();
        System.out.println("write lock...");
        rl.lock();
        System.out.println("---- read lock ");
        wl.unlock();
        rl.unlock();*/


        // pending read lock...
        rl.lock();
        System.out.println("---- read lock ");
        wl.lock();
        System.out.println("write lock...");
        rl.unlock();
        wl.unlock();
    }
}
