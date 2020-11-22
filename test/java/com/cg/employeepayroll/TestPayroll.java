package com.cg.employeepayroll;

import org.junit.*;

import java.time.Duration;
import java.time.Instant;

public class TestPayroll {
    PayRoll p;

    @Test
    public void givenEmployeePayrollInDB_WhenRetrieved_ShouldMatchEmployeeCountInLocalEmployeePayrollMap() {
        p = PayRoll.getInstance();
        Assert.assertEquals(3, p.readData().size());
    }

    @Test
    public void givenNewSalaryForEmployee_WhenUpdated_ShouldMatch() {
        p = PayRoll.getInstance();
        p.update("a", 200000);
        Assert.assertEquals(200000, p.readData().get(0).basic_pay);
    }

    @Test
    public void givenEmployeePayrollInDB_WhenRetrieved_Should_CountEmployeeInGivenRange() {
        p = PayRoll.getInstance();
        Assert.assertEquals(3, p.getBetween(1, 3));
    }

    @Test
    public void givenEmpoloyeePayrollInDB_WhenRetrieved_Should_CountEmployeesByGender() {
        p = PayRoll.getInstance();
        Assert.assertEquals(1, p.getSum());
    }

    @Test
    public void givenSqlQueryWhenExecuted_Should_InsertEmployeeToDB() {
        p = PayRoll.getInstance();
        int count = p.readData().size();
        Employee emp = new Employee("Capgemini", "Sales", "Abhinav", "67890", "ABCDE", 'F', 60000);
        p.createEmployee(emp);
        Assert.assertEquals(count + 1, p.readData().size());

    }
    @Test
    public void givenSqlQueryWhenExecuted_Should_DeleteEmployeeFromDB(){
        p = PayRoll.getInstance();
        int count = p.readData().size();
        p.cascadingDelete("Abhinav");
        Assert.assertEquals(count - 1, p.readData().size());
    }
    @Test
    public void givenEmployeePayrollInDB_Should_AddMultipleEmployees_WithoutThread(){
        p = PayRoll.getInstance();
        Employee e1 = new Employee("Capgemini", "Sales", "Abhinav", "67890", "ABCDE", 'M', 60000);
        Employee e2 = new Employee("VMC", "Illuminati", "Arpit", "54321", "hijkl", 'M', 40000);
        int count = p.readData().size();
        Instant start = Instant.now();
        p.createEmployee(e1);
        p.createEmployee(e2);
        Instant end = Instant.now();
        System.out.println("Duration Without Thread: " + Duration.between(start, end));
        Assert.assertEquals(count + 2, p.readData().size());
        p.cascadingDelete("Abhinav");
        p.cascadingDelete("Arpit");
    }
}