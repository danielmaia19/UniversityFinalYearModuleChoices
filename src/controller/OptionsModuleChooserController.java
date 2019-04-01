package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.*;
import model.*;
import view.*;

import java.util.List;
import java.util.stream.Collectors;

public class OptionsModuleChooserController {

    //fields to be used throughout the class
    private StudentProfile model;
    private ProfileMenuBar profileMenuBar;
    private OptionsModuleChooserRootPane view;
    private StudentProfileViewPane studentProfileViewPane;
    private ModuleSelectionPane moduleSelectionPane;
    private TermSelectionViewPane term1SelectionPane;
    private TermSelectionViewPane term2SelectionPane;
    private OverviewSelectionPane overviewSelectionPane;


    public OptionsModuleChooserController(OptionsModuleChooserRootPane view, StudentProfile model) {
        //initialise model and view fields
        this.model = model;
        this.view = view;

        profileMenuBar = view.getMenuBar();
        studentProfileViewPane = view.getStudentProfileViewPane();
        moduleSelectionPane = view.getModuleSelectionPane();
        overviewSelectionPane = view.getOverviewSelectionPane();
        term1SelectionPane = view.getModuleSelectionPane().getTerm1SectionViewPane();

        term2SelectionPane = view.getModuleSelectionPane().getTerm2SelectionViewPane();

        //populate combobox in create profile pane, e.g. if profilePane represented your create profile pane you could invoke the line below
        studentProfileViewPane.populateComboBoxWithCourses(setupAndRetrieveCourses());

        //attach event handlers to view using private helper method
        this.attachEventHandlers();

        // Attach Bindings
        this.attachBindings();

    }

    private void attachBindings() {
        studentProfileViewPane.addBtnDisableBind(studentProfileViewPane.isEitherFieldEmpty());
    }

