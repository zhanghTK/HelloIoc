package tk.zhangh.ioc.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * URL类资源
 *
 * Created by ZhangHao on 2016/10/31.
 */
public class UrlResource implements Resource {

    private final URL url;

     public UrlResource(URL url) {
        this.url = url;
    }

    @Override
    public InputStream getInputStream() {
        try {
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            return urlConnection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("");
        }
    }
}
