
#### 创建数据库

  右侧 Database , 点击 `+` 新建 Data Source 选择SQLite（Xerial)。
  
  name ： 数据库名
  file ： 数据库文件存储路径
  url  ： 访问url
  
  PS：下方记得配置SQLite的驱动Driver
  
#### 创建相关表
  
  sys_acl
  sys_acl_module
  sys_dept
  sys_log
  sys_role
  sys_role_acl
  sys_role_user
  sys_user

 这些表为权限管理的核心表，在集成时可以建一个demo表来做测试
 
#### 添加项目的pom依赖

```xml
  <dependency>
    <groupId>org.xerial</groupId>
    <artifactId>sqlite-jdbc</artifactId>
    <version>3.21.0.1</version>
  </dependency>
```

#### 配置spring的驱动

  spring.datasource.driver-class-name=org.sqlite.JDBC
  spring.datasource.url=jdbc:sqlite:D:\PROJECT\02_mine\admin\db\km_admin.db
  		
  
  url配置为我们上面创建表下面的访问url,windows下注意路径修改为反斜杠

#### 结束

  至此，集成已经完毕。测试数据访问，我打算用spring jpa和mybatis都试下。  


  
  