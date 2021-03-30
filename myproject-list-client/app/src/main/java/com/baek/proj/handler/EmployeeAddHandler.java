package com.baek.proj.handler;

import com.baek.driver.Statement;
import com.baek.proj.domain.Employee;
import com.baek.util.Prompt;

public class EmployeeAddHandler implements Command {

  Statement stmt;

  public EmployeeAddHandler(Statement stmt) {
    this.stmt = stmt;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[사원 등록]");

    Employee e = new Employee();

    e.setName(Prompt.inputString("이름> "));
    e.setDept(Prompt.inputString("부서> "));
    e.setEmail(Prompt.inputString("메일> "));
    e.setPhone(Prompt.inputString("전화번호> "));
    e.setJoinDate(Prompt.inputDate("입사일> "));

    stmt.executeUpdate("employee/insert", String.format("%s,%s,%s,%s,%s", 
        e.getName(), 
        e.getDept(), 
        e.getEmail(), 
        e.getPhone(), 
        e.getJoinDate())); 

    System.out.println("사원을 등록하였습니다.");
  }
}
