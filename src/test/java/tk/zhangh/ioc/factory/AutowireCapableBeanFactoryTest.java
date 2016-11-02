package tk.zhangh.ioc.factory;

import org.junit.Assert;
import org.junit.Test;
import tk.zhangh.ioc.bean.BeanDefinition;
import tk.zhangh.ioc.bean.PropertyValue;
import tk.zhangh.ioc.bean.PropertyValues;
import tk.zhangh.ioc.beans.HelloWorldService;
import tk.zhangh.ioc.beans.OutputService;
import tk.zhangh.ioc.io.ResourceLoader;
import tk.zhangh.ioc.io.xml.XmlBeanDefinitionReader;

/**
 * 测试AutowireCapableBeanFactory
 * 要求Bean必须有无参数构造的方法
 *
 * 测试内容
 * 1.手动注册简单BeanDefinition
 * 2.获取未注册的BeanDefinition
 * 3.手动注册BeanDefinition（包含基本类型属性）
 * 4.使用xml方式注册BeanDefinition（包含基本属性以及bean属性）
 * 5.使用xml方式注册BeanDefinition，在获取bean前与初始化
 *
 *
 * Created by ZhangHao on 2016/11/2.
 */
public class AutowireCapableBeanFactoryTest {

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
    public void register_get_no_constructor_base_field_bean() throws Exception {
        AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();
        beanFactory.registerBeanDefinition("helloWorldService", createBeanDefinitionWithProperties());
        HelloWorldService helloWorldService = (HelloWorldService)beanFactory.getBean("helloWorldService");
        Assert.assertEquals(helloWorldService.sayHello(), "hello world:anInt:123,aDouble:3.14,aString:string");
    }

    @Test
    public void read_xml_register_bean_with_bean_field() throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions("ioc.xml");
        AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();
        for (String id : xmlBeanDefinitionReader.getBeanNames()) {
            beanFactory.registerBeanDefinition(id, xmlBeanDefinitionReader.getBeanDefinition(id));
        }
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldOutputService");
        Assert.assertEquals(helloWorldService.sayHello(), "helloWorldOutputService");
    }

    @Test
    public void read_xml_register_bean_before_get_bean_pre_instantiate() throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions("ioc.xml");
        AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();
        for (String id : xmlBeanDefinitionReader.getBeanNames()) {
            beanFactory.registerBeanDefinition(id, xmlBeanDefinitionReader.getBeanDefinition(id));
        }
        beanFactory.preInstantiateSingletons();
        OutputService outputService = (OutputService) beanFactory.getBean("outputService");
        Assert.assertEquals(outputService.output("hello world"), "hello world");
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