package com.baek.proj.handler;

import com.baek.driver.Statement;
import com.baek.proj.domain.Review;
import com.baek.util.Prompt;

public class ReviewAddHandler implements Command {

  Statement stmt;
  ProductValidator productValidator;

  public ReviewAddHandler(Statement stmt, ProductValidator productValidator) {
    this.stmt = stmt;
    this.productValidator = productValidator;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[리뷰 등록]");

    Review r = new Review();

    r.setTitle(Prompt.inputString("제목> "));
    r.setWriter(Prompt.inputString("작성자> "));
    r.setProduct(productValidator.inputProduct("상품명> "));
    if (r.getProduct() == null) {
      System.out.println("리뷰 등록을 취소하였습니다.");
      return;
    }
    r.setContent(Prompt.inputString("내용> "));

    stmt.executeUpdate("review/insert", String.format("%s,%s,%s,%s", 
        r.getTitle(),
        r.getWriter(),
        r.getProduct(),
        r.getContent()));

    System.out.println("리뷰를 등록하였습니다.");
  }
} 
