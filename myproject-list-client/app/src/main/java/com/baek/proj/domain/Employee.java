package com.baek.proj.domain;

import java.sql.Date;

public class Employee {
  private int no;
  private String name;
  private String dept;
  private String email;
  private String phone;
  private Date joinDate;

  @Override
  public String toString() {
    return "Employee [no=" + no + ", name=" + name + ", dept=" + dept + ", email=" + email
        + ", phone=" + phone + ", joinDate=" + joinDate + "]";
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
  public String getDept() {
    return dept;
  }
  public void setDept(String dept) {
    this.dept = dept;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getPhone() {
    return phone;
  }
  public void setPhone(String phone) {
    this.phone = phone;
  }
  public Date getJoinDate() {
    return joinDate;
  }
  public void setJoinDate(Date joinDate) {
    this.joinDate = joinDate;
  }

  public static String phoneFormat(String phone) {
    if(phone.length() == 11) {  
      return phone.replaceFirst("(^010)([0-9]{4})([0-9]{4})$", "$1-$2-$3");
    }
    return phone; 
  }
}
