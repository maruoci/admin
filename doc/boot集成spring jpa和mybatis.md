
### 集成 Spring JPA

#### 添加依赖

```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
```

#### 配置config

```
## 配置Spring JPA
    ## ddl-auto :
#  create 启动时删除上一次库表并重新生成  create-drop 启动生成表，关闭后删除
#  update 启动时根据实体类生成表，实体类属性变动时表结构更新 validate 启动时验证实体类与表是否一致
#  none 不采取任何措施
## 定义方言(**Hibernate没有定义SQLite的方言，如果使用SQLite库，需要自定义方言**)
spring.jpa.database-platform=com.km.admin.dialect.SQLiteDialect
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
# 控制器输出的json格式更美观
spring.jackson.serialization.indent-output=true
```

#### 定义实体类

```java
@Data
@ToString
@Entity
public class SysAcl extends BaseModel {
  /** 主键: 权限id **/
  @Id
  @GeneratedValue
  private long id;

  /** 权限码 **/
  private String code;

  /** 权限名称 **/
  private String name;

  /** 权限所在的权限模块ID **/
  private int aclModuleId;

  /** 请求url, 支持正则 **/
  private String url;

  /** 权限类型 : 1: 菜单 2: 按钮  3: 其他 **/
  private int type;
}
```

 使用lombok插件, 简化代码。
 
 配置`@Entity`属性，指明这是一个和库表映射的实体。
 
 `@Id`注解指明属性映射为数据库的主键。
 
 `@GeneratedValue`注解默认使用主键生成方式为自增。Hibernate会自动生成一个名为HIBERNATE_SEQUENCE的序列。

#### 定义个人的Repository

```java
/* Hibernate 封装了一些基础的sql操作*/
public interface SysAclRepository extends JpaRepository<SysAcl,Long> {}
```

#### 定义测试方法

```java
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

  @Autowired
  private SysAclRepository sysAclRepository;

  @Test
  public void contextLoads() {
    long count = sysAclRepository.count();
    System.out.println(count);
  }

}
```

测试通过。

结尾附上自定义的SQLite

```java
public class SQLiteDialect extends Dialect {
    public SQLiteDialect() {
      super();
      registerColumnType(Types.BIT, "integer");
      registerColumnType(Types.TINYINT, "tinyint");
      registerColumnType(Types.SMALLINT, "smallint");
      registerColumnType(Types.INTEGER, "integer");
      registerColumnType(Types.BIGINT, "bigint");
      registerColumnType(Types.FLOAT, "float");
      registerColumnType(Types.REAL, "real");
      registerColumnType(Types.DOUBLE, "double");
      registerColumnType(Types.NUMERIC, "numeric");
      registerColumnType(Types.DECIMAL, "decimal");
      registerColumnType(Types.CHAR, "char");
      registerColumnType(Types.VARCHAR, "varchar");
      registerColumnType(Types.LONGVARCHAR, "longvarchar");
      registerColumnType(Types.DATE, "date");
      registerColumnType(Types.TIME, "time");
      registerColumnType(Types.TIMESTAMP, "timestamp");
      registerColumnType(Types.BINARY, "blob");
      registerColumnType(Types.VARBINARY, "blob");
      registerColumnType(Types.LONGVARBINARY, "blob");
      registerColumnType(Types.BLOB, "blob");
      registerColumnType(Types.CLOB, "clob");
      registerColumnType(Types.BOOLEAN, "integer");
      registerFunction("concat", new VarArgsSQLFunction(StringType.INSTANCE, "", "||", ""));
      registerFunction("mod", new SQLFunctionTemplate(IntegerType.INSTANCE, "?1 % ?2"));
      registerFunction("substr", new StandardSQLFunction("substr", StringType.INSTANCE));
      registerFunction("substring", new StandardSQLFunction("substr", StringType.INSTANCE));
    }

    public boolean supportsIdentityColumns() {
      return true;
    }

    /*
     public boolean supportsInsertSelectIdentity() {
     return true; // As specify in NHibernate dialect
     }
     */

    public boolean hasDataTypeInIdentityColumn() {
      return false; // As specify in NHibernate dialect
    }

    /*
     public String appendIdentitySelectToInsert(String insertString) {
     return new StringBuffer(insertString.length()+30). // As specify in NHibernate dialect
     append(insertString).
     append("; ").append(getIdentitySelectString()).
     toString();
     }
     */

    public String getIdentityColumnString() {
      // return "integer primary key autoincrement";
      return "integer";
    }

    public String getIdentitySelectString() {
      return "select last_insert_rowid()";
    }

    public boolean supportsLimit() {
      return true;
    }

    public String getLimitString(String query, boolean hasOffset) {
      return new StringBuffer(query.length() + 20).append(query).append(
          hasOffset ? " limit ? offset ?" : " limit ?").toString();
    }

    public boolean supportsTemporaryTables() {
      return true;
    }

    public String getCreateTemporaryTableString() {
      return "create temporary table if not exists";
    }

    public boolean dropTemporaryTableAfterUse() {
      return false;
    }

    @Override
    public boolean supportsCurrentTimestampSelection() {
      return true;
    }

    @Override
    public boolean isCurrentTimestampSelectStringCallable() {
      return false;
    }

    @Override
    public String getCurrentTimestampSelectString() {
      return "select current_timestamp";
    }

    @Override
    public boolean supportsUnionAll() {
      return true;
    }

    @Override
    public boolean hasAlterTable() {
      return false; // As specify in NHibernate dialect
    }

    @Override
    public boolean dropConstraints() {
      return false;
    }

    @Override
    public String getAddColumnString() {
      return "add column";
    }

    @Override
    public String getForUpdateString() {
      return "";
    }

    @Override
    public boolean supportsOuterJoinForUpdate() {
      return false;
    }

    @Override
    public String getDropForeignKeyString() {
      throw new UnsupportedOperationException(
          "No drop foreign key syntax supported by SQLiteDialect");
    }

    @Override
    public String getAddForeignKeyConstraintString(String constraintName,
        String[] foreignKey, String referencedTable, String[] primaryKey,
        boolean referencesPrimaryKey) {
      throw new UnsupportedOperationException(
          "No add foreign key syntax supported by SQLiteDialect");
    }

    @Override
    public String getAddPrimaryKeyConstraintString(String constraintName) {
      throw new UnsupportedOperationException(
          "No add primary key syntax supported by SQLiteDialect");
    }

    @Override
    public boolean supportsIfExistsBeforeTableName() {
      return true;
    }

    @Override
    public boolean supportsCascadeDelete() {
      return false;
    }
  }
```

### 基于mybatis的配置

#### 添加pom依赖

```xml
<dependency>
  <groupId>org.mybatis.spring.boot</groupId>
  <artifactId>mybatis-spring-boot-starter</artifactId>
  <version>1.3.2</version>
</dependency>
```

#### 编写dao文件

```java
@Mapper
@Component
public interface SysAclDao {

  @Select("SELECT COUNT(1) FROM sys_acl")
  int count();
}
```

#### 编写测试类

```java
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

  @Autowired
  private SysAclDao sysAclDao;

  @Test
  public void contextLoads() {
    int count = sysAclDao.count();
    System.out.println(count);
  }

}
```

测试完成。OK。

### 疑问点:

配置mybatis一般都配置`MapperScan`,这里我并没有配置，但是程序是可用的。暂时还不清楚为什么。

### 总结

目前看，Jpa相对简单些。这个项目里我打算使用JPA。
