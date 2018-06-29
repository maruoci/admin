package com.km.admin;

import com.km.admin.dao.SysAclDao;
import com.km.admin.repository.SysAclRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

  @Autowired
  private SysAclRepository sysAclRepository;

  @Autowired
  private SysAclDao sysAclDao;

  @Test
  public void contextLoads() {
    int count1 = sysAclDao.count();
    long count = sysAclRepository.count();
    System.out.println(count);
    System.out.println(count1);
  }

}
