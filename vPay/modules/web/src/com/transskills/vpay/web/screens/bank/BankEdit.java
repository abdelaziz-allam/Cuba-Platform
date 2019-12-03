package com.transskills.vpay.web.screens.bank;

import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import com.transskills.vpay.entity.bank.Bank;

import javax.inject.Inject;

@UiController("vpay_Bank.edit")
@UiDescriptor("bank-create.xml")
@EditedEntityContainer("bankDc")
@LoadDataBeforeShow
public class BankEdit extends StandardEditor<Bank> {


}