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

public class ModuleSelectionViewPane extends VBox {

    private Label yearLongModulesLabel;
    private Button submitBtn, resetBtn;
    private ListView yearLongModulesList;
    private TermSelectionViewPane term1SectionViewPane, term2SelectionViewPane;

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

    public void resetAll() {
        yearLongModulesList.getItems().clear();
        term1SectionViewPane.resetListsAndCredits();
        term2SelectionViewPane.resetListsAndCredits();
    }

    public TermSelectionViewPane getTerm1SectionViewPane() {
        return term1SectionViewPane;
    }

    public TermSelectionViewPane getTerm2SelectionViewPane() {
        return term2SelectionViewPane;
    }

    public void addYearLongModule(Module module) {
        yearLongModulesList.getItems().add(module);
    }

    // Button handlers

    public void addSubmitModulesHandler(EventHandler<ActionEvent> handler) {
        submitBtn.setOnAction(handler);
    }

    public void addResetModulesHandler(EventHandler<ActionEvent> handler) {
        resetBtn.setOnAction(handler);
    }

}
