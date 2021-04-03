package com.baek.proj.handler;

import com.baek.driver.Statement;
import com.baek.util.Prompt;

public class ReviewDetailHandler implements Command {

  Statement stmt;

  public ReviewDetailHandler(Statement stmt) {
    this.stmt = stmt;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[리뷰 상세]");

    int no = Prompt.inputInt("번호> ");

    String[] fields = stmt.executeQuery("review/select", Integer.toString(no)).next().split(",");

    System.out.printf("제목: %s\n", fields[1]);
    System.out.printf("작성자: %s\n", fields[2]);
    System.out.printf("상품명: %s\n", fields[3]);
    System.out.printf("내용: %s\n", fields[4]);
    System.out.printf("등록일: %s\n", fields[5]);
    System.out.printf("조회수: %s\n", fields[6]);
  }
} 
