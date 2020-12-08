package sample;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

import static sample.EnrollmentGUI.getEnrollment;

public class GradesGUI {


    public static BorderPane viewGradesStudent(){
        TableView<Enrollment> table;
        BorderPane bp =  new BorderPane();

        //Used to hold letter grades for choice box menu
        ChoiceBox<String> grade = new ChoiceBox<>();
        bp.setPadding(new Insets(10,10,10,10));

        //Student Name Column
        TableColumn<Enrollment, Integer> StudentName = new TableColumn<>("Student Name");
        StudentName.setMinWidth(100);
        StudentName.setCellValueFactory(new PropertyValueFactory<>("StudentName"));

        //Student ID Column
        TableColumn<Enrollment, Integer> StudentIDColumn = new TableColumn<>("Student ID");
        StudentIDColumn.setMinWidth(100);
        StudentIDColumn.setCellValueFactory(new PropertyValueFactory<>("StudentID"));


        //Course Number Column
        TableColumn<Enrollment, Integer> CourseNumberColumn = new TableColumn<>("Course #");
        CourseNumberColumn.setMinWidth(50);
        CourseNumberColumn.setCellValueFactory(new PropertyValueFactory<>("CourseID"));


        // Year Column
        TableColumn<Enrollment, Integer> YearColumn = new TableColumn<>("Year");
        YearColumn.setMinWidth(100);
        YearColumn.setCellValueFactory(new PropertyValueFactory<>("Year"));


        // Semester Column
        TableColumn<Enrollment, String> SemesterColumn = new TableColumn<>("Semester");
        SemesterColumn.setMinWidth(100);
        SemesterColumn.setCellValueFactory(new PropertyValueFactory<>("Semester"));

        TableColumn<Enrollment, String> GradeColumn = new TableColumn<>("Grade");
        GradeColumn.setMinWidth(50);
        GradeColumn.setCellValueFactory(new PropertyValueFactory<>("Grade"));


        //Course Number Column
        TableColumn<Enrollment, String> CourseIDColumn = new TableColumn<>("CourseID");
        CourseIDColumn.setMinWidth(50);
        CourseIDColumn.setCellValueFactory(new PropertyValueFactory<>("CourseNumber"));


        //Course Name Column
        TableColumn<Enrollment, String> CourseNameColumn = new TableColumn<>("Course Name");
        CourseNameColumn.setMinWidth(125);
        CourseNameColumn.setCellValueFactory(new PropertyValueFactory<>("CourseName"));


        TextField StudentID = new TextField();
        StudentID.setMaxWidth(90);
        StudentID.setPromptText("Enter Student ID");

        Label studentIDLabel = new Label("Student ID");



        TextField courseNum = new TextField();
        courseNum.setMaxWidth(90);
        courseNum.setPromptText("Course Number");

        Label courseNumLabel = new Label("Course Number");
        Button editGrade = new Button("Edit");



        grade.getItems().addAll("A", "B", "C", "D", "F");
        grade.getSelectionModel().select("A");


        //Initializes table view
        table = new TableView<>();


        Button searchStudent = new Button("Search");
        searchStudent.setOnAction(e-> {
            try{
                Student sread = new Student();
                StudentItemFile sf = new StudentItemFile("Students.dat");
                int studentIDinput = InputValidation.isInt(StudentID, StudentID.getText());
                table.setItems(getEnrollment(studentIDinput));

            }
            catch(IOException e1){
                AlertBox.display("IO Error", "An IO error has occurred!");
            }
            catch (NumberFormatException e1){
                AlertBox.display("Invalid Entry", "You have entered an Invalid ID entry!");
            }



        });
        table.getColumns().addAll(StudentName, StudentIDColumn, CourseNameColumn, CourseIDColumn,CourseNumberColumn, SemesterColumn,YearColumn ,GradeColumn);



        FlowPane topFlowPane = new FlowPane();
        topFlowPane.setHgap(25);
        topFlowPane.setMargin(studentIDLabel, new Insets(20,0,20,20));
        ObservableList List = topFlowPane.getChildren();
        List.addAll(studentIDLabel, StudentID, searchStudent);



        editGrade.setOnAction(e->{
            try{
                EnrollmentFile ef = new EnrollmentFile("Enrollments.dat");
                Enrollment enrollment = new Enrollment();
                String letterGrade = InputValidation.getChoice(grade);
                int sID = InputValidation.isInt(StudentID,StudentID.getText());
                int ciD = InputValidation.isInt(courseNum, courseNum.getText());
                int recordPOS = InputValidation.verifyRecordExists(enrollment, ef, ciD, sID);
                if(recordPOS == -1){
                    throw new InvalidID("Invalid Record", "The record you have entered does not exist!");
                }
                ef.moveFilePointer(0);

                ef.moveFilePointer(recordPOS);
                enrollment = ef.readCourseEnrollmentFile();
                enrollment.setGrade(letterGrade);
                ef.moveFilePointer(recordPOS);
                ef.writeCourseItem(enrollment);
                table.setItems(getEnrollment(sID));
                AlertBox.display("Grade edited!", "You have edited the grade!");

            }
            catch(IOException e1){

            }
            catch(InvalidID e1){

            }

        });


        FlowPane bottomFlowPane = new FlowPane();
        bottomFlowPane.setHgap(25);
        bottomFlowPane.setMargin(courseNumLabel, new Insets(20,20,20,20));
        ObservableList list2 = bottomFlowPane.getChildren();
        list2.addAll(courseNumLabel, courseNum, grade, editGrade);



        bp.setTop(topFlowPane);
        bp.setBottom(bottomFlowPane);
        bp.setCenter(table);


        return bp;



    }

