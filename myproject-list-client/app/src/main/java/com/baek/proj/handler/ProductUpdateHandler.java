package com.baek.proj.handler;

import com.baek.driver.Statement;
import com.baek.proj.domain.Product;
import com.baek.util.Prompt;

public class ProductUpdateHandler implements Command {

  Statement stmt;

  public ProductUpdateHandler(Statement stmt) {
    this.stmt = stmt;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[상품 수정]");

    int no = Prompt.inputInt("번호> ");

    String[] fields = stmt.executeQuery("product/select", Integer.toString(no)).next().split(",");

    int category = Prompt.inputInt(String.format(
        "카테고리\n0: 서적\n1: 굿즈\n2: 음반\n> ", Product.getChoiceCate(Integer.parseInt(fields[1]))));
    String name = Prompt.inputString(String.format("상품명(%s)> ", fields[2]));
    int price = Prompt.inputInt(String.format("가격(%,d)> ", fields[3]));
    int stock = Prompt.inputInt(String.format(
        "재고상태\n0: 예약상품\n1: 판매중\n2: 품절\n> ", Product.getState(Integer.parseInt(fields[4]))));
    String info = Prompt.inputString(String.format("설명(%s)> ", fields[5]));

    String input = Prompt.inputString("변경하시겠습니까?(Y/N) ");
    if (!input.equalsIgnoreCase("Y")) {
      System.out.println("수정을 취소하였습니다.");
      return;
    }

    stmt.executeUpdate("product/update", String.format("%d,%s,%s,%s,%s,%s", 
        no, category, name, price, stock, info));
    System.out.println("상품 정보를 변경하였습니다.");
  }
}
