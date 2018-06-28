package com.km.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 入口应用
 */
@SpringBootApplication
@Controller
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  /**
   * test
   * @return
   */
  @RequestMapping("/hello")
  public String hello(){
    return "index";
  }
}
