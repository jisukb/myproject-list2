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

        break;
      case "product/selectall":

        break;
      case "product/select":

        break;
      case "product/selectByName":

        break;
      case "product/selectByKeyword":

        break;
      case "product/update":

        break;
      case "product/delete":

        break;
      default:
        throw new Exception("해당 명령을 처리할 수 없습니다.");
    }
  }
}
