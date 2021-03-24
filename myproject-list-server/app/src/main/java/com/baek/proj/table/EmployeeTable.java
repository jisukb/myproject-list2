package com.baek.proj.table;

import java.io.File;
import java.sql.Date;
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
        // CSV 형식의 문자열을 필드 값으로 분리
        fields = request.getData().get(0).split(",");

        employee = new Employee();
        if (list.size() > 0) {
          employee.setNo(list.get(list.size() -1).getNo() + 1);
        } else {
          employee.setNo(1);
        }
        employee.setName(fields[0]);
        employee.setDept(fields[1]);
        employee.setEmail(fields[2]);
        employee.setPhone(fields[3]);
        employee.setJoinDate(Date.valueOf(fields[4]));

        list.add(employee);
        JsonFileHandler.saveObjects(jsonFile, list);
        break; // pms-29-server
      case "employee/selectall":
        break;
      case "employee/select":
        break;
      case "employee/update":
        break;
      case "employee/delete":
        break;
      default:
        throw new Exception("해당 명령을 처리할 수 없습니다.")
    }
  }

}
