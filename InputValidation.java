package sample;

import javafx.scene.control.TextField;

import java.io.IOException;

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















}
