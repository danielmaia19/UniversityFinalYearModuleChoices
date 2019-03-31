package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class TermSelectionButtonPane extends HBox {

    private Label credits;
    private Button addBtn, removeBtn;
    private TextField creditsTxtField;

    public TermSelectionButtonPane() {

        HBox.setHgrow(this, Priority.ALWAYS);
        this.setPrefHeight(50);
        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);

        addBtn = new Button("Add");
        removeBtn = new Button("Remove");
        credits = new Label("Total Credits:");
        creditsTxtField = new TextField("0");
        creditsTxtField.setPrefWidth(50);
        creditsTxtField.setEditable(false);

        this.getChildren().addAll(addBtn, removeBtn, credits, creditsTxtField);
    }

    public String getCreditsTxtField() {
        return creditsTxtField.getText();
    }
    public void setCreditsTxtField(String creditsTxtField) {
        this.creditsTxtField.setText(creditsTxtField);
    }

    public void addAddHandler(EventHandler<ActionEvent> handler) {
        addBtn.setOnAction(handler);
    }
    public void addRemoveHandler(EventHandler<ActionEvent> handler) {
        removeBtn.setOnAction(handler);
    }

}
