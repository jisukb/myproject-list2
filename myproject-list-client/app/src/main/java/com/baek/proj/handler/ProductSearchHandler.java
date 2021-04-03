package com.baek.proj.handler;

import java.util.Iterator;
import com.baek.driver.Statement;
import com.baek.proj.domain.Product;
import com.baek.util.Prompt;

public class ProductSearchHandler implements Command {

  Statement stmt;

  public ProductSearchHandler(Statement stmt) {
    this.stmt = stmt;
  }

  @Override
  public void service() throws Exception {
    String keyword = Prompt.inputString("검색어> ");

    if (keyword.length() == 0) {
      System.out.println("검색어를 입력하세요.");
      return;
    }

    Iterator<String> results = stmt.executeQuery("product/selectByKeyword", keyword);

    if (!results.hasNext()) {
      System.out.println("검색어에 해당하는 상품이 없습니다.");
      return;
    }

    while (results.hasNext()) {
      String[] fields = results.next().split(",");
      System.out.printf("%s. %s> %s %s원 %s\n",
          fields[0],
          Product.getChoiceCate(Integer.parseInt(fields[1])),
          fields[2],
          fields[3],
          Product.getState(Integer.parseInt(fields[4])));
    }
  }
} 
