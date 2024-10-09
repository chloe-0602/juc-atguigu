package com.atguigu.lock_student;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * ClassName: ThreadDemo4
 * Package: com.atguigu.lock_student
 * Description:
 * 演示 ArrayList、ArraySet、HashMap线程不安全问题
 *
 * @Author Xu, Luqin
 * @Create 2024/10/9 7:19
 * @Version 1.0
 */
public class ThreadDemo4 {
    public static void main(String[] args) {
//        ArrayList<String> list = new ArrayList<>();
//        Vector list = new Vector();
//        Collection<String> list = Collections.synchronizedCollection(new ArrayList<String>());
/*        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }).start();
        }*/

//        HashSet<String> set = new HashSet<>();
/*        CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }).start();
        }*/

//        HashMap<Integer, String> map = new HashMap<>();
        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                map.put(finalI, UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }).start();
        }
    }
}
