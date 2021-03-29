package com.baek.proj.table;

import java.io.File;
import java.util.List;
import com.baek.proj.domain.Employee;
import com.baek.util.JsonFileHandler;
import com.baek.util.Request;
import com.baek.util.Response;

public class EmployeeTable implements DataTable {

  File jsonFile = new File("employee.json");
  List<Employee> list;

  public EmployeeTable() {
    list = JsonFileHandler.loadObjects(jsonFile, Employee.class);
  }

  @Override
  public void service(Request request, Response response) throws Exception {
    Employee employee = null;
    String[] fields = null;

    switch (request.getCommand()) {
      case "employee/insert":

        break;
      case "employee/selectall":

        break;
      case "employee/select":

        break;
      case "employee/selectByName":

        break;
      case "employee/update":

        break;
      case "employee/delete":

        break;
      default:
        throw new Exception("해당 명령을 처리할 수 없습니다.");
    }
  }
}
