package view;

import model.Module;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

/**
 * Module Selection View Pane class is used to create the Selection view pane to allow users to select modules on the
 * basis of Term 1, Term 2 and Year Long list views.
 * Consists of the relevant methods perform certain tasks relevant to this view pane.
 *
 * @author P1718603X
 */
public class ModuleSelectionViewPane extends VBox {

    // Fields
    private Label yearLongModulesLabel;
    private Button submitBtn, resetBtn;
    private ListView yearLongModulesList;
    private TermSelectionViewPane term1SectionViewPane, term2SelectionViewPane;

    // Constructors
    /**
     * Default Constructor:
     * Creates the Module Selection View Pane.
     * This view creates a Year Long list view, Term 1 and Term 2 list views
     * along with the add, remove and credit view associated to each of Term 1 and Term 2 list views.
     * A submit and reset buttons at the bottom of the view pane.
     */
    public ModuleSelectionViewPane() {

        VBox selectedYearLongModule = new VBox();
        yearLongModulesLabel = new Label("Selected Year Long Module");
        yearLongModulesList = new ListView<>();
        selectedYearLongModule.setPrefHeight(70);
        selectedYearLongModule.getChildren().addAll(yearLongModulesLabel, yearLongModulesList);

        // Term 1 Selection View Pane
        term1SectionViewPane = new TermSelectionViewPane();
        term1SectionViewPane.setCreditLimit(60);
        term1SectionViewPane.setUnselectedTermModulesLabel("Unselected Term 1 Modules:");
        term1SectionViewPane.setSelectedTermModulesLabel("Selected Term 1 Modules:");

        // Term 2 Selection View Pane
        term2SelectionViewPane = new TermSelectionViewPane();
        term2SelectionViewPane.setCreditLimit(60);
        term2SelectionViewPane.setUnselectedTermModulesLabel("Unselected Term 2 Modules:");
        term2SelectionViewPane.setSelectedTermModulesLabel("Selected Term 2 Modules:");

        HBox actionBox = new HBox();
        submitBtn = new Button("Submit");
        resetBtn = new Button("Reset");
        actionBox.setSpacing(15);
        actionBox.setAlignment(Pos.CENTER);
        actionBox.setPrefWidth(150);
        actionBox.setPrefHeight(50);
        actionBox.getChildren().addAll(submitBtn, resetBtn);

        this.setPadding(new Insets(20, 20, 20, 20));
        this.getChildren().addAll(selectedYearLongModule, term1SectionViewPane, term2SelectionViewPane, actionBox);
    }

    // Methods
    /**
     * Resets the year long, term1 and term2 lists.
     */
    public void resetAll() {
        yearLongModulesList.getItems().clear();
        term1SectionViewPane.resetListsAndCredits();
        term2SelectionViewPane.resetListsAndCredits();
    }

    /**
     * Returns the Term1 Selection View Pane.
     * @return the Term1 Selection View Pane.
     */
    public TermSelectionViewPane getTerm1SectionViewPane() {
        return term1SectionViewPane;
    }

    /**
     * Returns the Term2 Selection View Pane.
     * @return the Term Selection View Pane.
     */
    public TermSelectionViewPane getTerm2SelectionViewPane() {
        return term2SelectionViewPane;
    }

    /**
     * Adds the year long module to the year long list view.
     * @param module used to be added to the year long list view.
     */
    public void addYearLongModule(Module module) {
        yearLongModulesList.getItems().add(module);
    }

    // Event handlers
    /**
     * Sets a set on action click event on the submit button.
     * @param handler is passed as a EventHandler of ActionEvent to perform a task on button click.
     */
    public void addSubmitModulesHandler(EventHandler<ActionEvent> handler) {
        submitBtn.setOnAction(handler);
    }

    /**
     * Sets a set on action click event on the reset button.
     * @param handler is passed as a EventHandler of ActionEvent to perform a task on button click.
     */
    public void addResetModulesHandler(EventHandler<ActionEvent> handler) {
        resetBtn.setOnAction(handler);
    }
}
