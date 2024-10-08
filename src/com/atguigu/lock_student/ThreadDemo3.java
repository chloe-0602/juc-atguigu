package com.atguigu.lock_student;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ClassName: ThreadDemo3
 * Package: com.atguigu.lock_student
 * Description:
 *         线程定制化通信
 * @Author Xu, Luqin
 * @Create 2024/10/9 6:27
 * @Version 1.0
 */
public class ThreadDemo3 {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();

        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                try {
                    shareResource.print5(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"AA").start();

        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                try {
                    shareResource.print10(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"BB").start();

        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                try {
                    shareResource.print15(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"CC").start();
    }
}

class ShareResource{
    private int flag = 1;
    private final Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    void print5(int loopNum) throws InterruptedException {
        lock.lock();
        try {
            while (flag != 1){
                c1.await();
            }
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName()+" :: " + i + " , 第" + loopNum + "轮");
            }
            flag = 2;
            c2.signal();
        } finally {
            lock.unlock();
        }
    }

    void print10(int loopNum) throws InterruptedException {
        lock.lock();
        try {
            while (flag != 2){
                c2.await();
            }
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName()+" :: " + i + " , 第" + loopNum + "轮");
            }
            flag = 3;
            c3.signal();
        } finally {
            lock.unlock();
        }
    }

    void print15(int loopNum) throws InterruptedException {
        lock.lock();
        try {
            while (flag != 3){
                c3.await();
            }
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName()+" :: " + i + " , 第" + loopNum + "轮");
            }
            flag = 1;
            c1.signal();
        } finally {
            lock.unlock();
        }
    }
}