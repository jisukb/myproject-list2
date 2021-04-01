package com.baek.proj.handler;

import com.baek.driver.Statement;
import com.baek.proj.domain.Product;
import com.baek.util.Prompt;

public class ProductAddHandler implements Command {

  Statement stmt;

  public ProductAddHandler(Statement stmt) {
    this.stmt = stmt;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[상품 등록]");

    Product p = new Product();

    p.setCategory(Prompt.inputInt("카테고리\n0: 서적\n1: 굿즈\n2: 음반\n> "));
    p.setName(Prompt.inputString("상품명> "));
    p.setPrice(Prompt.inputInt("가격> "));
    p.setStock(Prompt.inputInt("재고상태\n0: 예약\n1: 판매중\n2: 품절\n> "));
    p.setInfo(Prompt.inputString("설명> "));

    stmt.executeUpdate("product/insert", String.format("%s,%s,%s,%s,%s", 
        p.getCategory(), 
        p.getName(), 
        p.getPrice(), 
        p.getStock(),
        p.getInfo()));

    System.out.println("상품을 등록하였습니다.");
  }
}
