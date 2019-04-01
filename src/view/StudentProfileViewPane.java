package view;

import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Callback;
import model.Course;
import sun.plugin2.jvm.RemoteJVMLauncher;

import java.time.LocalDate;
import java.time.MonthDay;

public class StudentProfileViewPane extends GridPane {

    private Button btnProfile;
    private Label emailValidation;
    private ComboBox<Course> cboCourses;
    private TextField txtSurname, txtFirstName, txtPNumber, txtEmail;

    private DatePicker date;

    public StudentProfileViewPane() {
        //styling
        this.setPadding(new Insets(80, 80, 80, 80));
        this.setVgap(15);
        this.setHgap(20);
        this.setAlignment(Pos.CENTER);

        emailValidation = new Label();
        emailValidation.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");

        date = new DatePicker();
        date.setDayCellFactory(new DisablePastDates());
        date.setEditable(false);

        //create labels
        Label lblTitle = new Label("Select Course: ");
        Label lblPNumber = new Label("Input P Number: ");
        Label lblFirstName = new Label("Input first name: ");
        Label lblSurname = new Label("Input surname: ");
        Label lblEmail = new Label("Input email: ");
        Label lblDate = new Label("Input date: ");

        // setup combobox
        cboCourses = new ComboBox<>();

        //setup text fields
        txtPNumber = new TextField();
        txtFirstName = new TextField();
        txtSurname = new TextField();
        txtEmail = new TextField();

        //initialise play button
        btnProfile = new Button("Create Profile");
        btnProfile.setDisable(true);

        //add controls and labels to container
        this.add(lblTitle, 0, 0);
        this.add(cboCourses, 1, 0);

        this.add(lblPNumber, 0, 1);
        this.add(txtPNumber, 1, 1);

        this.add(lblFirstName, 0, 2);
        this.add(txtFirstName, 1, 2);

        this.add(lblSurname, 0, 3);
        this.add(txtSurname, 1, 3);

        this.add(lblEmail, 0, 4);
        this.add(txtEmail, 1, 4);

        this.add(lblDate, 0, 5);
        this.add(date, 1, 5);

        this.add(new HBox(), 0, 6);
        this.add(btnProfile, 1, 6);

        this.add(emailValidation, 1, 7);
    }

    // Get Methods
    public TextField getTxtSurname() {
        return txtSurname;
    }
    public TextField getTxtFirstName() {
        return txtFirstName;
    }
    public TextField getTxtPNumber() {
        return txtPNumber;
    }
    public TextField getTxtEmail() {
        return txtEmail;
    }
    public Course getSelectedCourse() {
        return cboCourses.getSelectionModel().getSelectedItem();
    }
    public DatePicker getDate() {
        return date;
    }

    // Set methods
    public void setEmailInvalidMessage(String message) {
        emailValidation.setText(message);
    }

    public void populateComboBoxWithCourses(Course[] courses) {
        cboCourses.getItems().addAll(courses);
        cboCourses.getSelectionModel().select(0); //select first opponent by default
    }

    public void addCreateStudentProfileHandler(EventHandler<ActionEvent> handler) {
        btnProfile.setOnAction(handler);
    }

    public void addBtnDisableBind(BooleanBinding property) {
        btnProfile.disableProperty().bind(property);
    }

    public BooleanBinding isEitherFieldEmpty() {
        return txtPNumber.textProperty().isEmpty()
                .or(txtFirstName.textProperty().isEmpty())
                .or(txtSurname.textProperty().isEmpty())
                .or(txtEmail.textProperty().isEmpty())
                .or(date.valueProperty().isNull());
    }

    private class DisablePastDates implements Callback<DatePicker, DateCell> {
        @Override
        public DateCell call(DatePicker param) {
            return new DateCell() {
                @Override
                public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item.compareTo(LocalDate.now()) < 0 ) {
                        setDisable(true);
                    }
                }
            };
        }
    }
}

