package com.baek.proj.handler;

import com.baek.driver.Statement;
import com.baek.proj.domain.Employee;
import com.baek.util.Prompt;

public class EmployeeUpdateHandler implements Command {

  Statement stmt;

  public EmployeeUpdateHandler(Statement stmt) {
    this.stmt = stmt;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[사원 수정]");

    int no = Prompt.inputInt("번호> ");

    String[] fields = stmt.executeQuery("employee/select", Integer.toString(no)).next().split(",");

    String name = Prompt.inputString(String.format("이름(%s)> ", fields[1]));
    String dept = Prompt.inputString(String.format("부서(%s)> ", fields[2]));
    String email = Prompt.inputString(String.format("메일(%s)> ", fields[3]));
    String phone = Prompt.inputString(String.format("전화번호(%s)> ", 
        Employee.phoneFormat(fields[4])));

    String input = Prompt.inputString("변경하시겠습니까?(Y/N) ");
    if (!input.equalsIgnoreCase("Y")) {
      System.out.println("수정을 취소하였습니다.");
      return;
    }

    stmt.executeUpdate("employee/update", String.format("%d,%s,%s,%s,%s", 
        no, name, dept, email, phone));
    System.out.println("사원 정보를 변경하였습니다.");
  }
}
