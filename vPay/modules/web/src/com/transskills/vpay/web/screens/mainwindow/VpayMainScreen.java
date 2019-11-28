package com.transskills.vpay.web.screens.mainwindow;

import com.haulmont.cuba.core.global.GlobalConfig;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.gui.UrlRouting;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.components.mainwindow.AppMenu;
import com.haulmont.cuba.gui.components.mainwindow.SideMenu;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.web.DefaultApp;
import com.haulmont.cuba.web.sys.RedirectHandler;
import com.haulmont.cuba.web.widgets.CubaHorizontalSplitPanel;

import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import java.util.*;


@UiController("vpMainScreen")
@UiDescriptor("vp-main-screen.xml")
public class VpayMainScreen extends AbstractMainWindow {
    private final static String FOUND_ITEM_STYLE = "found-item";

    @Inject
    private TextField<String> searchField;

    @Inject
    private  AppMenu mainMenu;

    @Inject
    private SideMenu sideMenu;
    @Inject
    private SplitPanel mainSplit;
    @Inject
    private LookupField<Locale> localesSelect;


    @Inject
    private GlobalConfig globalConfig;

    @Inject
    private DefaultApp app;

    @Inject
    private RedirectHandler redirectHandler;

    @Inject
    private UrlRouting urlRouting;


    @Inject
    private Messages messages;

    @Inject
    private MessageBundle messageBundle;

    private List<SideMenu.MenuItem> foundItems = new ArrayList<>();
    private List<SideMenu.MenuItem> currentMenuItems = new ArrayList<>();
    private Map<String,List<SideMenu.MenuItem>> sideMenuDictionary = new HashMap<>();
    private List<String> parentListIdsToExpand = new ArrayList<>();

    @Subscribe
    protected void onInit(InitEvent event) {
        initAppMenu();
        initSideMenuDictionary();
        initMainSplit();
        initLocales();
        searchField.focus();
    }

    private void initAppMenu() {
        List<AppMenu.MenuItem> menuItems = mainMenu.getMenuItems();

        menuItems.forEach(menuItem -> {
            if( menuItem.hasChildren()) {
               menuItem.setCommand( parent -> {
                   sideMenu.removeAllMenuItems();
                   List<SideMenu.MenuItem> foundSideMenuItems = sideMenuDictionary.get(parent.getCaption());
                   foundSideMenuItems.forEach(sideItem->{
                       sideMenu.addMenuItem(sideItem);
                   });

                   currentMenuItems = sideMenu.getMenuItems();
               });
                List<AppMenu.MenuItem> children = menuItem.getChildren();
                children.forEach(child -> {
                    menuItem.removeChildItem(child);
                });
            }
        });
    }

    private void initSideMenuDictionary() {
        List<SideMenu.MenuItem> menuItems = sideMenu.getMenuItems();
        menuItems.forEach(menuItem -> {
            if( menuItem.hasChildren()) {
                sideMenuDictionary.put(menuItem.getCaption(),menuItem.getChildren());
            }
        });

        sideMenu.removeAllMenuItems();
    }

    private void initMainSplit() {
        mainSplit.unwrap(CubaHorizontalSplitPanel.class)
                .setDockable(true);
    }

    private void initLocales() {
        localesSelect.setOptionsMap(globalConfig.getAvailableLocales());
        localesSelect.setValue(app.getLocale());

        boolean localeSelectVisible = globalConfig.getLocaleSelectVisible();
        localesSelect.setVisible(localeSelectVisible);

        localesSelect.addValueChangeListener(e -> {
            Locale selectedLocale = e.getValue();

            redirectHandler.schedule(urlRouting.getState());

            app.setLocale(selectedLocale);
            app.createTopLevelWindow();
        });

        localesSelect.setOptionStyleProvider(locale ->
                locale.equals(app.getLocale()) ? "selected-locale" : null
        );
    }

    @Subscribe("searchButton")
    protected void onSearchButtonClick(Button.ClickEvent event) {
        search(searchField.getValue());
    }


    @Subscribe("searchField")
    protected void onSearchFieldEnterPress(TextInputField.EnterPressEvent event) {
        search(searchField.getValue());
    }


    private void search(String searchValue) {
        foundItems.clear();
        sideMenu.removeAllMenuItems();
        if (!StringUtils.isEmpty(searchValue)) {
            findItemsRecursively(currentMenuItems, searchValue);
        } else {
           currentMenuItems.forEach(item -> {
               sideMenu.addMenuItem(item);
           });
        }
    }






    private void findItemsRecursively(List<SideMenu.MenuItem> items, String searchValue) {
        for (SideMenu.MenuItem item : items) {
            if ( StringUtils.containsIgnoreCase(item.getCaption(), searchValue)) {
                item.setStyleName(FOUND_ITEM_STYLE);
                foundItems.add(item);
                sideMenu.addMenuItem(item);
            }
        }
    }


}