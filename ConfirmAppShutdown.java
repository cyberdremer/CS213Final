package sample;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmAppShutdown {

    static boolean answer;


    public static boolean display(String title, String message){
        Stage window = new Stage();

        // Blocks user interaction with other windows until event is
        // taken care of
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Enrollment Shutdown");
        window.setMinHeight(300);
        window.setMinWidth(300);
        Label l = new Label();
        l.setText(message);





        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        FlowPane flowPane = new FlowPane();
        flowPane.setHgap(25);
        flowPane.setMargin(yesButton, new Insets(20,0,20,20));
        ObservableList List = flowPane.getChildren();
        List.addAll(yesButton,noButton);
        flowPane.setAlignment(Pos.CENTER);

        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });

        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });




        VBox Layout = new VBox();
        Layout.getChildren().addAll(l, flowPane);
        Layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(Layout);
        window.setScene(scene);

        // Forces window to be closed in order to return to main window
        window.showAndWait();


        return answer;
    }

}













