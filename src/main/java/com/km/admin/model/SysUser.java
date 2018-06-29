package com.km.admin.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.Data;
import lombok.ToString;

/**
 * 用户实体类
 * @author km
 */
@Entity
@Data
@ToString
public class SysUser extends BaseModel {

  /** 主键 用户id**/
  @Id
  @GeneratedValue
  private long id;

  /** 用户名 **/
  private String username;

  /** 电话 **/
  private String telephone;

  /** 邮箱 **/
  private String mail;

  /** 密码 **/
  private String password;

  /** 所属部门id **/
  private String deptId;

  /*** hibernate 关系映射列 ***/
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name="sysRoleUser",joinColumns = {@JoinColumn(name="userId")}, inverseJoinColumns = {@JoinColumn(name="roleId")})
  private List<SysRole> roleList;
}
