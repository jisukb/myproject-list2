package com.baek.proj.handler;

import com.baek.driver.Statement;
import com.baek.util.Prompt;

public class EmployeeValidator {

  Statement stmt;

  public EmployeeValidator(Statement stmt) {
    this.stmt = stmt;
  }

  public String inputEmployee(String promptTitle) {
    while (true) {
      String name = Prompt.inputString(promptTitle);
      if (name.length() == 0) {
        return null;
      } 

      try {
        return this.stmt.executeQuery("employee/selectByName", name).next().split(",")[1];
      } catch (Exception e) {
        System.out.println("등록된 사원이 아닙니다.");
      }
    }
  }
}
