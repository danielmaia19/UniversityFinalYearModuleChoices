package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class TermSelectionButtonPane extends HBox {

    private int creditLimit;
    private Label lblCredits;
    private TextField credits;
    private Button addBtn;
    private Button removeBtn;

    public TermSelectionButtonPane() {

        HBox.setHgrow(this, Priority.ALWAYS);
        this.setPrefHeight(50);
        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);

        addBtn = new Button("Add");
        removeBtn = new Button("Remove");
        lblCredits = new Label("Total Credits:");
        credits = new TextField("0");
        credits.setPrefWidth(50);
        credits.setEditable(false);

        this.getChildren().addAll(addBtn, removeBtn, lblCredits, credits);
    }

    public int getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(int creditLimit) {
        this.creditLimit = creditLimit;
    }

    public int getCredits() {
        return Integer.parseInt(credits.getText());
    }

    public void setCredits(int value) {
        credits.setText(String.valueOf(value));
    }

    public Button getAddBtn() {
        return addBtn;
    }

    public Button getRemoveBtn() {
        return removeBtn;
    }

}
