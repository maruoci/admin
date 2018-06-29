package com.km.admin.model;

import lombok.Data;

/**
 * 父类
 *
 * @author km
 */
@Data
public class BaseModel {

  /** 顺序 由小到大**/
  private int seq;

  /** 状态 1：正常 0：冻结 **/
  private int status;

  /** 备注 **/
  private String remark;

  /** 操作者 **/
  private String operator;

  /** 最近一次更新时间 **/
  private String operateTime;

  /** 最近一次更新ip **/
  private String operateIp;
}
