package com.transskills.vpay.web.screens.currency;

import com.haulmont.cuba.gui.screen.*;
import com.transskills.vpay.entity.Currency;

@UiController("vpay_Currency.browse")
@UiDescriptor("currency-browse.xml")
@LookupComponent("table")
@LoadDataBeforeShow
public class CurrencyBrowse extends MasterDetailScreen<Currency> {
}