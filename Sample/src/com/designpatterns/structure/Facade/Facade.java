package com.designpatterns.structure.Facade;

public class Facade {
    private IndustryAdmin admin;
    private Bank bank;
    private Taxation taxation;

    public Facade() {
        this.admin = new IndustryAdmin();
        this.bank = new Bank();
        this.taxation = new Taxation();
    }

    public Company openCompany(String name) {
        Company c = this.admin.register(name);
        String bankAccount = this.bank.openAccount(c.getId());
        c.setBankAccount(bankAccount);
        String taxCode = this.taxation.applyTaxCode(c.getId());
        c.setTaxCode(taxCode);
        return c;
    }
}
