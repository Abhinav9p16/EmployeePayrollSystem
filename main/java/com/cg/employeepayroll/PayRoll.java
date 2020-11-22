package com.cg.employeepayroll;

import java.sql.*;
import java.util.*;

public class PayRoll {
    ConnectionRetriever con = new ConnectionRetriever();
    PreparedStatement payrollUpdateStatement;

    public List<Employee> readData() {
        String sql = "select * from employee e, payroll p where e.emp_id=p.emp_id ;";
        List<Employee> arr = new ArrayList<Employee>();
        try {
            Connection c = con.getConnection();
            Statement statement = c.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                Employee e = new Employee();
                e.company_id = result.getInt("company_id");
                e.emp_id = result.getInt("emp_id");
                e.name = result.getString("name");
                e.phone = result.getString("phone");
                e.address = result.getString("address");
                e.gender = result.getString("gender").charAt(0);
                e.basic_pay = result.getInt("basic_pay");
                arr.add(e);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return arr;
    }

    public void update(String name, int salary) {
        try {
            Connection c = con.getConnection();
            payrollUpdateStatement = c.prepareStatement("update payroll set basic_pay= ? where emp_id=(select emp_id from employee where name = ?);");
            payrollUpdateStatement.setInt(1, salary);
            payrollUpdateStatement.setString(2, name);
            payrollUpdateStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}