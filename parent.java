package com.example.ics108project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.BorderPane.*;

public class parent extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //borders
        BorderPane borderPane= new BorderPane();
        Pane center = new Pane();
        //center.setStyle("-fx-background-color: #8c2b2b;");
        //center.setPrefSize(10, 50);

        //buttons and textfield for the top horizontal
        Label nameLabel = new Label("Name:");
        TextField topText = new TextField();
        Button AddButtonHorizontal = new Button("Add");
        Button DeleteButtonHorizontal = new Button("Delete");
        Button lookupButton = new Button("Lookup");

        // buttons and textfields for the vertical side
        Button changeStatus = new Button("Change Status");
        Button changePicture = new Button("Change Picture");
        Button addFriendVertical = new Button("Add Friend");
        TextField textFieldChangePic = new TextField();
        TextField textFieldChangeStatus = new TextField();
        TextField textFieldAddFriend= new TextField();
        textFieldAddFriend.setMinWidth(100);
        textFieldChangePic.setMinWidth(100);
        textFieldChangeStatus.setMinWidth(100);
        changePicture.setMinWidth(300);
        changeStatus.setMinWidth(300);
        addFriendVertical.setMinWidth(300);

        //event handlers for the buttons - should be classes
        addFriendVertical.setOnAction(event -> {
            String text = textFieldAddFriend.getText();
        });
        changePicture.setOnAction(event -> {
            String text = textFieldChangePic.getText();
        });
        changeStatus.setOnAction(event -> {
            String text = textFieldChangeStatus.getText();
        });



        //setting up
        VBox vertical = new VBox(30, new VBox(textFieldAddFriend, addFriendVertical), new VBox(textFieldChangePic, changePicture), new VBox(textFieldChangeStatus, changeStatus));
        vertical.setStyle("-fx-background-color: #dad7d7;");
        vertical.setPrefSize(300, 100);
        vertical.setAlignment(Pos.CENTER);
        vertical.setPadding(new Insets(0, 10, 0, 10));
        HBox horizontal = new HBox(10, nameLabel,topText, AddButtonHorizontal, DeleteButtonHorizontal, lookupButton);
        horizontal.setAlignment(Pos.CENTER);
        horizontal.setStyle("-fx-background-color: #dad7d7;");
        horizontal.setPrefSize(100, 50);


        borderPane.setLeft(vertical);
        borderPane.setTop(horizontal);
        //borderPane.setBottom(center);
        //setAlignment(center, Pos.BOTTOM_RIGHT);



        Scene scene = new Scene(borderPane,1100, 800);
        stage.setTitle("FaceLite");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}