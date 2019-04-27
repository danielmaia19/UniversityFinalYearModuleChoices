package view;

import model.Course;
import java.util.Set;
import javafx.geometry.Pos;
import java.time.LocalDate;
import javafx.util.Callback;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.layout.HBox;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import javafx.beans.value.ChangeListener;
import javafx.beans.binding.BooleanBinding;

/**
 * Creates the Student Profile View for allow user interactivity to enter their information.
 * This includes, their course, P number, first name, surname, and email.
 *
 * @author P1718603X
 */
public class StudentProfileViewPane extends GridPane {

    // Fields
    private DatePicker date;
    private Button btnProfile;
    private Label emailValidation;
    private ComboBox<Course> cboCourses;
    private TextField txtSurname, txtFirstName, txtPNumber, txtEmail;

    // Constructors

    /**
     * Default Constructor:
     * The student profile view pane is created with the relevant fields to allow users to enter their information.
     * This includes, their course, P number, first name, surname, and email.
     */
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

    // Methods

    /**
     * Check if the inputted data is a valid email address
     * using regular expression.
     *
     * @return if the email is valid by returning a boolean.
     */
    public boolean isEmailValid() {
        return txtEmail.getText().matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    }

    /**
     * Adds a boolean binding to all the text properties including the combobox of courses and
     * the datepicker.
     *
     * @return a boolean providing all the properties are empty.
     */
    public BooleanBinding isEitherFieldEmpty() {
        return txtPNumber.textProperty().isEmpty()
                .or(txtFirstName.textProperty().isEmpty())
                .or(txtSurname.textProperty().isEmpty())
                .or(txtEmail.textProperty().isEmpty())
                .or(date.valueProperty().isNull())
                .or(cboCourses.valueProperty().isNull());
    }

    /**
     * Returns the date selected from the datepicker.
     *
     * @return the date selected from the datepicker.
     */
    public LocalDate getDate() {
        return date.getValue();
    }

    /**
     * Returns the email form the email textfield.
     *
     * @return the email from the email textfield.
     */
    public String getTxtEmail() {
        return txtEmail.getText();
    }

    /**
     * Clears the courses from the combobox.
     */
    public void clearCombox() {
        cboCourses.getItems().clear();
    }

    /**
     * Returns the p number form the p number textfield.
     *
     * @return the p number from the p number textfield.
     */
    public String getTxtPNumber() {
        return txtPNumber.getText();
    }

    /**
     * Returns the surname form the surname textfield.
     *
     * @return the surname from the surname textfield.
     */
    public String getTxtSurname() {
        return txtSurname.getText();
    }

    /**
     * Sets the date by passing a LocalDate object type.
     *
     * @param date is passed as a LocalDate object type.
     */
    public void setDate(LocalDate date) {
        this.date.setValue(date);
    }

    /**
     * Returns the first name from the textfield.
     *
     * @return the first name from the textfield.
     */
    public String getTxtFirstName() {
        return txtFirstName.getText();
    }

    /**
     * Populates the combobox with courses passed by a set of courses to prevent duplicate entries.
     *
     * @param courses is passed by a Set to avoid duplicate courses.
     */
    public void populateComboBoxWithCourses(Set<Course> courses) {
        cboCourses.getItems().addAll(courses);
        cboCourses.getSelectionModel().select(0);
    }

    /**
     * Sets the email textfield.
     *
     * @param txtEmail is passed as a string to set the email textfield.
     */
    public void setTxtEmail(String txtEmail) {
        this.txtEmail.setText(txtEmail);
    }

    /**
     * Sets the surname textfield.
     *
     * @param txtSurname is passed as a string to set the surname textfield.
     */
    public void setTxtSurname(String txtSurname) {
        this.txtSurname.setText(txtSurname);
    }

    /**
     * Sets the P Number textfield.
     *
     * @param txtPNumber is passed as a string to set the P Number textfield.
     */
    public void setTxtPNumber(String txtPNumber) {
        this.txtPNumber.setText(txtPNumber);
    }

    /**
     * Sets the validation message for invalid email.
     *
     * @param message is passed as a string to set the validation error message.
     */
    public void setInvalidEmailMessage(String message) {
        emailValidation.setText(message);
    }

    /**
     * Sets the first name textfield.
     *
     * @param txtFirstName is passed as a string to set the first name textfield.
     */
    public void setTxtFirstName(String txtFirstName) {
        this.txtFirstName.setText(txtFirstName);
    }

    /**
     * Returns the selected course form the combobox.
     *
     * @return the selected course from the combobox.
     */
    public Course getSelectedCourse() {
        return cboCourses.getSelectionModel().getSelectedItem();
    }

    /**
     * Sets the Selected course in the combobox.
     *
     * @param course is the course to be selected in the combobox.
     */
    public void setSelectedCourse(Course course) {
        cboCourses.getSelectionModel().select(course);
    }

    /**
     * Disables the add button by passing a BooleanBinding object type.
     *
     * @param property passing a boolean binding object type.
     */
    public void addBtnDisableBind(BooleanBinding property) {
        btnProfile.disableProperty().bind(property);
    }

    // Event Handlers

    /**
     * Sets a set on action click event on the create student profile button.
     *
     * @param handler is passed as a EventHandler of ActionEvent to perform a task on button click.
     */
    public void addCreateStudentProfileHandler(EventHandler<ActionEvent> handler) {
        btnProfile.setOnAction(handler);
    }

    // Change Listeners

    /**
     * Adds a listener to the PNumber textfield.
     *
     * @param listener is passed as a EventHandler of ActionEvent to perform a task on button click.
     */
    public void addPNumberChangeListener(ChangeListener<String> listener) {
        txtPNumber.textProperty().addListener(listener);
    }

    /**
     * Adds a listener to the surname textfield.
     *
     * @param listener is passed as a EventHandler of ActionEvent to perform a task on button click.
     */
    public void addSurnameChangeListener(ChangeListener<String> listener) {
        txtSurname.textProperty().addListener(listener);
    }

    /**
     * Adds a listener to the first name textfield.
     *
     * @param listener is passed as a EventHandler of ActionEvent to perform a task on button click.
     */
    public void addFirstNameChangeListener(ChangeListener<String> listener) {
        txtFirstName.textProperty().addListener(listener);
    }

    /**
     * Disables past dates on the datepicker
     * Reference from here:
     * https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/DatePicker.html#setDayCellFactory-javafx.util.Callback-
     */
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

