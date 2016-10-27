package tk.zhangh.ioc.factory;

import org.junit.Assert;
import org.junit.Test;
import tk.zhangh.ioc.bean.BeanDefinition;
import tk.zhangh.ioc.bean.PropertyValue;
import tk.zhangh.ioc.bean.PropertyValues;
import tk.zhangh.ioc.beans.HelloWorldService;

/**
 * 测试AbstractBeanFactory
 * 要求Bean必须有无参数构造的方法，并且Bean只有简单属性，不存在依赖依赖关系
 *
 * 测试内容
 * 1.BeanDefinition注册
 * 2.Bean实例获取过程
 * 3.Bean注册，注入基本（以及String）类型的属性
 *
 * Created by ZhangHao on 2016/10/26.
 */
public class AbstractBeanFactoryTest {

    @Test
    public void register_get_no_constructor_no_field_bean() throws Exception {
        AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();
        beanFactory.registerBeanDefinition("helloWorldService", createBeanDefinition());
        HelloWorldService helloWorldService = (HelloWorldService)beanFactory.getBean("helloWorldService");
        Assert.assertEquals(helloWorldService.sayHello(), "hello world:anInt:0,aDouble:0.0,aString:null");
    }

    @Test
    public void get_not_register_bean() throws Exception {
        AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();
        HelloWorldService helloWorldService = null;
        try {
            helloWorldService = (HelloWorldService)beanFactory.getBean("helloWorldService");
        } catch (Exception e) {
            Assert.assertNull(helloWorldService);
        }
    }

    @Test
    public void register_get_no_constructor_many_field_bean() throws Exception {
        AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();
        beanFactory.registerBeanDefinition("helloWorldService", createBeanDefinitionWithProperties());
        HelloWorldService helloWorldService = (HelloWorldService)beanFactory.getBean("helloWorldService");
        Assert.assertEquals(helloWorldService.sayHello(), "hello world:anInt:123,aDouble:3.14,aString:string");
    }

    private PropertyValues createBasePropertyValues() {
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("aString", "string"));
        propertyValues.addPropertyValue(new PropertyValue("anInt", 123));
        propertyValues.addPropertyValue(new PropertyValue("aDouble", 3.14));
        return propertyValues;
    }

    private BeanDefinition createBeanDefinition() {
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName("tk.zhangh.ioc.beans.HelloWorldServiceImpl");
        return beanDefinition;
    }

    private BeanDefinition createBeanDefinitionWithProperties() {
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName("tk.zhangh.ioc.beans.HelloWorldServiceImpl");
        beanDefinition.setPropertyValues(createBasePropertyValues());
        return beanDefinition;
    }
}