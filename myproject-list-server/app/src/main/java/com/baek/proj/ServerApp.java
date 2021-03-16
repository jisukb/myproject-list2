package com.baek.proj;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerApp {

  int port; 

  public static void main(String[] args) {
    ServerApp app = new ServerApp(8888);
    app.service();
  }

  public ServerApp(int port) {
    this.port = port;
  }

  public void service() {
    try (ServerSocket serverSocket = new ServerSocket(this.port)) {
      System.out.println("서버 실행!");
      processRequest(serverSocket.accept());
    } catch (Exception e) {
      System.out.println("서버 실행 중 오류 발생");
      e.printStackTrace();
    }
  }

  private void processRequest(Socket socket) {
    try (DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream())) {

      while (true) {
        // 명령어 문자열을 읽는다.
        String request = in.readUTF();
        // 클라이언트가 보낸 데이터의 개수를 읽는다.
        int length = in.readInt();
        // List 컬렉션에 담는다.
        ArrayList<String> data = null;
        if (length  > 0) {
          data = new ArrayList<>();
          for (int i = 0; i < length; i++) {
            data.add(in.readUTF());
          }
        }

        System.out.println("----------------------------");
        System.out.printf("명령: %s\n", request);
        System.out.printf("데이터 개수: %d\n", length);
        if (data != null) {
          System.out.println("데이터: ");
          for (String str : data) {
            System.out.println(str);
          }
        }

        // 클라이언트에게 요청에 대한 작업 결과를 보낸다.
        out.writeUTF("success");
        // 클라이언트에게 보낼 데이터의 개수를 보낸다.
        out.writeInt(1);
        // 클라이언트에게 데이터를 보낸다.
        out.writeUTF("test OK");
        out.flush();

        if (request.equals("quit")) {
          break;
        }
      }

    } catch (Exception e) {
      System.out.println("클라이언트의 요청 처리 중 오류 발생");
      e.printStackTrace();
    }
  }
}
