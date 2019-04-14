package view;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.*;
import javafx.scene.layout.BorderPane;

//You may change this class to extend another type if you wish
public class OptionsModuleChooserRootPane extends BorderPane {

    private TabPane tabPane;
    private OverviewViewPane overviewViewPane;
    private OptionsModuleChooserMenuBar menuBar;
    private StudentProfileViewPane studentProfileViewPane;
    private ModuleSelectionViewPane moduleSelectionViewPane;
    private Tab createProfileTab, createCourseSelectionTab, createOverviewTab;

    public OptionsModuleChooserRootPane() {
        tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

        menuBar = new OptionsModuleChooserMenuBar();
        studentProfileViewPane = new StudentProfileViewPane();
        overviewViewPane = new OverviewViewPane();
        moduleSelectionViewPane = new ModuleSelectionViewPane();

        createProfileTab = new Tab("Create Profile", studentProfileViewPane);

        createCourseSelectionTab = new Tab("Select Modules", moduleSelectionViewPane);
        createCourseSelectionTab.setDisable(true);

        createOverviewTab = new Tab("Overview Selection", overviewViewPane);
        createOverviewTab.setDisable(true);

        //add tabs to tab pane
        tabPane.getTabs().addAll(createProfileTab, createCourseSelectionTab, createOverviewTab);

        this.setTop(menuBar);
        this.setCenter(tabPane);
    }

    public void enableTab(Tab tab) {
        tab.setDisable(false);
    }

    // Here if required, but not used
    public void disableTab(Tab tab) {
        tab.setDisable(false);
    }

    public Tab getCreateOverviewTab() {
        return createOverviewTab;
    }

    public OptionsModuleChooserMenuBar getMenuBar() {
        return menuBar;
    }

    public Tab getCreateCourseSelectionTab() {
        return createCourseSelectionTab;
    }

    // Change tabs
    public void changeTab(int index) {
        tabPane.getSelectionModel().select(index);
    }

    public OverviewViewPane getOverviewViewPane() {
        return overviewViewPane;
    }

    public StudentProfileViewPane getStudentProfileViewPane() {
        return studentProfileViewPane;
    }

    public ModuleSelectionViewPane getModuleSelectionViewPane() {
        return moduleSelectionViewPane;
    }

}
