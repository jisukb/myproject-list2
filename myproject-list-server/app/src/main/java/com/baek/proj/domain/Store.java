package com.baek.proj.domain;

public class Store {
  private int no;
  private String name;
  private String address;
  private String tel;
  private String time;
  private String manager;

  @Override
  public String toString() {
    return "Store [no=" + no + ", name=" + name + ", address=" + address + ", tel=" + tel
        + ", time=" + time + ", manager=" + manager + "]";
  }
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getAddress() {
    return address;
  }
  public void setAddress(String address) {
    this.address = address;
  }
  public String getTel() {
    return tel;
  }
  public void setTel(String tel) {
    this.tel = tel;
  }
  public String getTime() {
    return time;
  }
  public void setTime(String time) {
    this.time = time;
  }
  public String getManager() {
    return manager;
  }
  public void setManager(String manager) {
    this.manager = manager;
  }

  public String telFormat(String tel) {
    if(tel.length() == 10) {  
      return tel.replaceFirst("(^02|[0-9]{3})([0-9]{3,4})([0-9]{4})$", "$1-$2-$3");
    } else if (tel.length() == 11) { 
      return tel.replaceFirst("(^[0-9]{3})([0-9]{4})([0-9]{4})$", "$1-$2-$3");
    } else {
      return tel.replaceFirst("(^02)([0-9]{3})([0-9]{4})$", "$1-$2-$3"); 
    }
  }
}
