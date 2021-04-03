package com.baek.proj.handler;

import java.util.Iterator;
import com.baek.driver.Statement;

public class ReviewListHandler implements Command {

  Statement stmt;

  public ReviewListHandler(Statement stmt) {
    this.stmt = stmt;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[리뷰 목록]");

    Iterator<String> results = stmt.executeQuery("review/selectall");

    while (results.hasNext()) {
      String[] fields = results.next().split(",");
      // 번호, 제목, 작성자, 상품, 등록일, 조회수
      System.out.printf("%s. %s, %s, %s, %s, %s\n",
          fields[0],
          fields[1],
          fields[2],
          fields[3],
          fields[4],
          fields[5]);
    }
  }
}
