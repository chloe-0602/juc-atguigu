package com.atguigu.queue_student;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: BlockingQueueDemo
 * Package: com.atguigu.queue_student
 * Description:
 *   三组增删查的方法
 *   FIFO
 *   see BlockingQueue interface
 * @Author Xu, Luqin
 * @Create 2024/10/9 16:56
 * @Version 1.0
 */
public class BlockingQueueDemo {
    @Test
    public void testThrowsExeception(){
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(2);

        /**
         * add()
         * remove()
         * element()
         */
        blockingQueue.add("aa");
        blockingQueue.add("bb");
        //Queue full
//        blockingQueue.add("cc");
//        blockingQueue.remove();
//        blockingQueue.remove();
//        blockingQueue.remove();

        System.out.println(blockingQueue.element());

    }

    /**
     * offer(e)
     * poll()
     * peek()
     */
    @Test
    public void testSpecialValue(){
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(2);

        System.out.println(blockingQueue.offer("aa"));
        System.out.println(blockingQueue.offer("bb"));
        System.out.println(blockingQueue.offer("cc")); //false
/*
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());//null*/

        System.out.println(blockingQueue.peek());

    }

    @Test
    public void testBlock() throws InterruptedException {
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(2);

        blockingQueue.put("aa");
        blockingQueue.put("bb");
//        blockingQueue.put("cc");

        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
//        blockingQueue.take();// blocking
    }


    @Test
    public void testTimeout() throws InterruptedException {
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(2);

        System.out.println(blockingQueue.offer("aa"));
        System.out.println(blockingQueue.offer("bb"));
        System.out.println(blockingQueue.offer("ww",3L, TimeUnit.SECONDS));
    }

}
