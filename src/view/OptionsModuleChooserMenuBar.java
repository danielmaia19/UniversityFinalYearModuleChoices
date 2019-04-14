package view;

import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.event.EventHandler;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.control.SeparatorMenuItem;

public class OptionsModuleChooserMenuBar extends MenuBar {

    //declared for access throughout class, as handlers are now attached via methods - see towards end of class
    private MenuItem loadItem, saveItem, exitItem, aboutItem, loadCourseItem;

    public OptionsModuleChooserMenuBar() {

        Menu menu, loadMenu;

        menu = new Menu("_File");

        loadMenu = new Menu("_Load");

        loadCourseItem = new MenuItem("_Courses");
        loadCourseItem.setAccelerator(KeyCombination.keyCombination("CTRL+T"));

        loadItem = new MenuItem("_Student Data");
        loadItem.setAccelerator(KeyCombination.keyCombination("CTRL+L"));

        loadMenu.getItems().addAll(loadItem, loadCourseItem);

        saveItem = new MenuItem("_Save Student Data");
        saveItem.setAccelerator(KeyCombination.keyCombination("CTRL+S"));
        saveItem.setDisable(true);
        menu.getItems().addAll(loadMenu, saveItem);

        menu.getItems().add(new SeparatorMenuItem());

        exitItem = new MenuItem("_Exit");
        exitItem.setAccelerator(KeyCombination.keyCombination("CTRL+X"));
        menu.getItems().add(exitItem);

        this.getMenus().add(menu);

        menu = new Menu("_Help");

        aboutItem = new MenuItem("_About");
        aboutItem.setAccelerator(KeyCombination.keyCombination("CTRL+A"));
        menu.getItems().add(aboutItem);

        this.getMenus().addAll(menu);
    }

    public MenuItem getSaveItem() {
        return saveItem;
    }

    public void enableMenuItem(MenuItem item) {
        item.setDisable(false);
    }

    //these methods allow handlers to be externally attached to this view and used by the controller
    public void addExitHandler(EventHandler<ActionEvent> handler) {
        exitItem.setOnAction(handler);
    }

    public void addAboutHandler(EventHandler<ActionEvent> handler) {
        aboutItem.setOnAction(handler);
    }

    public void addLoadStudentDataHandler(EventHandler<ActionEvent> handler) {
        loadItem.setOnAction(handler);
    }

    public void addSaveStudentDataHandler(EventHandler<ActionEvent> handler) {
        saveItem.setOnAction(handler);
    }

    public void addLoadCourseDataHandler(EventHandler<ActionEvent> handler) {
        loadCourseItem.setOnAction(handler);
    }
}
