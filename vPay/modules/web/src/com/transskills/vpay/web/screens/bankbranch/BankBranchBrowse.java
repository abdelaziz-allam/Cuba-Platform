package com.transskills.vpay.web.screens.bankbranch;

import com.haulmont.cuba.gui.screen.*;
import com.transskills.vpay.entity.bank.BankBranch;

@UiController("vpay_BankBranch.browse")
@UiDescriptor("bank-branch-browse.xml")
@LookupComponent("table")
@LoadDataBeforeShow
public class BankBranchBrowse extends MasterDetailScreen<BankBranch> {
}