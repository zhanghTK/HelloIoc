package tk.zhangh.ioc.bean;

/**
 * Bean依赖的包装类型
 *
 * Created by ZhangHao on 2016/11/2.
 */
public class BeanReference {

    /**
     * 依赖的bean name
     */
    private String name;

    /**
     * 依赖的bean对象
     */
    private Object bean;

    public BeanReference(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(final Object bean) {
        this.bean = bean;
    }
}
