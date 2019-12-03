package com.transskills.vpay.web.screens.bank;

import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import com.transskills.vpay.entity.bank.Bank;
import com.transskills.vpay.entity.bank.BankBranch;

import javax.inject.Inject;

@UiController("vpay_Bank.browse")
@UiDescriptor("bank-browse.xml")
@LookupComponent("banksTable")
public class BankBrowse extends MasterDetailScreen<Bank> {

    @Inject
    private CollectionLoader<Bank> banksDl;

    @Subscribe
    protected void onBeforeShow(BeforeShowEvent event) {
        banksDl.load();
    }

    @Inject
    private CollectionLoader<BankBranch> bankBranchesDl;


    @Subscribe(id = "banksDc", target = Target.DATA_CONTAINER)
    protected void onBanksDcItemChange(InstanceContainer.ItemChangeEvent<Bank> event) {
        bankBranchesDl.setParameter("bank", event.getItem());
        bankBranchesDl.load();
    }
}