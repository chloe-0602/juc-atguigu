package com.atguigu.pool_student;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ClassName: ThreadPoolDemo1
 * Package: com.atguigu.pool_student
 * Description:
 *  线程池的三种使用方式
 * @Author Xu, Luqin
 * @Create 2024/10/9 19:00
 * @Version 1.0
 */
public class ThreadPoolDemo1 {
    @Test
    public void test1(){
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "办理业务");
                });
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            threadPool.shutdown();
        }
    }


    @Test
    public void test2(){
        ExecutorService threadPool = Executors.newSingleThreadExecutor();

        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "办理业务");
                });
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            threadPool.shutdown();
        }
    }

    @Test
    public void test3(){
        ExecutorService threadPool = Executors.newCachedThreadPool();

        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "办理业务");
                });
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            threadPool.shutdown();
        }
    }
}
