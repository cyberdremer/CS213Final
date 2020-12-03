package sample;

import com.sun.javafx.iio.ios.IosDescriptor;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application  {

    Stage window;
    Scene base;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception, IOException {
        window = primaryStage;
        window.setTitle("University Enrollment");
        MenuBar menuBar = new MenuBar();


        //StudentGUI Menu
        Menu menuStudent = new Menu("_Student");
        MenuItem studentView = new MenuItem("View Student");

        MenuItem addStudent = new MenuItem("Add Student");

        MenuItem studentEdit = new MenuItem("Edit Student");

        menuStudent.getItems().addAll(addStudent, studentView, studentEdit);


        //Courses Menu
        Menu menuCourse = new Menu("_Courses");
        MenuItem AddCourse = new MenuItem("Add Course");

        MenuItem EditCourse = new MenuItem("Edit Course");

        MenuItem ViewCourse = new MenuItem("View Course");

        menuCourse.getItems().addAll(AddCourse, EditCourse, ViewCourse);



        //CourseGUI Enrollment Menu
        Menu menuCourseEnrollment = new Menu("_Enrollment");
        MenuItem addEnrollment = new MenuItem("Add Course Enrollment");

        MenuItem editEnrollment = new MenuItem("Edit Course Enrollment");
        editEnrollment.setOnAction(e-> {

        });
        MenuItem viewEnrollment = new MenuItem("View Course Enrollment");
        viewEnrollment.setOnAction(e-> {

        });
        menuCourseEnrollment.getItems().addAll(addEnrollment,editEnrollment,viewEnrollment);

        //Grades Menu
        Menu menuGrades = new Menu("_Grades");
        MenuItem viewGrade = new MenuItem("View Grades");
        viewGrade.setOnAction(e-> {

        });
        MenuItem addGrade = new MenuItem("Add Grade");
        addGrade.setOnAction(e-> {

        });
        MenuItem editGrade = new MenuItem("Edit Grade");
        editGrade.setOnAction(e-> {

        });
        menuGrades.getItems().addAll(addGrade,editGrade,viewGrade);


        //Reports Menu
        Menu menuReports = new Menu("_Reports");
        MenuItem studentReports = new MenuItem("Student Reports");
        studentReports.setOnAction(e->{

        });
        menuReports.getItems().add(studentReports);


        Label l = new Label("Welcome to the university enrollment app!");

        menuBar.getMenus().addAll(menuStudent,menuCourse,menuCourseEnrollment,menuGrades,menuReports);
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        borderPane.setCenter(l);
        base = new Scene(borderPane, 700, 400);



        addStudent.setOnAction(e-> {
            try{
                borderPane.setCenter(StudentGUI.AddStudents());
                window.setScene(base);
            }
            catch (IOException e1){
                AlertBox.display("IO Error", "IO Error has occurred");
            }
        });

        studentView.setOnAction(e -> {
            try{
                borderPane.setCenter(StudentGUI.ViewStudents());
                window.setScene(base);
            }
            catch (IOException e1){

            }

        });
        studentEdit.setOnAction(e-> {
            try{
                borderPane.setCenter(StudentGUI.EditStudents());
                window.setScene(base);
            }
            catch (IOException e1){

            }

        });
        AddCourse.setOnAction(e-> {
            try{
                borderPane.setCenter(CourseGUI.AddCourse());
                window.setScene(base);
            }
            catch (IOException e1){
                AlertBox.display("IO Error", "IO Error has occurred");
            }

        });
        ViewCourse.setOnAction(e-> {

            borderPane.setCenter(CourseGUI.ViewCourse());
            window.setScene(base);


        });
        EditCourse.setOnAction(e-> {
            borderPane.setCenter(CourseGUI.EditCourse());
            window.setScene(base);

        });
        addEnrollment.setOnAction(e -> {

            borderPane.setCenter(EnrollmentGUI.AddCourseEnrollment());
            window.setScene(base);

        });
        window.setScene(base);
        window.show();
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });







    }


    private void closeProgram(){
        boolean answer = ConfirmAppShutdown.display("Title", "Are you sure you want to exit?");
        if(answer){
            window.close();
        }




    }



    

}
