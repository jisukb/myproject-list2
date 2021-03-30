package com.baek.proj;

import java.net.ServerSocket;
import java.util.HashMap;
import com.baek.proj.table.DataTable;
import com.baek.proj.table.EmployeeTable;
import com.baek.proj.table.ProductTable;
import com.baek.proj.table.ReviewTable;
import com.baek.proj.table.StoreTable;

public class ServerApp {

  int port;
  HashMap<String,DataTable> tableMap = new HashMap<>();

  public static void main(String[] args) {
    ServerApp app = new ServerApp(8888);
    app.service();
  }

  public ServerApp(int port) {
    this.port = port;
  }

  public void service() {

    tableMap.put("employee/", new EmployeeTable());
    tableMap.put("store/", new StoreTable());
    tableMap.put("product/", new ProductTable());
    tableMap.put("review/", new ReviewTable());

    try (ServerSocket serverSocket = new ServerSocket(this.port)) {
      System.out.println("서버 실행!");
      while (true) {
        new StatementHandlerThread(serverSocket.accept(), tableMap).start();
      }
    } catch (Exception e) {
      System.out.println("서버 실행 중 오류 발생");
      e.printStackTrace();
    }
  }
}
