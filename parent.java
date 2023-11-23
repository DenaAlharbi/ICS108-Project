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
    ArrayList<profileBase> users = new ArrayList<profileBase>();
    //borders
    BorderPane borderPane = new BorderPane();
    StackPane welcomePane = new StackPane();
    HBox updateLabelPane = new HBox();
    VBox friendsVbox = new VBox();
    StackPane userRemovedPane = new StackPane();
    StackPane errorMessagePane = new StackPane();
    StackPane PicFrame = new StackPane();

    GridPane center = new GridPane();
    Label welcomeLabel = new Label("Welcome");
    Label userRemovedLabel = new Label("The user has been removed");

    Label errorLabel = new Label("Error - You have to pick a profile first");
    //buttons and textfield for the top horizontal
    Label nameLabel = new Label("Name:");

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
    TextField addFriendText = new TextField();
    int IDnum = 0;

    @Override
    public void start(Stage stage) throws IOException {
        //borders
        center.setStyle("-fx-background-color: #eeecec;");
        center.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);

        //Labels
        nameLabel.setMinWidth(50);
        nameLabel.setMinHeight(100);

        //Text fields
        addFriendText.setMinWidth(100);
        changePicText.setMinWidth(100);
        changeStatusText.setMinWidth(100);
        changePicture.setMinWidth(300);
        changeStatus.setMinWidth(300);
        addFriend.setMinWidth(300);
        topText.setMinWidth(200);

        // setonAction statements
        addButton.setOnAction(new ButtonHandler());
        deleteButton.setOnAction(new ButtonHandler());
        lookupButton.setOnAction(new ButtonHandler());
        changeStatus.setOnAction(new ButtonHandler());
        changePicture.setOnAction(new ButtonHandler());
        addFriend.setOnAction(new ButtonHandler());

        //Temporary panes
        userRemovedPane.getChildren().add(userRemovedLabel);
        errorMessagePane.getChildren().add(errorLabel);
        welcomePane.getChildren().add(welcomeLabel);

        //setting up
        VBox vertical = new VBox(30, new VBox(addFriendText, addFriend), new VBox(changePicText, changePicture), new VBox(changeStatusText, changeStatus));
        vertical.setStyle("-fx-background-color: #dad7d7;");
        vertical.setSpacing(20); //doesnt work
        vertical.setPrefSize(300, 100);
        vertical.setAlignment(Pos.CENTER);
        vertical.setPadding(new Insets(0, 10, 0, 10));

        HBox horizontal = new HBox(10, nameLabel, topText, addButton, deleteButton, lookupButton);
        horizontal.setAlignment(Pos.CENTER);
        horizontal.setStyle("-fx-background-color: #dad7d7;");
        horizontal.setPrefSize(100, 50);

        friendsVbox.setStyle("-fx-background-color: #ad4242;");
        friendsVbox.setPrefSize(50, 50);
        friendsVbox.setPadding(new Insets(0, 10, 0, 10));

        center.setVgap(8);
        center.setHgap(10);
        center.getChildren().add(welcomePane);
        borderPane.setLeft(vertical);
        borderPane.setTop(horizontal);
        borderPane.setCenter(center);


        Scene scene = new Scene(borderPane, 1100, 800);
        stage.setTitle("FaceLite");
        stage.setScene(scene);
        stage.show();
    }

    public class ButtonHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            String userNOW = topText.getText();


            if (e.getSource() == addButton) {
                String nameRN = topText.getText();
                for (profileBase userAddress : users) {
                    if (!users.contains(userAddress)) {
                        profileBase newUserBase = new profileBase(nameRN);
                        users.add(newUserBase);
                        IDnum++;
                        newUserBase.getId(IDnum);
                        Label nameLabelUpdated = newUserBase.getNameLabel();
                        PicFrame.getChildren().add(newUserBase.getProfileDefault());
                        PicFrame.setPrefWidth(300);
                        PicFrame.getChildren().add(newUserBase.getProfileDefault());

                        GridPane.setConstraints(nameLabelUpdated, 0, 0);
                        GridPane.setConstraints(newUserBase.getLabelFriends(), 1, 0);
                        GridPane.setConstraints(PicFrame, 0, 1);
                        GridPane.setConstraints(newUserBase.getStatusDefault(), 0, 2);
                        GridPane.setConstraints(friendsVbox, 1, 1);
                        nameLabelUpdated.setPadding(new Insets(10));
                        newUserBase.getLabelFriends().setPadding(new Insets(10));
                        newUserBase.getStatusDefault().setPadding(new Insets(10));
                        newUserBase.getProfileDefault().setFitWidth(300);
                        newUserBase.getProfileDefault().setPreserveRatio(true);
                        friendsVbox.getChildren().clear();
                        //updateLabel = new Label("A new user has been added");
                        //updateLabelPane.getChildren().add(updateLabel);
                        //GridPane.setHalignment(updateLabelPane, HPos.CENTER);
                        //GridPane.setValignment(updateLabelPane, VPos.BOTTOM);

                        center.getChildren().clear();
                        //center.getChildren().add(updateLabelPane, 0, center.getRowCount());


                        center.getChildren().addAll(friendsVbox, nameLabelUpdated, newUserBase.getLabelFriends(), PicFrame, newUserBase.getStatusDefault());
                        //center.getChildren().add(newUserPane);
                        borderPane.setCenter(center);
                    }
                    else{
                        center.getChildren().clear();
                        errorLabel = new Label("The user already exists- enter the lookup button...");
                        center.getChildren().add(errorMessagePane);
                    }
                }
            } else if (e.getSource() == deleteButton) {
                for (profileBase user : users) {

                    if (Objects.equals(user.getNameForButton(), userNOW)) {
                        users.remove(user);
                        center.getChildren().clear();
                        center.getChildren().add(userRemovedPane);
                    } else if (!users.contains(user)) {
                        center.getChildren().clear();
                        errorLabel = new Label("The user doesnt exists- enter the add button to add the profile...");
                        center.getChildren().add(errorMessagePane);
                    }
                }
            } else if (e.getSource() == lookupButton) {
                for (profileBase user : users) {
                    if (Objects.equals(user.getNameForButton(), userNOW)) {
                        display(user);
                    }
                    else if (!users.contains(user)) {
                        center.getChildren().clear();
                        errorLabel = new Label("The user doesnt exists- enter the add button to add the profile...");
                        center.getChildren().add(errorMessagePane);
                    }
                }
            } else if (e.getSource() == changeStatus) {

                for (profileBase user : users) {
                    if (Objects.equals(user.getNameForButton(), userNOW)) {
                        user.statusDefault = new Label(changeStatusText.getText());
                        display(user);
                    } else if (!users.contains(user)) {
                        center.getChildren().clear();
                        errorLabel = new Label("The user doesnt exists- enter the add button to add the profile...");
                        center.getChildren().add(errorMessagePane);
                    } else if (userNOW.isEmpty()) {
                        center.getChildren().clear();
                        errorLabel = new Label("Please pick a profile");
                        center.getChildren().add(errorMessagePane);
                    }
                }
            } else if (e.getSource() == changePicture) {

                for (profileBase user : users) {
                    if (Objects.equals(user.getNameForButton(), userNOW)) {
                        user.profileDefault = new ImageView(new Image(changePicText.getText()));
                        display(user);
                    }else if (userNOW.isEmpty()) {
                        center.getChildren().clear();
                        errorLabel = new Label("Please pick a profile");
                        center.getChildren().add(errorMessagePane);
                    }
                }
            } else if (e.getSource() == addFriend) {

                for (profileBase user : users) {

                    if (Objects.equals(user.getNameForButton(), userNOW)) {
                        for (profileBase userADD : users) {
                            if (Objects.equals(userADD.getNameForButton(), addFriendText.getText())) {
                                user.getMyFriends().add(userADD);
                                userADD.getMyFriends().add(user);
                                System.out.print(user.getMyFriends());
                                System.out.print(userADD.getMyFriends());
                            } //we need a condition here for what if the user that we want to add doesnt exist
                        }
                    }
                    if (Objects.equals(user.getNameForButton(), userNOW)) {
                        display(user);
                    }
                }

            }

        }

        public void display(profileBase user) {
            PicFrame.getChildren().clear();
            PicFrame.getChildren().add(user.getProfileDefault());
            GridPane.setConstraints(PicFrame, 0, 1);
            user.getProfileDefault().setFitWidth(300);
            user.getProfileDefault().setPreserveRatio(true);

            GridPane.setConstraints(user.getNameLabel(), 0, 0);
            GridPane.setConstraints(user.getLabelFriends(), 1, 0);
            GridPane.setConstraints(user.getStatusDefault(), 0, 2);

            user.getNameLabel().setPadding(new Insets(10));
            user.getLabelFriends().setPadding(new Insets(10));
            user.getStatusDefault().setPadding(new Insets(10));

            GridPane.setConstraints(friendsVbox, 1, 1);
            friendsVbox.getChildren().clear();
            for (profileBase userAddress : user.getMyFriends()) {
                if (users.contains(userAddress)) {
                    Label label = new Label(userAddress.getNameForButton());
                    friendsVbox.getChildren().add(label);
                    center.getChildren().clear();
                    center.getChildren().addAll(friendsVbox, user.getNameLabel(), user.getLabelFriends(), PicFrame, user.getStatusDefault());
                    borderPane.setCenter(center);
                } else {
                    center.getChildren().clear();
                    center.getChildren().add(errorMessagePane);
                }

            }
        }


        public static void main(String[] args) {
            launch(args);
        }
    }
}