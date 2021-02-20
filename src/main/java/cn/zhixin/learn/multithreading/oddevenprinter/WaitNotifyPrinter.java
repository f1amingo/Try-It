package cn.zhixin.learn.multithreading.oddevenprinter;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Zhixin Zhang
 * @Date: 2021/02/20/5:15 PM
 * @Description: 使用等待/通知来实现奇偶数交替打印。
 */
public class WaitNotifyPrinter {
    // 锁变量声明为final
    private static final Object lock = new Object();
    // count没必要声明为volatile
    // 根据Happens-Before的“管程中锁的规则”以及“传递性”
    // 临界区中count的修改对另一个线程总是有可见性
//    private static volatile int count = 1;
    private static int count = 1;
    private static final int MAX_PRINT_NUM = 100;

    private static void print() {
        //加锁进入临界区
        synchronized (lock) {
            while (count <= MAX_PRINT_NUM) {
                try {
                    //打印
                    System.out.printf("%s：%d\n", Thread.currentThread().getName(), count);
                    count += 1;
                    //先唤醒
                    lock.notifyAll();
                    //再睡眠
                    lock.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // 防止子线程阻塞，导致主线程不退出
            lock.notifyAll();
        }
    }


    public static void main(String[] args) {
//        这里不一定第一个线程先运行，所以第一个线程不一定打印奇数
//        所以把它们改称为A, B线程
//        new Thread(WaitNotifyPrinter::print, "奇数").start();
//        new Thread(WaitNotifyPrinter::print, "偶数").start();

        new Thread(WaitNotifyPrinter::print, "A").start();
        new Thread(WaitNotifyPrinter::print, "B").start();
    }
}
