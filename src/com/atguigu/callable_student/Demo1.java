package com.atguigu.callable_student;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * ClassName: Demo1
 * Package: com.atguigu.callable_student
 * Description:
 *  演示Callable和Runnable的区别
 * @Author Xu, Luqin
 * @Create 2024/10/9 11:28
 * @Version 1.0
 */
public class Demo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
/*        FutureTask<Integer> futureTask1 = new FutureTask<>(new MyThread2());


        new Thread(futureTask1, "AA").start();
        while (!futureTask1.isDone()){
            System.out.println("wating...");
        }
        System.out.println(futureTask1.get());
        System.out.println(futureTask1.get());*/

        FutureTask<Integer> futureTask2 = new FutureTask<>(() -> {
            System.out.println(Thread.currentThread().getName() + ":: " + "come into lamda callable.");
            return 2000;
        });

        new Thread(futureTask2, "BB").start();
        System.out.println(futureTask2.get());
    }
}

class MyThread1 implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" :: " + " come into runnable.");
    }
}

class MyThread2 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName()+" :: " + " come into callable.");
        return 1024;
    }
}
