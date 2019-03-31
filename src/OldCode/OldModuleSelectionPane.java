package OldCode;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class OldModuleSelectionPane extends HBox {

    private Label unselectedTerm1ModulesLabel, unselectedTerm2ModulesLabel, yearLongModulesLabel, selectedTerm1ModulesLabel,
    selectedTerm2ModulesLabel, currentTerm1CreditsLabel, currentTerm2CreditsLabel, term1Label, term2Label;

    private Button term1AddBtn, term1RemoveBtn, term2AddBtn, term2RemoveBtn, resetBtn, submitBtn;

    private ListView unselectedTerm1ModulesList, unselectedTerm2ModulesList, yearLongModulesList, selectedTerm1ModulesList,
            selectedTerm2ModulesList;

    private TextField term1CreditsTxtField, term2CreditsTxtField;

    public OldModuleSelectionPane() {

        resetBtn = new Button("Reset");
        submitBtn = new Button("Submit");

        // 1st Column
        VBox unselectedModules = new VBox();

        VBox unselectedTerm1Items = new VBox();
        unselectedTerm1Items.setMinHeight(150);
        unselectedTerm1ModulesLabel = new Label("Unselected Term 1 Modules");
        unselectedTerm1ModulesList = new ListView<>();
        unselectedTerm1ModulesList.setMaxHeight(150);
        unselectedTerm1Items.getChildren().addAll(unselectedTerm1ModulesLabel, unselectedTerm1ModulesList);

        HBox term1Buttons = new HBox();
        term1Label = new Label("Term 1");
        term1Buttons.setAlignment(Pos.CENTER_RIGHT);
        term1AddBtn = new Button("Add");
        term1AddBtn.setPrefWidth(70);
        term1RemoveBtn = new Button("Remove");
        term1RemoveBtn.setPrefWidth(80);
        term1RemoveBtn.setPadding(new Insets(5, 5, 5, 5));
        term1Label.setPadding(new Insets(10, 10, 10, 10));
        term1Buttons.setPadding(new Insets(10, 10, 10, 10));
        term1Buttons.setSpacing(15);
        term1Buttons.setAlignment(Pos.CENTER);
        term1Buttons.getChildren().addAll(term1Label, term1AddBtn, term1RemoveBtn);

        VBox unselectedTerm2Items = new VBox();
        unselectedTerm2ModulesLabel = new Label("Unselected Term 2 Modules");
        unselectedTerm2ModulesList = new ListView<>();
        unselectedTerm2ModulesList.setMaxHeight(150);
        unselectedTerm2Items.getChildren().addAll(unselectedTerm2ModulesLabel, unselectedTerm2ModulesList);

        HBox term2Buttons = new HBox();
        term2Label = new Label("Term 2");
        term2Buttons.setAlignment(Pos.CENTER);
        term2AddBtn = new Button("Add");
        term2AddBtn.setPrefWidth(70);
        term2RemoveBtn = new Button("Remove");
        term2RemoveBtn.setPrefWidth(80);
        term2RemoveBtn.setPadding(new Insets(5, 5, 5, 5));
        term2Label.setPadding(new Insets(10, 10, 10, 10));
        term2Buttons.setPadding(new Insets(10, 10, 10, 10));
        term2Buttons.setSpacing(15);
        term2Buttons.setAlignment(Pos.CENTER);
        term2Buttons.getChildren().addAll(term2Label, term2AddBtn, term2RemoveBtn);

        HBox term1CreditField = new HBox();
        currentTerm1CreditsLabel = new Label("Current Term 1 Credits:");
        currentTerm1CreditsLabel.setPadding(new Insets(10, 10, 10, 10));

        term1CreditsTxtField = new TextField();
        term1CreditsTxtField.setPrefWidth(50);
        term1CreditsTxtField.setEditable(false);

        term1CreditField.setAlignment(Pos.CENTER);
        term1CreditField.setPadding(new Insets(10, 10, 10, 10));
        term1CreditField.setSpacing(15);
        term1CreditField.setAlignment(Pos.CENTER);
        term1CreditField.getChildren().addAll(currentTerm1CreditsLabel, term1CreditsTxtField);

        unselectedModules.setAlignment(Pos.CENTER_RIGHT);
        unselectedModules.setPrefWidth(350);
        unselectedModules.setPadding(new Insets(20, 20, 20, 20));
        unselectedModules.getChildren().addAll(unselectedTerm1Items, term1Buttons, unselectedTerm2Items, term2Buttons, term1CreditField, submitBtn);

        // 2nd Column
        VBox selectedModules = new VBox();

        VBox yearLongModules = new VBox();
        yearLongModulesLabel = new Label("Selected Year Long Module");
        yearLongModulesList = new ListView<>();
        yearLongModulesList.setPrefHeight(50);
        yearLongModules.setPadding(new Insets(10, 10, 10, 10));
        yearLongModules.getChildren().addAll(yearLongModulesLabel, yearLongModulesList);

        VBox selectedTerm1Items = new VBox();
        selectedTerm1Items.setMinHeight(150);
        selectedTerm1ModulesLabel = new Label("Selected Term 1 Modules");
        selectedTerm1ModulesList = new ListView<>();
        selectedTerm1ModulesList.setMaxHeight(150);
        selectedTerm1Items.setPadding(new Insets(10, 10, 10, 10));
        selectedTerm1Items.getChildren().addAll(selectedTerm1ModulesLabel, selectedTerm1ModulesList);

        VBox selectedTerm2Items = new VBox();
        selectedTerm2ModulesLabel = new Label("Selected Term 2 Modules");
        selectedTerm2ModulesList = new ListView<>();
        selectedTerm2ModulesList.setMaxHeight(150);
        selectedTerm2Items.setPadding(new Insets(10, 10, 10, 10));
        selectedTerm2Items.getChildren().addAll(selectedTerm2ModulesLabel, selectedTerm2ModulesList);

        HBox term2CreditField = new HBox();
        currentTerm2CreditsLabel = new Label("Current Term 2 Credits:");
        currentTerm2CreditsLabel.setPadding(new Insets(10, 10, 10, 10));

        term2CreditsTxtField = new TextField();
        term2CreditsTxtField.setPrefWidth(50);
        term2CreditsTxtField.setEditable(false);

        term2CreditField.setAlignment(Pos.CENTER);
        term2CreditField.setPadding(new Insets(10, 10, 10, 10));
        term2CreditField.setSpacing(15);
        term2CreditField.setAlignment(Pos.CENTER);
        term2CreditField.getChildren().addAll(currentTerm2CreditsLabel, term2CreditsTxtField);

        selectedModules.setPrefWidth(350);
        selectedModules.setPadding(new Insets(10, 10, 10, 10));
        selectedModules.getChildren().addAll(yearLongModules, selectedTerm1Items, selectedTerm2Items, term2CreditField, resetBtn);

        //test.setBorder(new Border(new BorderStroke(Color.BLACK,
        //        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        //
        //test.getChildren().addAll(unselectedModules, selectedModules);

        this.getChildren().addAll(unselectedModules, selectedModules);
    }
}
