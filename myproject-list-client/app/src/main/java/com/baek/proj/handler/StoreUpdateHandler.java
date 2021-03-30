package com.baek.proj.handler;

import com.baek.driver.Statement;
import com.baek.proj.domain.Store;
import com.baek.util.Prompt;

public class StoreUpdateHandler implements Command {

  Statement stmt;
  EmployeeValidator employeeValidator;

  public StoreUpdateHandler(Statement stmt, EmployeeValidator employeeValidator) {
    this.stmt = stmt;
    this.employeeValidator = employeeValidator;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[지점 수정]");

    int no = Prompt.inputInt("번호> ");

    String[] fields = stmt.executeQuery("employee/select", Integer.toString(no)).next().split(",");

    String name = Prompt.inputString(String.format("지점명(%s)> ", fields[1]));
    String address = Prompt.inputString(String.format("주소(%s)> ", fields[2]));
    String tel = Prompt.inputString(String.format("전화번호(%s)> ", Store.telFormat(fields[3])));
    String time = Prompt.inputString(String.format("영업시간(%s)> ", fields[4]));
    String manager = employeeValidator.inputEmployee(String.format("매니저(%s)> ", fields[5]));
    if (manager == null) {
      System.out.println("지점 수정을 취소하였습니다.");
      return;
    }

    String input = Prompt.inputString("변경하시겠습니까?(Y/N) ");
    if (!input.equalsIgnoreCase("Y")) {
      System.out.println("수정을 취소하였습니다.");
      return;
    }

    stmt.executeUpdate("store/update", String.format("%d,%s,%s,%s,%s,%s", 
        no, name, address, tel, time, manager));
    System.out.println("지점 정보를 변경하였습니다.");
  }
}