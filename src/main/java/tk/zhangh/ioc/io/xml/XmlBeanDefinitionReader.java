package tk.zhangh.ioc.io.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import tk.zhangh.ioc.bean.BeanDefinition;
import tk.zhangh.ioc.bean.BeanReference;
import tk.zhangh.ioc.bean.PropertyValue;
import tk.zhangh.ioc.io.AbstractBeanDefinitionReader;
import tk.zhangh.ioc.io.ResourceLoader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;

/**
 * XML形式的BeanDefinition定义读取类
 *
 * Created by ZhangHao on 2016/11/1.
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    private static final Logger logger = LoggerFactory.getLogger(XmlBeanDefinitionReader.class);

    public XmlBeanDefinitionReader(final ResourceLoader resourceLoader) {
        super(resourceLoader);
    }

    /**
     * 读取XML形式配置的beanDefinitions
     * @param location bean信息定义位置
     */
    @Override
    public void loadBeanDefinitions(final String location) {
        InputStream inputStream = getResourceLoader().getResource(location).getInputStream();
        try {
            doLoadBeanDefinitions(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("根据配置文件加载bean信息错误：" + location, e);
            throw new RuntimeException("根据配置文件加载bean信息错误：" + location, e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据输入流加载beanDefinitions定义
     * @param inputStream 输入流
     * @throws Exception 读取xml形式beanDefinition信息错误
     */
    protected void doLoadBeanDefinitions(final InputStream inputStream) throws Exception {
        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = docBuilder.parse(inputStream);
        registerBeanDefinitions(doc);
        inputStream.close();
    }


    /**
     * 注册读取xml文件的beanDefinition
     * @param doc xml文件
     */
    public void registerBeanDefinitions(final Document doc) {
        Element root = doc.getDocumentElement();
        parseBeanDefinitions(root);
    }

    /**
     * 从根节点解析BeanDefinition信息
     * @param root 根节点
     */
    protected void parseBeanDefinitions(final Element root) {
        NodeList nodeList = root.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node instanceof Element) {
                Element ele = (Element) node;
                processBeanDefinition(ele);
            }
        }
    }

    /**
     * 解析具体的beanDefinition定义信息，创建bean实例
     * @param ele 具体的beanDefinition定义节点
     */
    protected void processBeanDefinition(final Element ele) {
        String name = ele.getAttribute("name");
        String className = ele.getAttribute("class");
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName(className);
        beanDefinition.setBeanName(name);
        processProperty(ele, beanDefinition);
        register(name, beanDefinition);
    }

    /**
     * 解析并创建bean依赖的属性
     * @param ele 具体的beanDefinition定义节点
     * @param beanDefinition 已创建待注入属性的beanDefinition实例
     */
    private void processProperty(final Element ele, final BeanDefinition beanDefinition) {
        NodeList propertyNode = ele.getElementsByTagName("property");
        for (int i = 0; i < propertyNode.getLength(); i++) {
            Node node = propertyNode.item(i);
            if (node instanceof Element) {
                Element propertyEle = (Element) node;
                String name = propertyEle.getAttribute("name");
                String ref = propertyEle.getAttribute("ref");
                String value = propertyEle.getAttribute("value");
                injectProperty(beanDefinition, name, ref, value);
            }
        }
    }

    /**
     * beanDefinition注入属性值
     * @param beanDefinition beanDefinition
     * @param name beanName
     * @param ref bean引用
     * @param value bean值
     */
    private void injectProperty(final BeanDefinition beanDefinition,
                                final String name, final String ref, final String value) {
        if (value != null && value.length() > 0) {
            beanDefinition.addPropertyValue(new PropertyValue(name, value));
        }
        if (ref != null && ref.length() > 0) {
            beanDefinition.addPropertyValue(new PropertyValue(name, new BeanReference(ref)));
        }
    }
}
