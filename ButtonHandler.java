package com.example.ics108project;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ButtonHandler extends parent implements EventHandler<ActionEvent> {
    public void handle(ActionEvent e){
        if (e.getSource() == addButton){
            System.out.print("The add button was clicked");
        }
        else if (e.getSource() == deleteButton){
            System.out.print("The delete button was clicked");
        }
        else if (e.getSource()==lookupButton) {
            System.out.println("the lookup button was clicked");
        }
        else if (e.getSource()==changeStatus) {
            System.out.println("the status button was clicked");
        }
        else if(e.getSource()==changePicture ){
            //center.getChildren().remove();
            System.out.println("the pic button was clicked");
        }
        else if (e.getSource()==addFriend) {
            System.out.println("the add button was clicked");
        }
    }
}
