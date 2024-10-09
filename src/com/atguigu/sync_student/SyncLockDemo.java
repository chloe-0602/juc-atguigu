package com.atguigu.sync_student;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ClassName: SyncLockDemo
 * Package: com.atguigu.sync_student
 * Description:
 * 使用synchronized和ReentranceLock演示可重入锁
 *
 * @Author Xu, Luqin
 * @Create 2024/10/9 10:19
 * @Version 1.0
 */
public class SyncLockDemo {
    @Test
    public void testSynchronized() {
        Object obj = new Object();
        synchronized (obj) {
            System.out.println(Thread.currentThread().getName() + " :: " + "外层");
            synchronized (obj) {
                System.out.println(Thread.currentThread().getName() + " :: " + "内层");
            }
        }
    }

    @Test
    public void testLock(){
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " :: " + "外层");
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " :: " + "内层");

            }finally {
                lock.unlock();
            }
        }finally {
            lock.unlock();
        }

        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " :: " + "第二个");
        }finally {
            lock.unlock();
        }
    }
}
