package com.baek.proj.handler;

import java.util.Iterator;
import com.baek.driver.Statement;
import com.baek.util.Prompt;

public class ReviewSearchHandler implements Command {

  Statement stmt;

  public ReviewSearchHandler(Statement stmt) {
    this.stmt = stmt;
  }

  @Override
  public void service() throws Exception {
    String keyword = Prompt.inputString("검색어> ");

    if (keyword.length() == 0) {
      System.out.println("검색어를 입력하세요.");
      return;
    }

    Iterator<String> results = stmt.executeQuery("review/selectByKeyword", keyword);

    if (!results.hasNext()) {
      System.out.println("해당하는 글이 없습니다.");
      return;
    }

    while (results.hasNext()) {
      String[] fields = results.next().split(",");
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
