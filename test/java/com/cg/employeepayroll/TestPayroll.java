package com.cg.employeepayroll;

import org.junit.*;

public class TestPayroll {
    PayRoll p;

    @Test
    public void givenEmployeePayrollInDB_WhenRetrieved_ShouldMatchEmployeeCountInLocalEmployeePayrollMap() {
        p=PayRoll.getInstance();
        Assert.assertEquals(3, p.readData().size());
    }

    @Test
    public void givenNewSalaryForEmployee_WhenUpdated_ShouldMatch() {
        p=PayRoll.getInstance();
        p.update("a", 200000);
        Assert.assertEquals(200000, p.readData().get(0).basic_pay);
    }
}