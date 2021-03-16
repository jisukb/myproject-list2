package com.baek.proj;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import com.baek.util.Prompt;

public class ClientApp {

  String serverAddress; 
  int port; 

  public static void main(String[] args) {
    ClientApp app = new ClientApp("localhost", 8888);
    app.execute();
  }

  public ClientApp(String serverAddress, int port) {
    this.serverAddress = serverAddress;
    this.port = port; 
  }

  public void execute() {
    try (Socket socket = new Socket(this.serverAddress, this.port);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream())) {

      while (true) {
        String message = Prompt.inputString("명령> ");
        // 명령어를 보낸다.
        out.writeUTF(message);
        // 서버에 보낼 데이터의 개수를 보낸다.
        out.writeInt(3);
        // 서버에 보내는 데이터
        out.writeUTF("aaaa");
        out.writeUTF("bbbb");
        out.writeUTF("cccc");
        out.flush();

        // 서버가 보낸 데이터를 읽는다. 작업 결과.
        String response = in.readUTF();
        // 데이터의 개수를 읽는다.
        int length = in.readInt();
        // List 컬렉션에 보관
        ArrayList<String> data = null;
        if (length > 0) {
          data = new ArrayList<>();
          for (int i = 0; i < length; i++) {
            data.add(in.readUTF());
          }
        }

        System.out.println("----------------------------");
        System.out.printf("작업 결과: %s\n", response);
        System.out.printf("데이터 개수: %d\n", length);
        if (data != null) {
          System.out.println("데이터: ");
          for (String str : data) {
            System.out.println(str);
          }
        }

        if (message.equals("quit")) {
          break;
        }
      }
      Prompt.close();

    } catch (Exception e) {
      System.out.println("서버와 통신 중 오류 발생");
    }
  }
}
