package com.baek.proj.handler;

import java.util.Iterator;
import com.baek.driver.Statement;
import com.baek.proj.domain.Employee;

public class StoreListHandler implements Command {

  Statement stmt;

  public StoreListHandler(Statement stmt) {
    this.stmt = stmt;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[지점 목록]");

    Iterator<String> results = stmt.executeQuery("store/selectall");

    while (results.hasNext()) {
      String[] fields = results.next().split(",");
      // 번호, 지점명, 주소, 전화번호
      System.out.printf("%s. %s점 %s TEL.%s\n", 
          fields[0],
          fields[1],
          fields[2],
          Employee.phoneFormat(fields[3]));
    } 
  } 
}
