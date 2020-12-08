package sample;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class EnrollmentGUI {
    public static GridPane AddCourseEnrollment(){
        Student sread = new Student();
        Course cread = new Course();
        Enrollment eread = new Enrollment();
        ChoiceBox<String> semesterChoiceBox = new ChoiceBox<>();

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);


        TextField studentID = new TextField();
        GridPane.setConstraints(studentID,0,0);
        studentID.setPromptText("Student ID");

        TextField studentName = new TextField();
        GridPane.setConstraints(studentName,0,1);
        studentName.setPromptText("Student Name");

        TextField courseNumber = new TextField();
        GridPane.setConstraints(courseNumber,0,2);
        courseNumber.setPromptText("Course Number");



        TextField courseID = new TextField();
        GridPane.setConstraints(courseID,0,3);
        courseID.setPromptText("Course ID");

        TextField CourseName = new TextField();
        GridPane.setConstraints(CourseName, 0,4 );
        CourseName.setPromptText("Course Name");

        Label Semester = new Label("Semester");
        GridPane.setConstraints(Semester, 0,6 );

        semesterChoiceBox.getItems().addAll("FALL", "WINTER", "SPRING", "SUMMER");
        semesterChoiceBox.getSelectionModel().select("FALL");
        GridPane.setConstraints(semesterChoiceBox, 0,7);



        TextField Year = new TextField();
        GridPane.setConstraints(Year, 0,5 );
        Year.setPromptText("Year");


        Label prompt = new Label("Enter Student ID and Course Number!");

        Button SearchStudent, SearchCourse, CreateEnrollment;

        SearchStudent= new Button("Search Student");
        SearchCourse = new Button("Search Course");
        CreateEnrollment = new Button("Create Enrollment");

        GridPane.setConstraints(SearchStudent, 1,0);
        GridPane.setConstraints(SearchCourse,1,2 );
        GridPane.setConstraints(CreateEnrollment,1,7);


        SearchStudent.setOnAction(e->{

            try{

                StudentItemFile studentItemFile = new StudentItemFile("Students.dat");
                studentItemFile.moveFilePointer(0);
                final int StudentID = InputValidation.isInt(studentID, studentID.getText());
                int pointerPosition = InputValidation.verifyRecordExists(sread, studentItemFile, StudentID);
                if(pointerPosition == -1){
                    studentName.clear();
                    throw new InvalidID("Student ID does not exist", "This Student ID does not exist");

                }
                studentItemFile.moveFilePointer(pointerPosition);
                Student student = studentItemFile.readStudentItemFile();
                studentName.setText(student.getName());
                studentItemFile.closeFile();






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

        SearchCourse.setOnAction(e->{
            try {
                CourseItemFile ciF = new CourseItemFile("Courses.dat");
                ciF.moveFilePointer(0);
                final int courseNum = InputValidation.isInt(courseNumber, courseNumber.getText());
                int filePointerPOS = InputValidation.verifyRecordExists(cread, ciF, courseNum);
                if (filePointerPOS == -1) {
                    courseID.clear();
                    CourseName.clear();
                    throw new InvalidID("Course ID not found!", "This Course ID does not exist!");
                }
                ciF.moveFilePointer(filePointerPOS);
                Course c = ciF.readCourseFile();

                courseID.setText(c.getCourseID());
                CourseName.setText(c.getCourseName());



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

        CreateEnrollment.setOnAction(e->{
            try{
                EnrollmentFile ef = new EnrollmentFile("Enrollments.dat");
                ef.moveFilePointer(0);
                Enrollment enrollment;
                String season = getChoice(semesterChoiceBox);
                int StudentID = InputValidation.isInt(studentID, studentID.getText());
                int CourseNum = InputValidation.isInt(courseNumber, courseNumber.getText());
                int year = InputValidation.isInt(Year, Year.getText());
                boolean validYear = InputValidation.ValidateYear(year);
                InputValidation.isEmpty(studentID, studentName, courseNumber, courseID, Year, CourseName);
                if(!validYear){
                    throw  new InvalidID("Invalid Year", "You have entered an invalid year!");
                }
                boolean validEnrollment = InputValidation.verifyRecordDuplicate(eread, ef, StudentID, CourseNum );
                if(validEnrollment){
                    throw new InvalidID("Duplicate Enrollment", "You have entered information for a duplicate enrollment!");
                }
                enrollment = new Enrollment(CourseNum,StudentID , year, season, "", courseID.getText(), CourseName.getText(), studentName.getText());
                ef.moveFilePointer(ef.getNumberOfRecords());
                ef.writeCourseItem(enrollment);
                ef.closeFile();
                AlertBox.display("Enrollment Created", "New Enrollment record added!");


            }
            catch(IOException e1){

            }
            catch(InvalidID e1){

            }
            catch(EmptyTextField e1){
                AlertBox.display("Empty textfield", "You have entered an empty input!");

            }
            catch(NumberFormatException e1){
                AlertBox.display("Invalid Input", "You have entered invalid input for one or more fields!");

            }


        });












        grid.getChildren().addAll(studentID,studentName, courseNumber, courseID, CourseName, Semester, Year, SearchStudent, SearchCourse, CreateEnrollment, semesterChoiceBox);
        grid.setAlignment(Pos.CENTER);




        return grid;





    }


    public static BorderPane ViewEnrollment(){
        TableView<Enrollment> table;
        BorderPane bp =  new BorderPane();
        bp.setPadding(new Insets(10,10,10,10));

        //Student Name Column
        TableColumn<Enrollment, Integer> StudentName = new TableColumn<>("Student Name");
        StudentName.setMinWidth(100);
        StudentName.setCellValueFactory(new PropertyValueFactory<>("StudentName"));

        //Student ID Column


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



        });
        table.getColumns().addAll(StudentName, CourseNameColumn, CourseIDColumn,CourseNumberColumn, SemesterColumn,YearColumn ,GradeColumn);

        FlowPane flowPane = new FlowPane();
        flowPane.setHgap(25);
        flowPane.setMargin(StudentID, new Insets(20,0,20,20));
        ObservableList List = flowPane.getChildren();
        List.addAll(StudentID,searchStudent);


        bp.setTop(flowPane);
        bp.setCenter(table);


        return bp;



    }


    public static GridPane EditCourseEnrollment(){

        Enrollment eread = new Enrollment();
        ChoiceBox<String> semesterChoiceBox = new ChoiceBox<>();

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);


        Label StudentIDLabel, courseNumberLabel, studentNameLabel, courseIDLabel, CourseNameLabel, SemesterLabel,
                YearLabel, GradeLabel;

        StudentIDLabel = new Label("Student ID: ");
        GridPane.setConstraints(StudentIDLabel, 0,0);


        TextField studentID = new TextField();
        GridPane.setConstraints(studentID,1,0);
        studentID.setPromptText("Student ID");

        courseNumberLabel = new Label("Course Number");
        GridPane.setConstraints(courseNumberLabel,0,1);

        TextField courseNumber = new TextField();
        GridPane.setConstraints(courseNumber,1,1);
        courseNumber.setPromptText("Course Number");


        studentNameLabel = new Label("Student Name");
        GridPane.setConstraints(studentNameLabel,0,2 );


        TextField studentName = new TextField();
        GridPane.setConstraints(studentName,1,2);
        studentName.setPromptText("Student Name");


        courseIDLabel = new Label("Course ID");
        GridPane.setConstraints(courseIDLabel,0,3);


        TextField courseID = new TextField();
        GridPane.setConstraints(courseID,1,3);
        courseID.setPromptText("Course ID");


        CourseNameLabel = new Label("Course Name");
        GridPane.setConstraints(CourseNameLabel, 0,4);

        TextField CourseName = new TextField();
        GridPane.setConstraints(CourseName, 1,4 );
        CourseName.setPromptText("Course Name");

        SemesterLabel = new Label("Semester");
        GridPane.setConstraints(SemesterLabel,0,5 );

        TextField SemesterDisplay = new TextField();
        GridPane.setConstraints(SemesterDisplay,1,5);
        SemesterDisplay.setPromptText("Semester");


        YearLabel = new Label("Year");
        GridPane.setConstraints(YearLabel, 0,6);

        TextField Year = new TextField();
        GridPane.setConstraints(Year, 1,6);
        Year.setPromptText("Year");


        semesterChoiceBox.getItems().addAll("FALL", "WINTER", "SPRING", "SUMMER");
        semesterChoiceBox.getSelectionModel().select("FALL");
        GridPane.setConstraints(semesterChoiceBox, 1,8);


        GradeLabel = new Label("Grade");
        GridPane.setConstraints(GradeLabel, 0,7);

        TextField gradeTextField = new TextField();
        GridPane.setConstraints(gradeTextField, 1,7);
        gradeTextField.setPromptText("Grade");







        Button SearchEnrollment, EditEnrollment, Clear;

        SearchEnrollment= new Button("Search Enrollment");
        EditEnrollment = new Button("Edit Enrollment");
        Clear = new Button("Clear");

        GridPane.setConstraints(SearchEnrollment, 2,0);
        GridPane.setConstraints(EditEnrollment,3,8);
        GridPane.setConstraints(Clear,2,8);


        SearchEnrollment.setOnAction(e->{

            try{
                String yearIntToString;

                EnrollmentFile ef = new EnrollmentFile("Enrollments.dat");
                int StudentID = InputValidation.isInt(studentID, studentID.getText());
                int CourseNumber = InputValidation.isInt(courseNumber, courseNumber.getText());
                int pointerPosition = InputValidation.verifyRecordExists(eread, ef, CourseNumber, StudentID);
                if(pointerPosition == -1){
                    studentName.clear();
                    Year.clear();
                    CourseName.clear();
                    SemesterDisplay.clear();
                    courseID.clear();
                    courseNumber.clear();
                    throw new InvalidID("Course/ Student ID invalid", "Course and/ or Student ID invalid!");

                }
                ef.moveFilePointer(pointerPosition);
                Enrollment enrollment = ef.readCourseEnrollmentFile();
                yearIntToString = String.valueOf(enrollment.getYear());
                studentName.setText(enrollment.getStudentName());
                Year.setText(yearIntToString);
                CourseName.setText(enrollment.getCourseName());
                SemesterDisplay.setText(enrollment.getSemester());
                courseID.setText(enrollment.getCourseNumber());
                gradeTextField.setText(enrollment.getGrade());

                gradeTextField.setDisable(true);
                studentName.setDisable(true);
                CourseName.setDisable(true);
                SemesterDisplay.setDisable(true);
                courseID.setDisable(true);








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


        EditEnrollment.setOnAction(e->{
            try{
                EnrollmentFile ef = new EnrollmentFile("Enrollments.dat");
                ef.moveFilePointer(0);
                Enrollment enrollment;
                String season = getChoice(semesterChoiceBox);
                int StudentID = InputValidation.isInt(studentID, studentID.getText());
                int CourseNum = InputValidation.isInt(courseNumber, courseNumber.getText());
                int year = InputValidation.isInt(Year, Year.getText());
                boolean validYear = InputValidation.ValidateYear(year);
                if(!validYear){
                    throw  new InvalidID("Invalid Year", "You have entered an invalid year!");
                }
                InputValidation.isEmpty(studentID, studentName, courseNumber, courseID, Year, CourseName);
                int PointerPOS = InputValidation.verifyRecordExists(eread, ef, CourseNum, StudentID);
                if(PointerPOS == -1){
                    throw new InvalidID("Invalid ID entries!", "You have entered invalid ID Entries!");
                }


                enrollment = new Enrollment(CourseNum,StudentID , year, season, "", courseID.getText(), CourseName.getText(), studentName.getText());
                enrollment.setGrade(gradeTextField.getText());
                ef.moveFilePointer(PointerPOS);
                ef.writeCourseItem(enrollment);
                ef.closeFile();
                AlertBox.display("Enrollment Edited", "Enrollment Record Edited!");


            }
            catch(IOException e1){

            }
            catch(InvalidID e1){

            }
            catch(EmptyTextField e1){
                AlertBox.display("Empty textfield", "You have entered an empty input!");

            }
            catch(NumberFormatException e1){
                AlertBox.display("Invalid Input", "You have entered invalid input for one or more fields!");

            }


        });

        Clear.setOnAction(e->{
            courseNumber.setDisable(false);
            studentID.setDisable(false);
            studentName.setDisable(false);
            courseNumber.setDisable(false);
            courseID.setDisable(false);
            CourseName.setDisable(false);

            courseNumber.clear();
            studentID.clear();
            studentName.clear();
            courseNumber.clear();
            courseID.clear();
            Year.clear();
            CourseName.clear();


        });












        grid.getChildren().addAll(studentID,studentName, courseNumber, courseID, CourseName, SemesterDisplay, Year, SearchEnrollment, EditEnrollment, Clear, semesterChoiceBox, gradeTextField);
        grid.getChildren().addAll(StudentIDLabel, courseNumberLabel, studentNameLabel, courseIDLabel, CourseNameLabel, SemesterLabel,
                YearLabel, GradeLabel);
        grid.setAlignment(Pos.CENTER);




        return grid;





    }



    //Get all enrollments and returns them as viewable lsit
    public static ObservableList<Enrollment> getEnrollment(int studentID) throws IOException{
        ObservableList<Enrollment> enrollments = FXCollections.observableArrayList();
        Enrollment eread = new Enrollment();
        EnrollmentFile ef = new EnrollmentFile("Enrollments.dat");
        List<Enrollment> enrollments1 = new ArrayList<Enrollment>();
        int enrollmentPOS = 0;
        ef.moveFilePointer(0);
        for(int i =0; i < ef.getNumberOfRecords(); i++){
            //Keeps track of the index of enrollments with specifed @params studentID

            ef.moveFilePointer(i);
            eread = ef.readCourseEnrollmentFile();
            if(studentID == eread.getStudentID()){
                enrollments1.add(eread);
                enrollments.add(enrollments1.get(enrollmentPOS));
                enrollmentPOS++;
            }




        }

        return enrollments;

    }

    private static String getChoice(ChoiceBox<String> choiceBox){
        String semester = choiceBox.getValue();
        return semester;
    }

}
