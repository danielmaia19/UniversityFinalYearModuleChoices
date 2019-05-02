package controller;

import view.*;
import model.*;
import java.io.*;
import java.util.*;
import java.nio.file.Paths;
import javafx.stage.FileChooser;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.*;
import javafx.scene.input.MouseEvent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.property.StringProperty;
import static java.util.stream.Collectors.toList;

/**
 * As the controller, you will find all the logic to the application here, with all the handlers.
 * bindings and listeners.
 *
 * @author P1718603X
 */
public class OptionsModuleChooserController {

    // Fields
    List<Module> courseModules;
    private StudentProfile model;
    private OptionsModuleChooserRootPane view;
    private OverviewViewPane overviewViewPane;
    private StudentProfileViewPane studentProfileViewPane;
    private ModuleSelectionViewPane moduleSelectionViewPane;
    private OptionsModuleChooserMenuBar optionsModuleChooserMenuBar;
    private TermSelectionViewPane term1SelectionPane, term2SelectionPane;

    // Constructors

    /**
     * The constructor is used to pass the view and controller in the main application class.
     *
     * @param view  is passed to create the view of the application
     * @param model is passed to create the model of the Student Profile.
     */
    public OptionsModuleChooserController(OptionsModuleChooserRootPane view, StudentProfile model) {
        //initialise model and view fields
        this.model = model;
        this.view = view;

        optionsModuleChooserMenuBar = view.getMenuBar();
        studentProfileViewPane = view.getStudentProfileViewPane();
        moduleSelectionViewPane = view.getModuleSelectionViewPane();
        overviewViewPane = view.getOverviewViewPane();
        term1SelectionPane = view.getModuleSelectionViewPane().getTerm1SectionViewPane();
        term2SelectionPane = view.getModuleSelectionViewPane().getTerm2SelectionViewPane();

        studentProfileViewPane.populateComboBoxWithCourses(setupAndRetrieveCourses());

        // Attach Event Handlers
        this.attachEventHandlers();

        // Attach Bindings
        this.attachBindings();

        // Attach Change Listeners
        this.attachChangeListeners();
    }

    // Methods

    /**
     * Creates the Bindings needed for the application and
     * attaches the Bindings in the constructor.
     */
    private void attachBindings() {
        studentProfileViewPane.addBtnDisableBind(studentProfileViewPane.isEitherFieldEmpty());
    }

    /**
     * Creates the Events Handlers needed for the application and
     * attaches the Event Handlers in the constructors.
     */
    private void attachEventHandlers() {
        optionsModuleChooserMenuBar.addAboutHandler(a -> alertDialogBuilder(AlertType.INFORMATION, "Information Dialog", null, "Options Module Chooser MVC app v1.0"));
        optionsModuleChooserMenuBar.addExitHandler(e -> System.exit(0));

        studentProfileViewPane.addCreateStudentProfileHandler(new StudentProfilesHandler());
        moduleSelectionViewPane.addSubmitModulesHandler(new SubmitModulesHandler());
        moduleSelectionViewPane.addResetModulesHandler(new ResetModulesHandler());

        optionsModuleChooserMenuBar.addLoadStudentDataHandler(new LoadStudentHandler());
        optionsModuleChooserMenuBar.addSaveStudentDataHandler(new SaveStudentHandler());

        // Term 1 - Add & Remove Handlers
        term1SelectionPane.addAddHandler(new AddHandler(term1SelectionPane));
        term1SelectionPane.addRemoveHandler(new RemoveHandler(term1SelectionPane));

        // Term 2 - Add & Remove Handlers
        term2SelectionPane.addAddHandler(new AddHandler(term2SelectionPane));
        term2SelectionPane.addRemoveHandler(new RemoveHandler(term2SelectionPane));

        // Term 1 - Select and Remove items with double clicks
        term1SelectionPane.addDoubleMouseAddClickSelectionHandler(new DoubleMouseAddClickSelectionHandler(term1SelectionPane));
        term1SelectionPane.addDoubleMouseRemoveClickSelectionHandler(new DoubleMouseRemoveClickSelectionHandler(term1SelectionPane));

        // Term 2 - Select and Remove items with double clicks
        term2SelectionPane.addDoubleMouseAddClickSelectionHandler(new DoubleMouseAddClickSelectionHandler(term2SelectionPane));
        term2SelectionPane.addDoubleMouseRemoveClickSelectionHandler(new DoubleMouseRemoveClickSelectionHandler(term2SelectionPane));

        overviewViewPane.addSaveOverviewHandler(new SaveOverviewHandler());

        optionsModuleChooserMenuBar.addLoadCourseDataHandler(new LoadCoursesHandler());
    }

