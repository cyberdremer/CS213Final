package sample;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;


import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class ReportGUI {

    public static BorderPane viewReports(){
        BorderPane borderPane = new BorderPane();
        Label courseNumberLabel = new Label("Course Number:");
        TextField courseNumberInput = new TextField();
        courseNumberInput.setMaxWidth(50);
        courseNumberInput.setPromptText("Course Number");


        Label yearLabel = new Label("Year");
        TextField yearInput = new TextField();
        yearInput.setPromptText("Year");
        yearInput.setMaxWidth(50);
        Button generateReport = new Button("Generate Report");


        // Used to initialize choicebox menu for selecting semester
        ChoiceBox<String> semesterChoiceBox = new ChoiceBox<>();
        semesterChoiceBox.getItems().addAll("FALL", "WINTER", "SPRING", "SUMMER");
        semesterChoiceBox.getSelectionModel().select("FALL");




        //Used for placing buttons at top of this scene
        FlowPane topPane = new FlowPane();
        topPane.setHgap(20);
        topPane.setMargin(courseNumberInput, new Insets(20,0,20,20));
        ObservableList topMenu = topPane.getChildren();
        topMenu.addAll(courseNumberLabel, courseNumberInput, semesterChoiceBox, yearLabel, yearInput, generateReport);
        BorderPane.setMargin(topPane, new Insets(10,10,20,10));
        BorderPane.setAlignment(topPane, Pos.CENTER);
        borderPane.setTop(topPane);


        TextArea reportsArea = new TextArea();
        BorderPane.setMargin(reportsArea, new Insets(20,20,10,10));
        borderPane.setCenter(reportsArea);





        generateReport.setOnAction(e->{
            try{
                EnrollmentFile eFile = new EnrollmentFile("Enrollments.txt");
                Enrollment eread = new Enrollment();


                int courseNumber = InputValidation.isInt(courseNumberInput,courseNumberInput.getText());
                int year = InputValidation.isInt(yearInput, yearInput.getText());
                boolean validYear = InputValidation.ValidateYear(year);
                String semester = InputValidation.getChoice(semesterChoiceBox);
                if(!validYear){
                    throw new InvalidID("Invalid Year", "You have entered an invalid year!");
                }
                int filePointerPOS = InputValidation.verifyRecordExists(eread, eFile, courseNumber);
                if(filePointerPOS == -1){
                    throw new InvalidID("Course ID Invalid", "Course Number entered does not exist!");
                }

                eFile.moveFilePointer(filePointerPOS);
                eread = eFile.readCourseEnrollmentFile();
                StringBuilder Report = new StringBuilder();
                Report.append(eread.getCourseNumber() + " " + eread.getCourseName() + " Report" + "\n-----------------");
                for(int loopIndex = filePointerPOS; loopIndex < eFile.getNumberOfRecords(); loopIndex++){
                    eFile.moveFilePointer(loopIndex);
                    eread = eFile.readCourseEnrollmentFile();
                    if( semester.equals(eread.getSemester()) && year == eread.getYear() && courseNumber == eread.getCourseID()){
                        Report.append("\n" + eread.getStudentName() + "  " + eread.getGrade());
                    }
                }
                String finalReport = Report.toString();

                reportsArea.setText(finalReport);

            }
            catch (NumberFormatException e1){
                AlertBox.display("Invalid ID", "You have entered and Invalid ID");
            }
            catch(InvalidID e1){


            }
            catch(IOException e1){

            }



        });







        return  borderPane;
    }
}
