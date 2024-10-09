package com.atguigu.forkjoin_stu;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * ClassName: ForkJoinDemo
 * Package: com.atguigu.forkjoin_stu
 * Description:
 *
 * @Author Xu, Luqin
 * @Create 2024/10/9 23:31
 * @Version 1.0
 */
public class ForkJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyTask myTask = new MyTask(0, 100);

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(myTask);

        Integer res = forkJoinTask.get();
        System.out.println(res);

        forkJoinPool.shutdown();
    }
}

class MyTask extends RecursiveTask<Integer> {

    private final int VALUE = 10;
    private int start;
    private int end;
    private int result;

    public MyTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if ((end - start) <= VALUE) {
            for (int i = start; i <= end; i++) {
                result += i;
            }
        } else {
            int mid = (end + start) / 2;
            MyTask task1 = new MyTask(start, mid);
            MyTask task2 = new MyTask(mid + 1, end);

            task1.fork();
            task2.fork();

            result = task1.join() + task2.join();

        }
        return result;
    }
}
