package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.Module;

public class TermSelectionViewPane extends HBox {

    private Label selectedTermModulesLabel;
    private Label unselectedTermModulesLabel;
    private ListView<Module> selectedTermModulesList;
    private ListView<Module> unselectedTermModulesList;
    private TermSelectionButtonPane termSelectionButtonPane;

    public TermSelectionViewPane() {

        VBox unselectedModules = new VBox();
        unselectedTermModulesLabel = new Label();
        unselectedTermModulesList = new ListView<>();
        unselectedTermModulesList.setMinHeight(100);
        unselectedTermModulesList.setMinWidth(100);
        unselectedModules.setPadding(new Insets(20,20,20,0));
        unselectedModules.getChildren().addAll(unselectedTermModulesLabel, unselectedTermModulesList);

        VBox selectedModules = new VBox();
        selectedTermModulesLabel = new Label();
        selectedTermModulesList = new ListView<>();
        selectedTermModulesList.setMinHeight(100);
        selectedTermModulesList.setMinWidth(100);
        selectedTermModulesList.setEditable(false);
        selectedModules.setPadding(new Insets(20,0,20,0));
        selectedModules.getChildren().addAll(selectedTermModulesLabel, selectedTermModulesList);

        termSelectionButtonPane = new TermSelectionButtonPane();
        termSelectionButtonPane.setCreditsTxtField("23");

        this.setPrefHeight(50);
        HBox.setHgrow(selectedModules, Priority.ALWAYS);
        HBox.setHgrow(unselectedModules, Priority.ALWAYS);
        HBox.setHgrow(this,Priority.ALWAYS);
        VBox.setVgrow(this,Priority.ALWAYS);
        this.getChildren().addAll(unselectedModules, selectedModules);
    }

    public Label getUnselectedTermModulesLabel() {
        return unselectedTermModulesLabel;
    }

    public Label getSelectedTermModulesLabel() {
        return selectedTermModulesLabel;
    }

    public void addToUnselectedList(Module m) {
        unselectedTermModulesList.getItems().add(m);
    }

    public void addToSelectedList(Module m) {
        selectedTermModulesList.getItems().add(m);
    }
}
