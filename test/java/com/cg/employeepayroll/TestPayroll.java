package com.cg.employeepayroll;

import org.junit.*;

public class TestPayroll {

    @Test
    public void givenEmployeePayrollInDB_WhenRetrieved_ShouldMatchEmployeeCountInLocalEmployeePayrollMap() {
        PayRoll p = new PayRoll();
        Assert.assertEquals(3, p.readData().size());
    }

    @Test
    public void givenNewSalaryForEmployee_WhenUpdated_ShouldMatch() {
        PayRoll p = new PayRoll();
        p.update("a", 200000);
        Assert.assertEquals(200000, p.readData().get(0).basic_pay);
    }
}