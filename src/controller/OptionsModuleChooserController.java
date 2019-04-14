package controller;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import model.*;
import view.*;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

import static java.util.stream.Collectors.toList;

public class OptionsModuleChooserController {

    private StudentProfile model;
    private OptionsModuleChooserRootPane view;
    private OverviewViewPane overviewViewPane;
    private TermSelectionViewPane term1SelectionPane;
    private TermSelectionViewPane term2SelectionPane;
    private StudentProfileViewPane studentProfileViewPane;
    private ModuleSelectionViewPane moduleSelectionViewPane;
    private OptionsModuleChooserMenuBar optionsModuleChooserMenuBar;

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

        //populate combobox in create profile pane, e.g. if profilePane represented your create profile pane you could invoke the line below
        studentProfileViewPane.populateComboBoxWithCourses(setupAndRetrieveCourses());

        //attach event handlers to view using private helper method
        this.attachEventHandlers();

        // Attach Bindings
        this.attachBindings();

        // Attach Change Listeners
        this.attachChangeListeners();

    }

    private void attachBindings() {
        studentProfileViewPane.addBtnDisableBind(studentProfileViewPane.isEitherFieldEmpty());
    }

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

        // Select items with double clicks
        term1SelectionPane.addDoubleMouseClickSelectionHandler(new DoubleMouseClickSelectionHandler(term1SelectionPane));
        term2SelectionPane.addDoubleMouseClickSelectionHandler(new DoubleMouseClickSelectionHandler(term2SelectionPane));

        overviewViewPane.addSaveOverviewHandler(new SaveOverviewHandler());

        optionsModuleChooserMenuBar.addLoadCourseDataHandler(new LoadCoursesHandler());
    }

    private void attachChangeListeners() {
        studentProfileViewPane.addPNumberChangeListener(new TextFieldLimitListener(8));
        studentProfileViewPane.addFirstNameChangeListener(new TextFieldLimitListener(30));
        studentProfileViewPane.addSurnameChangeListener(new TextFieldLimitListener(30));
    }

    // Populates the list views accordingly
    // Also used to reset when ResentHandler is used
    public void populateTermModulesViews() {
        List<Module> collect = new ArrayList<>(studentProfileViewPane.getSelectedCourse().getAllModulesOnCourse());

        for (Module m : collect) {
            if (m.getRunPlan() == Delivery.YEAR_LONG) {
                moduleSelectionViewPane.addYearLongModule(m);
                model.addToSelectedModules(m);
                term1SelectionPane.increaseCreditsBy(m.getCredits() / 2);
                term2SelectionPane.increaseCreditsBy(m.getCredits() / 2);
            }

            if (m.getRunPlan() == Delivery.TERM_1 && m.isMandatory()) {
                model.addToSelectedModules(m);
                term1SelectionPane.addToSelectedList(m);
                term1SelectionPane.increaseCreditsBy(m.getCredits());
            }

            if (m.getRunPlan() == Delivery.TERM_2 && m.isMandatory()) {
                model.addToSelectedModules(m);
                term2SelectionPane.addToSelectedList(m);
                term2SelectionPane.increaseCreditsBy(m.getCredits());
            }

            term1SelectionPane.addToUnselectedList(m, Delivery.TERM_1);
            term2SelectionPane.addToUnselectedList(m, Delivery.TERM_2);

        }
    }

    private Course[] setupAndRetrieveCourses() {
        Module imat3423 = new Module("IMAT3423", "Systems Building: Methods", 15, true, Delivery.TERM_1);
        Module imat3451 = new Module("IMAT3451", "Computing Project", 30, true, Delivery.YEAR_LONG);
        Module ctec3902_SoftEng = new Module("CTEC3902", "Rigorous Systems", 15, true, Delivery.TERM_2);
        Module ctec3902_CompSci = new Module("CTEC3902", "Rigorous Systems", 15, false, Delivery.TERM_2);
        Module ctec3110 = new Module("CTEC3110", "Secure Web Application Development", 15, false, Delivery.TERM_1);
        Module ctec3426 = new Module("CTEC3426", "Telematics", 15, false, Delivery.TERM_1);
        Module ctec3605 = new Module("CTEC3605", "Multi-service Networks 1", 15, false, Delivery.TERM_1);
        Module ctec3606 = new Module("CTEC3606", "Multi-service Networks 2", 15, false, Delivery.TERM_2);
        Module ctec3410 = new Module("CTEC3410", "Web Application Penetration Testing", 15, false, Delivery.TERM_2);
        Module ctec3904 = new Module("CTEC3904", "Functional Software Development", 15, false, Delivery.TERM_2);
        Module ctec3905 = new Module("CTEC3905", "Front-End Web Development", 15, false, Delivery.TERM_2);
        Module ctec3906 = new Module("CTEC3906", "Interaction Design", 15, false, Delivery.TERM_1);
        Module imat3410 = new Module("IMAT3104", "Database Management and Programming", 15, false, Delivery.TERM_2);
        Module imat3406 = new Module("IMAT3406", "Fuzzy Logic and Knowledge Based Systems", 15, false, Delivery.TERM_1);
        Module imat3611 = new Module("IMAT3611", "Popular Technology Ethics", 15, false, Delivery.TERM_1);
        Module imat3613 = new Module("IMAT3613", "Data Mining", 15, false, Delivery.TERM_1);
        Module imat3614 = new Module("IMAT3614", "Big Data and Business Models", 15, false, Delivery.TERM_2);
        Module imat3428_CompSci = new Module("IMAT3428", "Information Technology Services Practice", 15, false, Delivery.TERM_2);

        Course compSci = new Course("Computer Science");
        compSci.addModuleToCourse(imat3423);
        compSci.addModuleToCourse(imat3451);
        compSci.addModuleToCourse(ctec3902_CompSci);
        compSci.addModuleToCourse(ctec3110);
        compSci.addModuleToCourse(ctec3426);
        compSci.addModuleToCourse(ctec3605);
        compSci.addModuleToCourse(ctec3606);
        compSci.addModuleToCourse(ctec3410);
        compSci.addModuleToCourse(ctec3904);
        compSci.addModuleToCourse(ctec3905);
        compSci.addModuleToCourse(ctec3906);
        compSci.addModuleToCourse(imat3410);
        compSci.addModuleToCourse(imat3406);
        compSci.addModuleToCourse(imat3611);
        compSci.addModuleToCourse(imat3613);
        compSci.addModuleToCourse(imat3614);
        compSci.addModuleToCourse(imat3428_CompSci);

        Course softEng = new Course("Software Engineering");
        softEng.addModuleToCourse(imat3423);
        softEng.addModuleToCourse(imat3451);
        softEng.addModuleToCourse(ctec3902_SoftEng);
        softEng.addModuleToCourse(ctec3110);
        softEng.addModuleToCourse(ctec3426);
        softEng.addModuleToCourse(ctec3605);
        softEng.addModuleToCourse(ctec3606);
        softEng.addModuleToCourse(ctec3410);
        softEng.addModuleToCourse(ctec3904);
        softEng.addModuleToCourse(ctec3905);
        softEng.addModuleToCourse(ctec3906);
        softEng.addModuleToCourse(imat3410);
        softEng.addModuleToCourse(imat3406);
        softEng.addModuleToCourse(imat3611);
        softEng.addModuleToCourse(imat3613);
        softEng.addModuleToCourse(imat3614);

        Course[] courses = new Course[2];
        courses[0] = compSci;
        courses[1] = softEng;

        return courses;
    }

    /**
     * Add the selected module to the list and increase the credits accordingly
     *
     * @param termSelectionViewPane - used to behave according to the view
     */
    public void addModuleToListAndUpdateCredits(TermSelectionViewPane termSelectionViewPane) {
        if (termSelectionViewPane.getCredits() < termSelectionViewPane.getCreditLimit()) {
            model.addToSelectedModules(termSelectionViewPane.getUnSelectedModule());
            termSelectionViewPane.addToSelectedList(termSelectionViewPane.getUnSelectedModule());
            termSelectionViewPane.removeModuleFromList(termSelectionViewPane.getUnSelectedModule());
            termSelectionViewPane.increaseCreditsBy(termSelectionViewPane.getUnSelectedModule().getCredits());
        } else {
            alertDialogBuilder(AlertType.ERROR, "Max Modules", null, "Selected the maximum amount of modules");
        }
    }

    // EventHandlers used
    public class AddHandler implements EventHandler {
        TermSelectionViewPane termSelectionViewPane;

        public AddHandler(TermSelectionViewPane termSelectionViewPane) {
            this.termSelectionViewPane = termSelectionViewPane;
        }

        @Override
        public void handle(Event event) {
            if (!model.getAllSelectedModules().contains(termSelectionViewPane.getUnSelectedModule())) {
                addModuleToListAndUpdateCredits(termSelectionViewPane);
            } else {
                alertDialogBuilder(AlertType.ERROR, "Duplicate Module", null, "Module has already been added");
            }
        }
    }

    public class RemoveHandler implements EventHandler {

        TermSelectionViewPane termSelectionViewPane;

        public RemoveHandler(TermSelectionViewPane termSelectionViewPane) {
            this.termSelectionViewPane = termSelectionViewPane;
        }

        @Override
        public void handle(Event event) {
            if (termSelectionViewPane.getSelectedItem() == null) {
                alertDialogBuilder(Alert.AlertType.ERROR, "Error", null, "Please select an item to remove.");
            } else if (termSelectionViewPane.getSelectedItem().isMandatory()) {
                alertDialogBuilder(Alert.AlertType.ERROR, "Error", null, "Cannot remove mandatory items");
            } else {
                //termSelectionViewPane.decreaseCreditsBy(termSelectionViewPane.getCredits());
                //int currentVal = termSelectionViewPane.getCredits();
                //int newVal = currentVal - termSelectionViewPane.getSelectedItem().getCredits();
                //termSelectionViewPane.setCredits(newVal);
                termSelectionViewPane.decreaseCreditsBy(termSelectionViewPane.getCredits());
                model.getAllSelectedModules().remove(termSelectionViewPane.getSelectedItem());
                termSelectionViewPane.removeSelectedItem();
            }
        }
    }

    public class SaveStudentHandler implements EventHandler {
        @Override
        public void handle(Event event) {

            File selectedFile = saveDialogBuilder("Save Profile", "DAT files (*.dat)", "dat");

            ObjectOutputStream oos;
            try {
                oos = new ObjectOutputStream(new FileOutputStream(selectedFile));
                oos.writeObject(model);
                oos.flush();
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("Dialog was cancelled");
            }
        }
    }

    public class LoadStudentHandler implements EventHandler {
        @Override
        public void handle(Event event) {
            model.clearAllSelectedModules();
            view.getModuleSelectionViewPane().resetAll();

            File selectedFile = openDialogBuilder("Load Profile", "DAT files (*.dat)", "dat");

            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(selectedFile));
                StudentProfile s = (StudentProfile) ois.readObject();

                model.setStudentName(s.getStudentName());
                studentProfileViewPane.setTxtFirstName(s.getStudentName().getFirstName());
                studentProfileViewPane.setTxtSurname(s.getStudentName().getFamilyName());

                model.setPnumber(s.getPnumber());
                studentProfileViewPane.setTxtPNumber(s.getPnumber());

                model.setEmail(s.getEmail());
                studentProfileViewPane.setTxtEmail(s.getEmail());

                model.setSubmissionDate(s.getSubmissionDate());
                studentProfileViewPane.setDate(s.getSubmissionDate());

                model.setCourseOfStudy(s.getCourseOfStudy());
                studentProfileViewPane.setSelectedCourse(s.getCourseOfStudy());

                List<Module> courseModules = new ArrayList<>(studentProfileViewPane.getSelectedCourse().getAllModulesOnCourse());
                List<Module> savedStudentProfile = new ArrayList<>(s.getAllSelectedModules());

                for (Module m : savedStudentProfile) {
                    if (m.getRunPlan() == Delivery.YEAR_LONG) {
                        moduleSelectionViewPane.addYearLongModule(m);
                        model.addToSelectedModules(m);
                        term1SelectionPane.increaseCreditsBy(m.getCredits() / 2);
                        term2SelectionPane.increaseCreditsBy(m.getCredits() / 2);
                    }

                    if (m.getRunPlan() == Delivery.TERM_1) {
                        term1SelectionPane.addToSelectedList(m);
                        model.addToSelectedModules(m);
                        term1SelectionPane.increaseCreditsBy(m.getCredits());
                    }

                    if (m.getRunPlan() == Delivery.TERM_2) {
                        term2SelectionPane.addToSelectedList(m);
                        model.addToSelectedModules(m);
                        term2SelectionPane.increaseCreditsBy(m.getCredits());
                    }
                }

                for (Module m : courseModules) {
                    if (!savedStudentProfile.contains(m)) {
                        term1SelectionPane.addToUnselectedList(m, Delivery.TERM_1);
                        term2SelectionPane.addToUnselectedList(m, Delivery.TERM_2);
                    }
                }

                ois.close();
                view.changeTab(1);
                view.enableTab(view.getCreateCourseSelectionTab());
                optionsModuleChooserMenuBar.enableMenuItem(optionsModuleChooserMenuBar.getSaveItem());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("Dialog was cancelled");
            }
        }
    }

    public class ResetModulesHandler implements EventHandler {
        @Override
        public void handle(Event event) {
            model.getAllSelectedModules().clear();
            view.getModuleSelectionViewPane().resetAll();
            populateTermModulesViews();
        }
    }

    public class SaveOverviewHandler implements EventHandler {

        @Override
        public void handle(Event event) {
            File selectedFile = saveDialogBuilder("Save Overview", "TXT files (*.txt)", "txt");

            FileWriter fileWriter;
            try {
                fileWriter = new FileWriter(selectedFile);
                PrintWriter printWriter = new PrintWriter(fileWriter);

                printWriter.println("Full Name: " + model.getStudentName().getFirstName() + " " + model.getStudentName().getFamilyName());
                printWriter.println("PNumber: " + model.getPnumber());
                printWriter.println("Email: " + model.getEmail());
                printWriter.println("Submission Date: " + model.getSubmissionDate());
                printWriter.println("Course: " + model.getCourseOfStudy());

                printWriter.println("\n" + "Selected Modules:");
                printWriter.println("==========" + "\n");

                for (Module m : model.getAllSelectedModules()) {
                    printWriter.println(m.toString());
                    printWriter.println("credits: " + m.getCredits() + ", " + "Mandatory on your course? " + m.isMandatory() + ", " + "Delivery: " + m.getRunPlan() + "\n");
                }

                printWriter.close();
                alertDialogBuilder(AlertType.INFORMATION, "Data Saved", null, "Your course selected has been saved");
            } catch (IOException e) {
                e.printStackTrace();
                alertDialogBuilder(AlertType.ERROR, "Error", null, e.getMessage());
            } catch (NullPointerException e) {
                System.out.println("Dialog was cancelled");
            }
        }
    }

    public class SubmitModulesHandler implements EventHandler {
        @Override
        public void handle(Event event) {

            //model.clearAllSelectedModules();
            overviewViewPane.clearResults();

            if (term1SelectionPane.getCredits() < term1SelectionPane.getCreditLimit()) {
                alertDialogBuilder(AlertType.ERROR, "Not enough modules", null, "Please select more modules for Term 1");
            } else if (term2SelectionPane.getCredits() < term2SelectionPane.getCreditLimit()) {
                alertDialogBuilder(AlertType.ERROR, "Not enough modules", null, "Please select more modules for Term 2");
            } else {
                overviewViewPane.setResults("Name: " + model.getStudentName().getFullName() + "\n");
                overviewViewPane.setResults("PNumber: " + model.getPnumber() + "\n");
                overviewViewPane.setResults("Email: " + model.getEmail() + "\n");
                overviewViewPane.setResults("Date: " + model.getSubmissionDate() + "\n");
                overviewViewPane.setResults("Course: " + model.getCourseOfStudy() + "\n");

                overviewViewPane.setResults("\n" + "Selected modules:" + "\n");
                overviewViewPane.setResults("==========" + "\n");

                List<Module> test = model.getAllSelectedModules().stream().sorted(Comparator.comparing(Module::getRunPlan)).collect(toList());

                for (Module m : test) {
                    overviewViewPane.setResults(m.toString() + "\n");
                    overviewViewPane.setResults("credits: " + m.getCredits() + ", " + "Mandatory on your course? " + m.isMandatory() + ", " + "Delivery: " + m.getRunPlan() + "\n\n");
                }

                view.changeTab(2);
                view.enableTab(view.getCreateOverviewTab());
            }
        }
    }

    public class StudentProfilesHandler implements EventHandler {
        @Override
        public void handle(Event event) {
            model.getAllSelectedModules().clear();
            view.getModuleSelectionViewPane().resetAll();

            // Email validation - check if the email is valid
            if (studentProfileViewPane.isEmailValid()) {

                model.setCourseOfStudy(studentProfileViewPane.getSelectedCourse());
                model.setEmail(studentProfileViewPane.getTxtEmail());
                model.setPnumber(studentProfileViewPane.getTxtPNumber());
                model.setStudentName(new Name(studentProfileViewPane.getTxtFirstName(), studentProfileViewPane.getTxtSurname()));
                model.setSubmissionDate(studentProfileViewPane.getDate());

                populateTermModulesViews();

                view.changeTab(1);
                view.enableTab(view.getCreateCourseSelectionTab());
                optionsModuleChooserMenuBar.enableMenuItem(optionsModuleChooserMenuBar.getSaveItem());
            } else {
                studentProfileViewPane.setInvalidEmailMessage("Enter a valid email address");
            }
        }
    }

    public class LoadCoursesHandler implements EventHandler {

        @Override
        public void handle(Event event) {
            //Scanner out;
            //
            //try {
            //    out = new Scanner(new File("courses.txt"));
            //    out.useDelimiter("[,\r\n]+");
            //
            //    while (out.hasNext()) {
            //
            //        Course course = new Course(out.next());
            //        String courseCode = out.next();
            //        String courseName = out.next();
            //        int credits = Integer.parseInt(out.next());
            //        boolean mandatory = Boolean.parseBoolean(out.next());
            //        Delivery delivery = Delivery.valueOf(out.next().toUpperCase());
            //
            //        Module module = new Module(courseCode, courseName, credits, mandatory, delivery);
            //        courses.put(course, module);
            //
            //    }
            //    out.close();
            //
            //
            //
            //} catch (FileNotFoundException e) {
            //    e.printStackTrace();
            //}


        }
    }

    public class DoubleMouseClickSelectionHandler implements EventHandler<MouseEvent> {

        TermSelectionViewPane termSelectionViewPane;

        public DoubleMouseClickSelectionHandler(TermSelectionViewPane termSelectionViewPane) {
            this.termSelectionViewPane = termSelectionViewPane;
        }

        @Override
        public void handle(MouseEvent event) {
            if (event.getClickCount() == 2) {
                if (model.getAllSelectedModules().contains(termSelectionViewPane.getUnSelectedModule())) {
                    alertDialogBuilder(AlertType.ERROR, "Duplicate Module", null, "Module already added");
                } else {
                    addModuleToListAndUpdateCredits(termSelectionViewPane);
                }
            }
        }
    }

    public class TextFieldLimitListener implements ChangeListener<String> {

        private int maxLength;

        public TextFieldLimitListener(int maxLength) {
            this.maxLength = maxLength;
        }

        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (newValue.length() > maxLength) ((StringProperty) observable).setValue(oldValue);
        }
    }

    //helper method to build dialogs
    private void alertDialogBuilder(AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public File saveDialogBuilder(String title, String description, String extension) throws NullPointerException {
        FileChooser fc = new FileChooser();
        fc.setTitle(title);
        fc.setInitialDirectory(new File(Paths.get("").toUri()));
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(description, "*." + extension.toLowerCase());
        fc.getExtensionFilters().add(extFilter);
        File selectedFile = fc.showSaveDialog(null);

        return selectedFile;

    }

    public File openDialogBuilder(String title, String description, String extension) throws NullPointerException {
        FileChooser fc = new FileChooser();
        fc.setTitle(title);
        fc.setInitialDirectory(new File(Paths.get("").toUri()));
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(description, "*." + extension);
        fc.getExtensionFilters().add(extFilter);
        File selectedFile = fc.showOpenDialog(null);

        return selectedFile;
    }
}
