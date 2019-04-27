package view;

import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * Overview View Pane creates an overview of the results of the modules from the selected.
 * There is also a button to allow users to save the overview.
 *
 * @author P1718603X
 */
public class OverviewViewPane extends VBox {

    // Fields
    private TextArea overview;
    private Button saveOverviewBtn;

    // Constructors/**

    /**
     * Default Constructor:
     * Creates the view of a large textarea to contain the results of the selection of modules
     * Textarea contains a button for saving the overview.
     */
    public OverviewViewPane() {

        // Setting the padding for this VBox
        this.setPadding(new Insets(30, 30, 30, 30));

        // Create the overview textarea to display the students overview selection
        VBox overviewBox = new VBox();
        overviewBox.setMinHeight(300);
        overviewBox.setMinHeight(400);
        overview = new TextArea();
        overview.setEditable(false);
        overviewBox.getChildren().add(overview);

        // Create the button to save students overview selection
        VBox button = new VBox();
        saveOverviewBtn = new Button("Save Overview");
        button.setAlignment(Pos.CENTER);
        button.setPadding(new Insets(20, 20, 0, 20));
        button.getChildren().add(saveOverviewBtn);

        VBox.setVgrow(overview, Priority.ALWAYS);
        VBox.setVgrow(overviewBox, Priority.ALWAYS);
        this.getChildren().addAll(overviewBox, button);
    }

    // Methods

    /**
     * Clears the text from the textarea.
     */
    public void clearResults() {
        overview.clear();
    }

    /**
     * Sets the overview results from the module selections.
     *
     * @param overviewText string to append to the overview from the modules selected.
     */
    public void setOverviewResults(String overviewText) {
        overview.appendText(overviewText + "\n");
    }

    // Event Handlers

    /**
     * Sets a set on action click event on the save overview button.
     *
     * @param handler is passed as a EventHandler of ActionEvent to perform a task on button click.
     */
    public void addSaveOverviewHandler(EventHandler<ActionEvent> handler) {
        saveOverviewBtn.setOnAction(handler);
    }
}
