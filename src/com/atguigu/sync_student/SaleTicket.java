package com.atguigu.sync_student;

/**
 * ClassName: SaleTicket
 * Package: com.atguigu.sync_student
 * Description:
 *
 * @Author Xu, Luqin
 * @Create 2024/10/8 17:07
 * @Version 1.0
 */
public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "w1").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "w2").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "w3").start();

    }
}

class Ticket {
    private int number = 30;

    synchronized void sale() {
        if (this.number > 0) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " sell No." + (this.number--) + " ticket");
        }
    }
}
