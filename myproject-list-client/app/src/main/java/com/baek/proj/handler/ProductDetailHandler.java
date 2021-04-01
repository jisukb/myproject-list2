package com.baek.proj.handler;

import com.baek.driver.Statement;
import com.baek.proj.domain.Product;
import com.baek.util.Prompt;

public class ProductDetailHandler implements Command {

  Statement stmt;

  public ProductDetailHandler(Statement stmt) {
    this.stmt = stmt;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[상품 상세]");

    int no = Prompt.inputInt("번호> ");

    String[] fields = stmt.executeQuery("product/select", Integer.toString(no)).next().split(",");

    System.out.printf("카테고리: %s\n", Product.getChoiceCate(Integer.parseInt(fields[1])));
    System.out.printf("상품명: %s\n", fields[2]);
    System.out.printf("가격: %,d\n", fields[3]);
    System.out.printf("재고상태: %s\n", Product.getState(Integer.parseInt(fields[4])));
    System.out.printf("설명: %s\n", fields[5]);
  }
} 
