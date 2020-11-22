package com.cg.employeepayroll;

import java.sql.*;
import java.util.*;

public class PayRoll {
    ConnectionRetriever con = new ConnectionRetriever();

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
    public void update(String name,int salary)
    {
        try {

            String sql = "update payroll set basic_pay= " + salary + " where emp_id=" + "(select emp_id from employee where name = '" + name + "');";
            Connection c = con.getConnection();
            Statement statement = c.createStatement();
            statement.executeUpdate(sql);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}