    /**
     * Creates the Change Listeners needed for the application and
     * attaches the Change Listeners in the constructors.
     */
    private void attachChangeListeners() {
        studentProfileViewPane.addPNumberChangeListener(new TextFieldLimitListener(10));
        studentProfileViewPane.addFirstNameChangeListener(new TextFieldLimitListener(30));
        studentProfileViewPane.addSurnameChangeListener(new TextFieldLimitListener(30));
    }

    /**
     * Populates the list views accordingly
     * Also used to reset when ResetHandler is called.
     */
    private void populateTermModulesViews() {
        courseModules = new ArrayList<>(studentProfileViewPane.getSelectedCourse().getAllModulesOnCourse());
        for (Module module : courseModules) {
            if (module.getRunPlan() == Delivery.YEAR_LONG) {
                moduleSelectionViewPane.addYearLongModule(module);
                model.addToSelectedModules(module);
                term1SelectionPane.increaseCreditsBy(module.getCredits() / 2);
                term2SelectionPane.increaseCreditsBy(module.getCredits() / 2);
            }

            if (module.getRunPlan() == Delivery.TERM_1 && module.isMandatory()) {
                model.addToSelectedModules(module);
                term1SelectionPane.addToSelectedList(module);
                term1SelectionPane.increaseCreditsBy(module.getCredits());
            }

            if (module.getRunPlan() == Delivery.TERM_2 && module.isMandatory()) {
                model.addToSelectedModules(module);
                term2SelectionPane.addToSelectedList(module);
                term2SelectionPane.increaseCreditsBy(module.getCredits());
            }

            term1SelectionPane.addToUnselectedList(module, Delivery.TERM_1);
            term2SelectionPane.addToUnselectedList(module, Delivery.TERM_2);
        }
    }

    /**
     * Retrieve all courses and modules associated to it by reading from a file with a
     * certain format.
     *
     * @return a set of courses and their modules.
     */
    private Set<Course> setupAndRetrieveCourses() {
        // Used Sets to prevent duplicate courses and TreeSet for ordering.
        Set<Course> courses = new TreeSet<>();
        List<String> lines = new ArrayList<>();
        Course course = null;

        try(Scanner scan = new Scanner(new File(Paths.get("courses.txt").toUri()))) {

            while (scan.hasNextLine()) lines.add(scan.nextLine());

            for (String line : lines) {
                if (line.split(",").length == 1) course = new Course(line);
                else {
                    String[] lineValues = line.split(",");
                    Module module = new Module(null, null, 0, false, null);
                    module.setModuleCode(lineValues[0]);
                    module.setModuleName(lineValues[1]);
                    module.setCredits(Integer.parseInt(lineValues[2]));
                    module.setMandatory(module.convertMandatoryToBoolean(lineValues[3].toLowerCase()));
                    module.setRunPlan(Delivery.convertFromString(lineValues[4]));
                    if (course != null) {
                        course.addModuleToCourse(module);
                    }
                    courses.add(course);
                }
            }
        } catch (FileNotFoundException error) {
            System.out.println(error.getMessage());
        } catch (NullPointerException error) {
            System.out.println("Dialog was cancelled");
        }
        return courses;
    }

    /**
     * Add the selected module to the list and increase the credits accordingly
     *
     * @param termSelectionViewPane used to behave according to the view passing a instance of TermSelectionView Pane.
     */
    public void addModulesToListAndUpdateCredits(TermSelectionViewPane termSelectionViewPane) {
        if (termSelectionViewPane.getCredits() < termSelectionViewPane.getCreditLimit()) {
            model.addToSelectedModules(termSelectionViewPane.getUnSelectedModule());
            termSelectionViewPane.addToSelectedList(termSelectionViewPane.getUnSelectedModule());
            termSelectionViewPane.removeModuleFromList(termSelectionViewPane.getUnSelectedModule());
            termSelectionViewPane.increaseCreditsBy(termSelectionViewPane.getUnSelectedModule().getCredits());
        } else {
            alertDialogBuilder(AlertType.ERROR, "Max Modules", null, "Selected the maximum amount of modules");
        }
    }

