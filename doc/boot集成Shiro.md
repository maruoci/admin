
### 添加依赖

```xml
<!-- shiro 依赖-->
<dependency>
  <groupId>org.apache.shiro</groupId>
  <artifactId>shiro-spring</artifactId>
  <version>1.4.0</version>
</dependency>
```

### 建立实体类

```java
/**
 * 权限表
 *
 * @author km
 */
@Data
@ToString
@Entity
public class SysAcl extends BaseModel {
  @Id
  @GeneratedValue
  private long id;
  private String code;
  private String name;
  // ... 省略 其他 ... 
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name="sysRoleAcl",joinColumns = {@JoinColumn(name="aclId")}, inverseJoinColumns = {@JoinColumn(name="roleId")})
  private List<SysRole> roleList;
}

@Entity
@Data
@ToString
public class SysRole extends BaseModel {
  @Id
  @GeneratedValue
  private long id;
  private String name;
  // - 省略...其他
  /** 用户列表 **/
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name="sysRoleUser",joinColumns = {@JoinColumn(name="roleId")}, inverseJoinColumns = {@JoinColumn(name="userId")})
  private List<SysUser> userList;
  /** 权限列表 **/
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name="sysRoleAcl",joinColumns = {@JoinColumn(name="roleId")}, inverseJoinColumns = {@JoinColumn(name="aclId")})
  private List<SysAcl> aclList;
}
/**
 * 用户实体类
 * @author km
 */
@Entity
@Data
@ToString
public class SysUser extends BaseModel {
  @Id
  @GeneratedValue
  private long id;
  private String username;
  private String password;
  // - 省略...其他
  /*** hibernate 关系映射列 ***/
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name="sysRoleUser",joinColumns = {@JoinColumn(name="userId")}, inverseJoinColumns = {@JoinColumn(name="roleId")})
  private List<SysRole> roleList;
}
```

### 配置Shiro
