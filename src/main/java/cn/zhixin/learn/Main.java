package cn.zhixin.learn;


public class Main {

    private static int number = 1;
    private static final int MAX = 10;
    private final static Object lock = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
//            打印奇数
            synchronized (lock) {
                while (number < MAX) {
                    try {
                        System.out.println("奇数：" + number);
                        number += 1;
                        lock.notifyAll();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(() -> {
//            偶数线程
            synchronized (lock) {
                while (number < MAX) {
                    try {
                        System.out.println("偶数：" + number);
                        number += 1;
                        lock.notifyAll();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
