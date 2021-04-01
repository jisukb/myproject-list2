package com.baek.proj.handler;

import java.util.Iterator;
import com.baek.driver.Statement;
import com.baek.proj.domain.Employee;

public class EmployeeListHandler implements Command {

  Statement stmt;

  public EmployeeListHandler(Statement stmt) {
    this.stmt = stmt;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[사원 목록]");

    Iterator<String> results = stmt.executeQuery("employee/selectall");

    while (results.hasNext()) {
      String[] fields = results.next().split(",");
      // 번호, 이름, 부서, 이메일, 전화번호, 입사일
      System.out.printf("%s. %s (%s부) %s, %s, %s 입사\n", 
          fields[0],
          fields[1],
          fields[2],
          fields[3],
          Employee.phoneFormat(fields[4]),
          fields[5]);
    }
  }
}
