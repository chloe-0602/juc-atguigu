package com.atguigu.juc_student;

import java.util.concurrent.CountDownLatch;

/**
 * ClassName: CountDownLatchDemo
 * Package: com.atguigu.juc_student
 * Description:
 *  JUC辅助类之一CountDownLatch
 *     案例： 6位同学离开后班长才关门
 * @Author Xu, Luqin
 * @Create 2024/10/9 13:30
 * @Version 1.0
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println("No." + Thread.currentThread().getName() + " student leave.");

                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }

        countDownLatch.await();
        System.out.println("班长关门啦");
    }
}
