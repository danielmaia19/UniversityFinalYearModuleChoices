package view;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.*;
import javafx.scene.layout.BorderPane;

//You may change this class to extend another type if you wish
public class OptionsModuleChooserRootPane extends BorderPane {

    private TabPane tabPane;
    private ProfileMenuBar menuBar;
    private StudentProfileViewPane studentProfileViewPane;
    private ModuleSelectionPane moduleSelectionPane;
    private OverviewSelectionPane overviewSelectionPane;
    private Tab createProfileTab, createCourseSelectionTab, createOverviewTab;

    public OptionsModuleChooserRootPane() {
        tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

        menuBar = new ProfileMenuBar();
        studentProfileViewPane = new StudentProfileViewPane();
        overviewSelectionPane = new OverviewSelectionPane();
        moduleSelectionPane = new ModuleSelectionPane();

        createProfileTab = new Tab("Create Profile", studentProfileViewPane);

        createCourseSelectionTab = new Tab("Select Modules", moduleSelectionPane);
        createCourseSelectionTab.setDisable(true);

        createOverviewTab = new Tab("Overview Selection", overviewSelectionPane);
        createOverviewTab.setDisable(true);

        //add tabs to tab pane
        tabPane.getTabs().addAll(createProfileTab, createCourseSelectionTab, createOverviewTab);

        this.setTop(menuBar);
        this.setCenter(tabPane);
    }

    //method to allow the controller to change tabs
    public void changeTab(int index) {
        tabPane.getSelectionModel().select(index);
    }

    public void enableTab(Tab tab) {
        tab.setDisable(false);
    }
    public void disableTab(Tab tab) {
        tab.setDisable(true);
    }

    public Tab getCreateCourseSelectionTab() {
        return createCourseSelectionTab;
    }
    public Tab getCreateOverviewTab() {
        return createOverviewTab;
    }
    public Tab getCreateProfileTab() {
        return createProfileTab;
    }

    public ProfileMenuBar getMenuBar() {
        return menuBar;
    }
    public StudentProfileViewPane getStudentProfileViewPane() {
        return studentProfileViewPane;
    }
    public ModuleSelectionPane getModuleSelectionPane() {
        return moduleSelectionPane;
    }
    public OverviewSelectionPane getOverviewSelectionPane() {
        return overviewSelectionPane;
    }

}
