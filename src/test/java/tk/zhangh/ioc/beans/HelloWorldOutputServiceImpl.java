package tk.zhangh.ioc.beans;

/**
 * 测试使用的简单Bean
 *
 * 有空构造方法且包含简单属性以及其他bean依赖
 *
 * Created by ZhangHao on 2016/11/2.
 */
public class HelloWorldOutputServiceImpl implements HelloWorldService {

    private String name;

    private OutputService outputService;

    public void setName(String name) {
        this.name = name;
    }

    public void setOutputService(OutputService outputService) {
        this.outputService = outputService;
    }

    @Override
    public String sayHello() {
        return outputService.output(name);
    }
}
