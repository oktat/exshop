package controllers;

import views.MainView;

public class MainController {
    MainView mainView;
    public MainController() {
        this.mainView = new MainView();
    }
    public MainView getMainView() {
        return this.mainView;
    }
}
