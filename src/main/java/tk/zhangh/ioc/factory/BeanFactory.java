package tk.zhangh.ioc.factory;

/**
 * Bean工厂接口
 *
 * Created by ZhangHao on 2016/10/26.
 */
public interface BeanFactory {
    Object getBean(String name);
}
