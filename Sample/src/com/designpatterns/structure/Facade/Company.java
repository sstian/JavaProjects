package com.designpatterns.structure.Facade;

public class Company {
    private String id = "1";
    private String bankAccount;
    private String taxCode;

    public String getId() {
        return id;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }
}
