package com.baek.proj;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import com.baek.driver.Statement;
import com.baek.proj.handler.Command;
import com.baek.proj.handler.EmployeeAddHandler;
import com.baek.proj.handler.EmployeeDeleteHandler;
import com.baek.proj.handler.EmployeeDetailHandler;
import com.baek.proj.handler.EmployeeListHandler;
import com.baek.proj.handler.EmployeeUpdateHandler;
import com.baek.proj.handler.EmployeeValidator;
import com.baek.proj.handler.ProductAddHandler;
import com.baek.proj.handler.ProductDeleteHandler;
import com.baek.proj.handler.ProductDetailHandler;
import com.baek.proj.handler.ProductListHandler;
import com.baek.proj.handler.ProductSearchHandler;
import com.baek.proj.handler.ProductUpdateHandler;
import com.baek.proj.handler.ProductValidator;
import com.baek.proj.handler.ReviewAddHandler;
import com.baek.proj.handler.ReviewDeleteHandler;
import com.baek.proj.handler.ReviewDetailHandler;
import com.baek.proj.handler.ReviewListHandler;
import com.baek.proj.handler.ReviewSearchHandler;
import com.baek.proj.handler.ReviewUpdateHandler;
import com.baek.proj.handler.StoreAddHandler;
import com.baek.proj.handler.StoreDeleteHandler;
import com.baek.proj.handler.StoreDetailHandler;
import com.baek.proj.handler.StoreListHandler;
import com.baek.proj.handler.StoreUpdateHandler;
import com.baek.util.Prompt;

public class ClientApp {

  // 입력한 명령 저장
  ArrayDeque<String> commandStack = new ArrayDeque<>();
  LinkedList<String> commandQueue = new LinkedList<>();

  String serverAddress;
  int port;

  public static void main(String[] args) {
    ClientApp app = new ClientApp("localhost", 8888);

    try {
      app.execute();

    } catch (Exception e) {
      System.out.println("서버와 통신 중 오류 발생");
      e.printStackTrace();
    }
  }

  public ClientApp(String serverAddress, int port) {
    this.serverAddress = serverAddress;
    this.port = port; 
  }

  public void execute() throws Exception {

    Statement stmt = new Statement(serverAddress, port);

    HashMap<String,Command> commandMap = new HashMap<>();

    commandMap.put("employeeadd", new EmployeeAddHandler(stmt));
    commandMap.put("employeelist", new EmployeeListHandler(stmt));
    commandMap.put("employeedetail", new EmployeeDetailHandler(stmt));
    commandMap.put("employeeupdate", new EmployeeUpdateHandler(stmt));
    commandMap.put("employeedelete", new EmployeeDeleteHandler(stmt));
    EmployeeValidator employeeValidator = new EmployeeValidator(stmt);

    commandMap.put("storeadd", new StoreAddHandler(stmt, employeeValidator));
    commandMap.put("storelist", new StoreListHandler(stmt));
    commandMap.put("storedetail", new StoreDetailHandler(stmt));
    commandMap.put("storeupdate", new StoreUpdateHandler(stmt, employeeValidator));
    commandMap.put("storedelete", new StoreDeleteHandler(stmt));

    commandMap.put("productadd", new ProductAddHandler(stmt));
    commandMap.put("productlist", new ProductListHandler(stmt));
    commandMap.put("productdetail", new ProductDetailHandler(stmt));
    commandMap.put("productupdate", new ProductUpdateHandler(stmt));
    commandMap.put("productdelete", new ProductDeleteHandler(stmt));
    commandMap.put("productsearch", new ProductSearchHandler(stmt));
    ProductValidator productValidator = new ProductValidator(stmt);

    commandMap.put("reviewadd", new ReviewAddHandler(stmt, productValidator));
    commandMap.put("reviewlist", new ReviewListHandler(stmt));
    commandMap.put("reviewdetail", new ReviewDetailHandler(stmt));
    commandMap.put("reviewupdate", new ReviewUpdateHandler(stmt, productValidator));
    commandMap.put("reviewdelete", new ReviewDeleteHandler(stmt));
    commandMap.put("reviewsearch", new ReviewSearchHandler(stmt));

    System.out.println("===============메인===============");
    System.out.println("1. 사원 2. 지점 3. 상품 4. 리뷰");
    System.out.println("종료 quit/exit");
    System.out.println("==================================");

    try {
      while (true) {

        String command = com.baek.util.Prompt.inputString("메인> ");
        System.out.println();

        if (command.length() == 0) {
          continue;
        }

        commandStack.push(command);
        commandQueue.offer(command);

        try {
          switch (command) {
            case "1":
              System.out.println("----------사원----------");
              System.out.println("등록 employeeadd");
              System.out.println("목록 employeelist");
              System.out.println("상세 employeedetail");
              System.out.println("수정 employeeupdate");
              System.out.println("삭제 employeedelete");
              System.out.println("------------------------");
              break;
            case "2":
              System.out.println("----------지점----------");
              System.out.println("등록 storeadd");
              System.out.println("목록 storelist");
              System.out.println("상세 storedetail");
              System.out.println("수정 storeupdate");
              System.out.println("삭제 storedelete");
              System.out.println("------------------------");
              break;
            case "3":
              System.out.println("----------상품----------");
              System.out.println("등록 productadd");
              System.out.println("목록 productlist");
              System.out.println("상세 productdetail");
              System.out.println("수정 productupdate");
              System.out.println("삭제 productdelete");
              System.out.println("검색 productsearch");
              System.out.println("------------------------");
              break;
            case "4":
              System.out.println("----------리뷰----------");
              System.out.println("등록 reviewadd");
              System.out.println("목록 reviewlist");
              System.out.println("상세 reviewdetail");
              System.out.println("수정 reviewupdate");
              System.out.println("삭제 reviewdelete");
              System.out.println("검색 reviewsearch");
              System.out.println("------------------------");
              break;
            case "history":
              printCommandHistory(commandStack.iterator());
              break;
            case "history2":
              printCommandHistory(commandQueue.iterator());
              break;
            case "exit":
            case "quit":
              stmt.executeUpdate("quit");
              System.out.println("프로그램을 종료합니다.");
              return;
            default:
              Command commandHandler = commandMap.get(command);

              if (commandHandler == null) {
                System.out.println("실행할 수 없는 명령입니다.");
              } else {
                commandHandler.service();
              }
          }
        } catch (Exception e) {
          System.out.println("-----------------------------------------");
          System.out.printf("오류 발생: %s - %s\n", e.getMessage());
          System.out.println("-----------------------------------------");
        }
        System.out.println();
      }

    } catch (Exception e) {
      System.out.println("서버와 통신 중 오류 발생");
    }

    Prompt.close();
    stmt.close();
  }

  private void printCommandHistory(Iterator<String> iterator) {
    int count = 0;
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
      if ((++count % 5) == 0) {
        String input = Prompt.inputString(": ");
        if (input.equalsIgnoreCase("q")) {
          break;
        }
      }
    }
  }
}
