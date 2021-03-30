package com.baek.proj.handler;

import com.baek.driver.Statement;
import com.baek.util.Prompt;

public class EmployeeDeleteHandler implements Command {

  Statement stmt;

  public EmployeeDeleteHandler(Statement stmt) {
    this.stmt = stmt;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[사원 삭제]");

    int no = Prompt.inputInt("번호> ");

    stmt.executeQuery("employee/select", Integer.toString(no));

    String input = Prompt.inputString("삭제하시겠습니까?(Y/N) ");
    if (!input.equalsIgnoreCase("Y")) {
      System.out.println("삭제를 취소하였습니다.");
      return;
    }

    stmt.executeUpdate("employee/delete", Integer.toString(no));
    System.out.println("사원을 삭제하였습니다.");
  }
}
