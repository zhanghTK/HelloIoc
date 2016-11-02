package tk.zhangh.ioc.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.zhangh.ioc.bean.BeanDefinition;
import tk.zhangh.ioc.bean.BeanReference;
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

    /**
     * 根据BeanDefinition实例化bean对象
     * @param beanDefinition BeanDefinition
     * @return bean实例
     */
    @Override
    protected Object doCreateBean(final BeanDefinition beanDefinition) {
        logger.info("instantiate BeanDefinition:" + beanDefinition.getBeanName());
        try {
            Object bean = createBeanInstance(beanDefinition.getBeanClass());
            beanDefinition.setBean(bean);
            return setProperties(bean, beanDefinition.getPropertyValues());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("init bean error:" + beanDefinition.getBeanName());
            throw new RuntimeException("init bean error:" + beanDefinition.getBeanName());
        }
    }

    /**
     * 使用空构造创建bean实例
     * @param clazz bean对应的class
     * @return bean实例
     */
    private Object createBeanInstance(final Class clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("使用空构造方法创建bean实例失败，" + clazz.getName());
        }
    }

    /**
     * 给bean注入对应的属性信息
     * @param bean bean实例
     * @param propertyValues 属性信息列表
     * @return 注入属性的bean
     */
    private Object setProperties(final Object bean,
                                 final PropertyValues propertyValues) {
        Class clazz = bean.getClass();
        for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
            try {
                Field field = clazz.getDeclaredField(propertyValue.getName());
                field.setAccessible(true);
                Object value = propertyValue.getValue();
                if (value instanceof BeanReference) {
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getName());
                }
                field.set(bean, getRealTypeValue(field.getType(), value));
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("bean set properties error:" + propertyValue.getName());
                throw new RuntimeException("bean set properties error:" + propertyValue.getName());
            }
        }
        return bean;
    }

    /**
     * 根据具体类型进行类型转换，只转换部分基本类型
     * @param type 实际类型
     * @param value 带转换的值
     * @return 具体类型的属性
     */
    private Object getRealTypeValue(final Class type, final Object value) {
        String valueStr = String.valueOf(value);
        if (type == BeanReference.class) {
            BeanReference beanReference = (BeanReference) value;
            return getBean(beanReference.getName());
        } if (type == byte.class) {
            return Byte.parseByte(valueStr);
        } if (type == short.class) {
            return Short.parseShort(valueStr);
        } if (type == int.class) {
            return Integer.parseInt(valueStr);
        } if (type == long.class) {
            return Long.parseLong(valueStr);
        } if (type == float.class) {
            return Float.parseFloat(valueStr);
        } if (type == double.class) {
            return Double.parseDouble(valueStr);
        } else {
            return value;
        }
    }
}
