package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputValidation {

    public static int isInt(TextField input, String message) throws NumberFormatException{
        int ID = Integer.parseInt(input.getText());
        return ID;



    }
    public static void isEmpty(TextField input1, TextField input2, TextField input3, TextField input4, TextField input5) throws EmptyTextField{
        if(input1.getText().isEmpty() || input2.getText().isEmpty() || input3.getText().isEmpty()
                || input4.getText().isEmpty() || input5.getText().isEmpty()){
            throw new EmptyTextField();

        }

    }

    public static boolean verifyRecordDuplicate(Student s, StudentItemFile sf,  int studentID) throws IOException {
        boolean idInvalid = false;
        for(int i = 0; i <  sf.getNumberOfRecords(); i++){
            sf.moveFilePointer(i);
            s = sf.readStudentItemFile();
            if(studentID == s.getStudentID()){
                idInvalid = true;
                sf.closeFile();
                break;

            }

        }
        return  idInvalid;

    }

    public static int verifyRecordExists(Student s, StudentItemFile sf, int studentID) throws IOException{
        int filePointer = -1;
        for(int i = 0; i <  sf.getNumberOfRecords(); i++){
            sf.moveFilePointer(i);
            s = sf.readStudentItemFile();
            if(studentID == s.getStudentID()){
                filePointer = i;
                break;

            }

        }
        return  filePointer;

    }
    public static void isEmpty(TextField input1, TextField input2, TextField input3, TextField input4) throws EmptyTextField{
        if(input1.getText().isEmpty() || input2.getText().isEmpty() || input3.getText().isEmpty()
                || input4.getText().isEmpty()){
            throw new EmptyTextField();

        }

    }

    public static void isEmpty(TextField input1, TextField input2, TextField input3, TextField input4, TextField input5, TextField input6) throws EmptyTextField{
        if(input1.getText().isEmpty() || input2.getText().isEmpty() || input3.getText().isEmpty()
                || input4.getText().isEmpty() || input5.getText().isEmpty() || input6.getText().isEmpty()){
            throw new EmptyTextField();

        }

    }

    public static boolean verifyRecordDuplicate(Course s, CourseItemFile sf,  int studentID) throws IOException{
        boolean idInvalid = false;
        for(int i = 0; i <  sf.getNumberOfRecords(); i++){
            sf.moveFilePointer(i);
            s = sf.readCourseFile();
            if(studentID == s.getCourseNumber()){
                idInvalid = true;
                sf.closeFile();
                break;

            }

        }
        return  idInvalid;

    }




    public static int verifyRecordExists(Course c, CourseItemFile cf, int courseID) throws IOException{
        int filePointer = -1;
        for(int i = 0; i <  cf.getNumberOfRecords(); i++){
            cf.moveFilePointer(i);
            c = cf.readCourseFile();
            if(courseID == c.getCourseNumber()){
                filePointer = i;
                break;

            }

        }
        return  filePointer;

    }
    public static int verifyRecordExists(Enrollment e, EnrollmentFile ef, int courseID, int studentID) throws IOException{
        int filePointer = -1;
        for(int i = 0; i <  ef.getNumberOfRecords(); i++){
            ef.moveFilePointer(i);
            e = ef.readCourseEnrollmentFile();
            if(courseID == e.getCourseID() && studentID == e.getStudentID()){
                filePointer = i;
                break;

            }

        }
        return  filePointer;

    }

    public static boolean ValidateYear(int year ){
        int length = (int)(Math.log10(year) + 1);
        boolean validYear = true;
        if(length > 4  || length < 4){
            validYear = false;

        }
        else if(year < 0){
            validYear = false;
        }

        return validYear;
    }

    public static boolean verifyRecordDuplicate(Enrollment enrollment, EnrollmentFile ef, int year, String semester,  int studentID, int courseNumber) throws IOException{
        boolean duplicateEnrollmentData = false;
        for(int i = 0; i < ef.getNumberOfRecords(); i++){
            ef.moveFilePointer(i);
            enrollment = ef.readCourseEnrollmentFile();
            if(year == enrollment.getYear() && semester.toUpperCase().equals(enrollment.getSemester().toUpperCase()) && courseNumber == enrollment.getCourseID() && studentID == enrollment.getStudentID()){
                duplicateEnrollmentData = true;
            }

        }
        return  duplicateEnrollmentData;
    }



    public static ObservableList<Enrollment> getEnrollmentByCourse(int courseID) throws IOException{
        ObservableList<Enrollment> enrollments = FXCollections.observableArrayList();
        Enrollment eread = new Enrollment();
        EnrollmentFile ef = new EnrollmentFile("Enrollments.txt");
        List<Enrollment> enrollments1 = new ArrayList<Enrollment>();
        int enrollmentPOS = 0;
        ef.moveFilePointer(0);
        for(int i =0; i < ef.getNumberOfRecords(); i++){
            //Keeps track of the index of enrollments with specifed @params studentID

            ef.moveFilePointer(i);
            eread = ef.readCourseEnrollmentFile();
            if(courseID == eread.getCourseID()){
                enrollments1.add(eread);
                enrollments.add(enrollments1.get(enrollmentPOS));
                enrollmentPOS++;
            }




        }

        return enrollments;

    }


    public static String getChoice(ChoiceBox<String> choiceBox){
        String semester = choiceBox.getValue();
        return semester;
    }















}
