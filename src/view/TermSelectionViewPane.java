package view;


import javafx.event.ActionEvent;
import model.Module;
import model.Delivery;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ListView;

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
        termSelectionButtonPane.setCredits(0);
        buttonPaneContainer.getChildren().add(termSelectionButtonPane);

        this.setPrefHeight(50);
        HBox.setHgrow(selectedModules, Priority.ALWAYS);
        HBox.setHgrow(unselectedModules, Priority.ALWAYS);
        HBox.setHgrow(this, Priority.ALWAYS);
        VBox.setVgrow(this, Priority.ALWAYS);
        this.getChildren().addAll(selectionContainer, buttonPaneContainer);
    }

    public void resetListsAndCredits() {
        selectedTermModulesList.getItems().clear();
        unselectedTermModulesList.getItems().clear();
        termSelectionButtonPane.setCredits(0);
    }

    // Delegation to ButtonPane
    public int getCreditLimit() {
        return termSelectionButtonPane.getCreditLimit();
    }

    public void increaseCreditsBy(int value) {
        int newVal = value + termSelectionButtonPane.getCredits();
        termSelectionButtonPane.setCredits(newVal);
        //setCredits(getCredits() + getSelectedItem().getCredits());
    }

    public void decreaseCreditsBy(int value) {
        termSelectionButtonPane.setCredits(value - getSelectedItem().getCredits());
    }

    public int getCredits() {
        return termSelectionButtonPane.getCredits();
    }

    public void setCreditLimit(int creditLimit) {
        termSelectionButtonPane.setCreditLimit(creditLimit);
    }

    public void removeSelectedItem() {
        unselectedTermModulesList.getItems().add(selectedTermModulesList.getSelectionModel().getSelectedItem());
        selectedTermModulesList.getItems().remove(selectedTermModulesList.getSelectionModel().getSelectedItem());
    }

    public Module getSelectedItem() {
        return selectedTermModulesList.getSelectionModel().getSelectedItem();
    }

    public void addToUnselectedList(Module module, Delivery term) {
        if (module.getRunPlan() == term && !module.isMandatory()) {
            unselectedTermModulesList.getItems().add(module);
        }
    }

    //public void addToUnselectedList(Module module) {
    //    unselectedTermModulesList.getItems().add(module);
    //}

    public void addDoubleMouseClickSelectionHandler(EventHandler<MouseEvent> handler) {
        unselectedTermModulesList.setOnMouseClicked(handler);
    }

    public void removeModuleFromList(Module module) {
        unselectedTermModulesList.getItems().remove(module);
    }

    //public TermSelectionButtonPane getTermSelectionButtonPane() {
    //    return termSelectionButtonPane;
    //}

    public void addToSelectedList(Module module) {
        selectedTermModulesList.getItems().add(module);
    }

    public void setSelectedTermModulesLabel(String text) {
        this.selectedTermModulesLabel.setText(text);
    }

    public void setUnselectedTermModulesLabel(String text) {
        this.unselectedTermModulesLabel.setText(text);
    }

    public Module getUnSelectedModule() {
        return unselectedTermModulesList.getSelectionModel().getSelectedItem();
    }

    // Handlers below
    public void addAddHandler(EventHandler<ActionEvent> handler) {
        termSelectionButtonPane.getAddBtn().setOnAction(handler);
    }

    public void addRemoveHandler(EventHandler<ActionEvent> handler) {
        termSelectionButtonPane.getRemoveBtn().setOnAction(handler);
    }

}
