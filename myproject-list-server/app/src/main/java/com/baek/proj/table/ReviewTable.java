package com.baek.proj.table;

import java.io.File;
import java.sql.Date;
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
        fields = request.getData().get(0).split(",");

        review = new Review();

        if (list.size() > 0) {
          review.setNo(list.get(list.size() - 1).getNo() + 1);
        } else {
          review.setNo(1);
        }

        review.setTitle(fields[0]);
        review.setWriter(fields[1]);
        review.setProduct(fields[2]);
        review.setContent(fields[3]);
        review.setRegistereDate(new Date(System.currentTimeMillis()));

        list.add(review);
        JsonFileHandler.saveObjects(jsonFile, list);
        break;
      case "review/selectall":
        for (Review r : list) {
          response.appendData(String.format("%d,%s,%s,%s,%s,%d", 
              r.getNo(), 
              r.getTitle(),
              r.getWriter(),
              r.getProduct(),
              r.getRegistereDate(), 
              r.getViewCount()));
        }
        break;
      case "review/select":
        int no = Integer.parseInt(request.getData().get(0));

        review = getReview(no);
        if (review != null) {
          response.appendData(String.format("%d,%s,%s,%s,%s,%s,%d", 
              review.getNo(),
              review.getTitle(),
              review.getWriter(),
              review.getProduct(),
              review.getContent(),
              review.getRegistereDate(),
              review.getViewCount()));
        } else {
          throw new Exception("해당 번호의 리뷰가 없습니다.");
        }
        break;
      case "review/selectByKeyword":
        String keyword = request.getData().get(0);

        for (Review r : list) {
          if (r.getTitle().contains(keyword) || 
              r.getWriter().contains(keyword) ||
              r.getContent().contains(keyword) ||
              r.getProduct().contains(keyword)) {
            response.appendData(String.format("%d,%s,%s,%s,%s,%d", 
                r.getNo(), 
                r.getTitle(),
                r.getWriter(),
                r.getProduct(),
                r.getRegistereDate(), 
                r.getViewCount()));
          }
        }
        break;
      case "review/update":
        fields = request.getData().get(0).split(",");

        review = getReview(Integer.parseInt(fields[0]));
        if (review == null) {
          throw new Exception("해당 번호의 리뷰가 없습니다.");
        }

        review.setTitle(fields[1]);
        review.setContent(fields[2]);

        JsonFileHandler.saveObjects(jsonFile, list);
        break;
      case "review/delete":
        no = Integer.parseInt(request.getData().get(0));
        review = getReview(no);
        if (review == null) {
          throw new Exception("해당 번호의 리뷰가 없습니다.");
        }

        list.remove(review);
        JsonFileHandler.saveObjects(jsonFile, list);
        break;
      default:
        throw new Exception("해당 명령을 처리할 수 없습니다.");
    }
  }

  private Review getReview(int reviewNo) {
    for (Review r : list) {
      if (r.getNo() == reviewNo) {
        return r;
      }
    }
    return null;
  }
}
