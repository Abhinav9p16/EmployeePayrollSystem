package com.cg.employeepayroll;

import org.junit.*;

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
    public void givenSqlQueryWhenExecuted_Should_Commit() {
        p = PayRoll.getInstance();
        int count = p.readData().size();
        Employee emp = new Employee("Capgemini", "Sales", "Abhinav", "67890", "ABCDE", 'F', 60000);
        p.createEmployee(emp);
        Assert.assertEquals(count + 1, p.readData().size());

    }
}