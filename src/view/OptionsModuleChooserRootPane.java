package view;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TabPane.*;

/**
 * This is the main root pane used by creating each of the views of the application by the tab pane
 * and associating each tab to the
 * Menu Bar, Student Profile View Pane, Module Selection View Pane, Overview View Pane.
 *
 * @author P1718603X
 */
public class OptionsModuleChooserRootPane extends BorderPane {

    // Fields
    private TabPane tabPane;
    private OverviewViewPane overviewViewPane;
    private OptionsModuleChooserMenuBar menuBar;
    private StudentProfileViewPane studentProfileViewPane;
    private ModuleSelectionViewPane moduleSelectionViewPane;
    private Tab profileTab, courseSelectionTab, overviewTab;

    // Constructors

    /**
     * Default Constructor:
     * This is the main root pane used by creating each of the views of the application by the tab pane
     * and associating each tab to the
     * Menu Bar, Student Profile View Pane, Module Selection View Pane, Overview View Pane.
     */
    public OptionsModuleChooserRootPane() {
        tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

        menuBar = new OptionsModuleChooserMenuBar();
        studentProfileViewPane = new StudentProfileViewPane();
        overviewViewPane = new OverviewViewPane();
        moduleSelectionViewPane = new ModuleSelectionViewPane();

        profileTab = new Tab("Create Profile", studentProfileViewPane);

        courseSelectionTab = new Tab("Select Modules", moduleSelectionViewPane);
        courseSelectionTab.setDisable(true);

        overviewTab = new Tab("Overview Selection", overviewViewPane);
        overviewTab.setDisable(true);

        //add tabs to tab pane
        tabPane.getTabs().addAll(profileTab, courseSelectionTab, overviewTab);

        this.setTop(menuBar);
        this.setCenter(tabPane);
    }

    // Methods

    /**
     * Returns the overview tab.
     *
     * @return the overview tab.
     */
    public Tab getOverviewTab() {
        return overviewTab;
    }

    /**
     * Enables the specified tab by index.
     *
     * @param tab to pass to enable tab by index.
     */
    public void enableTab(Tab tab) {
        tab.setDisable(false);
    }

    /**
     * Returns the course selection view pane tab.
     *
     * @return the course selection view pane tab.
     */
    public Tab getCourseSelectionTab() {
        return courseSelectionTab;
    }

    /**
     * Returns the menu bar.
     *
     * @return the menu bar.
     */
    public OptionsModuleChooserMenuBar getMenuBar() {
        return menuBar;
    }

    /**
     * Change between tabs by the specified index.
     *
     * @param index to which the tab needs to be changed to.
     */
    public void changeTab(int index) {
        tabPane.getSelectionModel().select(index);
    }

    /**
     * Returns the Overview View Pane.
     *
     * @return the Overview View Pane.
     */
    public OverviewViewPane getOverviewViewPane() {
        return overviewViewPane;
    }

    /**
     * Returns the Student Profile View Pane.
     *
     * @return the Term Selection View Pane.
     */
    public StudentProfileViewPane getStudentProfileViewPane() {
        return studentProfileViewPane;
    }

    /**
     * Returns the Module Selection View Pane.
     *
     * @return the Module Selection View Pane.
     */
    public ModuleSelectionViewPane getModuleSelectionViewPane() {
        return moduleSelectionViewPane;
    }
}
