package tk.zhangh.ioc.io;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * URL类资源
 *
 * Created by ZhangHao on 2016/10/31.
 */
public class UrlResource implements Resource {

    /**
     * 配置文件URL
     */
    private final URL url;

    /**
     * 根据URL初始化对象
     * @param url 配置文件URL
     */
     public UrlResource(final URL url) {
        this.url = url;
    }

    /**
     * 获取URL类资源输入流
     * @return 输入流
     */
    @Override
    public InputStream getInputStream() {
        try {
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            return urlConnection.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("打开文件失败" + url.getPath());
        }
    }
}
