package com.baek.proj.handler;

import com.baek.driver.Statement;
import com.baek.proj.domain.Employee;
import com.baek.util.Prompt;

public class EmployeeDetailHandler implements Command {

  Statement stmt;

  public EmployeeDetailHandler(Statement stmt) {
    this.stmt = stmt;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[사원 상세]");

    int no = Prompt.inputInt("번호> ");

    String[] fields = stmt.executeQuery("employee/select", Integer.toString(no)).next().split(",");

    System.out.printf("이름: %s\n", fields[1]);
    System.out.printf("부서: %s\n", fields[2]);
    System.out.printf("메일: %s\n", fields[3]);
    System.out.printf("전화번호: %s\n", 
        //fields[4].replaceFirst("(\\d{3})(\\d{4})(\\d+)", "$1-$2-$3"));
        Employee.phoneFormat(fields[4]));
    System.out.printf("입사일: %s\n", fields[5]);
  }
}
