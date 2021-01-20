package cn.zhixin.learn.dubbo.provider;

import cn.zhixin.learn.dubbo.api.GreetingsService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import java.util.concurrent.CountDownLatch;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Zhixin Zhang
 * @Date: 2021/01/20/5:31 PM
 * @Description:
 */
public class Application {
    private static final String zookeeperHost = System.getProperty("zookeeper.address", "127.0.0.1");

    public static void main(String[] args) throws Exception {
        ServiceConfig<GreetingsService> service = new ServiceConfig<>();
        service.setApplication(new ApplicationConfig("first-dubbo-provider"));
        service.setRegistry(new RegistryConfig("zookeeper://" + zookeeperHost + ":2181"));
        service.setInterface(GreetingsService.class);
        service.setRef(new GreetingsServiceImpl());
        service.export();
        System.out.println("dubbo service started");
        new CountDownLatch(1).await();
    }
}
