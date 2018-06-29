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
 * 角色实体类
 * @author km
 */
@Entity
@Data
@ToString
public class SysRole extends BaseModel {

  /** 主键 角色id **/
  @Id
  @GeneratedValue
  private long id;

  /** 角色名 **/
  private String name;

  /** 角色类型 1：管理员 2：其他**/
  private int type;

  /*** 角色与 权限、用户的关系映射 ***/

  /** 用户列表 **/
  @ManyToMany
  @JoinTable(name="sysRoleUser",joinColumns = {@JoinColumn(name="roleId")}, inverseJoinColumns = {@JoinColumn(name="userId")})
  private List<SysUser> userList;

  /** 权限列表 **/
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name="sysRoleAcl",joinColumns = {@JoinColumn(name="roleId")}, inverseJoinColumns = {@JoinColumn(name="aclId")})
  private List<SysAcl> aclList;
}
