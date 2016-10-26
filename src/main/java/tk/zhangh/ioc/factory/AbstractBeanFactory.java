package tk.zhangh.ioc.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.zhangh.ioc.bean.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 抽象Bean工厂
 * 提供基本的Bean注册，获取方法
 *
 * 要求Bean必须有无参数构造的方法，并且Bean本身是简单的，没有其他属性，不存在依赖依赖关系
 *
 * Created by ZhangHao on 2016/10/26.
 */
public class AbstractBeanFactory implements BeanFactory {

    private static final Logger logger = LoggerFactory.getLogger(AbstractBeanFactory.class);

    // 缓存Bean实例
    private Map<String, BeanDefinition> beanContext = new ConcurrentHashMap<>();

    /**
     * 从Bean工厂获取Bean实例
     * @param name bean名称
     * @return bean实例
     */
    public Object getBean(String name) {
        BeanDefinition beanDefinition = beanContext.get(name);
        if (beanDefinition == null) {
            logger.error("get bean error:" + name);
            throw new RuntimeException("not found bean:" + name);
        }
        return beanDefinition.getBean();
    }

    /**
     * 向Bean工厂注册Bean，
     * @param name Bean id，在容器内唯一
     * @param beanDefinition Bean包装类
     */
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        logger.info("register bean:" + name);
        beanContext.put(name, beanDefinition);
    }
}
