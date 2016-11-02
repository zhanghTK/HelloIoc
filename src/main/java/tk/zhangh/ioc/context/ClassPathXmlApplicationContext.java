package tk.zhangh.ioc.context;

import tk.zhangh.ioc.factory.AbstractBeanFactory;
import tk.zhangh.ioc.factory.AutowireCapableBeanFactory;
import tk.zhangh.ioc.io.ResourceLoader;
import tk.zhangh.ioc.io.xml.XmlBeanDefinitionReader;

/**
 * ApplicationContext实现
 *
 * refresh实现中使用xmlBeanDefinitionReader读取xml形式的bean配置
 * 未指定bean工厂时使用默认的AutowireCapableBeanFactory
 *
 * Created by ZhangHao on 2016/11/2.
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    /**
     * 配置文件位置
     */
    private String configLocation;

    /**
     * 使用配置文件位置初始化ClassPathXmlApplicationContext
     * 使用AutowireCapableBeanFactory作为默认BeanFactory
     * @param configLocation 配置文件位置
     */
    public ClassPathXmlApplicationContext(final String configLocation) {
        this(configLocation, new AutowireCapableBeanFactory());
    }

    /**
     * 使用配置文件位置，具体的BeanFactory初始化ClassPathXmlApplicationContext
     * @param configLocation 配置文件位置
     * @param beanFactory BeanFactory
     */
    public ClassPathXmlApplicationContext(final String configLocation, final AbstractBeanFactory beanFactory) {
        super(beanFactory);
        this.configLocation = configLocation;
        refresh();
    }

    /**
     * 使用XmlBeanDefinitionReader解析XML形式的Bean配置
     * 向Bean工厂注册解析获取的BeanDefinition
     */
    @Override
    public void refresh() {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions(configLocation);
        for (String id : xmlBeanDefinitionReader.getBeanNames()) {
            getBeanFactory().registerBeanDefinition(id, xmlBeanDefinitionReader.getBeanDefinition(id));
        }
    }
}
