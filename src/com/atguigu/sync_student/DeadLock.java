package com.atguigu.sync_student;

import java.util.concurrent.TimeUnit;

/**
 * ClassName: DeadLock
 * Package: com.atguigu.sync_student
 * Description:
 *    死锁
 *      1.什么是死锁
 *      2.产生死锁的原因
 *          1）资源不足
 *          2）资源分配不合适
 *          3）线程推进的顺序不对
 *      3.验证是否产生死锁： jps jstack
 * @Author Xu, Luqin
 * @Create 2024/10/9 10:39
 * @Version 1.0
 */
public class DeadLock {
    public static void main(String[] args) {
        Object obj1 = new Object();
        Object obj2 = new Object();

        new Thread(()->{
            synchronized (obj1){
                System.out.println(Thread.currentThread().getName()+"握住锁obj1，尝试握住锁obj2");

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                synchronized (obj2){
                    System.out.println(Thread.currentThread().getName()+"握住锁obj2");
                }
            }
        }, "AA").start();

        new Thread(()->{
            synchronized (obj2){
                System.out.println(Thread.currentThread().getName()+"握住锁obj2，尝试握住锁obj1");

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                synchronized (obj1){
                    System.out.println(Thread.currentThread().getName()+"握住锁obj1");
                }
            }
        }, "BB").start();
    }
}
