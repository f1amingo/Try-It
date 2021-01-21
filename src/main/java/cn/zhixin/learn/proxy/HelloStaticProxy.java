package cn.zhixin.learn.proxy;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Zhixin Zhang
 * @Date: 2021/01/21/8:53 PM
 * @Description: 静态代理实现
 * 代理模式：将对象的实际行为封装起来，对外提供代理，以控制对这个对象的访问
 * 应用：远程代理（RMI, RPC），虚代理（JDBC连接），保护代理（鉴权）
 * 参考：
 * 1. https://xie.infoq.cn/article/b70498d6c65595e0a3ccb4c7d
 * 2. https://www.liaoxuefeng.com/wiki/1252599548343744/1281319432618017#0
 * 3. https://juejin.cn/post/6844903568986603534#heading-7
 */

//实现Hello接口，就可以替换掉HelloImpl
public class HelloStaticProxy implements Hello {

    //将实际的实现类（HelloImpl）作为私有属性
    //具体任务由helloImpl（可怜的家伙T_T）完成
    //HelloStaticProxy只是额外添加一些行为，而不是完全接管HelloImpl
    private Hello helloImpl;

    //需要一个含参数的构造函数
    public HelloStaticProxy(Hello hello) {
        this.helloImpl = hello;
    }

    //关于@Override注解，写不写没实质区别
    //写了表示想重写父类方法，如果父类没有这个方法，会报错，可以避免低级错误
    @Override
    public void sayHello(String name) {
        //开始之前，做一些操作
        System.out.println("Before...");
        //干活的还是helloImpl，好惨。。所以我们要坚决去除中间商！
        this.helloImpl.sayHello(name);
        //结束之后，做一些操作
        System.out.println("After...");
    }

    public static void main(String[] args) {
        HelloStaticProxy helloStaticProxy = new HelloStaticProxy(new HelloImpl());
        helloStaticProxy.sayHello("Zhang");
    }

}
