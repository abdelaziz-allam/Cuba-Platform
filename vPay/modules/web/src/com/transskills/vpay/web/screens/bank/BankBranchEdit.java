package com.transskills.vpay.web.screens.bank;

import com.haulmont.cuba.gui.screen.*;
import com.transskills.vpay.entity.bank.BankBranch;

@UiController("vpay_BankBranch.edit")
@UiDescriptor("bank-branch-edit.xml")
@EditedEntityContainer("bankBranchDc")
@LoadDataBeforeShow
public class BankBranchEdit extends StandardEditor<BankBranch> {
}