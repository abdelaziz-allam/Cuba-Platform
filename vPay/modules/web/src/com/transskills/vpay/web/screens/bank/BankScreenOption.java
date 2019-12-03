package com.transskills.vpay.web.screens.bank;

import com.haulmont.cuba.gui.screen.ScreenOptions;
import com.transskills.vpay.entity.bank.Bank;

public class BankScreenOption  implements ScreenOptions {

    private Bank bank;

    private String parentScreenId;

    public BankScreenOption(Bank bank,String parentScreenId) {
        this.bank = bank;
        this.parentScreenId =parentScreenId;
    }

    public Bank getBank() {
        return bank;
    }

    public String getParentScreenId() {
        return parentScreenId;
    }
}
