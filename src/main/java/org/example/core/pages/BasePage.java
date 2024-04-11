package org.example.core.pages;

import org.example.core.elements.NavigationToolbarElement;
import org.example.core.elements.SideNavigationBarElement;

public abstract class BasePage implements LoadablePage {
    private final SideNavigationBarElement sideBar = new SideNavigationBarElement();
    private final NavigationToolbarElement NavToolBar = new NavigationToolbarElement();

    public MessagePage openMessagePage() {
        return NavToolBar.clickOnMessageButton();
    }
}
