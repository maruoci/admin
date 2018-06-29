package com.km.admin.config;

import com.km.admin.model.SysAcl;
import com.km.admin.model.SysRole;
import com.km.admin.model.SysUser;
import java.util.Objects;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * 自定义Realm,重写获取用户信息的方法
 * @author km
 */
public class MyShiroRealm extends AuthorizingRealm {

  /**
   * Authentication 是用来验证用户身份
   * @param token
   * @return
   * @throws AuthenticationException
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
      throws AuthenticationException {
    String username = (String) token.getPrincipal();

    //默认密码 123456
    //TODO 取数据库的密码值
    String password = "4280d89a5a03f812751f504cc10ee8a5";
    SimpleAuthenticationInfo auth = new SimpleAuthenticationInfo(username,password,getName());

    /*String password = "96e79218965eb72c92a549dd5a330112";
    // 从数据库获取salt
    SimpleAuthenticationInfo auth = new SimpleAuthenticationInfo(
        username, password,this.getName());*/
    return auth;
  }

  /**
   * 授权
   * @param principals
   * @return
   */
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    SysUser user = (SysUser) principals.getPrimaryPrincipal();
    for(SysRole role : user.getRoleList()){
      info.addRole(String.valueOf(role.getId()));
      for(SysAcl sysAcl : role.getAclList()){
        info.addStringPermission(sysAcl.getUrl());
      }
    }
    return info;
  }
}
