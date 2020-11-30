package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application  {

    Stage window;

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
        studentView.setOnAction(e -> {
            try{
                StudentGUI.ViewStudents();
            }
            catch (IOException e1){

            }

        });
        MenuItem addStudent = new MenuItem("Add Student");
        addStudent.setOnAction(e-> {
            try{
                StudentGUI.AddStudents();
            }
            catch (IOException e1){
                AlertBox.display("IO Error", "IO Error has occurred");
            }
        });
        MenuItem studentEdit = new MenuItem("Edit Student");
        studentEdit.setOnAction(e-> {

        });
        menuStudent.getItems().addAll(addStudent, studentView, studentEdit);


        //Courses Menu
        Menu menuCourse = new Menu("_Courses");
        MenuItem AddCourse = new MenuItem("Add Course");
        AddCourse.setOnAction(e-> {
            try{
                CourseGUI.AddCourse();
            }
            catch (IOException e1){
                AlertBox.display("IO Error", "IO Error has occurred");
            }

        });
        MenuItem EditCourse = new MenuItem("Edit Course");
        EditCourse.setOnAction(e-> {

        });
        MenuItem ViewCourse = new MenuItem("View Course");
        ViewCourse.setOnAction(e-> {

        });
        menuCourse.getItems().addAll(AddCourse, EditCourse, ViewCourse);



        //CourseGUI Enrollment Menu
        Menu menuCourseEnrollment = new Menu("_Enrollment");
        MenuItem addEnrollment = new MenuItem("Add Course Enrollment");
        addEnrollment.setOnAction(e -> {

        });
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
        MenuItem studentReports = new MenuItem("StudentGUI Reports");
        studentReports.setOnAction(e->{

        });
        menuReports.getItems().add(studentReports);

        HBox center = new HBox();
        Label l = new Label("Welcome to the University Enrollment App!");
        center.getChildren().addAll(l);
        center.setAlignment(Pos.CENTER);

        menuBar.getMenus().addAll(menuStudent,menuCourse,menuCourseEnrollment,menuGrades,menuReports);
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        borderPane.setCenter(center);


        Scene scene = new Scene(borderPane, 500, 300);
        window.setScene(scene);
        window.show();
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });







        window.show();


    }


    private void closeProgram(){
        boolean answer = ConfirmAppShutdown.display("Title", "Are you sure you want to exit?");
        if(answer){
            window.close();
        }




    }



    

}
