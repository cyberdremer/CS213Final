package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
    public static void display(String title, String message){
        Stage window = new Stage();

        // Blocks user interaction with other windows until event is
        // taken care of
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinHeight(300);
        window.setMinWidth(300);


        Label l = new Label();
        l.setText(message);
        Button closeButton = new Button("Click me to close window");
        closeButton.setOnAction(e -> window.close());

        VBox Layout = new VBox();
        Layout.getChildren().addAll(l, closeButton);
        Layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(Layout);
        window.setScene(scene);

        // Forces window to be closed in order to return to main window
        window.showAndWait();


    }

}













