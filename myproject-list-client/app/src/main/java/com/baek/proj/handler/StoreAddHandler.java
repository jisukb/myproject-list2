package com.baek.proj.handler;

import com.baek.driver.Statement;
import com.baek.proj.domain.Store;
import com.baek.util.Prompt;

public class StoreAddHandler implements Command {

  Statement stmt;
  EmployeeValidator employeeValidator;

  public StoreAddHandler(Statement stmt, EmployeeValidator employeeValidator) {
    this.stmt = stmt;
    this.employeeValidator = employeeValidator;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[지점 등록]");

    Store s = new Store();

    s.setName(Prompt.inputString("지점명> "));
    s.setAddress(Prompt.inputString("주소> "));
    s.setTel(Prompt.inputString("전화번호> "));
    s.setTime(Prompt.inputString("영업시간> "));
    s.setManager(employeeValidator.inputEmployee("매니저> "));
    if (s.getManager() == null) {
      System.out.println("지점 등록을 취소하였습니다.");
      return;
    }

    stmt.executeUpdate("store/insert", String.format("%s,%s,%s,%s,%s", 
        s.getName(), 
        s.getAddress(), 
        s.getTel(),
        s.getTime(),
        s.getManager()));

    System.out.println("지점을 등록하였습니다.");
  }
} 
