package view;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.Module;

public class TermSelectionViewPane extends VBox {

    private Label selectedTermModulesLabel;
    private Label unselectedTermModulesLabel;
    private ListView<Module> selectedTermModulesList;
    private ListView<Module> unselectedTermModulesList;
    private TermSelectionButtonPane termSelectionButtonPane;

    public TermSelectionViewPane() {

        HBox selectionContainer = new HBox();

        VBox unselectedModules = new VBox();
        unselectedTermModulesLabel = new Label();
        unselectedTermModulesList = new ListView<>();
        unselectedTermModulesList.setMinHeight(100);
        unselectedTermModulesList.setMinWidth(100);
        unselectedModules.setPadding(new Insets(20, 20, 20, 0));
        unselectedModules.getChildren().addAll(unselectedTermModulesLabel, unselectedTermModulesList);

        VBox selectedModules = new VBox();
        selectedTermModulesLabel = new Label();
        selectedTermModulesList = new ListView<>();
        selectedTermModulesList.setMinHeight(100);
        selectedTermModulesList.setMinWidth(100);
        selectedTermModulesList.setEditable(false);
        selectedModules.setPadding(new Insets(20, 0, 20, 0));
        selectedModules.getChildren().addAll(selectedTermModulesLabel, selectedTermModulesList);

        selectionContainer.getChildren().addAll(unselectedModules, selectedModules);

        HBox buttonPaneContainer = new HBox();
        termSelectionButtonPane = new TermSelectionButtonPane();
        termSelectionButtonPane.setCreditsTxtField("0");
        buttonPaneContainer.getChildren().add(termSelectionButtonPane);

        this.setPrefHeight(50);
        HBox.setHgrow(selectedModules, Priority.ALWAYS);
        HBox.setHgrow(unselectedModules, Priority.ALWAYS);
        HBox.setHgrow(this, Priority.ALWAYS);
        VBox.setVgrow(this, Priority.ALWAYS);
        this.getChildren().addAll(selectionContainer, buttonPaneContainer);
    }

    public ObservableList<Module> getAllSelectedModules() {
        return selectedTermModulesList.getItems();
    }

    public void clearAllListViews() {
        this.unselectedTermModulesList.getItems().clear();
        this.selectedTermModulesList.getItems().clear();
    }

    public void addToUnselectedList(Module m) {
        unselectedTermModulesList.getItems().add(m);
    }

    public void addToSelectedList(Module m) {
        selectedTermModulesList.getItems().add(m);
    }

    // Getter and Setters Methods
    public Label getSelectedTermModulesLabel() {
        return selectedTermModulesLabel;
    }

    public Label getUnselectedTermModulesLabel() {
        return unselectedTermModulesLabel;
    }

    public TermSelectionButtonPane getTermSelectionButtonPane() {
        return termSelectionButtonPane;
    }

    public Module getUnSelectedModule() {
        return unselectedTermModulesList.getSelectionModel().getSelectedItem();
    }

    public Module getSelectedModule() {
        return selectedTermModulesList.getSelectionModel().getSelectedItem();
    }

    public void removeSelectedItem() {
        int index = selectedTermModulesList.getSelectionModel().getSelectedIndex();

        if (index != -1) {
            selectedTermModulesList.getItems().remove(index);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Selection Error");
            alert.setContentText("Please select an item to remove.");
            alert.showAndWait();
        }

    }

}
