package com.atguigu.juc_student;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: SemaphoreDemo
 * Package: com.atguigu.juc_student
 * Description:
 * JUC辅助类之一 Semaphore
 * 案例： 6辆车停三个车位
 *
 * @Author Xu, Luqin
 * @Create 2024/10/9 13:47
 * @Version 1.0
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("No." + Thread.currentThread().getName() + "抢到了车位");
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                    System.out.println("No." + Thread.currentThread().getName() + "--------离开了车位");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    semaphore.release();

                }
            }, String.valueOf(i + 1)).start();
        }
    }
}
