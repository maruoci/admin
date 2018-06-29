package com.km.admin.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * 基于mybatis的数据库操作
 * @author km
 */
@Mapper
@Component
public interface SysAclDao {

  @Select("SELECT COUNT(1) FROM sys_acl")
  int count();
}
