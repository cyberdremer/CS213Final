package sample;

public class InvalidID extends Exception {
    InvalidID(String message, String message2){
        AlertBox.display(message, message2);

    }
}
