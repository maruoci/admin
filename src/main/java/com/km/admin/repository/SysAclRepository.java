package com.km.admin.repository;

import com.km.admin.model.SysAcl;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * SysAcl 相关数据库操作
 * @author km
 */
public interface SysAclRepository extends JpaRepository<SysAcl,Long> {

}
