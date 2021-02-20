package cn.zhixin.learn.multithreading.oddevenprinter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Zhixin Zhang
 * @Date: 2021/02/21/12:16 AM
 * @Description: 仍然是一种错误的写法！！！
 * 和volatile写法同一片文章，使用AtomicInteger来保证原子性与可见性
 * 整个while循环都应该被视作临界区
 */
public class AtomicIntegerPrinter {
    //AtomicInteger
    private static final AtomicInteger atomicCount = new AtomicInteger(1);
    private static final int MAX_PRINT_NUM = 100;

    public static void main(String[] args) {
        new Thread(() -> {
            while (atomicCount.get() <= MAX_PRINT_NUM) {
                if (atomicCount.get() % 2 == 1) {
                    System.out.printf("%s：%d\n", Thread.currentThread().getName(), atomicCount.get());
                    atomicCount.incrementAndGet();
                }
            }
        }, "奇数").start();

        new Thread(() -> {
            while (atomicCount.get() <= MAX_PRINT_NUM) {
                if (atomicCount.get() % 2 == 0) {
                    System.out.printf("%s：%d\n", Thread.currentThread().getName(), atomicCount.get());
                    atomicCount.incrementAndGet();
                }
            }
        }, "偶数").start();
    }
}
