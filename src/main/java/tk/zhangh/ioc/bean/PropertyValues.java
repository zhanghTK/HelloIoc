package tk.zhangh.ioc.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Bean实例的所有属性信息
 *
 * Created by ZhangHao on 2016/10/27.
 */
public class PropertyValues {
    private final List<PropertyValue> propertyValues = new ArrayList<>();

    public void addPropertyValue(PropertyValue pv) {
        this.propertyValues.add(pv);
    }

    public List<PropertyValue> getPropertyValues() {
        return this.propertyValues;
    }
}