    private void attachEventHandlers() {
        profileMenuBar.addAboutHandler(a -> alertDialogBuilder(AlertType.INFORMATION, "Information Dialog", null, "Options Module Chooser MVC app v1.0"));
        studentProfileViewPane.addCreateStudentProfileHandler(new StudentProfilesHandler());
        moduleSelectionPane.submitModulesHandler(new SubmitModulesHandler());
        profileMenuBar.addLoadStudentDataHandler(new LoadStudentHandler());
        moduleSelectionPane.resetModulesHandler(new ResetModulesHandler());
        profileMenuBar.addSaveStudentDataHandler(new SaveStudentHandler());
        profileMenuBar.addExitHandler(e -> System.exit(0));
        term1SelectionPane.getTermSelectionButtonPane().addAddHandler(new AddTerm1Handler());
        term1SelectionPane.getTermSelectionButtonPane().addRemoveHandler(new RemoveTerm1SelectedModuleHandler());
        term2SelectionPane.getTermSelectionButtonPane().addAddHandler(new AddTerm2Handler());
        term2SelectionPane.getTermSelectionButtonPane().addRemoveHandler(new RemoveTerm2SelectedModuleHandler());
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

    // EventHandlers used
    public class StudentProfilesHandler implements EventHandler {

        @Override
        public void handle(Event event) {
            if (studentProfileViewPane.getTxtEmail().getText().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {

                term1SelectionPane.clearAllListViews();
                term2SelectionPane.clearAllListViews();

                List<Module> collect = studentProfileViewPane.getSelectedCourse().getAllModulesOnCourse().stream().collect(Collectors.toList());
                for (Module m : collect) {
                    if (m.getRunPlan() == Delivery.TERM_1 && !m.isMandatory()) {
                        term1SelectionPane.addToUnselectedList(m);
                    }
                    if (m.getRunPlan() == Delivery.TERM_2 && !m.isMandatory()) {
                        term2SelectionPane.addToUnselectedList(m);
                    }
                    if (m.getRunPlan() == Delivery.YEAR_LONG) {
                        moduleSelectionPane.addYearLong(m);
                    }
                    if (m.getRunPlan() == Delivery.TERM_1 && m.isMandatory()) {
                        term1SelectionPane.addToSelectedList(m);
                    }
                    if (m.getRunPlan() == Delivery.TERM_2 && m.isMandatory()) {
                        term2SelectionPane.addToSelectedList(m);
                    }
                }

                model.setCourseOfStudy(studentProfileViewPane.getSelectedCourse());
                model.setEmail(studentProfileViewPane.getTxtEmail().getText());
                model.setPnumber(studentProfileViewPane.getTxtPNumber().getText());
                model.setStudentName(new Name(studentProfileViewPane.getTxtFirstName().getText(), studentProfileViewPane.getTxtSurname().getText()));

                //TODO:: Can we change this method to return a string rather then a LocalDate, so we can change the format?
                model.setSubmissionDate(studentProfileViewPane.getDate().getValue());

                view.changeTab(1);
                view.enableTab(view.getCreateCourseSelectionTab());
            } else {
                studentProfileViewPane.setEmailInvalidMessage("Please enter a valid email address");
            }
        }
    }

    public class LoadStudentHandler implements EventHandler {
        @Override
        public void handle(Event event) {
            System.out.println("Loading button clicked");
        }
    }

    public class SaveStudentHandler implements EventHandler {
        @Override
        public void handle(Event event) {
            System.out.println("Save Student Eventhandler clicked");
        }
    }

    public class SubmitModulesHandler implements EventHandler {
        @Override
        public void handle(Event event) {

            model.clearAllSelectedModules();
            
            for (Module m : term1SelectionPane.getAllSelectedModules()) {
                model.addToSelectedModules(m);
            }
            
            for (Module m : term2SelectionPane.getAllSelectedModules()) {
                model.addToSelectedModules(m);
            }

            System.out.println(model.getAllSelectedModules());
            
            view.changeTab(2);
            view.enableTab(view.getCreateOverviewTab());
        }
    }

    public class ResetModulesHandler implements EventHandler {
        @Override
        public void handle(Event event) {
            model.clearAllSelectedModules();
        }
    }

    public class AddTerm1Handler implements EventHandler {
        @Override
        public void handle(Event event) {
            if (term1SelectionPane.getAllSelectedModules().contains(term1SelectionPane.getUnSelectedModule())) {
                alertDialogBuilder(AlertType.ERROR, "Duplicate Module", "Already Exists", "That module has already been added, please select another one");
            } else {
                term1SelectionPane.addToSelectedList(term1SelectionPane.getUnSelectedModule());
                int currentVal = Integer.parseInt(term1SelectionPane.getTermSelectionButtonPane().getCreditsTxtField());
                int newVal = term1SelectionPane.getUnSelectedModule().getCredits() + currentVal;
                term1SelectionPane.getTermSelectionButtonPane().setCreditsTxtField(String.valueOf(newVal));
            }
        }
    }

    public class AddTerm2Handler implements EventHandler {
        @Override
        public void handle(Event event) {
            if (term2SelectionPane.getAllSelectedModules().contains(term2SelectionPane.getUnSelectedModule())) {
                alertDialogBuilder(AlertType.ERROR, "Duplicate Module", "Already Exists", "That module has already been added, please select another one");
            } else {
                if(Integer.parseInt(term2SelectionPane.getTermSelectionButtonPane().getCreditsTxtField()) < 60) {
                    term2SelectionPane.addToSelectedList(term2SelectionPane.getUnSelectedModule());
                    int currentVal = Integer.parseInt(term2SelectionPane.getTermSelectionButtonPane().getCreditsTxtField());
                    int newVal = term2SelectionPane.getUnSelectedModule().getCredits() + currentVal;
                    term2SelectionPane.getTermSelectionButtonPane().setCreditsTxtField(String.valueOf(newVal));
                } else {
                    alertDialogBuilder(AlertType.ERROR, "Max Modules", "Max Modules", "You have reached your limit");
                }

            }
        }
    }

    public class RemoveTerm1SelectedModuleHandler implements EventHandler {

        @Override
        public void handle(Event event) {
            if (term1SelectionPane.getSelectedModule().isMandatory()) {
                alertDialogBuilder(AlertType.ERROR, "Mandatory Module", "Mandatory Module", "You cannot remove a mandatory module");
            } else {
                term1SelectionPane.removeSelectedItem();
            }
        }
    }

    public class RemoveTerm2SelectedModuleHandler implements EventHandler {

        @Override
        public void handle(Event event) {
            if (term2SelectionPane.getSelectedModule().isMandatory()) {
                alertDialogBuilder(AlertType.ERROR, "Mandatory Module", "Mandatory Module", "You cannot remove a mandatory module");
            } else {
                term2SelectionPane.removeSelectedItem();
            }
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

}
