package tk.zhangh.ioc.io;

import java.io.InputStream;

/**
 * 资源接口
 *
 * Created by ZhangHao on 2016/10/31.
 */
public interface Resource {
    /**
     * 获取资源文件的输入流
     * @return 资源文件输入流
     */
    InputStream getInputStream();
}
