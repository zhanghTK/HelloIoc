package tk.zhangh.ioc.beans;

import org.junit.Assert;

/**
 * 测试使用的简单Bean
 *
 * 有空构造方法且包含其他bean依赖
 *
 * Created by ZhangHao on 2016/11/2.
 */
public class OutputService {
    private HelloWorldService helloWorldService;

    public void setHelloWorldService(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    public String output(String name) {
        Assert.assertNotNull(helloWorldService);
        System.out.println(name);
        return name;
    }
}
