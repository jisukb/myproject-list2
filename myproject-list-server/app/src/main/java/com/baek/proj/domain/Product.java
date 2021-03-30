package com.baek.proj.domain;

public class Product {
  private int no;
  private int category;
  private String name;
  private int price;
  private int stock;
  private String info;

  @Override
  public String toString() {
    return "Product [no=" + no + ", category=" + category + ", name=" + name + ", price=" + price
        + ", stock=" + stock + ", info=" + info + "]";
  }
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public int getCategory() {
    return category;
  }
  public void setCategory(int category) {
    this.category = category;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public int getPrice() {
    return price;
  }
  public void setPrice(int price) {
    this.price = price;
  }
  public int getStock() {
    return stock;
  }
  public void setStock(int stock) {
    this.stock = stock;
  }
  public String getInfo() {
    return info;
  }
  public void setInfo(String info) {
    this.info = info;
  }

  public String getChoiceCate(int category) {
    switch (category) {
      case 1:
        return "굿즈";
      case 2:
        return "음반";
      default:
        return "서적";
    }
  }

  public String getState(int stock) {
    switch (stock) {
      case 1:
        return "판매 중입니다.";
      case 2:
        return "품절입니다.";
      default:
        return "예약상품입니다.";
    }
  }
}
