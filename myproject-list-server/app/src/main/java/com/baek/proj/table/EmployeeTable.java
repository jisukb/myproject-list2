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
        fields = request.getData().get(0).split(",");

        employee = new Employee();

        if (list.size() > 0) {
          employee.setNo(list.get(list.size() - 1).getNo() + 1);
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
        break;
      case "employee/selectall":
        for (Employee e : list) {
          response.appendData(String.format("%d,%s,%s,%s,%s,%s", 
              e.getNo(), 
              e.getName(), 
              e.getDept(), 
              e.getEmail(), 
              e.getPhone(), 
              e.getJoinDate()));
        }
        break;
      case "employee/select":
        int no = Integer.parseInt(request.getData().get(0));

        employee = getEmployee(no);
        if (employee != null) {
          response.appendData(String.format("%d,%s,%s,%s,%s,%s", 
              employee.getNo(),
              employee.getName(),
              employee.getDept(),
              employee.getEmail(),
              employee.getPhone(),
              employee.getJoinDate()));
        } else {
          throw new Exception("해당 번호의 사원이 없습니다.");
        }
        break;
      case "employee/selectByName":
        String name = request.getData().get(0);

        employee = getEmployeeByName(name);
        if (employee != null) {
          response.appendData(String.format("%d,%s,%s,%s,%s,%s", 
              employee.getNo(),
              employee.getName(),
              employee.getDept(),
              employee.getEmail(),
              employee.getPhone(),
              employee.getJoinDate()));
        } else {
          throw new Exception("해당 번호의 사원이 없습니다.");
        }
        break;
      case "employee/update":
        fields = request.getData().get(0).split(",");

        employee = getEmployee(Integer.parseInt(fields[0]));
        if (employee == null) {
          throw new Exception("해당 번호의 사원이 없습니다.");
        }

        employee.setName(fields[1]);
        employee.setDept(fields[2]);
        employee.setEmail(fields[3]);
        employee.setPhone(fields[4]);

        JsonFileHandler.saveObjects(jsonFile, list);
        break;
      case "employee/delete":
        no = Integer.parseInt(request.getData().get(0));
        employee = getEmployee(no);
        if (employee == null) {
          throw new Exception("해당 번호의 사원이 없습니다.");
        }

        list.remove(employee);
        JsonFileHandler.saveObjects(jsonFile, list);
        break;
      default:
        throw new Exception("해당 명령을 처리할 수 없습니다.");
    }
  }

  private Employee getEmployee(int employeeNo) {
    for (Employee e : list) {
      if (e.getNo() == employeeNo) {
        return e;
      }
    }
    return null;
  }

  private Employee getEmployeeByName(String name) {
    for (Employee e : list) {
      if (e.getName().equals(name)) {
        return e;
      }
    }
    return null;
  }
}
