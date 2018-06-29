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
 * 权限表
 *
 * @author km
 */
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

  /*** 权限 与 角色的关系映射 ***/

  /** 权限列表 **/
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name="sysRoleAcl",joinColumns = {@JoinColumn(name="aclId")}, inverseJoinColumns = {@JoinColumn(name="roleId")})
  private List<SysRole> roleList;
}
