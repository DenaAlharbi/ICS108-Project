package com.example.ics108project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class parent extends Application {
    //borders
    BorderPane borderPane= new BorderPane();
    StackPane welcomePane= new StackPane();
    StackPane errorMessagePane= new StackPane();
    GridPane center = new GridPane();
    //buttons and textfield for the top horizontal
    Label nameLabel = new Label("Name:");
    Label welcomeLabel = new Label("Welcome");
    Label errorLabel = new Label("Error - You have to pick a profile first");
    TextField topText = new TextField();
    Button addButton = new Button("Add");
    Button deleteButton = new Button("Delete");
    Button lookupButton = new Button("Lookup");
    // buttons and textfields for the vertical side
    Button changeStatus = new Button("Change Status");
    Button changePicture = new Button("Change Picture");

    Button addFriend = new Button("Add Friend");
    TextField changePicText = new TextField();
    TextField changeStatusText = new TextField();
    TextField addFriendText= new TextField();

    @Override
    public void start(Stage stage) throws IOException {
        //borders
        center.setStyle("-fx-background-color: #eeecec;");
        //center.setPrefSize(10, 50);
        //center.setMinWidth(0);
        //center.setMinHeight(0);

        center.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);

        nameLabel.setMinWidth(50);
        nameLabel.setMinHeight(100);


        addFriendText.setMinWidth(100);
        changePicText.setMinWidth(100);
        changeStatusText.setMinWidth(100);
        changePicture.setMinWidth(300);
        changeStatus.setMinWidth(300);
        addFriend.setMinWidth(300);
        topText.setMinWidth(200);
        //changeStatusText.setPadding(new Insets(10));

        // setonAction statements
        addButton.setOnAction(new ButtonHandler());
        deleteButton.setOnAction(new ButtonHandler());
        lookupButton.setOnAction(new ButtonHandler());
        changeStatus.setOnAction(new ButtonHandler());
        changePicture.setOnAction(new ButtonHandler());
        addFriend.setOnAction(new ButtonHandler());

        //setting up
        VBox vertical = new VBox(30, new VBox(addFriendText, addFriend), new VBox(changePicText, changePicture), new VBox(changeStatusText, changeStatus));
        vertical.setStyle("-fx-background-color: #dad7d7;");
        vertical.setPrefSize(300, 100);
        vertical.setAlignment(Pos.CENTER);
        vertical.setPadding(new Insets(0, 10, 0, 10));
        HBox horizontal = new HBox(10, nameLabel,topText, addButton, deleteButton, lookupButton);
        horizontal.setAlignment(Pos.CENTER);
        horizontal.setStyle("-fx-background-color: #dad7d7;");
        horizontal.setPrefSize(100, 50);
        vertical.setSpacing(20); //doesnt work


        borderPane.setLeft(vertical);
        borderPane.setTop(horizontal);
        //borderPane.setBottom(center);
        //borderPane.setBottom(anchorPane);
        //BorderPane.setAlignment(anchorPane, Pos.BOTTOM_RIGHT);



        //User userTrial = new User("Dena");
        //profileBase profileBaseTrail = new profileBase(userTrial);


        //
        //profileDefault.setFitWidth(300);
        //profileDefault.setPreserveRatio(true);
        //labelName.setPadding(new Insets(10));
        //labelFriends.setPadding(new Insets(10));

        //profileDefault.setPadding(new Insets(10));
        //statusDefault.setPadding(new Insets(10));
        //StackPane imagePane = new StackPane();
        //imagePane.setPadding(new Insets(10));
        //imagePane.getChildren().add(profileDefault);
        //GridPane.setConstraints(labelName, 0, 0);
        //GridPane.setConstraints(labelFriends, 1, 0);
        //GridPane.setConstraints(profileDefault, 0, 1);
        //GridPane.setConstraints(statusDefault, 0, 2);
       /* center.setVgap(8);
        center.setHgap(10);
        GridPane.setConstraints(nameLabelUpdated, 0, 0);
        GridPane.setConstraints(profileBaseTrail.getLabelFriends(), 1, 0);
        GridPane.setConstraints(profileBaseTrail.getProfileDefault(), 0, 1);
        GridPane.setConstraints(profileBaseTrail.getStatusDefault(), 0, 2);
        nameLabelUpdated.setPadding(new Insets(10));
        profileBaseTrail.getLabelFriends().setPadding(new Insets(10));
        profileBaseTrail.getStatusDefault().setPadding(new Insets(10));
        profileBaseTrail.getProfileDefault().setFitWidth(300);
        profileBaseTrail.getProfileDefault().setPreserveRatio(true);




        center.getChildren().addAll(nameLabelUpdated,profileBaseTrail.getLabelFriends(), profileBaseTrail.getProfileDefault(), profileBaseTrail.getStatusDefault());
        borderPane.setCenter(center);
*/
        center.setVgap(8);
        center.setHgap(10);
        welcomePane.getChildren().add(welcomeLabel);
        center.getChildren().add(welcomePane);
        borderPane.setCenter(center);

        errorMessagePane.getChildren().add(errorLabel);





        Scene scene = new Scene(borderPane,1100, 800);
        stage.setTitle("FaceLite");
        stage.setScene(scene);
        stage.show();
    }
    public class ButtonHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e){
            ArrayList<User> users=new ArrayList<>();

            if (e.getSource() == addButton){
                //System.out.print("The add button was clicked");
                String nameRN =topText.getText();
                User newUser= new User(nameRN);
                profileBase newUserPane=new profileBase(newUser);
                users.add(newUser);
                Label nameLabelUpdated = new Label(newUser.getName());

                GridPane.setConstraints(nameLabelUpdated, 0, 0);
                GridPane.setConstraints(newUserPane.getLabelFriends(), 1, 0);
                GridPane.setConstraints(newUserPane.getProfileDefault(), 0, 1);
                GridPane.setConstraints(newUserPane.getStatusDefault(), 0, 2);
                nameLabelUpdated.setPadding(new Insets(10));
                newUserPane.getLabelFriends().setPadding(new Insets(10));
                newUserPane.getStatusDefault().setPadding(new Insets(10));
                newUserPane.getProfileDefault().setFitWidth(300);
                newUserPane.getProfileDefault().setPreserveRatio(true);
                center.getChildren().clear();

                center.getChildren().addAll(nameLabelUpdated,newUserPane.getLabelFriends(), newUserPane.getProfileDefault(), newUserPane.getStatusDefault());
                borderPane.setCenter(center);
            }
            else if (e.getSource() == deleteButton){
                System.out.print("The delete button was clicked");
            }
            else if (e.getSource()==lookupButton) {
                System.out.println("the lookup button was clicked");
            }
            else if (e.getSource()==changeStatus) {
                //System.out.println("the status button was clicked");

                Pane node = (Pane) center.getChildren();
                if (node instanceof profileBase) {
                    profileBase profile = (profileBase) node;
                    profile.statusDefault= new Label(changeStatusText.getText());
                } else {
                    center.getChildren().clear();
                    center.getChildren().add(errorMessagePane);
                }
            }
            else if(e.getSource()==changePicture ){
                System.out.println("the pic button was clicked");
            }
            else if (e.getSource()==addFriend) {
                System.out.println("the add button was clicked");
            }
        }
    }




    public static void main(String[] args) {
        launch(args);
    }
}