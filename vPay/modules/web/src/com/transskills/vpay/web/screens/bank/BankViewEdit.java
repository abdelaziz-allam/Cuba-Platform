package com.transskills.vpay.web.screens.bank;

import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import com.transskills.vpay.entity.bank.Bank;
import com.transskills.vpay.entity.bank.BankBranch;

import javax.inject.Inject;

@UiController("vpay_Bank_View.edit")
@UiDescriptor("bank-edit.xml")
@EditedEntityContainer("bankDc")
@LoadDataBeforeShow
public class BankViewEdit extends StandardEditor<Bank> {

    @Inject
    private CollectionLoader<BankBranch> bankBranchesDl;

    @Inject
    private ScreenBuilders screenBuilders;

    @Inject
    private Table<BankBranch> branchesTbl;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {

        bankBranchesDl.setParameter("bank", getEditedEntity());
        bankBranchesDl.load();

    }

    @Subscribe("branchesTbl.create")
    protected void onBranchesTableCreateActionPerformed(Action.ActionPerformedEvent event) {
        BankBranchEdit editScreen = screenBuilders.editor(branchesTbl)
                .newEntity()
                .withScreenClass(BankBranchEdit.class)
                .withLaunchMode(OpenMode.DIALOG)
                .build();
        BankBranch bankBranch = new BankBranch();
        bankBranch.setBank(getEditedEntity());
        editScreen.setEntityToEdit(bankBranch);
        editScreen.show();
    }

}