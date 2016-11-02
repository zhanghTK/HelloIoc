package tk.zhangh.ioc.xml;

import org.junit.Assert;
import org.junit.Test;
import tk.zhangh.ioc.io.ResourceLoader;
import tk.zhangh.ioc.io.xml.XmlBeanDefinitionReader;

import java.util.Set;

/**
 * XmlBeanDefinitionReader测试方法
 *
 * 测试XmlBeanDefinitionReader读取bean配置信息
 *
 * Created by ZhangHao on 2016/11/1.
 */
public class XmlBeanDefinitionReaderTest {

    @Test
    public void read_xml() throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions("ioc.xml");
        Set<String> beanNames = xmlBeanDefinitionReader.getBeanNames();
        Assert.assertTrue(beanNames.size() > 0);
    }

}