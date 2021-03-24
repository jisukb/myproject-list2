package com.baek.proj.table;

import com.baek.util.Request;
import com.baek.util.Response;

public interface DataTable {
  void service(Request request, Response response) throws Exception;
}
