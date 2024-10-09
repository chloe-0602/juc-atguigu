package com.atguigu.lock_student;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ClassName: LSaleTicket
 * Package: com.atguigu.lock_student
 * Description:
 *  使用ReentrantLock实现卖票
 *
 *  查看公平锁和非公平锁
 * @Author Xu, Luqin
 * @Create 2024/10/8 17:37
 * @Version 1.0
 */
public class LSaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        // 注意，此处线程不是马上创建的， private native void start0();
        // 看操作系统
        new Thread(()-> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "W1").start();

        new Thread(()-> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "W2").start();

        new Thread(()-> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "W3").start();
    }
}

class Ticket{
    private int number = 30;

    private final ReentrantLock lock = new ReentrantLock(false);
    void sale(){
        lock.lock();

        try {
            if (this.number > 0){
                System.out.println(Thread.currentThread().getName() +" sale No." + (this.number--) + " ticket.");
            }
        } finally {
            lock.unlock();
        }
    }
}
