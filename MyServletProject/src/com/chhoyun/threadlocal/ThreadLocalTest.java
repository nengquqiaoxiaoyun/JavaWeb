package com.chhoyun.threadlocal;

import java.util.Random;

/**
 * @author: huakaimay
 * @since: 2020-11-19
 */
public class ThreadLocalTest {

    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    private static Random ran = new Random();


    public static void main(String[] args) {


        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                Integer random = ran.nextInt(1000);
                String name = Thread.currentThread().getName();
                System.out.println("线程[" + name + "]生成的随机数是：" + random);
                threadLocal.set(random);


                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Integer j = threadLocal.get();
                System.out.println("在线程[" + name + "]快结束时取出关联的数据是：" + j);


            }).start();
        }


    }

}
