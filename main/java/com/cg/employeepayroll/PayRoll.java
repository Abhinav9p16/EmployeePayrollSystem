package com.cg.employeepayroll;

import java.sql.*;
import java.util.*;

public class PayRoll {
    static ConnectionRetriever con = new ConnectionRetriever();

    public static List<Employee> readData() {
        String sql = "select * from employee;";
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
                arr.add(e);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return arr;
    }

    public static void main(String[] args) {
        List<Employee> ar = readData();
        for (Employee e : ar) {
            System.out.println(e);
        }
    }
}