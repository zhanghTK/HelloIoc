package tk.zhangh.ioc.factory;

import org.junit.Assert;
import org.junit.Test;
import tk.zhangh.ioc.bean.BeanDefinition;
import tk.zhangh.ioc.beans.HelloWorldService;

/**
 * 测试AbstractBeanFactory
 * 要求Bean必须有无参数构造的方法，并且Bean本身是简单的，没有其他属性，不存在依赖依赖关系
 *
 * 测试内容
 * 1.BeanDefinition注册
 * 2.Bean实例获取过程
 *
 * Created by ZhangHao on 2016/10/26.
 */
public class AbstractBeanFactoryTest {

    @Test
    public void test_register_get_no_constructor_no_field_bean() throws Exception {
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName("tk.zhangh.ioc.beans.HelloWorldServiceImpl");
        AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();
        beanFactory.registerBeanDefinition("helloWorldService", beanDefinition);
        HelloWorldService helloWorldService = (HelloWorldService)beanFactory.getBean("helloWorldService");
        Assert.assertEquals(helloWorldService.sayHello(), "hello world");
    }

    @Test
    public void test_get_not_register_bean() throws Exception {
        AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();
        HelloWorldService helloWorldService = null;
        try {
            helloWorldService = (HelloWorldService)beanFactory.getBean("helloWorldService");
        } catch (Exception e) {
            Assert.assertNull(helloWorldService);
        }
    }
}