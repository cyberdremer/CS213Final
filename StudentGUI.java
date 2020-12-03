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


    public static GridPane AddStudents() throws IOException {
        Student sread = new Student();

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
                final int StudentID = InputValidation.isInt(studentIDInput, studentIDInput.getText());
                InputValidation.isEmpty(studentIDInput,nameInput,addressInput, cityInput,stateInput);
                boolean InvalidID = InputValidation.verifyRecordDuplicate(sread, studentItemFile, StudentID);
                if(StudentID < 0){
                    throw new InvalidID("Invalid ID entry", "You have entered a negative number!");
                }
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



        return grid;





    }


    public static GridPane ViewStudents() throws IOException{
        Student sread = new Student();
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
                final int StudentID = InputValidation.isInt(studentIDInput, studentIDInput.getText());
                if(StudentID < 0){
                    nameInput.clear();
                    stateInput.clear();
                    addressInput.clear();
                    cityInput.clear();
                    throw new InvalidID("Invalid ID entry", "You have entered a negative number!");
                }
                int pointerPosition = InputValidation.verifyRecordExists(sread, studentItemFile, StudentID);
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


        return gridPane;






    }


    public static GridPane EditStudents() throws IOException{
        int StudentPOS;
        Student sread = new Student();
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

        Button search = new Button("Search");
        GridPane.setConstraints(search, 2,0);

        Button clear = new Button("Clear");
        GridPane.setConstraints(clear,1,5);

        Button update = new Button("Update");
        GridPane.setConstraints(update,2,5);




        gridPane.getChildren().addAll(studentIDLabel,studentIDInput,nameLabel,nameInput,addressLabel,addressInput, cityLabel,cityInput, stateLabel, stateInput, search,update,clear);
        gridPane.setAlignment(Pos.CENTER);

        search.setOnAction(e-> {
            try{
                StudentItemFile studentItemFile = new StudentItemFile("Students.dat");
                studentItemFile.moveFilePointer(0);
                final int StudentID = InputValidation.isInt(studentIDInput, studentIDInput.getText());
                int pointerPosition = InputValidation.verifyRecordExists(sread, studentItemFile, StudentID);

                if(pointerPosition == -1){
                    throw new InvalidID("Student ID does not exist", "This Student ID does not exist");
                }
                studentItemFile.moveFilePointer(pointerPosition);
                Student student = studentItemFile.readStudentItemFile();
                nameInput.setText(student.getName());
                stateInput.setText(student.getState());
                addressInput.setText(student.getAddress());
                cityInput.setText(student.getCity());
                studentIDInput.setDisable(true);








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

        clear.setOnAction( e -> {
            studentIDInput.setDisable(false);
            stateInput.clear();
            studentIDInput.clear();
            nameInput.clear();
            addressInput.clear();
            cityInput.clear();
        });


        update.setOnAction(e->{

            try{
                StudentItemFile studentItemFile = new StudentItemFile("Students.dat");
                Student student;
                studentItemFile.moveFilePointer(0);
                final int StudentID = InputValidation.isInt(studentIDInput, studentIDInput.getText());
                int pointerPosition = InputValidation.verifyRecordExists(sread, studentItemFile, StudentID);
                if(pointerPosition == -1){
                    throw new InvalidID("Student ID does not exist", "This Student ID does not exist");
                }
                InputValidation.isEmpty(nameInput,stateInput,addressInput,cityInput);
                studentItemFile.moveFilePointer(pointerPosition);
                student  = new Student(StudentID, nameInput.getText(), addressInput.getText(), cityInput.getText(), stateInput.getText());
                studentItemFile.writeStudentItem(student);
                studentItemFile.closeFile();
                AlertBox.display("Student record edited", "The Student record has been edited!");


            }

            catch(NumberFormatException e1){
                AlertBox.display("Invalid Student ID.", "You have entered and invalid student ID.");


            }

            catch(IOException e1){
                AlertBox.display("File not found", "File has not been found!");
            }

            catch (InvalidID e1){

            }
            catch(EmptyTextField e1){

            }



        });


        return gridPane;
















    }
}

