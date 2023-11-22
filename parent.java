package com.example.ics108project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
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
    ArrayList<profileBase> users=new ArrayList<profileBase>();
    //borders
    BorderPane borderPane= new BorderPane();
    StackPane welcomePane= new StackPane();
    StackPane userRemovedPane= new StackPane();
    StackPane errorMessagePane= new StackPane();
    StackPane PicFrame= new StackPane();

    GridPane center = new GridPane();
    //buttons and textfield for the top horizontal
    Label nameLabel = new Label("Name:");
    Label welcomeLabel = new Label("Welcome");
    Label userRemovedLabel = new Label("The user has been removed");

    Label errorLabel = new Label("Error - You have to pick a profile first");
    Label updateLabel = new Label();
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
        public void handle(ActionEvent e) {
            if (e.getSource() == addButton) {
                String nameRN = topText.getText();
                profileBase newUserBase = new profileBase(nameRN);
                users.add(newUserBase);
                Label nameLabelUpdated = newUserBase.getNameLabel();
                PicFrame.getChildren().add(newUserBase.getProfileDefault());
                GridPane.setConstraints(nameLabelUpdated, 0, 0);
                GridPane.setConstraints(newUserBase.getLabelFriends(), 1, 0);
                GridPane.setConstraints(PicFrame, 0, 1);
                GridPane.setConstraints(newUserBase.getStatusDefault(), 0, 2);
                nameLabelUpdated.setPadding(new Insets(10));
                newUserBase.getLabelFriends().setPadding(new Insets(10));
                newUserBase.getStatusDefault().setPadding(new Insets(10));
                newUserBase.getProfileDefault().setFitWidth(300);
                newUserBase.getProfileDefault().setPreserveRatio(true);
                updateLabel = new Label("A new user has been added");
                //GridPane.setHalignment(updateLabel, HPos.CENTER);
                //GridPane.setValignment(updateLabel, VPos.BOTTOM);

                center.getChildren().clear();
                center.add(updateLabel,0, Integer.MAX_VALUE);

                center.getChildren().addAll(nameLabelUpdated, newUserBase.getLabelFriends(), newUserBase.getProfileDefault(), newUserBase.getStatusDefault());
                //center.getChildren().add(newUserPane);
                borderPane.setCenter(center);
            }
            else if (e.getSource() == deleteButton) {
                //System.out.print("The delete button was clicked");
                String userNOW = topText.getText();
                System.out.println(users);

                for (profileBase user : users) {

                    if (Objects.equals(user.getNameForButton(), userNOW)){
                        users.remove(user);
                        center.getChildren().clear();
                        center.getChildren().add(userRemovedPane);
                    }
                    System.out.println(users);
                }
            }
            else if (e.getSource() == lookupButton) {
                String userNOW = topText.getText();
                for (profileBase user : users) {
                    if (Objects.equals(user.getNameForButton(), userNOW)) {
                        PicFrame.getChildren().clear();
                        PicFrame.getChildren().add(user.getProfileDefault());
                        GridPane.setConstraints(user.getNameLabel(), 0, 0);
                        GridPane.setConstraints(user.getLabelFriends(), 1, 0);
                        GridPane.setConstraints(PicFrame, 0, 1);
                        GridPane.setConstraints(user.getStatusDefault(), 0, 2);
                        user.getNameLabel().setPadding(new Insets(10));
                        user.getLabelFriends().setPadding(new Insets(10));
                        user.getStatusDefault().setPadding(new Insets(10));
                        user.getProfileDefault().setFitWidth(300);
                        user.getProfileDefault().setPreserveRatio(true);
                        center.getChildren().clear();

                        center.getChildren().addAll(user.getNameLabel(), user.getLabelFriends(), user.getProfileDefault(), user.getStatusDefault());
                        borderPane.setCenter(center);
                    }
                }
            }
            else if(e.getSource()==changeStatus){
                String userNOW = topText.getText();
                for (profileBase user : users) {
                    if (Objects.equals(user.getNameForButton(), userNOW)) {

                        user.statusDefault = new Label(changeStatusText.getText());
                        PicFrame.getChildren().clear();
                        PicFrame.getChildren().add(user.getProfileDefault());
                        GridPane.setConstraints(user.getNameLabel(), 0, 0);
                        GridPane.setConstraints(user.getLabelFriends(), 1, 0);
                        GridPane.setConstraints(PicFrame, 0, 1);
                        GridPane.setConstraints(user.getStatusDefault(), 0, 2);
                        user.getNameLabel().setPadding(new Insets(10));
                        user.getLabelFriends().setPadding(new Insets(10));
                        user.getStatusDefault().setPadding(new Insets(10));
                        user.getProfileDefault().setFitWidth(300);
                        user.getProfileDefault().setPreserveRatio(true);
                        center.getChildren().clear();

                        center.getChildren().addAll(user.getNameLabel(), user.getLabelFriends(), user.getProfileDefault(), user.getStatusDefault());
                        borderPane.setCenter(center);
                    }
                }
            }
            else if(e.getSource()==changePicture ){
                String userNOW = topText.getText();
                //ImageView profileDefault = new ImageView(new Image("C:\\Users\\denaa\\JavaProjects231\\ICS108-project\\src\\main\\java\\com\\example\\ics108project\\defaultPIC.png"));
                for (profileBase user : users) {
                    if (Objects.equals(user.getNameForButton(), userNOW)) {
                        user.profileDefault = new ImageView(new Image(changePicText.getText()));
                        PicFrame.getChildren().clear();
                        PicFrame.getChildren().add(user.getProfileDefault());
                        GridPane.setConstraints(user.getNameLabel(), 0, 0);
                        GridPane.setConstraints(user.getLabelFriends(), 1, 0);
                        GridPane.setConstraints(PicFrame, 0, 1);
                        GridPane.setConstraints(user.getStatusDefault(), 0, 2);
                        user.getNameLabel().setPadding(new Insets(10));
                        user.getLabelFriends().setPadding(new Insets(10));
                        user.getStatusDefault().setPadding(new Insets(10));
                        user.getProfileDefault().setFitWidth(300);
                        user.getProfileDefault().setPreserveRatio(true);
                        center.getChildren().clear();
                        center.getChildren().addAll(user.getNameLabel(), user.getLabelFriends(), user.getProfileDefault(), user.getStatusDefault());
                        borderPane.setCenter(center);
                    }
                }
            }
            else if(e.getSource()==addFriend){

            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}