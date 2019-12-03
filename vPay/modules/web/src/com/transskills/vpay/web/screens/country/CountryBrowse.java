package com.transskills.vpay.web.screens.country;

import com.haulmont.cuba.gui.screen.*;
import com.transskills.vpay.entity.country.Country;

@UiController("vpay_Country.browse")
@UiDescriptor("country-browse.xml")
@LookupComponent("table")
@LoadDataBeforeShow
public class CountryBrowse extends MasterDetailScreen<Country> {
}
