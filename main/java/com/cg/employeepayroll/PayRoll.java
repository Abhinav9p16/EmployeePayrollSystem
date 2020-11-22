package com.cg.employeepayroll;

import java.sql.*;
import java.util.*;

public class PayRoll {
    private PayRoll() {
    }

    private static PayRoll pinstance = null;

    public static PayRoll getInstance() {
        if (pinstance == null)
            pinstance = new PayRoll();

        return pinstance;
    }

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

    public int getBetween(int start, int end) {
        int count = 0;
        Connection c = con.getConnection();
        try {
            payrollUpdateStatement = c.prepareStatement("select * from employee where emp_id between ? and ?");
            payrollUpdateStatement.setInt(1, start);
            payrollUpdateStatement.setInt(2, end);
            ResultSet result = payrollUpdateStatement.executeQuery();
            while (result.next()) {
                count++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
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
    public int getSum() {
        int count = 0;
        Connection c = con.getConnection();
        try {
            payrollUpdateStatement = c.prepareStatement("select count(name) from employee where gender='F'");
            ResultSet result = payrollUpdateStatement.executeQuery();
            while (result.next()) {
                count++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }
}