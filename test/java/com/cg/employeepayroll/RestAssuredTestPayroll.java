package com.cg.employeepayroll;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.*;
import org.junit.*;

import java.util.*;

public class RestAssuredTestPayroll {
    private int empId;

    @Before
    public void setup() throws Exception {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 4001;
        empId = 1;
    }

    public Response getEmployeeList() {
        Response response = RestAssured.get("/employees/list");
        System.out.println(response.getBody());
        return response;
    }

    @Test
    public void ListEmployee() {
        Response employeeList = getEmployeeList();
        System.out.println("string is " + employeeList.asString());
    }
}
