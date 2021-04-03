package com.baek.proj.handler;

import com.baek.driver.Statement;
import com.baek.util.Prompt;

public class ReviewUpdateHandler implements Command {

  Statement stmt;
  ProductValidator productValidator;

  public ReviewUpdateHandler(Statement stmt, ProductValidator productValidator) {
    this.stmt = stmt;
    this.productValidator = productValidator;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[리뷰 수정]");

    int no = Prompt.inputInt("번호> ");

    String[] fields = stmt.executeQuery("review/select", Integer.toString(no)).next().split(",");

    String title = Prompt.inputString(String.format("제목(%s)> ", fields[1]));
    String content = Prompt.inputString(String.format("내용(%s)> ", fields[2]));

    String input = Prompt.inputString("변경하시겠습니까?(Y/N) ");
    if (!input.equalsIgnoreCase("Y")) {
      System.out.println("수정을 취소하였습니다.");
      return;
    }

    stmt.executeUpdate("review/update", String.format("%d,%s,%s", 
        no, title, content));
    System.out.println("리뷰를 변경하였습니다.");
  }
}
