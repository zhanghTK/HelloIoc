# HelloIoc

## simple Ioc bean context

---

### How to Use?

Like Spring:

```Java

ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ioc.xml");
HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldOutputService");
helloWorldService.sayHello();

```