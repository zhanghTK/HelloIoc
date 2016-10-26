package tk.zhangh.ioc.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Bean的包装类型
 *
 * 包含基本bean实例、bean实例对应的Class类信息
 *
 * Created by ZhangHao on 2016/10/26.
 */
public class BeanDefinition {
    private static final Logger logger = LoggerFactory.getLogger(BeanDefinition.class);

    private Object bean;  // bean实例
    private Class<?> beanClass;  // bean对应的Class
    private String beanClassName;  // bean对应的Class名称

    public BeanDefinition(String beanClassName) {
        setBeanClassName(beanClassName);
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
        try {
            this.beanClass = Class.forName(beanClassName);
            bean = beanClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("init bean error," + beanClassName, e);
            throw new RuntimeException("init bean error," + beanClassName, e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BeanDefinition)) return false;

        BeanDefinition that = (BeanDefinition) o;

        if (getBean() != null ? !getBean().equals(that.getBean()) : that.getBean() != null) return false;
        if (getBeanClass() != null ? !getBeanClass().equals(that.getBeanClass()) : that.getBeanClass() != null)
            return false;
        return !(getBeanClassName() != null ? !getBeanClassName().equals(that.getBeanClassName()) : that.getBeanClassName() != null);

    }

    @Override
    public int hashCode() {
        int result = getBean() != null ? getBean().hashCode() : 0;
        result = 31 * result + (getBeanClass() != null ? getBeanClass().hashCode() : 0);
        result = 31 * result + (getBeanClassName() != null ? getBeanClassName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BeanDefinition{" +
                "bean=" + bean +
                ", beanClass=" + beanClass +
                ", beanClassName='" + beanClassName + '\'' +
                '}';
    }
}
