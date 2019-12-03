package com.transskills.vpay.web.screens.bank;

import com.haulmont.cuba.core.global.DataLoadContext;
import com.haulmont.cuba.core.global.GlobalConfig;
import com.haulmont.cuba.core.global.MetadataTools;
import com.haulmont.cuba.gui.Dialogs;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.components.actions.BaseAction;
import com.haulmont.cuba.gui.icons.CubaIcon;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.gui.screen.LookupComponent;
import com.transskills.vpay.entity.bank.Bank;

import javax.inject.Inject;
import java.util.UUID;

@UiController("vpay_Bank.browse_generated")
@UiDescriptor("bank-browse-generated.xml")
@LookupComponent("banksTable")
@LoadDataBeforeShow
public class BankBrowseGenerated extends StandardLookup<Bank> {
    @Inject
    private Table<Bank> banksTable;

    @Inject
    private UiComponents uiComponents;

    @Inject
    private MessageBundle messageBundle;

    @Inject
    private Notifications notifications;

    @Inject
    private Dialogs dialogs;

    @Inject
    private ScreenBuilders screenBuilders;

    @Subscribe
    protected void onInit(InitEvent event) {
        banksTable.addGeneratedColumn("Actions", entity -> {
            HBoxLayout hBoxLayout =uiComponents.create(HBoxLayout.class);
            hBoxLayout.setSpacing(true);
            LinkButton viewBtn = uiComponents.create(LinkButton.class);
            viewBtn.setIcon(CubaIcon.EYE.source());
            viewBtn.setId(String.valueOf(entity.getId()));
            viewBtn.setDescription(messageBundle.getMessage("action.buttons.view"));
            // OR

            viewBtn.setAction(new BaseAction("viewAction")
                    .withPrimary(true)
                    .withHandler(e -> {
                               screenBuilders.screen(this)
                                        .withScreenClass(BankViewScreen.class)
                                       .withOptions(new BankScreenOption(entity,this.getId()))
                                       .build()
                                        .show();
                            }
                            ));
            LinkButton editBtn = uiComponents.create(LinkButton.class);
            editBtn.setIcon(CubaIcon.EDIT_ACTION.source());
            editBtn.setId(String.valueOf(entity.getId()));
            editBtn.setDescription(messageBundle.getMessage("action.buttons.edit"));
            // OR
            editBtn.setAction(new BaseAction("editAction")
                    .withPrimary(true)
                    .withHandler(e ->
                            screenBuilders.editor(banksTable).editEntity(entity).withScreenClass(BankViewEdit.class).build().show()));

            LinkButton deleteBtn = uiComponents.create(LinkButton.class);
            deleteBtn.setIcon(CubaIcon.REMOVE_ACTION.source());
            deleteBtn.setDescription(messageBundle.getMessage("action.buttons.delete"));
            deleteBtn.setId(String.valueOf(entity.getId()));
            // OR
            deleteBtn.setAction(new BaseAction("deleteAction")
                    .withPrimary(true)
                    .withHandler(e ->
                    {

                        dialogs.createOptionDialog()
                                .withCaption("Confirm")
                                .withMessage("Are you sure?")
                                .withActions(
                                        new DialogAction(DialogAction.Type.YES, Action.Status.PRIMARY).withHandler(de -> {
                                            deleteEntity(UUID.fromString(deleteBtn.getId()));
                                        }),
                                        new DialogAction(DialogAction.Type.NO)
                                )
                                .show();

                    }));

           hBoxLayout.add(viewBtn);
            hBoxLayout.add(editBtn);
            hBoxLayout.add(deleteBtn);
            return hBoxLayout;
        });

    }

    private void doCreateBankViewScreeen() {

    }

    private void deleteEntity (UUID entityId) {
        notifications.create()
                .withCaption("Inside Delete Entity Id Function : ".concat(entityId.toString()))
                .withType(Notifications.NotificationType.TRAY)
                .show();

    }
}