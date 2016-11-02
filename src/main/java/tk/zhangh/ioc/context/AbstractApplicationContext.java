package tk.zhangh.ioc.context;

import tk.zhangh.ioc.factory.AbstractBeanFactory;

/**
 * ApplicationContext基本实现，解析、注册、获取bean
 *
 * bean配置解析、注册在refresh方法实现，具体的实现过程由子类实现
 * bean获取委托给bean工厂，具体bean工厂实现由注入决定
 *
 * Created by ZhangHao on 2016/11/2.
 */
public abstract class AbstractApplicationContext implements ApplicationContext {

    /**
     * bean容器，从中获取bean
     */
    private AbstractBeanFactory beanFactory;

    public AbstractBeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public AbstractApplicationContext(final AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    /**
     * bean配置解析、注册方法
     */
    protected abstract void refresh();

    /**
     * 从bean factory中获取bean
     * @param name bane name
     * @return bean实例
     */
    @Override
    public Object getBean(final String name) {
        return beanFactory.getBean(name);
    }
}
