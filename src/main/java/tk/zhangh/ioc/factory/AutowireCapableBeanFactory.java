package tk.zhangh.ioc.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.zhangh.ioc.bean.BeanDefinition;

/**
 * AbstractBeanFactory的基本实现
 *
 * 根据BeanDefinition信息实例化bean对象
 *
 * Created by ZhangHao on 2016/10/26.
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

    private static final Logger logger = LoggerFactory.getLogger(AutowireCapableBeanFactory.class);

    @Override
    protected Object doCreateBean(BeanDefinition beanDefinition) {
        try {
            logger.info("instantiate BeanDefinition:" + beanDefinition.getBeanName());
            return beanDefinition.getBeanClass().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("init bean error," + beanDefinition.getBeanName(), e);
        }
    }
}
