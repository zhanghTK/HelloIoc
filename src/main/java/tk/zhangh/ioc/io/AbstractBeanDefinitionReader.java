package tk.zhangh.ioc.io;

import tk.zhangh.ioc.bean.BeanDefinition;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 抽象BeanBeanDefinition读取类
 *
 * 提供加载后beanDefinition保存，读取
 * Created by ZhangHao on 2016/11/1.
 */
public abstract class AbstractBeanDefinitionReader
        implements BeanDefinitionReader {

    /**
     *  保存读取的BeanDefinition信息
     *  key：beanName
     *  value：BeanDefinition
     */
    private Map<String, BeanDefinition> registry = new HashMap<>();

    /**
     * 委托具体的ResourceLoader加载BeanDefinition的定义
     */
    private ResourceLoader resourceLoader;

    /**
     * 根据资源加载类创建BeanDefinitionReader对象
     * @param resourceLoader 具体的资源加载类
     */
    protected AbstractBeanDefinitionReader(final ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    /**
     * 获取具体的资源加载类
     * @return 委托的具体ResourceLoader
     */
    public final ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

    /**
     * 获取已读取的所有beanDefinition的bean name
     * @return 所有bean name
     */
    public Set<String> getBeanNames() {
        return registry.keySet();
    }

    /**
     * 根据bean name获得BeanDefinition
     * @param beanName bean name
     * @return beanDefinition
     */
    public BeanDefinition getBeanDefinition(final String beanName) {
        return registry.get(beanName);
    }

    /**
     * （暂时）保存读取到的beanDefinition
     * @param beanName bean name
     * @param beanDefinition beanDefinition
     */
    public void register(final String beanName, final BeanDefinition beanDefinition) {
        registry.put(beanName, beanDefinition);
    }
}
