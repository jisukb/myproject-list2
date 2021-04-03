package com.baek.proj.handler;

import com.baek.driver.Statement;
import com.baek.util.Prompt;

public class ReviewDeleteHandler implements Command {

  Statement stmt;

  public ReviewDeleteHandler(Statement stmt) {
    this.stmt = stmt;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[리뷰 삭제]");

    int no = Prompt.inputInt("번호> ");

    stmt.executeQuery("review/select", Integer.toString(no));

    String input = Prompt.inputString("삭제하시겠습니까?(Y/N) ");
    if (!input.equalsIgnoreCase("Y")) {
      System.out.println("삭제를 취소하였습니다.");
      return;
    }

    stmt.executeUpdate("review/delete", Integer.toString(no));
    System.out.println("리뷰를 삭제하였습니다.");
  }
}