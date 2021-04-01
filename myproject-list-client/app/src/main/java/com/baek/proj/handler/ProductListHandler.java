package com.baek.proj.handler;

import java.util.Iterator;
import com.baek.driver.Statement;
import com.baek.proj.domain.Product;

public class ProductListHandler implements Command {

  Statement stmt;

  public ProductListHandler(Statement stmt) {
    this.stmt = stmt;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[상품 목록]");

    Iterator<String> results = stmt.executeQuery("product/selectall");

    while (results.hasNext()) {
      String[] fields = results.next().split(",");
      // 번호, 카테고리, 상품명, 가격, 재고상태
      System.out.printf("%s. %s> %s %,s원 %s\n",
          fields[1],
          Product.getChoiceCate(Integer.parseInt(fields[1])),
          fields[2],
          fields[3],
          Product.getState(Integer.parseInt(fields[4])));
    }
  }
} 
