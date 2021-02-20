package cn.zhixin.learn.multithreading.oddevenprinter;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Zhixin Zhang
 * @Date: 2021/02/21/12:50 AM
 * @Description: 不完全对的版本。使用一个volatile标志位来实现线程互斥
 * 仍然可能打印出101
 */
public class VolatileFlagPrinter {

//  volatile是必须的
    private static volatile int count = 1;
    private static final int MAX_PRINT_NUM = 100;

    private static volatile boolean flag = true;

    public static void main(String[] args) {
        new Thread(() -> {
            while (count <= 100) {
                if (flag) {
                    System.out.printf("%s：%d\n", Thread.currentThread().getName(), count);
                    count++;
                    flag = false;
                }
            }
        }, "奇数").start();

        new Thread(() -> {
            while (count <= 100) {
                if (!flag) {
                    System.out.printf("%s：%d\n", Thread.currentThread().getName(), count);
                    count++;
                    flag = true;
                }
            }
        }, "偶数").start();
    }

}
