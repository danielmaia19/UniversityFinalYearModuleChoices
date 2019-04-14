package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;

public class OverviewViewPane extends VBox {

    private TextArea overview;
    private Button saveOverviewBtn;

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

    public void clearResults() {
        overview.clear();
    }

    public void setResults(String overviewText) {
        overview.appendText(overviewText);
    }

    public void addSaveOverviewHandler(EventHandler<ActionEvent> handler) {
        saveOverviewBtn.setOnAction(handler);
    }

}
