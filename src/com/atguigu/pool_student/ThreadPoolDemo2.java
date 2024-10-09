package com.atguigu.pool_student;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: ThreadPoolDemo2
 * Package: com.atguigu.pool_student
 * Description:
 *   自定义线程池
 * @Author Xu, Luqin
 * @Create 2024/10/9 22:38
 * @Version 1.0
 */
public class ThreadPoolDemo2 {
    @Test
    public void test(){
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2,
                5,
                2, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());



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
