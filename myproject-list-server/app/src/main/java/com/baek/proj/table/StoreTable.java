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
        fields = request.getData().get(0).split(",");

        store = new Store();

        if (list.size() > 0) {
          store.setNo(list.get(list.size() - 1).getNo() + 1);
        } else {
          store.setNo(1);
        }

        store.setName(fields[0]);
        store.setAddress(fields[1]);
        store.setTel(fields[2]);
        store.setTime(fields[3]);
        store.setManager(fields[4]);

        list.add(store);
        JsonFileHandler.saveObjects(jsonFile, list);
        break;
      case "store/selectall":
        for (Store s : list) {
          response.appendData(String.format("%d,%s,%s,%s", 
              s.getNo(), 
              s.getName(), 
              s.getAddress(), 
              s.getTel()));
        }
        break;
      case "store/select":
        int no = Integer.parseInt(request.getData().get(0));

        store = getStore(no);
        if (store != null) {
          response.appendData(String.format("%d,%s,%s,%s,%s,%s", 
              store.getNo(),
              store.getName(),
              store.getAddress(),
              store.getTel(),
              store.getTime(),
              store.getManager()));
        } else {
          throw new Exception("해당 번호의 지점이 없습니다.");
        }
        break;
      case "store/update":
        fields = request.getData().get(0).split(",");

        store = getStore(Integer.parseInt(fields[0]));
        if (store == null) {
          throw new Exception("해당 번호의 지점이 없습니다.");
        }

        store.setName(fields[1]);
        store.setAddress(fields[2]);
        store.setTel(fields[3]);
        store.setTime(fields[4]);
        store.setManager(fields[5]);

        JsonFileHandler.saveObjects(jsonFile, list);
        break;
      case "store/delete":
        no = Integer.parseInt(request.getData().get(0));
        store = getStore(no);
        if (store == null) {
          throw new Exception("해당 번호의 지점이 없습니다.");
        }

        list.remove(store);
        JsonFileHandler.saveObjects(jsonFile, list);
        break;
      default:
        throw new Exception("해당 명령을 처리할 수 없습니다.");
    }
  }

  protected Store getStore(int storeNo) {
    for (Store s : list) {
      if (s.getNo() == storeNo) {
        return s;
      }
    }
    return null;
  }
}
