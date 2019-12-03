package com.transskills.vpay.web.screens.bank;

import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.WindowParam;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import com.transskills.vpay.entity.bank.Bank;
import com.transskills.vpay.entity.bank.BankBranch;

import javax.inject.Inject;

@UiController("vpay_BankViewScreen")
@UiDescriptor("bank-view-screen.xml")
public class BankViewScreen extends Screen {

    private  String parentScreenId;

    @Inject
    private InstanceContainer<Bank> bankDc;

    @Inject
    private CollectionLoader<BankBranch> bankBranchesDl;

    @Inject
    private ScreenBuilders screenBuilders;

    @Subscribe
    public void onInit(InitEvent event) {
        ScreenOptions options = event.getOptions();
        if (options instanceof BankScreenOption) {
            bankDc.setItem(((BankScreenOption) options).getBank());
            parentScreenId = ((BankScreenOption) options).getParentScreenId();
            bankBranchesDl.setParameter("bank", ((BankScreenOption) options).getBank());
            bankBranchesDl.load();
        }
    }

    @Subscribe("back")
    protected void onBackActionPerformed(Action.ActionPerformedEvent event) {
        screenBuilders.screen(this)
                .withScreenId(parentScreenId)
                .build()
                .show();
    }


}