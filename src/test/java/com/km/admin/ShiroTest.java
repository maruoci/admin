package com.km.admin;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Base64;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.Factory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Base64Utils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroTest {

  // 用户登陆和退出，这里我自定了一个realm（开发肯定需要自定义realm获取数据库密码和权限）
  @Test
  public void testCustomRealmMd5() {

    // 创建securityManager工厂，通过ini配置文件创建securityManager工厂
    Factory<SecurityManager> factory = new IniSecurityManagerFactory(
        "classpath:shiro-test.ini");

    // 创建SecurityManager
    SecurityManager securityManager = factory.getInstance();

    // 将securityManager设置当前的运行环境中
    SecurityUtils.setSecurityManager(securityManager);

    // 从SecurityUtils里边创建一个subject
    Subject subject = SecurityUtils.getSubject();

    // 在认证提交前准备token（令牌）
    // 这里的账号和密码 将来是由用户输入进去
    UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "111111");

    try {
      // 执行认证提交
      subject.login(token);
    } catch (AuthenticationException e) {
      e.printStackTrace();
    }

    // 是否认证通过
    boolean isAuthenticated = subject.isAuthenticated();

    System.out.println("加密后是否认证通过：" + isAuthenticated);
    // 退出操作
    subject.logout();
    // 是否认证通过
    isAuthenticated = subject.isAuthenticated();
    System.out.println("退出后是否认证通过：" + isAuthenticated);
  }

  public static void main(String[] args) throws UnsupportedEncodingException {
    //原始 密码
    String source = "123456";
    //盐
    String salt = "qwerty";

    //散列次数（和ini中credentialsMatcher.hashIterations=1一致）
    int hashIterations = 2;
    //上边散列1次：f3694f162729b7d0254c6e40260bf15c
    //上边散列2次：36f2dfa24d0a9fa97276abbe13e596fc

    //第一个参数：散列算法
    //这里的散列算法是md5要和ini中credentialsMatcher.hashAlgorithmName=md5一致)
    //第二个参数：明文，原始密码
    //第三个参数：盐，通过使用随机数
    //第四个参数：散列的次数，比如散列两次，相当 于md5(md5(''))
    SimpleHash simpleHash = new SimpleHash("md5", source, null, hashIterations);
    System.out.println(simpleHash.toString());

    System.out.println(ByteSource.Util.bytes("123456"));

    HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
    //散列算法:这里使用MD5算法;
    hashedCredentialsMatcher.setHashAlgorithmName("md5");
    //散列的次数，比如散列两次，相当于 md5(md5(""));
    hashedCredentialsMatcher.setHashIterations(2);
  }
}
