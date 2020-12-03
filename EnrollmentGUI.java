package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;


public class EnrollmentGUI {
    public static GridPane AddCourseEnrollment(){
        Student sread = new Student();
        Course cread = new Course();
        ChoiceBox<String> semester = new ChoiceBox<>();

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

        semester.getItems().addAll("FALL", "WINTER", "SPRING", "SUMMER");
        semester.getSelectionModel().select("FALL");
        GridPane.setConstraints(semester, 0,7);



        TextField Year = new TextField();
        GridPane.setConstraints(Year, 0,5 );
        Year.setPromptText("Year");

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
                String season = getChoice(semester);
                int StudentID = InputValidation.isInt(studentID, studentID.getText());
                int CourseNum = InputValidation.isInt(courseNumber, courseNumber.getText());
                int year = InputValidation.isInt(Year, Year.getText());
                boolean validYear = InputValidation.ValidateYear(year);
                if(!validYear){
                    throw  new InvalidID("Invalid Year", "You have entered an invalid year!");
                }
                enrollment = new Enrollment(StudentID,CourseNum , year, season, "", courseNumber.getText(), CourseName.getText());
                ef.moveFilePointer(ef.getNumberOfRecords());
                ef.writeCourseItem(enrollment);
                ef.closeFile();

                
            }
            catch(IOException e1){

            }
            catch(InvalidID e1){

            }


        });












        grid.getChildren().addAll(studentID,studentName, courseNumber, courseID, CourseName, Semester, Year, SearchStudent, SearchCourse, CreateEnrollment, semester);
        grid.setAlignment(Pos.CENTER);




        return grid;





    }


    public static void EditCourseEnrollment(){

    }

    private static String getChoice(ChoiceBox<String> choiceBox){
        String semester = choiceBox.getValue();
        return semester;
    }

}
