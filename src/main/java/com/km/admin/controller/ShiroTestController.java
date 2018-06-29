package com.km.admin.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * shiro测试类
 *
 * @author km
 */
@Controller
public class ShiroTestController {

  @RequestMapping({"/", "/index"})
  public String index() {
    return "/index";
  }

  @RequestMapping("/login")
  public String login(HttpServletRequest request) {
    String exception = (String) request.getAttribute("shiroLoginFailure");
    System.out.println("exception=" + exception);
    String msg = "";
    if (exception != null) {
      if (UnknownAccountException.class.getName().equals(exception)) {
        System.out.println("UnknownAccountException -- > 账号不存在：");
        msg = "UnknownAccountException -- > 账号不存在：";
      } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
        System.out.println("IncorrectCredentialsException -- > 密码不正确：");
        msg = "IncorrectCredentialsException -- > 密码不正确：";
      } else {
        msg = "else >> " + exception;
        System.out.println("else -- >" + exception);
      }
    }

    // 此方法不处理登录成功,由shiro进行处理
    return "/login";
  }

  /**
   * 用户查询.
   */
  @RequestMapping("/test/userList")
  @RequiresPermissions("/sys/product/product.page")
  public String userInfo() {
    return "userInfo";
  }

  /**
   * 用户添加;
   */
  @RequestMapping("/userAdd")
  @RequiresPermissions("/sys/product/product.page")
  public String userInfoAdd() {
    return "userInfoAdd";
  }

  /**
   * 用户删除;
   */
  @RequestMapping("/userDel")
  @RequiresPermissions("/sys/product/product.page")
  public String userDel() {
    return "userInfoDel";
  }
}
