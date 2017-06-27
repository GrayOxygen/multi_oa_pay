# mybatis-spring-adpater-example
写了个mybatis-spring-adpater框架（mybatis官方出的整合框架，相当于一个“适配器”）的例子，便于快速整合，配置更简化。
例子中采用mybatis的注解形式，数据库使用的是mysql。 

## 使用方法
1. 将本工程clone到本地
2. 执行maven命令mvn clean package  -Dmaven.test.skip=true cargo:run，即启动了tomcat8服务器（已配置cargo自动下载tomcat8）
3. 浏览器可查看localhost:8080/wang/hi 可看到主页
4. 将工程导入你的IDE，跑单元测试，即可熟悉CRUD操作

