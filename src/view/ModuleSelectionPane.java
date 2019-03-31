package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import model.Module;

public class ModuleSelectionPane extends VBox {

    private Label yearLongModulesLabel;
    private Button submitBtn, resetBtn;
    private ListView yearLongModulesList;
    private TermSelectionViewPane term1SectionViewPane;
    private TermSelectionViewPane term2SelectionViewPane;
    private TermSelectionButtonPane term1SelectionButtonPane, term2SelectionButtonPane;

    public ModuleSelectionPane() {

        VBox selectedYearLongModule = new VBox();
        yearLongModulesLabel = new Label("Selected Year Long Module");
        yearLongModulesList = new ListView<>();
        selectedYearLongModule.setPrefHeight(45);
        selectedYearLongModule.getChildren().addAll(yearLongModulesLabel, yearLongModulesList);

        // Term 1 Selection View Pane
        term1SectionViewPane = new TermSelectionViewPane();
        term1SectionViewPane.getUnselectedTermModulesLabel().setText("Unselected Term 1 Modules");
        term1SectionViewPane.getSelectedTermModulesLabel().setText("Selected Term 1 Modules");

        // Term 2 Selection View Pane
        term2SelectionViewPane = new TermSelectionViewPane();
        term2SelectionViewPane.getUnselectedTermModulesLabel().setText("Unselected Term 2 Modules");
        term2SelectionViewPane.getSelectedTermModulesLabel().setText("Selected Term 2 Modules");

        HBox actionBox = new HBox();
        submitBtn = new Button("Submit");
        resetBtn = new Button("Reset");
        actionBox.setSpacing(15);
        actionBox.setAlignment(Pos.CENTER);
        actionBox.setPrefWidth(150);
        actionBox.setPrefHeight(50);
        actionBox.getChildren().addAll(submitBtn, resetBtn);

        this.setPadding(new Insets(20,20,20,20));
        this.getChildren().addAll(selectedYearLongModule, term1SectionViewPane, term2SelectionViewPane, actionBox);
    }

    public TermSelectionViewPane getTerm1SectionViewPane() {
        return term1SectionViewPane;
    }
    public TermSelectionViewPane getTerm2SelectionViewPane() {
        return term2SelectionViewPane;
    }

    public void addYearLong(Module m) {
        yearLongModulesList.getItems().add(m);
    }

    // Button handlers
    public void submitModulesHandler(EventHandler<ActionEvent> handler) {
        submitBtn.setOnAction(handler);
    }
    public void resetModulesHandler(EventHandler<ActionEvent> handler) {
        resetBtn.setOnAction(handler);
    }
}
