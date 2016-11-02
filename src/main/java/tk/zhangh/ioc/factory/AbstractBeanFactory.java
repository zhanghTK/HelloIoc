package tk.zhangh.ioc.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.zhangh.ioc.bean.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 抽象Bean工厂
 *
 * 提供基本的Bean注册，获取方法
 *
 * Created by ZhangHao on 2016/10/26.
 */
public abstract class AbstractBeanFactory implements BeanFactory {

    private static final Logger logger = LoggerFactory.getLogger(AbstractBeanFactory.class);

    /**
     * 缓存读取的bean
     */
    private Map<String, BeanDefinition> beanContext = new ConcurrentHashMap<>();

    /**
     * 从Bean工厂获取Bean实例
     * @param name bean名称
     * @return bean实例
     */
    public Object getBean(final String name) {
        BeanDefinition beanDefinition = beanContext.get(name);
        if (beanDefinition == null) {
            logger.error("get bean error:" + name);
            throw new RuntimeException("not found bean:" + name);
        }
        Object bean = beanDefinition.getBean();
        if (bean == null) {
            bean = doCreateBean(beanDefinition);
        }
        return bean;
    }

    /**
     * 向Bean工厂注册Bean，
     * @param name Bean id，在容器内唯一
     * @param beanDefinition Bean包装类
     */
    public void registerBeanDefinition(final String name, final BeanDefinition beanDefinition) {
        beanContext.put(name, beanDefinition);
    }

    /**
     * 与实例化Bean工厂内的所有未实例化bean
     */
    public void preInstantiateSingletons() {
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : beanContext.entrySet()) {
            String beanName = beanDefinitionEntry.getKey();
            getBean(beanName);
        }
    }

    /**
     * 抽象方法，根据BeanDefinition实例化bean对象
     *
     * 由具体BeanFactory实现
     *
     * @param beanDefinition BeanDefinition
     * @return bean实例对象
     */
    abstract protected Object doCreateBean(BeanDefinition beanDefinition);
}
