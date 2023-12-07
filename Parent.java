package com.example.project1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class parent extends Application {
    static String FILE_NAME = "C:\\Users\\denaa\\OneDrive\\Desktop\\IdeaProjects\\Project1\\src\\main\\java\\com\\example\\project1\\data.txt";
    ArrayList<profileBase> users = new ArrayList<>();
    ArrayList<String[]> Retriever = new ArrayList<>();
    String topNOW = "", addFriendNOW = "", changePICNOWW = "", changestatusNOW = "";


    //borders
    BorderPane borderPane = new BorderPane();

    StackPane stackPane = new StackPane();
    HBox errorMessagePane = new HBox();

    StackPane welcomePane = new StackPane();
    Pane updateLabelPane = new Pane();
    VBox friendsVbox = new VBox();
    StackPane userRemovedPane = new StackPane();
    StackPane PicFrame = new StackPane();
    GridPane center = new GridPane();
    Text welcomeText = new Text("Thanks for joining FaceLite!\n We hope you enjoy your experience.");
    Text userRemovedLabel = new Text("The user has been removed");

    Label errorLabel = new Label("Error - You have to pick a profile first");
    //buttons and textfield for the top horizontal
    Label nameLabel = new Label("Name:");
    Label NoimageLabel = new Label("No Image");


    Text updateText = new Text();
    TextField topText = new TextField();
    TextField hostEvent = new TextField();
    TextField nameEvent = new TextField();
    TextField friendEvent = new TextField();
    Button addFriendEvent = new Button("Add Friend to event");


    Button addButton = new Button("Add");
    Button chat = new Button("Ask AI");

    Button deleteButton = new Button("Delete");
    Button lookupButton = new Button("Lookup");
    Button DismissButton = new Button("X");
    // buttons and textfields for the vertical side
    Button changeStatus = new Button("Change Status");
    Button changePicture = new Button("Change Picture");

    Button addFriend = new Button("Add Friend");
    Button mainPage = new Button("Main Page");

    Button images = new Button("Image Gallery");
    Button createGroup = new Button("Create a group");

    TextField changePicText = new TextField();
    TextField changeStatusText = new TextField();
    TextField addFriendText = new TextField();
    ImageView imageWelcome = new ImageView(new Image("WelcomePic.png"));
    Text date = new Text();
    Text numOfAccountss = new Text();

    //int IDnum = 0;
    HBox horizontal = new HBox(10, nameLabel, topText, addButton, deleteButton, lookupButton);
    VBox vertical = new VBox(30, mainPage, createGroup, images, new VBox(addFriendText, addFriend), new VBox(changePicText, changePicture), new VBox(changeStatusText, changeStatus));


    @Override
    public void start(Stage stage) throws IOException {
        //array list of users and their data

        int numOfAccounts = 0;
        try (Scanner input = new Scanner(new File(FILE_NAME))) {
            while (input.hasNextLine()) {
                String newString = input.nextLine();
                if (!newString.isEmpty()) {
                    String[] arr = newString.split("@");
                    Retriever.add(arr);
                }
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.err.format("File not found: %s%n", FILE_NAME);
        }
        for (String[] strings : Retriever) {
            profileBase newUserBase = new profileBase(strings[0]);
            numOfAccounts++;
            users.add(newUserBase);
            if (!strings[1].equals("No-Image")) {
                newUserBase.getProfileDefault(strings[1]);
                newUserBase.getPicPath(strings[1]);
            }
            if (!strings[2].equals("No-current-status")) {
                newUserBase.getStatusDefault(strings[2]);
                newUserBase.getFinStatus(strings[2]);
            }
            if (!strings[3].equals("No-Friends")) {
                String in = strings[3];
                String[] sub = in.substring(1, in.length() - 1).split(",");
                ArrayList<String> list = new ArrayList<>(Arrays.asList(sub));
                newUserBase.getMyFriends(list);
                newUserBase.getMyFriendsupdateRR(strings[3]);
            }
        }
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
        images.setOnAction(new ButtonHandler());
        deleteButton.setOnAction(new ButtonHandler());
        lookupButton.setOnAction(new ButtonHandler());
        changeStatus.setOnAction(new ButtonHandler());
        changePicture.setOnAction(new ButtonHandler());
        addFriend.setOnAction(new ButtonHandler());
        DismissButton.setOnAction(new ButtonHandler());
        createGroup.setOnAction(new ButtonHandler());
        DismissButton.setPadding(new Insets(10));
        addFriendEvent.setOnAction(new ButtonHandler());
        chat.setOnAction(new ButtonHandler());
        mainPage.setOnAction(new ButtonHandler());

        //Temporary panes
        userRemovedLabel.setStyle("-fx-font-size: 24; -fx-fill: white;");
        StackPane.setAlignment(userRemovedLabel, Pos.CENTER);


        // Set the text of the labels
        date.setText("The date today is: " + LocalDate.now().toString());
        date.setStyle("-fx-font-size: 24; -fx-fill: white;");
        numOfAccountss.setText("The Number Of Accounts is: " + numOfAccounts);
        numOfAccountss.setStyle("-fx-font-size: 24; -fx-fill: white;");
        center.getChildren().clear();
        center.add(date, 0, 0);
        center.add(numOfAccountss, 0, 1);

        GridPane.setHalignment(date, HPos.LEFT);

        //setting up
        vertical.setStyle("-fx-background-color: #bdd2dc;-fx-background-radius: 4;");
        vertical.setSpacing(20); //doesnt work
        vertical.setPrefSize(300, 100);
        vertical.setAlignment(Pos.CENTER);
        vertical.setPadding(new Insets(0, 10, 0, 10));

        horizontal.setAlignment(Pos.CENTER);
        horizontal.setStyle("-fx-background-color: #bdd2dc;-fx-background-radius: 4;");
        horizontal.setPrefSize(100, 100);

        friendsVbox.setPrefSize(300, 200);
        friendsVbox.setPadding(new Insets(0, 10, 0, 10));

        center.setStyle("-fx-background-color: #062746;-fx-background-radius: 4;");
        center.setVgap(8);
        center.setHgap(10);
        center.setPadding(new Insets(10));
        borderPane.setTop(horizontal);
        stackPane.getChildren().add(center);
        borderPane.setCenter(stackPane);

        //making the pane scrollable
        /*ScrollPane scrollPane = new ScrollPane(//wrtire the name of the border);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);*/


        Scene scene = new Scene(borderPane, 1100, 800);
        stage.setTitle("FaceLite");
        stage.setScene(scene);
        stage.show();
    }

    public class ButtonHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {

            if (!topText.getText().equals("")) {
                topNOW = topText.getText();
                topText.clear();
            }
            if (!addFriendText.getText().equals("")) {
                addFriendNOW = addFriendText.getText();
                addFriendText.clear();
            }
            if (!changePicText.getText().equals("")) {
                changePICNOWW = changePicText.getText();
                changePicText.clear();
            }
            if (!changeStatusText.getText().equals("")) {
                changestatusNOW = changeStatusText.getText();
                changeStatusText.clear();
            }

            boolean checker;


            if (e.getSource() == addButton) {
                checker = true;

                if (!topNOW.isEmpty()) {
                    for (profileBase userAddress : users) {
                        if (Objects.equals(userAddress.getNameForButton(), topNOW)) {
                            errorLabel = new Label("THIS USER ALREADY EXISTS!");
                            ErrorPane(errorLabel);
                            checker = false;
                            break;
                        }

                    }
                    if (checker) {


                        profileBase newUserBase = new profileBase(topNOW);
                        users.add(newUserBase);
                        Label nameLabelUpdated = newUserBase.getNameLabel();
                        PicFrame.getChildren().clear();
                        PicFrame.setPrefWidth(300);
                        PicFrame.setPrefHeight(300);
                        PicFrame.setStyle("-fx-border-color: black;");
                        PicFrame.getChildren().add(NoimageLabel);

                        GridPane.setConstraints(nameLabelUpdated, 0, 0);
                        GridPane.setConstraints(newUserBase.getLabelFriends(), 1, 0);
                        GridPane.setConstraints(PicFrame, 0, 1);
                        GridPane.setConstraints(newUserBase.getStatusDefault(), 0, 2);
                        GridPane.setConstraints(friendsVbox, 1, 1);
                        GridPane.setConstraints(updateText, 0, 3);
                        nameLabelUpdated.setPadding(new Insets(10));
                        newUserBase.getLabelFriends().setPadding(new Insets(10));
                        newUserBase.getStatusDefault().setPadding(new Insets(10));
                        //newUserBase.getProfileDefault().setFitWidth(300);
                        //newUserBase.getProfileDefault().setPreserveRatio(true);
                        friendsVbox.getChildren().clear();
                        //updateText = new Text("A new user has been added");
                        //updateLabelPane.getChildren().add(updateText);
                        //StackPane.setAlignment(updateLabelPane, Pos.BOTTOM_CENTER);
                        //StackPane.setMargin(updateLabelPane, new Insets(0, 0, 10, 0));
                        center.setStyle("-fx-background-image: url('/profil3.jpg');-fx-background-size: cover;-fx-background-position: center;");


                        center.getChildren().clear();

                        //center.getChildren().add(updateLabelPane, 0, center.getRowCount());
                        //stackPane.getChildren().clear();
                        //stackPane.getChildren().addAll(center,updateLabelPane);
                        center.getChildren().addAll(friendsVbox, nameLabelUpdated, newUserBase.getLabelFriends(), PicFrame, newUserBase.getStatusDefault());
                        //center.getChildren().add(newUserPane);
                        //borderPane.setCenter(stackPane);
                        borderPane.setLeft(vertical);

                        borderPane.setCenter(center);

                    } else if (topNOW.isEmpty()) {
                        errorLabel = new Label("ENTER A PROFILE NAME FIRST!");
                        ErrorPane(errorLabel);

                    }
                }


            } else if (e.getSource() == deleteButton) {
                for (profileBase user : users) {

                    if (Objects.equals(user.getNameForButton(), topNOW)) {
                        users.remove(user);
                        center.getChildren().clear();
                        center.getChildren().add(userRemovedLabel); //edit the pane
                    } else if (!users.contains(user)) {
                        center.getChildren().clear();
                        errorLabel = new Label("THIS USER DOES NOT EXIST!");
                        ErrorPane(errorLabel);
                    } else if (topNOW.isEmpty()) {
                        errorLabel = new Label("ENTER A NAME FIRST!");
                        ErrorPane(errorLabel);

                    }
                }
            } else if (e.getSource() == lookupButton) {
                checker = false;
                if (topNOW.isEmpty()) {
                    errorLabel = new Label("ENTER A NAME FIRST!");
                    ErrorPane(errorLabel);
                } else {
                    for (profileBase user : users) {
                        if (Objects.equals(user.getNameForButton(), topNOW)) {
                            checker = true;
                            break;
                        }
                    }
                }
                if (checker) {
                    for (profileBase user : users) {
                        if (Objects.equals(user.getNameForButton(), topNOW)) {
                            display(user);
                        }
                    }
                } else if (!checker) {
                    errorLabel = new Label("THE USER DOES NOT EXIST!");
                    ErrorPane(errorLabel);
                }


            } else if (e.getSource() == changeStatus) {
                checker = false;
                if (topNOW.isEmpty()) {
                    errorLabel = new Label("ENTER A NAME FIRST!");
                    ErrorPane(errorLabel);
                } else if (changestatusNOW.equals("")) {
                    errorLabel = new Label("ENTER A STATUS FIRST!");
                    ErrorPane(errorLabel);
                } else {
                    for (profileBase user : users) {
                        if (Objects.equals(user.getNameForButton(), topNOW)) {
                            checker = true;
                            break;
                        }
                    }
                }
                if (checker) {
                    for (profileBase user : users) {
                        if (Objects.equals(user.getNameForButton(), topNOW)) {
                            user.statusDefault = new Label(changestatusNOW);
                            user.getFinStatus(changestatusNOW);
                            display(user);
                        }
                    }
                } else if (!checker) {
                    errorLabel = new Label("Status can not be empty!");
                    ErrorPane(errorLabel);
                }
            } else if (e.getSource() == changePicture) {
                // WE NEED A CONDITION FOR IF THE PIC DOES NOT EXIST
                checker = false;
                if (topNOW.isEmpty()) {
                    errorLabel = new Label("ENTER A VALID PROFILE NAME FIRST!");
                    ErrorPane(errorLabel);
                } else if (changePICNOWW.equals("")) {
                    errorLabel = new Label("ENTER A PICTURE PATH!");
                    ErrorPane(errorLabel);
                } else {
                    for (profileBase user : users) {
                        if (Objects.equals(user.getNameForButton(), topNOW)) {
                            checker = true;
                            break;
                        }
                    }
                    if (!checker) {
                        errorLabel = new Label("THE USER DOES NOT EXIST!");
                        ErrorPane(errorLabel);
                    }
                }
                if (checker) {
                    for (profileBase user : users) {
                        if (Objects.equals(user.getNameForButton(), topNOW)) {
                            user.profileDefault = new ImageView(new Image(changePICNOWW));
                            user.getPicPath(changePICNOWW);
                            display(user);
                        }
                    }
                }


            } else if (e.getSource() == addFriend) {
                checker = false;

                if (topNOW.equals("")) {
                    errorLabel = new Label("ENTER A NAME FIRST!");
                    ErrorPane(errorLabel);
                } else if (addFriendNOW.equals("")) {
                    errorLabel = new Label("ENTER A NAME FIRST!");
                    ErrorPane(errorLabel);


                }
                if (!checker) {
                    for (profileBase user : users) {
                        if (Objects.equals(user.getNameForButton(), topNOW)) {
                            checker = true;
                            break;
                        }
                    }
                    if (!checker) {
                        errorLabel = new Label("ENTER A VALID PROFILE NAME FIRST!");
                        ErrorPane(errorLabel);
                    }
                }
                if (checker) {
                    checker = false;

                    for (profileBase user : users) {
                        if (Objects.equals(user.getNameForButton(), addFriendNOW)) {
                            checker = true;
                            break;
                        }
                    }
                    if (!checker) {
                        errorLabel = new Label("THE USER YOU WANT TO ADD DOES NOT EXIST!");
                        ErrorPane(errorLabel);
                    }
                }
                if (checker) {
                    checker = false;

                    if (!topNOW.equals(addFriendNOW)) {
                        checker = true;
                    } else {
                        errorLabel = new Label("CANNOT ADD YOURSELF AS A FRIEND!");
                        ErrorPane(errorLabel);

                    }

                }
                if (checker) {
                    checker = false;

                    for (profileBase user : users) {
                        if (Objects.equals(user.getNameForButton(), topNOW)) {
                            if (!user.getMyFriends().isEmpty()) {
                                for (String userName : user.getMyFriends()) {
                                    if (Objects.equals(addFriendNOW, userName)) {
                                        errorLabel = new Label("The user you have chosen as a friend is already added");
                                        ErrorPane(errorLabel);
                                        break;
                                    } else {
                                        checker = true;
                                        break;
                                    }


                                }
                            } else {
                                checker = true;
                                break;
                            }
                        }
                    }

                }

                if (checker) {
                    for (profileBase user : users) {

                        if (Objects.equals(user.getNameForButton(), topNOW)) {
                            for (profileBase userADD : users) {
                                if (Objects.equals(userADD.getNameForButton(), addFriendNOW)) {
                                    user.getMyFriends().add(userADD.getNameForButton());
                                    userADD.getMyFriends().add(user.getNameForButton());
                                    for (int i = 0; i < userADD.getMyFriends().size(); i++) {

                                        userADD.getMyFriendsUpdate(userADD.getMyFriends().get(i));
                                    }
                                    for (int i = 0; i < user.getMyFriends().size(); i++) {

                                        user.getMyFriendsUpdate(user.getMyFriends().get(i));
                                    }
                                }
                            }
                        }
                        if (Objects.equals(user.getNameForButton(), topNOW)) {
                            display(user);
                        }
                    }


                }
            }
            if (e.getSource() == DismissButton) {
                stackPane.getChildren().remove(errorMessagePane);

                if (borderPane.getChildren().contains(vertical)) {
                    borderPane.getChildren().clear();
                    borderPane.setTop(horizontal);
                    borderPane.setLeft(vertical);
                    borderPane.setCenter(stackPane);
                } else {
                    borderPane.getChildren().clear();
                    borderPane.setTop(horizontal);
                    borderPane.setCenter(stackPane);

                }


            }
            if (e.getSource() == images) {
                stackPane.getChildren().clear();

                borderPane.getChildren().clear();
                borderPane.setLeft(vertical);
                borderPane.setTop(horizontal);
                borderPane.setCenter(stackPane);


            }
            if (e.getSource() == mainPage) {
                stackPane.getChildren().clear();
                center.getChildren().clear();
                GridPane.setHalignment(date, HPos.LEFT);
                center.add(date, 0, 0);
                center.add(numOfAccountss, 0, 1);
                center.setStyle("-fx-background-color: #062746;-fx-background-radius: 4;");
                stackPane.getChildren().add(center);

                borderPane.getChildren().clear();

                borderPane.setTop(horizontal);
                borderPane.setCenter(stackPane);


            }
            if (e.getSource() == createGroup) {
                stackPane.getChildren().clear();
                center.getChildren().clear();

                center.setAlignment(Pos.CENTER);
                hostEvent.setPrefWidth(200);
                hostEvent.setPrefHeight(50);

                friendEvent.setPrefWidth(200);
                friendEvent.setPrefHeight(50);

                addFriendEvent.setPrefWidth(200);
                addFriendEvent.setPrefHeight(50);

                center.add(hostEvent, 0, 1);
                center.add(friendEvent, 0, 2);
                center.add(addFriendEvent, 0, 3);
                center.add(new Text("What is the name of the host?"), 0, 0);
                stackPane.getChildren().add(center);

                borderPane.getChildren().clear();
                borderPane.setLeft(vertical);
                borderPane.setTop(horizontal);
                borderPane.setCenter(stackPane);

            }


        }
    }


    public void display(profileBase user) {
        if (!(user.getProfileDefault() == null)) {
            PicFrame.getChildren().clear();
            PicFrame.setStyle("-fx-border-color: rgba(0,0,0,0);");

            user.getProfileDefault().setFitWidth(300);
            user.getProfileDefault().setPreserveRatio(true);
            PicFrame.getChildren().add(user.getProfileDefault());


        } else {
            PicFrame.getChildren().clear();

            PicFrame.setPrefWidth(300);
            PicFrame.setPrefHeight(300);
            PicFrame.setStyle("-fx-border-color: black;");
            PicFrame.getChildren().add(NoimageLabel);

        }
        // center.setStyle("-fx-background-color: #d8e2eb;-fx-background-radius: 4;");
        center.getChildren().clear();

        center.setAlignment(Pos.TOP_LEFT);
        center.setStyle("-fx-background-image: url('/profil3.jpg');-fx-background-size: cover;-fx-background-position: center;");


        GridPane.setConstraints(PicFrame, 0, 1);
        GridPane.setConstraints(user.getNameLabel(), 0, 0);
        GridPane.setConstraints(user.getLabelFriends(), 1, 0);
        GridPane.setConstraints(user.getStatusDefault(), 0, 2);

        user.getNameLabel().setPadding(new Insets(10));
        user.getLabelFriends().setPadding(new Insets(10));
        user.getStatusDefault().setPadding(new Insets(10));

        GridPane.setConstraints(friendsVbox, 1, 1);
        friendsVbox.getChildren().clear();
        if (!user.getMyFriends().isEmpty()) {
            for (String userName : user.getMyFriends()) {
                for (profileBase userrr : users) {
                    if (userrr.getNameForButton().equals(userName)) {
                        Label label = new Label(userName);
                        friendsVbox.getChildren().add(label);
                    }
                }
            }
        }
        center.getChildren().addAll(friendsVbox, user.getNameLabel(), user.getLabelFriends(), PicFrame, user.getStatusDefault());
        stackPane.getChildren().clear();
        stackPane.getChildren().add(center);
        borderPane.getChildren().clear();
        borderPane.setLeft(vertical);
        borderPane.setTop(horizontal);
        borderPane.setCenter(stackPane);
    }

    public void ErrorPane(Label errorLabel) {
        errorMessagePane = new HBox(10, DismissButton, errorLabel);
        errorMessagePane.setPadding(new Insets(10));
        errorMessagePane.setAlignment(Pos.CENTER);
        errorMessagePane.setStyle("-fx-background-color: #e8e7e7;-fx-background-radius: 10;-fx-border-color: #da4d4d;-fx-border-radius: 10");
        errorMessagePane.getChildren().clear();
        errorMessagePane.getChildren().addAll(errorLabel, DismissButton);
        errorMessagePane.setMinSize(200, 50);
        errorMessagePane.setMaxSize(300, 50);
        errorLabel.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(errorLabel, Priority.ALWAYS);
        stackPane.getChildren().clear();
        stackPane.getChildren().addAll(center, errorMessagePane);
        borderPane.setCenter(stackPane);
    }

    public void stop() throws Exception {
        writer.writeData(users);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
