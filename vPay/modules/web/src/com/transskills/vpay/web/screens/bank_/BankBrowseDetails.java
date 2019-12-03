package com.transskills.vpay.web.screens.bank_;

import com.haulmont.cuba.core.global.MetadataTools;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.components.actions.BaseAction;
import com.haulmont.cuba.gui.screen.*;
import com.transskills.vpay.entity.bank.Bank;
import com.transskills.vpay.entity.bank.BankBranch;
import com.transskills.vpay.service.BankService;

import javax.inject.Inject;
import java.util.List;

@UiController("vpay_Bank.browse_details")
@UiDescriptor("bank-browse-details.xml")
@LoadDataBeforeShow
public class BankBrowseDetails extends StandardLookup<Bank> {
    @Inject
    private DataGrid<Bank> banksDataGrid;
    @Inject
    private UiComponents uiComponents;
    @Inject
    private BankService bankService;


    @Subscribe
    protected void onInit(InitEvent event) {
        banksDataGrid.setItemClickAction(new BaseAction("itemClickAction")
                .withHandler(actionPerformedEvent ->
                        banksDataGrid.setDetailsVisible(banksDataGrid.getSingleSelected(), true)));
    }

    @Install(to = "banksDataGrid", subject = "detailsGenerator")
    protected Component ordersDataGridDetailsGenerator(Bank bank) {
        VBoxLayout mainLayout = uiComponents.create(VBoxLayout.class);
        mainLayout.setWidth("100%");
        mainLayout.setMargin(true);

        HBoxLayout headerBox = uiComponents.create(HBoxLayout.class);
        headerBox.setWidth("100%");

        Label<String> infoLabel = uiComponents.create(Label.TYPE_STRING);
        infoLabel.setHtmlEnabled(true);
        infoLabel.setStyleName("h1");
        infoLabel.setValue("Branches info:");

        Component closeButton = createCloseButton(bank);
        headerBox.add(infoLabel);
        headerBox.add(closeButton);
        headerBox.expand(infoLabel);

        Component content = getContent(bank);

        mainLayout.add(headerBox);
        mainLayout.add(content);
        mainLayout.expand(content);

        return mainLayout;
    }

    private Component getContent(Bank entity) {
        Label<String> content = uiComponents.create(Label.TYPE_STRING);
        content.setHtmlEnabled(true);

        StringBuilder sb = new StringBuilder();
        sb.append("<table cellspacing=3px cellpadding=3px>")
                .append("<tr>")
                .append("<th>Name</th>")
                .append("<th>BBIC</th>")
                .append("<th>CBIC</th>")
                .append("</tr>");

        List<BankBranch> branches = bankService.loadBranchesByBankId(entity.getId());
        for (BankBranch item : branches) {
            sb.append("<tr>");
            sb.append("<td>").append(item.getName()).append("</td>");
            sb.append("<td>").append(item.getBbic()).append("</td>");
            sb.append("<td>").append(item.getBbic()).append("</td>");


            sb.append("</tr>");
        }
        sb.append("</table>");

        content.setValue(sb.toString());

        return content;
    }

    private Component createCloseButton(Bank entity) {
        Button closeButton = uiComponents.create(Button.class);
        closeButton.setIcon("icons/close.png");
        BaseAction closeAction = new BaseAction("closeAction")
                .withHandler(actionPerformedEvent ->
                        banksDataGrid.setDetailsVisible(entity, false))
                .withCaption("");
        closeButton.setAction(closeAction);
        return closeButton;
    }
}