package com.atguigu.readwrite_student;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ClassName: ReadWriteLockDemo
 * Package: com.atguigu.readwrite_student
 * Description:
 *     读写锁demo
 * @Author Xu, Luqin
 * @Create 2024/10/9 15:13
 * @Version 1.0
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        for (int i = 0; i < 5; i++) {
            final int num = i+ 1;
            new Thread(()->{
                myCache.put(num+"", num+"");
            }, String.valueOf(i+1)).start();
        }

        for (int i = 0; i < 5; i++) {
            final int num = i+ 1;
            new Thread(()->{
                myCache.get(num+"");
            }, String.valueOf(i+1)).start();
        }
    }
}


class MyCache{
    private volatile Map<String, Object> map =  new HashMap<>();
    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    void put(String key, Object value){
        rwl.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " is writing the data...");
            map.put(key, value);
            TimeUnit.MICROSECONDS.sleep(100);
            System.out.println(Thread.currentThread().getName() + " has writed completely.");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            rwl.writeLock().unlock();
        }
    }

    Object get(String key){
        rwl.readLock().lock();
        Object res = null;
        try {
            res = map.get(key);
            System.out.println(Thread.currentThread().getName() +" is reading.");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            rwl.readLock().unlock();
        }
        return res;
    }
}