    public static BorderPane viewGradesCourse(){
        TableView<Enrollment> table;
        BorderPane bp =  new BorderPane();

        //Used to hold letter grades for choice box menu
        ChoiceBox<String> grade = new ChoiceBox<>();
        bp.setPadding(new Insets(10,10,10,10));

        //Student Name Column
        TableColumn<Enrollment, Integer> StudentName = new TableColumn<>("Student Name");
        StudentName.setMinWidth(100);
        StudentName.setCellValueFactory(new PropertyValueFactory<>("StudentName"));

        TableColumn<Enrollment, Integer> StudentIDColumn = new TableColumn<>("Student ID");
        StudentIDColumn.setMinWidth(100);
        StudentIDColumn.setCellValueFactory(new PropertyValueFactory<>("StudentID"));




        //Course Number Column
        TableColumn<Enrollment, Integer> CourseNumberColumn = new TableColumn<>("Course #");
        CourseNumberColumn.setMinWidth(50);
        CourseNumberColumn.setCellValueFactory(new PropertyValueFactory<>("CourseID"));


        // Year Column
        TableColumn<Enrollment, Integer> YearColumn = new TableColumn<>("Year");
        YearColumn.setMinWidth(100);
        YearColumn.setCellValueFactory(new PropertyValueFactory<>("Year"));


        // Semester Column
        TableColumn<Enrollment, String> SemesterColumn = new TableColumn<>("Semester");
        SemesterColumn.setMinWidth(100);
        SemesterColumn.setCellValueFactory(new PropertyValueFactory<>("Semester"));

        TableColumn<Enrollment, String> GradeColumn = new TableColumn<>("Grade");
        GradeColumn.setMinWidth(50);
        GradeColumn.setCellValueFactory(new PropertyValueFactory<>("Grade"));




        //Course Name Column
        TableColumn<Enrollment, String> CourseNameColumn = new TableColumn<>("Course Name");
        CourseNameColumn.setMinWidth(125);
        CourseNameColumn.setCellValueFactory(new PropertyValueFactory<>("CourseName"));


        TextField courseNumberInput = new TextField();
        courseNumberInput.setMaxWidth(90);
        courseNumberInput.setPromptText("Enter Course Number");

        TextField studentIDNumberInput = new TextField();
        studentIDNumberInput.setMaxWidth(90);
        studentIDNumberInput.setPromptText("Enter Student ID");

        Label studentIDLabel = new Label("Student ID");



        TextField courseNum = new TextField();
        courseNum.setMaxWidth(90);
        courseNum.setPromptText("Course Number");

        Label courseNumLabel = new Label("Course Number");
        Button editGrade = new Button("Edit");



        grade.getItems().addAll("A", "B", "C", "D", "F");
        grade.getSelectionModel().select("A");


        //Initializes table view
        table = new TableView<>();


        Button searchCourseNumber = new Button("Search");

        searchCourseNumber.setOnAction(e-> {
            try{
                int courseNUm = InputValidation.isInt(courseNumberInput, courseNumberInput.getText());
                table.setItems(InputValidation.getEnrollmentByCourse(courseNUm));


            }
            catch(IOException e1){
                AlertBox.display("IO Error", "An IO error has occurred!");
            }



        });
        table.getColumns().addAll(StudentName, StudentIDColumn, CourseNameColumn,CourseNumberColumn, SemesterColumn,YearColumn ,GradeColumn);



        FlowPane topFlowPane = new FlowPane();
        topFlowPane.setHgap(25);
        topFlowPane.setMargin(courseNum, new Insets(20,0,20,20));
        ObservableList List = topFlowPane.getChildren();
        List.addAll(courseNumLabel, courseNumberInput, searchCourseNumber);



        editGrade.setOnAction(e->{
            try{
                EnrollmentFile ef = new EnrollmentFile("Enrollments.dat");
                Enrollment enrollment = new Enrollment();
                String letterGrade = InputValidation.getChoice(grade);
                int sID = InputValidation.isInt(studentIDNumberInput,studentIDNumberInput.getText());
                int ciD = InputValidation.isInt(courseNumberInput, courseNumberInput.getText());
                int recordPOS = InputValidation.verifyRecordExists(enrollment, ef, ciD, sID);
                if(recordPOS == -1){
                    throw new InvalidID("Invalid Record", "The record you have entered does not exist!");
                }
                ef.moveFilePointer(0);

                ef.moveFilePointer(recordPOS);
                enrollment = ef.readCourseEnrollmentFile();
                enrollment.setGrade(letterGrade);
                ef.moveFilePointer(recordPOS);
                ef.writeCourseItem(enrollment);
                table.setItems(InputValidation.getEnrollmentByCourse(ciD));
                AlertBox.display("Grade edited!", "You have edited the grade!");

            }
            catch(IOException e1){

            }
            catch(InvalidID e1){

            }
            catch(NumberFormatException e1){
                AlertBox.display("Invalid entry", "You have entered an invalid ID entry!");
            }

        });


        FlowPane bottomFlowPane = new FlowPane();
        bottomFlowPane.setHgap(25);
        bottomFlowPane.setMargin(courseNumLabel, new Insets(20,20,20,20));
        ObservableList list2 = bottomFlowPane.getChildren();
        list2.addAll(studentIDLabel, studentIDNumberInput, grade, editGrade);



        bp.setTop(topFlowPane);
        bp.setBottom(bottomFlowPane);
        bp.setCenter(table);


        return bp;

    }
}
