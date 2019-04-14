package view;

import javafx.beans.value.ChangeListener;
import model.Course;
import javafx.geometry.Pos;

import java.time.LocalDate;

import javafx.util.Callback;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import javafx.beans.binding.BooleanBinding;

public class StudentProfileViewPane extends GridPane {

    private DatePicker date;
    private Button btnProfile;
    private Label emailValidation;
    private ComboBox<Course> cboCourses;
    private TextField txtSurname, txtFirstName, txtPNumber, txtEmail;

    public StudentProfileViewPane() {
        //styling
        this.setPadding(new Insets(80, 80, 80, 80));
        this.setVgap(15);
        this.setHgap(20);
        this.setAlignment(Pos.CENTER);
        //this.setGridLinesVisible(true);

        emailValidation = new Label();
        emailValidation.setStyle("-fx-text-fill: red; -fx-font-size: 13px;");

        date = new DatePicker();
        date.setDayCellFactory(new DisablePastDates());
        date.setEditable(false);

        //create labels
        Label lblCourse = new Label("Select Course: ");
        Label lblPNumber = new Label("P Number: ");
        Label lblFirstName = new Label("First Name: ");
        Label lblSurname = new Label("Surname: ");
        Label lblEmail = new Label("Email: ");
        Label lblDate = new Label("Submission Date: ");

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
        this.add(lblCourse, 0, 0);
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

    // Used regular expressions to validate email address
    public boolean isEmailValid() {
        return txtEmail.getText().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    }

    public BooleanBinding isEitherFieldEmpty() {
        return txtPNumber.textProperty().isEmpty()
                .or(txtFirstName.textProperty().isEmpty())
                .or(txtSurname.textProperty().isEmpty())
                .or(txtEmail.textProperty().isEmpty())
                .or(date.valueProperty().isNull());
    }

    public LocalDate getDate() {
        return date.getValue();
    }

    public String getTxtEmail() {
        return txtEmail.getText();
    }

    public String getTxtPNumber() {
        return txtPNumber.getText();
    }

    public String getTxtSurname() {
        return txtSurname.getText();
    }

    public void populateComboBoxWithCourses(Course[] courses) {
        cboCourses.getItems().addAll(courses);
        cboCourses.getSelectionModel().select(0);
    }

    public void setDate(LocalDate date) {
        this.date.setValue(date);
    }

    public String getTxtFirstName() {
        return txtFirstName.getText();
    }

    public void setTxtEmail(String txtEmail) {
        this.txtEmail.setText(txtEmail);
    }

    public void setTxtSurname(String txtSurname) {
        this.txtSurname.setText(txtSurname);
    }

    public void setTxtPNumber(String txtPNumber) {
        this.txtPNumber.setText(txtPNumber);
    }

    public void setInvalidEmailMessage(String message) {
        emailValidation.setText(message);
    }

    public void setTxtFirstName(String txtFirstName) {
        this.txtFirstName.setText(txtFirstName);
    }

    public Course getSelectedCourse() {
        return cboCourses.getSelectionModel().getSelectedItem();
    }

    public void setSelectedCourse(Course course) {
        cboCourses.getSelectionModel().select(course);
    }

    public void addBtnDisableBind(BooleanBinding property) {
        btnProfile.disableProperty().bind(property);
    }

    // Event Handlers
    public void addCreateStudentProfileHandler(EventHandler<ActionEvent> handler) {
        btnProfile.setOnAction(handler);
    }

    // Change listeners
    public void addPNumberChangeListener(ChangeListener<String> handler) {
        txtPNumber.textProperty().addListener(handler);
    }

    public void addFirstNameChangeListener(ChangeListener<String> handler) {
        txtFirstName.textProperty().addListener(handler);
    }

    public void addSurnameChangeListener(ChangeListener<String> handler) {
        txtSurname.textProperty().addListener(handler);
    }

    // Disables past dates on the datepicker
    // Reference from here:
    // https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/DatePicker.html#setDayCellFactory-javafx.util.Callback-
    private class DisablePastDates implements Callback<DatePicker, DateCell> {
        @Override
        public DateCell call(DatePicker param) {
            return new DateCell() {
                @Override
                public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item.compareTo(LocalDate.now()) < 0) {
                        setDisable(true);
                    }
                }
            };
        }
    }
}

