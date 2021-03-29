package com.baek.proj.table;

import java.io.File;
import java.util.List;
import com.baek.proj.domain.Store;
import com.baek.util.JsonFileHandler;
import com.baek.util.Request;
import com.baek.util.Response;

public class StoreTable implements DataTable {

  File jsonFile = new File("store.json");
  List<Store> list;

  public StoreTable() {
    list = JsonFileHandler.loadObjects(jsonFile, Store.class);
  }

  @Override
  public void service(Request request, Response response) throws Exception {
    Store store = null;
    String[] fields = null;

    switch (request.getCommand()) {
      case "store/insert":

        break;
      case "store/selectall":

        break;
      case "store/select":

        break;
      case "store/update":

        break;
      case "store/delete":

        break;
      default:
        throw new Exception("해당 명령을 처리할 수 없습니다.");
    }
  }
}
