package cn.zhixin.learn.dubbo.provider;

import cn.zhixin.learn.dubbo.api.GreetingsService;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Zhixin Zhang
 * @Date: 2021/01/20/5:30 PM
 * @Description:
 */
public class GreetingsServiceImpl implements GreetingsService {
    public String sayHi(String name) {
        return "hi, " + name;
    }
}