    // Event Handlers

    /**
     * Event handler class used to handle when the add button is pressed
     * on each of the term views.
     */
    public class AddHandler implements EventHandler<ActionEvent> {

        // Fields
        TermSelectionViewPane termSelectionViewPane;

        // Constructors

        /**
         * Passing a reference to the term selection view pane to handle the add button event.
         *
         * @param termSelectionViewPane reference to the term selection view pane
         */
        public AddHandler(TermSelectionViewPane termSelectionViewPane) {
            this.termSelectionViewPane = termSelectionViewPane;
        }

        // Methods

        /**
         * Adds the module to the selected list view and updates the credits in the process.
         * Otherwise an alert is shown.
         *
         * @param event when the add button is pressed.
         */
        @Override
        public void handle(ActionEvent event) {
            try {
                if (model.getAllSelectedModules().contains(termSelectionViewPane.getUnSelectedModule())) {
                    alertDialogBuilder(AlertType.ERROR, "Error", null, "Module has already been added");
                } else {
                    addModulesToListAndUpdateCredits(termSelectionViewPane);
                }
            } catch(NullPointerException e) {
                alertDialogBuilder(Alert.AlertType.ERROR, "Error", null, "Please select an item to add.");
            }

        }
    }

    /**
     * Event Handler class used to handle when the remove button is pressed
     * on each of the term views.
     */
    public class RemoveHandler implements EventHandler<ActionEvent> {

        // Fields
        TermSelectionViewPane termSelectionViewPane;

        // Constructors

        /**
         * Passing a reference to the term selection view pane to handle the add button event.
         *
         * @param termSelectionViewPane reference to the term selection view pane
         */
        public RemoveHandler(TermSelectionViewPane termSelectionViewPane) {
            this.termSelectionViewPane = termSelectionViewPane;
        }

        // Methods

        /**
         * Removes the module from the selected list view and updates the credits in the process.
         * If the module is mandatory or nothing is selected upon remove button is pressed then an
         * alert is shown accordingly.
         *
         * @param event when the remove button is pressed.
         */
        @Override
        public void handle(ActionEvent event) {
            try {
                if (termSelectionViewPane.getSelectedItem().isMandatory()) {
                    alertDialogBuilder(Alert.AlertType.ERROR, "Error", null, "Cannot remove mandatory items");
                } else {
                    termSelectionViewPane.decreaseCreditsBy(termSelectionViewPane.getCredits());
                    model.getAllSelectedModules().remove(termSelectionViewPane.getSelectedItem());
                    termSelectionViewPane.removeSelectedItem();
                }
            } catch(NullPointerException e) {
                alertDialogBuilder(Alert.AlertType.ERROR, "Error", null, "Please select an item to remove.");
            }
        }
    }

    /**
     * Event Handler class used to handle when the Save Student button is pressed
     * in the menu.
     */
    public class SaveStudentHandler implements EventHandler<ActionEvent> {

