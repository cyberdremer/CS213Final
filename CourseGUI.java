package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class CourseGUI {


    public static GridPane AddCourse() throws IOException {
        Course cread = new Course();

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        //Name Label
        Label courseNumberLabel = new Label("Course Number: ");
        GridPane.setConstraints(courseNumberLabel, 0, 0);


            //Name Input
        TextField courseNumberInput = new TextField();
        GridPane.setConstraints(courseNumberInput, 1, 0);


        //Student ID Label
        Label courseIDLabel = new Label("Course ID: ");
        GridPane.setConstraints(courseIDLabel, 0, 1);


        //Student ID Input
        TextField courseIDInput = new TextField();
        GridPane.setConstraints(courseIDInput, 1, 1);


        //Address Label
        Label courseNameLabel = new Label("Course Name: ");
        GridPane.setConstraints(courseNameLabel, 0, 2);


        //Address Input
        TextField courseNameInput = new TextField();
        GridPane.setConstraints(courseNameInput, 1, 2);

        //City Label
        Label instructorLabel = new Label("Instructor Name: ");
        GridPane.setConstraints(instructorLabel, 0, 3);


        //City Input
        TextField instructorInput = new TextField();
        GridPane.setConstraints(instructorInput, 1, 3);

        //State Label
        Label departmentLabel = new Label("Department: ");
        GridPane.setConstraints(departmentLabel, 0, 4);


        //State Input
        TextField departmentInput = new TextField();
        GridPane.setConstraints(departmentInput, 1, 4);

        Button button = new Button("Add Course Record");
        GridPane.setConstraints(button, 1, 5);

        button.setOnAction(e -> {
            try {
                CourseItemFile ciF = new CourseItemFile("Courses.dat");
                ciF.moveFilePointer(0);
                final int courseID = InputValidation.isInt(courseNumberInput, courseNumberInput.getText());
                InputValidation.isEmpty(courseNumberInput, courseNameInput, instructorInput, departmentInput);
                boolean InvalidID = InputValidation.verifyRecordDuplicate(cread, ciF, courseID);
                if(courseID < 0){
                    courseNumberInput.clear();
                    courseNameInput.clear();
                    courseIDInput.clear();
                    instructorInput.clear();
                    departmentInput.clear();
                    throw new InvalidID("Invalid ID entry", "You have entered a negative number!");
                }
                if (InvalidID) {
                    throw new InvalidID("Course ID duplicate", "This Course ID already exists!");
                }
                Course c = new Course(courseID, instructorInput.getText(), courseNameInput.getText(), departmentInput.getText(), courseIDInput.getText());
                ciF.writeCourseItem(c);
                ciF.closeFile();
                AlertBox.display("Course record saved.", "The course record you have entered has been saved!");


            }
            catch (NumberFormatException e1) {
                AlertBox.display("Invalid Course Number.", "You have entered an invalid course number.");


            }
            //Catches blank text entries
            catch (EmptyTextField e1) {


            }
            catch (IOException e1) {
                AlertBox.display("File not found", "File has not been found!");
            }
            catch (InvalidID e1) {

            }


        });


        grid.getChildren().addAll( courseIDLabel, courseIDInput, courseNumberLabel, courseNumberInput, courseNameLabel, courseNameInput, instructorLabel, instructorInput, departmentLabel, departmentInput, button);
        grid.setAlignment(Pos.CENTER);

        return grid;
    }


    public static GridPane ViewCourse(){
        Course cread = new Course();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        //Name Label
        Label courseNumberLabel = new Label("Course Number: ");
        GridPane.setConstraints(courseNumberLabel, 0, 0);


        //Name Input
        TextField courseNumberInput = new TextField();
        GridPane.setConstraints(courseNumberInput, 1, 0);


        //Student ID Label
        Label courseIDLabel = new Label("Course ID: ");
        GridPane.setConstraints(courseIDLabel, 0, 1);


        //Student ID Input
        TextField courseIDInput = new TextField();
        GridPane.setConstraints(courseIDInput, 1, 1);


        //Address Label
        Label courseNameLabel = new Label("Course Name: ");
        GridPane.setConstraints(courseNameLabel, 0, 2);


        //Address Input
        TextField courseNameInput = new TextField();
        GridPane.setConstraints(courseNameInput, 1, 2);

        //City Label
        Label instructorLabel = new Label("Instructor Name: ");
        GridPane.setConstraints(instructorLabel, 0, 3);


        //City Input
        TextField instructorInput = new TextField();
        GridPane.setConstraints(instructorInput, 1, 3);

        //State Label
        Label departmentLabel = new Label("Department: ");
        GridPane.setConstraints(departmentLabel, 0, 4);


        //State Input
        TextField departmentInput = new TextField();
        GridPane.setConstraints(departmentInput, 1, 4);

        Button search = new Button("Search");
        GridPane.setConstraints(search, 2,0);

        search.setOnAction(e -> {
            try {
                CourseItemFile ciF = new CourseItemFile("Courses.dat");
                ciF.moveFilePointer(0);
                final int courseID = InputValidation.isInt(courseNumberInput, courseNumberInput.getText());
                int filePointerPOS = InputValidation.verifyRecordExists(cread, ciF, courseID);
                if (filePointerPOS == -1) {
                    throw new InvalidID("Course ID not found!", "This Course ID does not exist!");
                }
                ciF.moveFilePointer(filePointerPOS);
                Course c = ciF.readCourseFile();
                courseIDInput.setText(c.getCourseID());
                departmentInput.setText(c.getDepartmentName());
                instructorInput.setText(c.getInstructorName());
                courseNameInput.setText(c.getCourseName());


            }
            catch (NumberFormatException e1) {
                AlertBox.display("Invalid Course Number.", "You have entered an invalid course number.");


            }
            catch (IOException e1) {
                AlertBox.display("File not found", "File has not been found!");
            }
            catch (InvalidID e1) {

            }


        });


        grid.getChildren().addAll( courseIDLabel, courseIDInput, courseNumberLabel, courseNumberInput, courseNameLabel, courseNameInput, instructorLabel, instructorInput, departmentLabel, departmentInput, search);
        grid.setAlignment(Pos.CENTER);
        return grid;
    }



    public static GridPane EditCourse(){
        Course cread = new Course();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        //Name Label
        Label courseNumberLabel = new Label("Course Number: ");
        GridPane.setConstraints(courseNumberLabel, 0, 0);


        //Name Input
        TextField courseNumberInput = new TextField();
        GridPane.setConstraints(courseNumberInput, 1, 0);


        //Student ID Label
        Label courseIDLabel = new Label("Course ID: ");
        GridPane.setConstraints(courseIDLabel, 0, 1);


        //Student ID Input
        TextField courseIDInput = new TextField();
        GridPane.setConstraints(courseIDInput, 1, 1);


        //Address Label
        Label courseNameLabel = new Label("Course Name: ");
        GridPane.setConstraints(courseNameLabel, 0, 2);


        //Address Input
        TextField courseNameInput = new TextField();
        GridPane.setConstraints(courseNameInput, 1, 2);

        //City Label
        Label instructorLabel = new Label("Instructor Name: ");
        GridPane.setConstraints(instructorLabel, 0, 3);


        //City Input
        TextField instructorInput = new TextField();
        GridPane.setConstraints(instructorInput, 1, 3);

        //State Label
        Label departmentLabel = new Label("Department: ");
        GridPane.setConstraints(departmentLabel, 0, 4);


        //State Input
        TextField departmentInput = new TextField();
        GridPane.setConstraints(departmentInput, 1, 4);

        Button search = new Button("Search");
        GridPane.setConstraints(search, 2,0);

        Button clear = new Button("Clear");
        GridPane.setConstraints(clear,1,5);

        Button update = new Button("Update");
        GridPane.setConstraints(update,2,5);

        search.setOnAction(e -> {
            try {
                CourseItemFile ciF = new CourseItemFile("Courses.dat");
                ciF.moveFilePointer(0);
                final int courseID = InputValidation.isInt(courseNumberInput, courseNumberInput.getText());
                int filePointerPOS = InputValidation.verifyRecordExists(cread, ciF, courseID);
                if (filePointerPOS == -1) {
                    throw new InvalidID("Course ID not found!", "This Course ID does not exist!");
                }
                ciF.moveFilePointer(filePointerPOS);
                Course c = ciF.readCourseFile();
                courseIDInput.setText(c.getCourseID());
                departmentInput.setText(c.getDepartmentName());
                instructorInput.setText(c.getInstructorName());
                courseNameInput.setText(c.getCourseName());
                courseNumberInput.setDisable(true);


            }
            catch (NumberFormatException e1) {
                AlertBox.display("Invalid Course Number.", "You have entered an invalid course number.");


            }
            catch (IOException e1) {
                AlertBox.display("File not found", "File has not been found!");
            }
            catch (InvalidID e1) {

            }


        });

        clear.setOnAction( e -> {
            courseNumberInput.clear();
            instructorInput.clear();
            courseIDInput.clear();
            courseNameInput.clear();
            departmentInput.clear();
            courseNumberInput.setDisable(false);
        });


        update.setOnAction(e->{

            try{
                CourseItemFile cf = new CourseItemFile("Courses.dat");
                Course course;
                cf.moveFilePointer(0);
                final int courseNumber = InputValidation.isInt(courseNumberInput, courseNumberInput.getText());
                int pointerPosition = InputValidation.verifyRecordExists(cread, cf, courseNumber);
                if(pointerPosition == -1){
                    throw new InvalidID("Student ID does not exist", "This Student ID does not exist");
                }
                cf.moveFilePointer(pointerPosition);
                course  = new Course(courseNumber, instructorInput.getText(), courseNameInput.getText(), departmentInput.getText(), courseIDInput.getText());
                cf.writeCourseItem(course);
                cf.closeFile();
                AlertBox.display("Course record edited", "The Course record has been edited!");


            }

            catch(NumberFormatException e1){
                AlertBox.display("Invalid Student ID.", "You have entered and invalid student ID.");


            }

            catch(IOException e1){
                AlertBox.display("File not found", "File has not been found!");
            }

            catch (InvalidID e1){

            }



        });







        grid.getChildren().addAll( courseIDLabel, courseIDInput, courseNumberLabel, courseNumberInput, courseNameLabel, courseNameInput, instructorLabel, instructorInput, departmentLabel, departmentInput, search, update, clear);
        grid.setAlignment(Pos.CENTER);
        return grid;
    }







}
