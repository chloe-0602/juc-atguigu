package com.atguigu.completable_stu;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * ClassName: CompletableFurtureDemo
 * Package: com.atguigu.completable
 * Description:
 *   测试有参和无参返回的CompletableFuture
 * @Author Xu, Luqin
 * @Create 2024/10/9 23:43
 * @Version 1.0
 */
public class CompletableFutureDemo {
    /**
     * ForkJoinPool.commonPool-worker-9 is running
     * null
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void testVoid() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> cf = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName() + " is running");
        });

        System.out.println(cf.get());
    }


    /**
     * ForkJoinPool.commonPool-worker-9 is running
     * ------------ t = null
     * ------------ u = java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
     */
    @Test
    public void testPara(){
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName() + " is running");
            int i = 10/0;
            return 1024;
        });

        cf.whenComplete((t,u)->{
            System.out.println("------------ t = "+ t);
            System.out.println("------------ u = "+ u);
        });
    }
}
