package tk.zhangh.ioc.io;

import java.net.URL;

/**
 * 资源加载类
 *
 * Created by ZhangHao on 2016/10/31.
 */
public class ResourceLoader {

    public Resource getResource(String location) {
        URL resource = this.getClass().getClassLoader().getResource(location);
        return new UrlResource(resource);
    }
}
