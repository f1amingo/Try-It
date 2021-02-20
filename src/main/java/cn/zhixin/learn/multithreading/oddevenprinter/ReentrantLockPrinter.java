package cn.zhixin.learn.multithreading.oddevenprinter;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Zhixin Zhang
 * @Date: 2021/02/21/12:28 AM
 * @Description: 使用ReentrantLock和Condition实现，类似监视器的做法
 */
public class ReentrantLockPrinter {
    private static int count = 1;
    private static final int MAX_PRINT_NUM = 100;

    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();

    private static void print() {
        lock.lock();
        try {
            while (count <= MAX_PRINT_NUM) {
                System.out.printf("%s：%d\n", Thread.currentThread().getName(), count);
                count++;
                condition.signalAll();
//                condition.wait();
//                await千万别打错！！！
                condition.await();
            }
            //唤醒所有阻塞线程，结束主线程
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //使用锁的正确姿势：try-finally
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(ReentrantLockPrinter::print, "A").start();
        new Thread(ReentrantLockPrinter::print, "B").start();
    }

}
