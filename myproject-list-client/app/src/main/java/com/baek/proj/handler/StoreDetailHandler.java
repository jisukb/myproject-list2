package com.baek.proj.handler;

import com.baek.driver.Statement;
import com.baek.proj.domain.Store;
import com.baek.util.Prompt;

public class StoreDetailHandler implements Command {

  Statement stmt;

  public StoreDetailHandler(Statement stmt) {
    this.stmt = stmt;
  }

  @Override
  public void service() throws Exception {
    System.out.println("[지점 상세]");

    int no = Prompt.inputInt("번호> ");

    String[] fields = stmt.executeQuery("store/select", Integer.toString(no)).next().split(",");

    System.out.printf("지점명: %s\n", fields[1]);
    System.out.printf("주소: %s\n", fields[2]);
    System.out.printf("전화번호: %s\n", Store.telFormat(fields[3]));
    System.out.printf("영업시간: %s\n", fields[4]);
    System.out.printf("매니저: %s\n", fields[5]);
  }
} 
