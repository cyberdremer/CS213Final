package sample;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.*;

public class StudentGUI {


    public static void AddStudents() throws IOException {
        Student sread = new Student();
        Stage Window = new Stage();
        Window.initModality(Modality.APPLICATION_MODAL);

        Window.setTitle("Add Student");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);


        //Student ID Label
        Label studentIDLabel = new Label("Student ID: ");
        GridPane.setConstraints(studentIDLabel, 0, 0);


        //Student ID Input
        TextField studentIDInput = new TextField();
        GridPane.setConstraints(studentIDInput,1,0);


        //Name Label
        Label nameLabel = new Label("Student Name: ");
        GridPane.setConstraints(nameLabel, 0, 1);


        //Name Input
        TextField nameInput = new TextField();
        GridPane.setConstraints(nameInput,1,1);


        //Address Label
        Label addressLabel = new Label("Student address: ");
        GridPane.setConstraints(addressLabel, 0, 2);


        //Address Input
        TextField addressInput = new TextField();
        GridPane.setConstraints(addressInput,1,2);

        //City Label
        Label cityLabel = new Label("Student city: ");
        GridPane.setConstraints(cityLabel, 0, 3);


        //City Input
        TextField cityInput = new TextField();
        GridPane.setConstraints(cityInput,1,3);

        //State Label
        Label stateLabel = new Label("Student State: ");
        GridPane.setConstraints(stateLabel, 0, 4);


        //State Input
        TextField stateInput = new TextField();
        GridPane.setConstraints(stateInput,1,4);

        Button button = new Button("Add Student Record");
        GridPane.setConstraints(button, 1,5);

        button.setOnAction(e-> {
            try{
                StudentItemFile studentItemFile = new StudentItemFile("Students.dat");
                studentItemFile.moveFilePointer(0);
                final int StudentID = isInt(studentIDInput, studentIDInput.getText());
                isEmpty(studentIDInput,nameInput,addressInput, cityInput,stateInput);
                boolean InvalidID = verifyRecordDuplicate(sread, studentItemFile, StudentID);
                if(InvalidID){
                    throw new InvalidID("Student ID duplicate", "This student ID already exists!");
                }
                Student student  = new Student(StudentID, nameInput.getText(), addressInput.getText(), cityInput.getText(), stateInput.getText());
                studentItemFile.writeStudentItem(student);
                studentItemFile.closeFile();
                AlertBox.display("Student record added", "The Student record has been added!");


            }
            catch(NumberFormatException e1){
                AlertBox.display("Invalid Student ID.", "You have entered and invalid student ID.");


            }
            //Catches blank text entries
            catch (EmptyTextField e1){


            }
            catch(IOException e1){
                AlertBox.display("File not found", "File has not been found!");
            }

            catch (InvalidID e1){

            }





        });


        grid.getChildren().addAll(studentIDLabel,studentIDInput,nameLabel,nameInput,addressLabel,addressInput, cityLabel,cityInput, stateLabel, stateInput, button);
        grid.setAlignment(Pos.CENTER);


        Scene scene = new Scene(grid, 600, 400);
        Window.setScene(scene);
        Window.show();





    }


    public static void ViewStudents() throws IOException{
        Student sread = new Student();
        Stage Window = new Stage();
        Window.initModality(Modality.APPLICATION_MODAL);


        Window. setTitle("View Student Record");
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setHgap(10);
        gridPane.setVgap(8);



        Label studentIDLabel = new Label("Student ID: ");
        GridPane.setConstraints(studentIDLabel, 0, 0);


        //Student ID Input
        TextField studentIDInput = new TextField();
        GridPane.setConstraints(studentIDInput,1,0);


        //Name Label
        Label nameLabel = new Label("Student Name: ");
        GridPane.setConstraints(nameLabel, 0, 1);


        //Name Input
        TextField nameInput = new TextField();
        GridPane.setConstraints(nameInput,1,1);


        //Address Label
        Label addressLabel = new Label("Student Address: ");
        GridPane.setConstraints(addressLabel, 0, 2);


        //Address Input
        TextField addressInput = new TextField();
        GridPane.setConstraints(addressInput,1,2);

        //City Label
        Label cityLabel = new Label("Student City: ");
        GridPane.setConstraints(cityLabel, 0, 3);


        //City Input
        TextField cityInput = new TextField();
        GridPane.setConstraints(cityInput,1,3);

        //State Label
        Label stateLabel = new Label("Student State: ");
        GridPane.setConstraints(stateLabel, 0, 4);


        //State Input
        TextField stateInput = new TextField();
        GridPane.setConstraints(stateInput,1,4);

        Button button = new Button("Search");
        GridPane.setConstraints(button, 2,0);



        gridPane.getChildren().addAll(studentIDLabel,studentIDInput,nameLabel,nameInput,addressLabel,addressInput, cityLabel,cityInput, stateLabel, stateInput, button);
        gridPane.setAlignment(Pos.CENTER);

        button.setOnAction(e-> {
            try{
                StudentItemFile studentItemFile = new StudentItemFile("Students.dat");
                studentItemFile.moveFilePointer(0);
                final int StudentID = isInt(studentIDInput, studentIDInput.getText());
                int pointerPosition = verifyRecordExists(sread, studentItemFile, StudentID);
                if(pointerPosition == -1){
                    throw new InvalidID("Student ID does not exist", "This Student ID does not exist");
                }
                studentItemFile.moveFilePointer(pointerPosition);
                Student student = studentItemFile.readStudentItemFile();
                nameInput.setText(student.getName());
                stateInput.setText(student.getState());
                addressInput.setText(student.getAddress());
                cityInput.setText(student.getCity());






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


        Scene scene = new Scene(gridPane, 600, 400);
        Window.setScene(scene);
        Window.show();






    }



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

    public static boolean verifyRecordDuplicate(Student s, StudentItemFile sf,  int studentID) throws IOException{
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




}
