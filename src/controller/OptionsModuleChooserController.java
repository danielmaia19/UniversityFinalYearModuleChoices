package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.*;
import model.Course;
import model.Delivery;
import model.Module;
import model.StudentProfile;
import view.*;

import java.util.List;
import java.util.stream.Collectors;

public class OptionsModuleChooserController {

	//fields to be used throughout the class
	private StudentProfile model;
	private ProfileMenuBar profileMenuBar;
	private SetupProfilePane setupProfilePane;
	private OptionsModuleChooserRootPane view;
	private ModuleSelectionPane moduleSelectionPane;
	private TermSelectionViewPane term1SelectionPane;
	private TermSelectionViewPane term2SelectionPane;
	private OverviewSelectionPane overviewSelectionPane;
	private TermSelectionButtonPane termSelectionButtonPane;


	public OptionsModuleChooserController(OptionsModuleChooserRootPane view, StudentProfile model) {
		//initialise model and view fields
		this.model = model;
		this.view = view;

		profileMenuBar = view.getMenuBar();
		setupProfilePane = view.getSetupProfilePane();
		moduleSelectionPane = view.getModuleSelectionPane();
		overviewSelectionPane = view.getOverviewSelectionPane();
		term1SelectionPane = view.getModuleSelectionPane().getTerm1SectionViewPane();

		term2SelectionPane = view.getModuleSelectionPane().getTerm2SelectionViewPane();

		//populate combobox in create profile pane, e.g. if profilePane represented your create profile pane you could invoke the line below
		setupProfilePane.populateComboBoxWithCourses(setupAndRetrieveCourses());

		//attach event handlers to view using private helper method
		this.attachEventHandlers();

		// Attach Bindings
		this.attachBindings();

	}

	private void attachBindings() {
		setupProfilePane.addBtnDisableBind(setupProfilePane.isEitherFieldEmpty());
	}

	private void attachEventHandlers() {
		profileMenuBar.addAboutHandler(a -> alertDialogBuilder(AlertType.INFORMATION, "Information Dialog", null, "Options Module Chooser MVC app v1.0"));
		setupProfilePane.addCreateStudentProfileHandler(new StudentProfilesHandler());
		moduleSelectionPane.submitModulesHandler(new SubmitModulesHandler());
		profileMenuBar.addLoadStudentDataHandler(new LoadStudentHandler());
		profileMenuBar.addExitHandler(e -> System.exit(0));
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
			//System.out.println(setupProfilePane.getSelectedCourse().getAllModulesOnCourse());

			List<Module> collect = setupProfilePane.getSelectedCourse().getAllModulesOnCourse().stream().collect(Collectors.toList());
			for (Module m: collect) {
				if(m.getRunPlan() == Delivery.TERM_1 && !m.isMandatory()){
					term1SelectionPane.addToUnselectedList(m);

				}

				if(m.getRunPlan() == Delivery.TERM_2 && !m.isMandatory()){
					term2SelectionPane.addToUnselectedList(m);
				}

				if(m.getRunPlan() == Delivery.YEAR_LONG){
					moduleSelectionPane.addYearLong(m);
				}

				if(m.getRunPlan() == Delivery.TERM_1 && m.isMandatory()) {
					term1SelectionPane.addToSelectedList(m);
				}

				if(m.getRunPlan() == Delivery.TERM_2 && m.isMandatory()) {
					term2SelectionPane.addToSelectedList(m);
				}

			}

			if(setupProfilePane.getTxtEmail().getText().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
				view.changeTab(1);
				view.enableTab(view.getCreateCourseSelectionTab());
				view.disableTab(view.getCreateProfileTab());
			} else {
				setupProfilePane.setErrorMessage("Please enter a valid email address");
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

		}
	}
	public class SubmitModulesHandler implements EventHandler {

		@Override
		public void handle(Event event) {
			view.changeTab(2);
			view.disableTab(view.getCreateCourseSelectionTab());
			view.enableTab(view.getCreateOverviewTab());
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
