package tk.zhangh.ioc.io;

/**
 * BeanDefinition读取接口
 *
 * Created by ZhangHao on 2016/11/1.
 */
public interface BeanDefinitionReader {

    /**
     * 读取具体的beanDefinitions
     * @param location bean信息定义位置
     */
    void loadBeanDefinitions(String location);
}
