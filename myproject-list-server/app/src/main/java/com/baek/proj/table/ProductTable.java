package com.baek.proj.table;

import java.io.File;
import java.util.List;
import com.baek.proj.domain.Product;
import com.baek.util.JsonFileHandler;
import com.baek.util.Request;
import com.baek.util.Response;

public class ProductTable implements DataTable {

  File jsonFile = new File("product.json");
  List<Product> list;

  public ProductTable() {
    list = JsonFileHandler.loadObjects(jsonFile, Product.class);
  }

  @Override
  public void service(Request request, Response response) throws Exception {
    Product product = null;
    String[] fields = null;

    switch (request.getCommand()) {
      case "product/insert":
        fields = request.getData().get(0).split(",");

        product = new Product();

        if (list.size() > 0) {
          product.setNo(list.get(list.size() - 1).getNo() + 1);
        } else {
          product.setNo(1);
        }

        product.setCategory(Integer.parseInt(fields[0]));
        product.setName(fields[1]);
        product.setPrice(Integer.parseInt(fields[2]));
        product.setStock(Integer.parseInt(fields[3]));
        product.setInfo(fields[4]);

        list.add(product);
        JsonFileHandler.saveObjects(jsonFile, list);
        break;
      case "product/selectall":
        for (Product p : list) {
          response.appendData(String.format("%d,%s,%s,%d,%s", 
              p.getNo(), 
              p.getCategory(), 
              p.getName(), 
              p.getPrice(), 
              p.getStock()));
        }
        break;
      case "product/select":
        int no = Integer.parseInt(request.getData().get(0));

        product = getProduct(no);
        if (product != null) {
          response.appendData(String.format("%d,%s,%s,%d,%s,%s", 
              product.getNo(),
              product.getCategory(),
              product.getName(),
              product.getPrice(),
              product.getStock(),
              product.getInfo()));
        } else {
          throw new Exception("해당 번호의 상품이 없습니다.");
        }
        break;
      case "product/selectByName":
        String name = request.getData().get(0);

        product = getProductByName(name);
        if (product != null) {
          response.appendData(String.format("%d,%s,%s,%d,%s,%s", 
              product.getNo(),
              product.getCategory(),
              product.getName(),
              product.getPrice(),
              product.getStock(),
              product.getInfo()));
        } else {
          throw new Exception("해당 번호의 상품이 없습니다.");
        }
        break;
      case "product/selectByKeyword":
        String keyword = request.getData().get(0);

        for (Product p : list) {
          if (p.getName().contains(keyword)) {
            response.appendData(String.format("%d,%s,%s,%d,%s", 
                p.getNo(), 
                p.getCategory(), 
                p.getName(), 
                p.getPrice(), 
                p.getStock()));
          }
        }
        break;
      case "product/update":
        fields = request.getData().get(0).split(",");

        product = getProduct(Integer.parseInt(fields[0]));
        if (product == null) {
          throw new Exception("해당 번호의 상품이 없습니다.");
        }

        product.setCategory(Integer.parseInt(fields[1]));
        product.setName(fields[2]);
        product.setPrice(Integer.parseInt(fields[3]));
        product.setStock(Integer.parseInt(fields[4]));
        product.setInfo(fields[5]);

        JsonFileHandler.saveObjects(jsonFile, list);
        break;
      case "product/delete":
        no = Integer.parseInt(request.getData().get(0));
        product = getProduct(no);
        if (product == null) {
          throw new Exception("해당 번호의 상품이 없습니다.");
        }

        list.remove(product);
        JsonFileHandler.saveObjects(jsonFile, list);
        break;
      default:
        throw new Exception("해당 명령을 처리할 수 없습니다.");
    }
  }

  private Product getProduct(int productNo) {
    for (Product p : list) {
      if (p.getNo() == productNo) {
        return p;
      }
    }
    return null;
  }

  private Product getProductByName(String name) {
    for (Product p : list) {
      if (p.getName().equals(name)) {
        return p;
      }
    }
    return null;
  }
}
