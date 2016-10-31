package tk.zhangh.ioc.io;

import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;

/**
 * 资源加载类测试
 *
 * Created by ZhangHao on 2016/10/31.
 */
public class ResourceLoaderTest {

    @Test
    public void get_xml_resource() throws Exception {
        ResourceLoader resourceLoader = new ResourceLoader();
        Resource resource = resourceLoader.getResource("ioc.xml");
        InputStream inputStream = resource.getInputStream();
        Assert.assertNotNull(inputStream);
    }
}