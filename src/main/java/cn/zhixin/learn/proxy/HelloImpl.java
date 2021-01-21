package cn.zhixin.learn.proxy;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Zhixin Zhang
 * @Date: 2021/01/21/8:53 PM
 */
public class HelloImpl implements Hello {

    @Override
    public void sayHello(String name) {
        System.out.println("Hello, " + name);
    }
}
