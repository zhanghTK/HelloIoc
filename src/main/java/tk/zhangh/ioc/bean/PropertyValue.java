package tk.zhangh.ioc.bean;

/**
 * Bean实例的属性信息
 *
 * 名称和值对应关系
 *
 * Created by ZhangHao on 2016/10/27.
 */
public class PropertyValue {
    private final String name;

    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
