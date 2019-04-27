package main;

import javafx.scene.Scene;
import javafx.stage.Stage;
import model.StudentProfile;
import javafx.application.Application;
import view.OptionsModuleChooserRootPane;
import controller.OptionsModuleChooserController;

/**
 * Main class used to run the application.
 * A StudentProfile created as a the model
 * A OptionsModuleChooserRootPane as the view
 * with the OptionsModuleChooserController passing the view and model to it.
 *
 * @author P1718603X
 */
public class ApplicationLoader extends Application {

    private StudentProfile model;
    private OptionsModuleChooserRootPane view;

    @Override
    public void init() {
        model = new StudentProfile();
        view = new OptionsModuleChooserRootPane();
        new OptionsModuleChooserController(view, model);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setMinWidth(800);
        stage.setMinHeight(850);
        stage.setScene(new Scene(view));
        stage.setTitle("Final Year Module Chooser Tool");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}