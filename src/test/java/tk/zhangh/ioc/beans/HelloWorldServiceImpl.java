package tk.zhangh.ioc.beans;

/**
 * 测试使用的简单Bean
 *
 * 有空构造方法且包含简单属性
 *
 * Created by ZhangHao on 2016/10/26.
 */
public class HelloWorldServiceImpl implements HelloWorldService {
    private int anInt;
    private double aDouble;
    private String aString;

    public int getAnInt() {
        return anInt;
    }

    public void setAnInt(int anInt) {
        this.anInt = anInt;
    }

    public double getaDouble() {
        return aDouble;
    }

    public void setaDouble(double aDouble) {
        this.aDouble = aDouble;
    }

    public String getaString() {
        return aString;
    }

    public void setaString(String aString) {
        this.aString = aString;
    }

    @Override
    public String sayHello() {
        StringBuilder hello = new StringBuilder("hello world:");
        hello.append("anInt:").append(anInt).append(",")
                .append("aDouble:").append(aDouble).append(",")
                .append("aString:").append(aString);
        System.out.println(hello);
        return hello.toString();
    }
}
