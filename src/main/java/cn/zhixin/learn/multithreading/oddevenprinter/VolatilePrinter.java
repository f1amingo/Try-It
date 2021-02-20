package cn.zhixin.learn.multithreading.oddevenprinter;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Zhixin Zhang
 * @Date: 2021/02/21/12:05 AM
 * @Description: 这是一种错误的写法！！！
 * 网上看到的，使用volatile来实现奇偶打印。
 * 错误在于volatile只能保证可见性，不能保证count++操作的原子性
 * 所以多跑几次会发现最后一个的数字可能不等于100
 */
public class VolatilePrinter {

    //声明为volatile，只保证了可见性
    private static volatile int count = 1;
    private static final int MAX_PRINT_NUM = 100;

    public static void main(String[] args) {
        new Thread(() -> {
            while (count <= MAX_PRINT_NUM) {
                if (count % 2 == 1) {
                    System.out.printf("%s：%d\n", Thread.currentThread().getName(), count);
                    //count++不能保证原子性
                    count++;
                }
            }
        }, "奇数").start();

        new Thread(() -> {
            while (count <= MAX_PRINT_NUM) {
                if (count % 2 == 0) {
                    System.out.printf("%s：%d\n", Thread.currentThread().getName(), count);
                    //count++不能保证原子性
                    count++;
                }
            }
        }, "偶数").start();
    }

}
