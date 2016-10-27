package tk.zhangh.ioc.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.zhangh.ioc.bean.BeanDefinition;
import tk.zhangh.ioc.bean.PropertyValue;
import tk.zhangh.ioc.bean.PropertyValues;

import java.lang.reflect.Field;

/**
 * AbstractBeanFactory的基本实现
 *
 * 要求Bean必须有无参数构造的方法，并且Bean只有简单属性（基本类型以及String），不存在复杂依赖关系
 * 根据BeanDefinition信息实例化bean对象
 *
 * Created by ZhangHao on 2016/10/26.
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

    private static final Logger logger = LoggerFactory.getLogger(AutowireCapableBeanFactory.class);

    @Override
    protected Object doCreateBean(BeanDefinition beanDefinition) {
        logger.info("instantiate BeanDefinition:" + beanDefinition.getBeanName());
        try {
            Object object = createBeanInstance(beanDefinition.getBeanClass());
            return setProperties(object, beanDefinition.getPropertyValues());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("init bean error:" + beanDefinition.getBeanName());
            throw new RuntimeException("init bean error:" + beanDefinition.getBeanName());
        }
    }

    private Object createBeanInstance(Class clazz) throws Exception{
        return clazz.newInstance();
    }

    private Object setProperties(Object bean, PropertyValues propertyValues) throws Exception{
        if (propertyValues == null) {
            return bean;
        }
        Class clazz = bean.getClass();
        for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
            Field field = clazz.getDeclaredField(propertyValue.getName());
            field.setAccessible(true);
            field.set(bean, propertyValue.getValue());
        }
        return bean;
    }
}
