package com.km.admin.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
}
