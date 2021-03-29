package com.baek.proj.table;

import java.io.File;
import java.util.List;
import com.baek.proj.domain.Review;
import com.baek.util.JsonFileHandler;
import com.baek.util.Request;
import com.baek.util.Response;

public class ReviewTable implements DataTable {

  File jsonFile = new File("review.json");
  List<Review> list;

  public ReviewTable() {
    list = JsonFileHandler.loadObjects(jsonFile, Review.class);
  }

  @Override
  public void service(Request request, Response response) throws Exception {
    Review review = null;
    String[] fields = null;

    switch (request.getCommand()) {
      case "review/insert":

        break;
      case "review/selectall":

        break;
      case "review/select":

        break;
      case "review/selectByKeyword":

        break;
      case "review/update":

        break;
      case "review/delete":

        break;
      default:
        throw new Exception("해당 명령을 처리할 수 없습니다.");
    }
  }
}
