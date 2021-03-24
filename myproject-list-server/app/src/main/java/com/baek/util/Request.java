package com.baek.util;

import java.util.List;

public class Request {
  // 클라이언트의 요청 정보
  private String command;
  private List<String> data;

  @Override
  public String toString() {
    return "Request [command=" + command + ", data=" + data + "]";
  }

  public String getCommand() {
    return command;
  }
  public void setCommand(String command) {
    this.command = command;
  }
  public List<String> getData() {
    return data;
  }
  public void setData(List<String> data) {
    this.data = data;
  }
}
