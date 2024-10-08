package com.atguigu.lock_student;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ClassName: ThreadDemo2
 * Package: com.atguigu.lock_student
 * Description:
 *    线程间通信：对应ThreadDemo1
 *
 * @Author Xu, Luqin
 * @Create 2024/10/8 23:41
 * @Version 1.0
 */
public class ThreadDemo2 {
    public static void main(String[] args) {
        Share share = new Share();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    share.increase();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "AA").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    share.decrease();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "BB").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    share.increase();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "CC").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    share.decrease();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }, "DD").start();
    }
}

class Share{
    private int number = 0;
    private final Lock lock = new ReentrantLock();
    private Condition condition =  lock.newCondition();

    void increase() throws InterruptedException {
        lock.lock();
        while (number != 0){
            condition.await();
        }
        try {
            number++;
            System.out.println(Thread.currentThread().getName() +" -> " + number);
            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }

    void decrease() throws InterruptedException {
        lock.lock();

        while (number != 1){
            condition.await();
        }
        try {
            number--;
            System.out.println(Thread.currentThread().getName() +" -> " + number);
            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }
}
