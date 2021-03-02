package cn.zhixin.learn.designpattern;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Zhixin Zhang
 * @Date: 2021/03/02/2:57 PM
 * @Description: 线程安全的单例模式，双重检查创建单例对象
 */
public class Singleton {
    /**
     * 注意volatile，防止构造函数重排序
     * 正常构造函数顺序：1.分配内存M；2.初始化内存M；3.指针指向内存M；
     * 经过JVM指令重排序后，可能变为1->3->2，导致构造函数返回了，但对象还没有被被初始化。
     */
    private static volatile Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

}
