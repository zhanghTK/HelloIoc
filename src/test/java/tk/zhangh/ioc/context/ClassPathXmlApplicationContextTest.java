package tk.zhangh.ioc.context;

import org.junit.Test;
import tk.zhangh.ioc.beans.HelloWorldService;

/**
 * ClassPathXmlApplicationContext测试
 *
 * 使用ApplicationContext解析、注册、获取bean
 * bean要求有无参构造
 *
 * Created by ZhangHao on 2016/11/2.
 */
public class ClassPathXmlApplicationContextTest {

    @Test
    public void register_get_bean_by_ClassPathXmlApplicationContextTest() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ioc.xml");
        HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldOutputService");
        helloWorldService.sayHello();
    }

}