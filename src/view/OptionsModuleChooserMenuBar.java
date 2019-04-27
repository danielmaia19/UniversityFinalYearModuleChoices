package view;

import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.event.EventHandler;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.control.SeparatorMenuItem;

/**
 * Creates the menu bar with the necessary menus and menu items.
 * There are 2 menus of File and About.
 * File menu contains a sub-menu of load containing, loading Student Data and Courses.
 * File also contains saving Student Data and a exit to quit the application.
 *
 * @author P1718603X
 */
public class OptionsModuleChooserMenuBar extends MenuBar {

    // Fields
    private MenuItem loadItem, saveItem, exitItem, aboutItem, loadCourseItem;

    // Constructors

    /**
     * Default Constructor: Creates the menu bar with the menus and their menu items.
     * There are 2 menus of File and About.
     * File menu contains a sub-menu of Load containing, loading Student Data and Courses.
     * File also contains saving Student Data and a Exit to quit the application.
     */
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

    // Methods

    /**
     * Returns the save menu item.
     *
     * @return the save menu item from the menu.
     */
    public MenuItem getSaveItem() {
        return saveItem;
    }

    /**
     * Enables the menu item to allow interactivity.
     *
     * @param item is the menu item passed to enable it.
     */
    public void enableMenuItem(MenuItem item) {
        item.setDisable(false);
    }

    // Event Handlers

    /**
     * Sets a set on action click event on the exit menu item on the menu bar button.
     *
     * @param handler is passed as a EventHandler of ActionEvent to perform a task on menu item click.
     */
    public void addExitHandler(EventHandler<ActionEvent> handler) {
        exitItem.setOnAction(handler);
    }

    /**
     * Sets a set on action click event on the about menu item on the menu bar button.
     *
     * @param handler is passed as a EventHandler of ActionEvent to perform a task on menu item click.
     */
    public void addAboutHandler(EventHandler<ActionEvent> handler) {
        aboutItem.setOnAction(handler);
    }

    /**
     * Sets a set on action click event on the load student data menu item on the menu bar button.
     *
     * @param handler is passed as a EventHandler of ActionEvent to perform a task on menu item click.
     */
    public void addLoadStudentDataHandler(EventHandler<ActionEvent> handler) {
        loadItem.setOnAction(handler);
    }

    /**
     * Sets a set on action click event on the save student data menu item on the menu bar button.
     *
     * @param handler is passed as a EventHandler of ActionEvent to perform a task on menu item click.
     */
    public void addSaveStudentDataHandler(EventHandler<ActionEvent> handler) {
        saveItem.setOnAction(handler);
    }

    /**
     * Sets a set on action click event on the load course data menu item on the menu bar button.
     *
     * @param handler is passed as a EventHandler of ActionEvent to perform a task on menu item click.
     */
    public void addLoadCourseDataHandler(EventHandler<ActionEvent> handler) {
        loadCourseItem.setOnAction(handler);
    }
}
