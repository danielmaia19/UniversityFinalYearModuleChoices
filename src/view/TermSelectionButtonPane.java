package view;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.Priority;
import javafx.scene.control.TextField;

/**
 * This class represents a button pane to be used with any Term.
 * The button pane consists of credits label, credits textfield and
 * 2 buttons of add and remove to modules from the list.
 *
 * @author P1718603X
 */
public class TermSelectionButtonPane extends HBox {

    // Fields
    private int creditLimit;
    private Label lblCredits;
    private TextField credits;
    private Button addBtn, removeBtn;

    // Constructors

    /**
     * Default Constructor:
     * The button pane consists of credits label, credits textfield and
     * 2 buttons of add and remove to modules from the list.
     */
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

    // Methods

    /**
     * Returns the add button.
     *
     * @return the add button.
     */
    public Button getAddBtn() {
        return addBtn;
    }

    /**
     * Returns the remove button.
     *
     * @return the remove button.
     */
    public Button getRemoveBtn() {
        return removeBtn;
    }

    /**
     * Returns the credit limit value.
     *
     * @return the credit limit value.
     */
    public int getCreditLimit() {
        return creditLimit;
    }

    /**
     * Returns the credits value.
     *
     * @return the credits value.
     */
    public int getCredits() {
        return Integer.parseInt(credits.getText());
    }

    /**
     * Sets the credits value.
     *
     * @param value is the number to set the credits value.
     */
    public void setCredits(int value) {
        credits.setText(String.valueOf(value));
    }

    /**
     * Sets the credit limit on the term.
     *
     * @param max is the limit set on the term.
     */
    public void setCreditLimit(int max) {
        creditLimit = max;
    }
}
