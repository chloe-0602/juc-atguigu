package com.atguigu.sync_student;

import sun.awt.windows.ThemeReader;

/**
 * ClassName: ThreadDemo1
 * Package: com.atguigu.sync_student
 * Description:
 *    线程间通信：
 *        第一步 创建资源类，在资源类创建属性和操作方法
 *        第二步 在资源类中操作
 *               1. 判断
 *               2. 干活
 *               3. 通知
 *        第三步 创建多线程， 调用资源类的操作方法
 *        第四步 防止虚假唤醒问题
 * @Author Xu, Luqin
 * @Create 2024/10/8 23:28
 * @Version 1.0
 */
public class ThreadDemo1 {
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
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "DD").start();
    }
}

class Share{
    private int number = 0;
    synchronized void increase() throws InterruptedException {
        while (number != 0){
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() +" -> " + number);

        this.notifyAll();
    }

    synchronized void decrease() throws InterruptedException {
        while (number != 1){
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() +" -> " + number);

        this.notifyAll();
    }
}