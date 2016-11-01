package tk.zhangh.ioc.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Bean的包装类型
 *
 * 包含基本bean实例、bean实例对应的Class类信息、bean实例的基本类型（含String）属性
 *
 * Created by ZhangHao on 2016/10/26.
 */
public class BeanDefinition {
    private static final Logger logger = LoggerFactory.getLogger(BeanDefinition.class);

    /**
     * bean名称（Id）
     */
    private String beanName;

    /**
     * bean实例
     */
    private Object bean;

    /**
     * bean对应的Class
     */
    private Class<?> beanClass;

    /**
     * bean对应的Class名称
     */
    private String beanClassName;

    /**
     * bean实例所有属性
     */
    private PropertyValues propertyValues = new PropertyValues();

    public static Logger getLogger() {
        return logger;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(final String beanName) {
        this.beanName = beanName;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(final Object bean) {
        this.bean = bean;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(final Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(final String beanClassName) {
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

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(final PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof BeanDefinition)) return false;

        BeanDefinition that = (BeanDefinition) o;

        if (getBeanName() != null ? !getBeanName().equals(that.getBeanName()) : that.getBeanName() != null)
            return false;
        if (getBean() != null ? !getBean().equals(that.getBean()) : that.getBean() != null) return false;
        if (getBeanClass() != null ? !getBeanClass().equals(that.getBeanClass()) : that.getBeanClass() != null)
            return false;
        if (getBeanClassName() != null ? !getBeanClassName().equals(that.getBeanClassName()) : that.getBeanClassName() != null)
            return false;
        return !(getPropertyValues() != null ? !getPropertyValues().equals(that.getPropertyValues()) : that.getPropertyValues() != null);

    }

    @Override
    public int hashCode() {
        int result = getBeanName() != null ? getBeanName().hashCode() : 0;
        result = 31 * result + (getBean() != null ? getBean().hashCode() : 0);
        result = 31 * result + (getBeanClass() != null ? getBeanClass().hashCode() : 0);
        result = 31 * result + (getBeanClassName() != null ? getBeanClassName().hashCode() : 0);
        result = 31 * result + (getPropertyValues() != null ? getPropertyValues().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BeanDefinition{" +
                "beanName='" + beanName + '\'' +
                ", bean=" + bean +
                ", beanClass=" + beanClass +
                ", beanClassName='" + beanClassName + '\'' +
                ", propertyValues=" + propertyValues +
                '}';
    }
}