        /**
         * Saves the students profile including their selected course and modules.
         * If nothing is selected and the window is cancelled then a message is show in the console.
         *
         * @param event when the save student button is pressed.
         * @throws IOException          in the event an error of saving the students profile.
         * @throws NullPointerException in the event the dialog is cancelled, a message is outputted to the console.
         */
        @Override
        public void handle(ActionEvent event) {
            File selectedFile = saveDialogBuilder("Save Profile", "DAT files (*.dat)", "dat");
            try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(selectedFile))) {
                oos.writeObject(model);
                oos.flush();
                alertDialogBuilder(AlertType.INFORMATION, "Profile Data Successfully Saved", null, "Your profile has been saved successfully");
            } catch (IOException error) {
                error.printStackTrace();
                alertDialogBuilder(AlertType.ERROR, "Error", null, error.getMessage());
            } catch (NullPointerException error) {
                System.out.println("Dialog was cancelled. Nothing needs to be done.");
            }
        }
    }

    /**
     * Event handler class used to handle when loading student data when the button is pressed
     * in the menu.
     */
    public class LoadStudentHandler implements EventHandler<ActionEvent> {

        /**
         * Loads the students profile including their selected course and modules.
         * Populates all the relevant fields of the students information including their selected course and modules.
         *
         * @param event when the load student button is pressed.
         * @throws IOException            when there is an error with loading the students data, message in the console will appear.
         * @throws ClassNotFoundException when its unable to load a class.
         * @throws NullPointerException   when the dialog is cancelled then a message is shown in the console.
         */
        @Override
        public void handle(ActionEvent event) {
            model.clearAllSelectedModules();
            view.getModuleSelectionViewPane().resetAll();

            File selectedFile = openDialogBuilder("Load Profile", "DAT files (*.dat)", "dat");

            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(selectedFile))) {

                StudentProfile loadedStudentProfile = (StudentProfile) ois.readObject();

                model.setStudentName(loadedStudentProfile.getStudentName());
                studentProfileViewPane.setTxtFirstName(loadedStudentProfile.getStudentName().getFirstName());
                studentProfileViewPane.setTxtSurname(loadedStudentProfile.getStudentName().getFamilyName());

                model.setPnumber(loadedStudentProfile.getPnumber());
                studentProfileViewPane.setTxtPNumber(loadedStudentProfile.getPnumber());

                model.setEmail(loadedStudentProfile.getEmail());
                studentProfileViewPane.setTxtEmail(loadedStudentProfile.getEmail());

                model.setSubmissionDate(loadedStudentProfile.getSubmissionDate());
                studentProfileViewPane.setDate(loadedStudentProfile.getSubmissionDate());

                model.setCourseOfStudy(loadedStudentProfile.getCourseOfStudy());
                studentProfileViewPane.setSelectedCourse(loadedStudentProfile.getCourseOfStudy());

                courseModules = new ArrayList<>(studentProfileViewPane.getSelectedCourse().getAllModulesOnCourse());
                List<Module> savedStudentProfile = new ArrayList<>(loadedStudentProfile.getAllSelectedModules());

                for (Module module : savedStudentProfile) {
                    if (module.getRunPlan() == Delivery.YEAR_LONG) {
                        model.addToSelectedModules(module);
                        moduleSelectionViewPane.addYearLongModule(module);
                        term1SelectionPane.increaseCreditsBy(module.getCredits() / 2);
                        term2SelectionPane.increaseCreditsBy(module.getCredits() / 2);
                    }

                    if (module.getRunPlan() == Delivery.TERM_1) {
                        model.addToSelectedModules(module);
                        term1SelectionPane.addToSelectedList(module);
                        term1SelectionPane.increaseCreditsBy(module.getCredits());
                    }

                    if (module.getRunPlan() == Delivery.TERM_2) {
                        model.addToSelectedModules(module);
                        term2SelectionPane.addToSelectedList(module);
                        term2SelectionPane.increaseCreditsBy(module.getCredits());
                    }
                }

                for (Module module : courseModules) {
                    if (!savedStudentProfile.contains(module)) {
                        term1SelectionPane.addToUnselectedList(module, Delivery.TERM_1);
                        term2SelectionPane.addToUnselectedList(module, Delivery.TERM_2);
                    }
                }

                view.changeTab(1);
                view.enableTab(view.getCourseSelectionTab());
                optionsModuleChooserMenuBar.enableMenuItem(optionsModuleChooserMenuBar.getSaveItem());
            } catch (IOException | ClassNotFoundException error) {
                error.printStackTrace();
                alertDialogBuilder(AlertType.ERROR, "Error", null, error.getMessage());
            } catch (NullPointerException error) {
                System.out.println("Dialog was cancelled");
            }
        }
    }

    /**
     * Event handler class used to handle when reset button is pressed in the term views.
     */
    public class ResetModulesHandler implements EventHandler<ActionEvent> {

        /**
         * When the reset button is pressed in the module selection view pane it
         * clears the model, view and re-populates the list views.
         *
         * @param event
         */
        @Override
        public void handle(ActionEvent event) {
            model.getAllSelectedModules().clear();
            view.getModuleSelectionViewPane().resetAll();
            populateTermModulesViews();
        }
    }

    /**
     * Event handler class used to handle when saving students overview of modules
     * when the saveOverview button is pressed
     */
    public class SaveOverviewHandler implements EventHandler<ActionEvent> {

        /**
         * Saves the students overview which includes their information and their selected course and modules.
         * If nothing is selected and the window is cancelled then a message is show in the console.
         *
         * @param event when the save overview button is pressed.
         * @throws IOException          when there is an issue with saving the overview to a text file.
         * @throws NullPointerException when the dialog is cancelled a message will appear in the console.
         */
        @Override
        public void handle(ActionEvent event) {
            File selectedFile = saveDialogBuilder("Save Overview", "TXT files (*.txt)", "txt");

            try(FileWriter fileWriter = new FileWriter(selectedFile)) {
                PrintWriter printWriter = new PrintWriter(fileWriter);

                printWriter.println("Full Name: " + model.getStudentName().getFirstName() + " " + model.getStudentName().getFamilyName());
                printWriter.println("PNumber: " + model.getPnumber());
                printWriter.println("Email: " + model.getEmail());
                printWriter.println("Submission Date: " + model.getSubmissionDate());
                printWriter.println("Course: " + model.getCourseOfStudy());

                printWriter.println("\n" + "Selected Modules:");
                printWriter.println("==========" + "\n");

                for (Module module : model.getAllSelectedModules()) {
                    printWriter.println(module.toString());
                    printWriter.println("credits: " + module.getCredits() + ", " + "Mandatory on your course? " + module.isMandatory() + ", " + "Delivery: " + module.getRunPlan() + "\n");
                }

                alertDialogBuilder(AlertType.INFORMATION, "Module Selections Saved", null, "Your course and modules selections has been saved");
            } catch (IOException e) {
                e.printStackTrace();
                alertDialogBuilder(AlertType.ERROR, "Error", null, e.getMessage());
            } catch (NullPointerException e) {
                System.out.println("Dialog was cancelled. Nothing needs to be done.");
            }
        }
    }

    /**
     * Event handler class used to handle when submit button is pressed
     * in the module selection view pane.
     */
    public class SubmitModulesHandler implements EventHandler<ActionEvent> {

        /**
         * When the submit button is pressed in the module selection view pane
         * the overview textarea gets populated with all the students information.
         *
         * @param event when the submit button is pressed.
         */
        @Override
        public void handle(ActionEvent event) {
            overviewViewPane.clearResults();

            if (term1SelectionPane.getCredits() < term1SelectionPane.getCreditLimit()) {
                alertDialogBuilder(AlertType.ERROR, "Not enough modules", null, "Please select more modules for Term 1");
            } else if (term2SelectionPane.getCredits() < term2SelectionPane.getCreditLimit()) {
                alertDialogBuilder(AlertType.ERROR, "Not enough modules", null, "Please select more modules for Term 2");
            } else {
                overviewViewPane.setOverviewResults("Name: " + model.getStudentName().getFullName());
                overviewViewPane.setOverviewResults("PNumber: " + model.getPnumber());
                overviewViewPane.setOverviewResults("Email: " + model.getEmail());
                overviewViewPane.setOverviewResults("Date: " + model.getSubmissionDate());
                overviewViewPane.setOverviewResults("Course: " + model.getCourseOfStudy());

                overviewViewPane.setOverviewResults("\n" + "Selected modules:");
                overviewViewPane.setOverviewResults("==========");

                List<Module> modules = model.getAllSelectedModules().stream().sorted(Comparator.comparing(Module::getRunPlan)).collect(toList());

                for (Module module : modules) {
                    overviewViewPane.setOverviewResults("Module Code: " + module.getModuleCode() + ", " + "Module name: " + module.getModuleName());
                    overviewViewPane.setOverviewResults("credits: " + module.getCredits() + ", "
                            + "Mandatory on your course? " + module.convertMandatoryToString(module.isMandatory()) + ", "
                            + "Delivery: " + module.getRunPlan().toString() + "\n"
                    );
                }
                view.changeTab(2);
                view.enableTab(view.getOverviewTab());
            }
        }
    }

    /**
     * Event handler class used to handle when creating the students profile button is pressed
     * in the student profile view pane.
     */
    public class StudentProfilesHandler implements EventHandler<ActionEvent> {

        /**
         * Creates the students profile with all the information entered and sets the model for later reference.
         * Provided email they entered is valid.
         * The list views are populated with the relevant modules from the selected course.
         *
         * @param event when the create student profile button is pressed.
         */
        @Override
        public void handle(ActionEvent event) {
            model.getAllSelectedModules().clear();
            view.getModuleSelectionViewPane().resetAll();
            setupAndRetrieveCourses();

            // Email validation - check if the email is valid
            if (studentProfileViewPane.isEmailValid()) {
                // Sets the information to the model.
                model.setCourseOfStudy(studentProfileViewPane.getSelectedCourse());
                model.setEmail(studentProfileViewPane.getTxtEmail());
                model.setPnumber(studentProfileViewPane.getTxtPNumber());
                model.setStudentName(new Name(studentProfileViewPane.getTxtFirstName(), studentProfileViewPane.getTxtSurname()));
                model.setSubmissionDate(studentProfileViewPane.getDate());

                populateTermModulesViews();
                view.changeTab(1);
                studentProfileViewPane.hideErrorMessage();
                view.enableTab(view.getCourseSelectionTab());
                optionsModuleChooserMenuBar.enableMenuItem(optionsModuleChooserMenuBar.getSaveItem());
            } else {
                studentProfileViewPane.showErrorMessage("Enter a valid email address");
            }
        }
    }

    /**
     * Event handler class used to handle when load courses button is pressed
     * in the menu.
     */
    public class LoadCoursesHandler implements EventHandler<ActionEvent> {

        /**
         * Loads the courses by reading a text file and populates the combobox with all the courses.
         * Each of the modules have their modules associated to it.
         * If there is an error with loading the courses an error message is displayed.
         *
         * @param event when the add button is pressed.
         * @throws IOException              in the event there is an issue with loading the file.
         * @throws IllegalArgumentException in the event the file is not formatted correctly.
         */
        @Override
        public void handle(ActionEvent event) {
            studentProfileViewPane.clearCombox();
            try {
                studentProfileViewPane.populateComboBoxWithCourses(setupAndRetrieveCourses());
            } catch (IllegalArgumentException error) {
                alertDialogBuilder(AlertType.ERROR, "Error Loading File", null, error.getMessage() + ". The value inserted is incorrect.");
            }
        }
    }

    /**
     * Mouse Event handler class used to handle when double clicks are made
     * to the list views.
     */
    public class DoubleMouseAddClickSelectionHandler implements EventHandler<MouseEvent> {

        // Fields
        TermSelectionViewPane termSelectionViewPane;

        // Constructors

        /**
         * Passing a reference to the term selection view pane to handle the add button event.
         *
         * @param termSelectionViewPane reference to the term selection view pane
         */
        public DoubleMouseAddClickSelectionHandler(TermSelectionViewPane termSelectionViewPane) {
            this.termSelectionViewPane = termSelectionViewPane;
        }

        // Methods

        /**
         * When an item in the unselected list view is doubled clicked its added to the selected modules list view.
         *
         * @param event when item in unselected clicked 2x then its added to the selected modules list view.
         */
        @Override
        public void handle(MouseEvent event) {
            try {
                if (event.getClickCount() == 2) {
                    if (model.getAllSelectedModules().contains(termSelectionViewPane.getUnSelectedModule())) {
                        alertDialogBuilder(AlertType.ERROR, "Error", null, "Module already added");
                    } else {
                        addModulesToListAndUpdateCredits(termSelectionViewPane);
                    }
                }
            } catch (NullPointerException error) {
                return;
            }

        }
    }

    /**
     * Mouse Event handler class used to handle when double clicks are made
     * to the list views.
     */
    public class DoubleMouseRemoveClickSelectionHandler implements EventHandler<MouseEvent> {

        // Fields
        TermSelectionViewPane termSelectionViewPane;

        // Constructors

        /**
         * Passing a reference to the term selection view pane to handle the add button event.
         *
         * @param termSelectionViewPane reference to the term selection view pane
         */
        public DoubleMouseRemoveClickSelectionHandler(TermSelectionViewPane termSelectionViewPane) {
            this.termSelectionViewPane = termSelectionViewPane;
        }

        // Methods

        /**
         * When an item in the unselected list view is doubled clicked its added to the selected modules list view.
         *
         * @param event when item in unselected clicked 2x then its added to the selected modules list view.
         */
        @Override
        public void handle(MouseEvent event) {
            try {
                if (event.getClickCount() == 2) {
                    if (termSelectionViewPane.getSelectedItem().isMandatory()) {
                        alertDialogBuilder(Alert.AlertType.ERROR, "Error", null, "Cannot remove mandatory items");
                    } else {
                        termSelectionViewPane.decreaseCreditsBy(termSelectionViewPane.getCredits());
                        model.getAllSelectedModules().remove(termSelectionViewPane.getSelectedItem());
                        termSelectionViewPane.removeSelectedItem();
                    }
                }
            } catch (NullPointerException error) {
                return;
            }

        }
    }

    // Change Listeners

    /**
     * Change Listener class used to handle when changes are made on the textfields so that only the max
     * amount of characters are allowed on the textfield.
     * Restricts the textfields by the specified max amount of characters.
     */
    public class TextFieldLimitListener implements ChangeListener<String> {

        // Fields
        private int maxLength;

        // Constructors
        public TextFieldLimitListener(int maxLength) {
            this.maxLength = maxLength;
        }

        // Methods

        /**
         * Listens on the amount of characters from the calling property to the specified max value.
         * The property is casted to StringProperty as the implemented ChangeListener takes a String as the generic type.
         * Reference from:
         * https://docs.oracle.com/javase/8/javafx/api/javafx/beans/value/ChangeListener.html#changed-javafx.beans.value.ObservableValue-T-T-
         * and assistance from Intellij IDE.
         *
         * @param property is the calling requested property that changed.
         * @param oldValue is the old value.
         * @param newValue is the new value.
         */
        @Override
        public void changed(ObservableValue<? extends String> property, String oldValue, String newValue) {
            if (newValue.length() > maxLength) {
                ((StringProperty) property).setValue(oldValue);
            }
        }
    }

    // Builders

    /**
     * Creates an Alert Builder to create alerts dialogs
     *
     * @param type    of the alert dialog passed as a AlertType
     * @param title   of the alert dialog passed as a String.
     * @param header  of the alert dialog passed as a String.
     * @param content of the alert dialog passed as a String.
     */
    private void alertDialogBuilder(AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Creates a Save Dialog to create a dialog to save files.
     *
     * @param title       of the open dialog passed as a String.
     * @param description of the open dialog passed as a String.
     * @param extension   of the save type used to restrict to passed as a string. e.g. dat or txt is all its required to pass.
     * @return the save dialog with the file to save.
     * @throws NullPointerException if the dialog is cancelled then a NullPointerException is thrown.
     */
    public File saveDialogBuilder(String title, String description, String extension) throws NullPointerException {
        FileChooser fc = new FileChooser();
        fc.setTitle(title);
        fc.setInitialDirectory(new File(Paths.get("").toUri()));
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(description, "*." + extension.toLowerCase());
        fc.getExtensionFilters().add(extFilter);
        return fc.showSaveDialog(null);
    }

    /**
     * Creates a Open Dialog to create a dialog to open files.
     *
     * @param title       of the open dialog passed as a String.
     * @param description of the open dialog passed as a String.
     * @param extension   of the save type used to restrict to passed as a string. e.g. dat or txt is all its required to pass.
     * @return the open dialog with the file to load.
     * @throws NullPointerException if the dialog is cancelled then a NullPointerException is thrown.
     */
    public File openDialogBuilder(String title, String description, String extension) throws NullPointerException {
        FileChooser fc = new FileChooser();
        fc.setTitle(title);
        fc.setInitialDirectory(new File(Paths.get("").toUri()));
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(description, "*." + extension);
        fc.getExtensionFilters().add(extFilter);
        return fc.showOpenDialog(null);
    }
}
