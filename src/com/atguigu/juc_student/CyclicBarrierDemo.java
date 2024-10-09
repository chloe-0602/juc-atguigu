package com.atguigu.juc_student;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * ClassName: CyclicBarrierDemo
 * Package: com.atguigu.juc_student
 * Description:
 *  JUC辅助类之一 CyclicBarrier
 *     案例： 集齐7颗龙珠召唤神龙
 * @Author Xu, Luqin
 * @Create 2024/10/9 13:37
 * @Version 1.0
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, ()->{
            System.out.println("集齐7颗龙珠，现在召唤神龙");
        });
        for (int i = 1; i <= 7; i++) {
            new Thread(()->{
                System.out.println("No."+ Thread.currentThread().getName()+"被收集");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }, String.valueOf(i)).start();
        }
    }
}
