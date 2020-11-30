package sample;

import javafx.scene.control.Alert;

public class EmptyTextField extends Exception{
    EmptyTextField(){
        AlertBox.display("Empty Input", "Empty entries are not allowed");
    }
}
