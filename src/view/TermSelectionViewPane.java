package view;

import model.Module;
import model.Delivery;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ListView;

/**
 * The Term Selection View Pane represents the term selection view pane byt having 2 listviews
 * for each selected and unselected modules also containing buttons from the button pane.
 *
 * @author P1718603X
 */
public class TermSelectionViewPane extends VBox {

    // Fields
    private TermSelectionButtonPane termSelectionButtonPane;
    private Label selectedTermModulesLabel, unselectedTermModulesLabel;
    private ListView<Module> selectedTermModulesList, unselectedTermModulesList;

    // Constructors

    /**
     * Default Constructor:
     * Creates the 2 list views of selected and unselected list views
     * A reference to the Button pane class to show the add, remove and credits for each
     * instance of this Term Selection View Pane.
     */
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

    // Methods

    /**
     * Adds the removed module and adds it to the unselected module list view.
     */
    public void removeSelectedItem() {
        unselectedTermModulesList.getItems().add(selectedTermModulesList.getSelectionModel().getSelectedItem());
        selectedTermModulesList.getItems().remove(selectedTermModulesList.getSelectionModel().getSelectedItem());
    }

    /**
     * Resets the selected, unselected and credits to empty fields.
     */
    public void resetListsAndCredits() {
        selectedTermModulesList.getItems().clear();
        unselectedTermModulesList.getItems().clear();
        termSelectionButtonPane.setCredits(0);
    }

    /**
     * Increases the credits value by a number passed.
     *
     * @param value to be passed to increase the credit value by.
     */
    public void increaseCreditsBy(int value) {
        int newVal = value + termSelectionButtonPane.getCredits();
        termSelectionButtonPane.setCredits(newVal);
        //setCredits(getCredits() + getSelectedItem().getCredits());
    }

    /**
     * Decreases the credits value by a number passed.
     *
     * @param value to be passed to increase the credit value by.
     */
    public void decreaseCreditsBy(int value) {
        termSelectionButtonPane.setCredits(value - getSelectedItem().getCredits());
    }

    /**
     * Adds the modules to the unselected list view that is in a certain term and its not mandatory.
     *
     * @param module to be passed to add to the unselected list view.
     * @param term   to be passed to add to the correct term list view.
     */
    public void addToUnselectedList(Module module, Delivery term) {
        if (module.getRunPlan() == term && !module.isMandatory()) {
            unselectedTermModulesList.getItems().add(module);
        }
    }

    /**
     * Returns the credits value.
     *
     * @return the credits value.
     */
    public int getCredits() {
        return termSelectionButtonPane.getCredits();
    }

    // Delegation to ButtonPane

    /**
     * Delegation to the button pane by getting the credit limit.
     *
     * @return the delegation to the button pane to return the credit limit.
     */
    public int getCreditLimit() {
        return termSelectionButtonPane.getCreditLimit();
    }

    /**
     * Adds module to the selected list view.
     *
     * @param module to be added to the selected list view.
     */
    public void addToSelectedList(Module module) {
        selectedTermModulesList.getItems().add(module);
    }

    /**
     * Delegation to the button pane by setting the credit limit value.
     *
     * @param creditLimit to be passed as a int to set the credit limit value.
     */
    public void setCreditLimit(int creditLimit) {
        termSelectionButtonPane.setCreditLimit(creditLimit);
    }

    /**
     * Sets the selected term module label.
     *
     * @param text to passed as a string to set the selected term modules label.
     */
    public void setSelectedTermModulesLabel(String text) {
        selectedTermModulesLabel.setText(text);
    }

    /**
     * Removes the module from the unselected module list.
     *
     * @param module to be passed to remove from the list view.
     */
    public void removeModuleFromList(Module module) {
        unselectedTermModulesList.getItems().remove(module);
    }

    /**
     * Sets the unselected term module label.
     *
     * @param text to passed as a string to set the unselected term modules label.
     */
    public void setUnselectedTermModulesLabel(String text) {
        unselectedTermModulesLabel.setText(text);
    }

    /**
     * Returns the selected module from the selected list view.
     *
     * @return the selected module from the selected list view.
     */
    public Module getSelectedItem() {
        return selectedTermModulesList.getSelectionModel().getSelectedItem();
    }

    /**
     * Returns the unselected module from the selected list view.
     *
     * @return the unselected module from the selected list view.
     */
    public Module getUnSelectedModule() {
        return unselectedTermModulesList.getSelectionModel().getSelectedItem();
    }

    // Event Handlers

    /**
     * Sets a set on action click event on the add button.
     *
     * @param handler is passed as a EventHandler of ActionEvent to perform a task on button click.
     */
    public void addAddHandler(EventHandler<ActionEvent> handler) {
        termSelectionButtonPane.getAddBtn().setOnAction(handler);
    }

    /**
     * Sets a set on action click event on the remove button.
     *
     * @param handler is passed as a EventHandler of ActionEvent to perform a task on button click.
     */
    public void addRemoveHandler(EventHandler<ActionEvent> handler) {
        termSelectionButtonPane.getRemoveBtn().setOnAction(handler);
    }

    /**
     * Sets a set on mouse click event on double clicks to unselected modules.
     *
     * @param handler is passed as a EventHandler of Mouse Event to perform a task on double clicks on modules.
     */
    public void addDoubleMouseAddClickSelectionHandler(EventHandler<MouseEvent> handler) {
        unselectedTermModulesList.setOnMouseClicked(handler);
    }

    /**
     * Sets a set on mouse click event on double clicks to selected modules.
     *
     * @param handler is passed as a EventHandler of Mouse Event to perform a task on double clicks on modules.
     */
    public void addDoubleMouseRemoveClickSelectionHandler(EventHandler<MouseEvent> handler) {
        selectedTermModulesList.setOnMouseClicked(handler);
    }

}
