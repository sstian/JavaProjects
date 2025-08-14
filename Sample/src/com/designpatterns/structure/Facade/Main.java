package com.designpatterns.structure.Facade;

public class Main {
    public static void main(String[] args) {
        Facade facade = new Facade();
        Company c = facade.openCompany("Facade Software Ltd.");

    }
}
/*
IndustryAdmin.register
Bank.openAccount
Taxation.applyTaxCode
 */

/*
外观
为子系统中的一组接口提供一个一致的界面。Facade模式定义了一个高层接口，这个接口使得这一子系统更加容易使用。


 */