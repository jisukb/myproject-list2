package com.baek.proj.handler;

import com.baek.driver.Statement;
import com.baek.util.Prompt;

public class ProductValidator {

  Statement stmt;

  public ProductValidator(Statement stmt) {
    this.stmt = stmt;
  }

  public String inputProduct(String promptTitle) {

    while (true) {
      String name = Prompt.inputString(promptTitle);
      if (name.length() == 0) {
        return null;
      } 

      try {
        return this.stmt.executeQuery("product/selectByName", name).next().split(",")[2];
      } catch (Exception e) {
        System.out.println("등록된 상품이 아닙니다.");
      } 
    }
  }
}
