package tk.zhangh.ioc.beans;

/**
 * 测试使用的简单Bean
 *
 * 有空构造方法且没有属性依赖
 *
 * Created by ZhangHao on 2016/10/26.
 */
public class HelloWorldServiceImpl implements HelloWorldService {

    @Override
    public String sayHello() {
        String hello = "hello world";
        System.out.println(hello);
        return hello;
    }
}
