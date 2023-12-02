package com.example.project1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class parent extends Application {
    static String FILE_NAME = "data.txt";
    ArrayList<profileBase> users = new ArrayList<>();
    ArrayList<String[]> Retriever = new ArrayList<>();

    //String[]  Retrieve = new String[4];
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
    Label userRemovedLabel = new Label("The user has been removed");

    Label errorLabel = new Label("Error - You have to pick a profile first");
    //buttons and textfield for the top horizontal
    Label nameLabel = new Label("Name:");
    Label NoimageLabel = new Label("No Image");


    Text updateText = new Text();
    TextField topText = new TextField();
    Button addButton = new Button("Add");
    Button deleteButton = new Button("Delete");
    Button lookupButton = new Button("Lookup");
    Button DismissButton = new Button("X");
    // buttons and textfields for the vertical side
    Button changeStatus = new Button("Change Status");
    Button changePicture = new Button("Change Picture");

    Button addFriend = new Button("Add Friend");
    TextField changePicText = new TextField();
    TextField changeStatusText = new TextField();
    TextField addFriendText = new TextField();
    ImageView imageWelcome = new ImageView(new Image("WelcomePic.png"));
    //int IDnum = 0;
    HBox horizontal = new HBox(10, nameLabel, topText, addButton, deleteButton, lookupButton);
    VBox vertical = new VBox(30, new VBox(addFriendText, addFriend), new VBox(changePicText, changePicture), new VBox(changeStatusText, changeStatus));


    @Override
    public void start(Stage stage) throws IOException {
        //array list of users and their data
        try (Scanner input = new Scanner(new File(FILE_NAME))) {
            while (input.hasNextLine()) {
                String newString = input.nextLine();

                if (!newString.isEmpty()) {

                    String[] arr = newString.split("@");
                   /* for (int i = 0; i < arr.length; i++) {
                        System.out.println(arr[i]);
                    }*/


                    Retriever.add(arr);
                }
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.err.format("File not found: %s%n", FILE_NAME);
        }


        for (String[] strings : Retriever) {
            //System.out.println(strings);


            profileBase newUserBase = new profileBase(strings[0]);
            users.add(newUserBase);
            //System.out.println(newUserBase);


            if (!strings[1].equals("No-Image")){
                newUserBase.getProfileDefault(strings[1]);
                newUserBase.getPicPath(strings[1]);
            }
            if (!strings[2].equals("No-current-status")){
                newUserBase.getStatusDefault(strings[2]);
                newUserBase.getFinStatus(strings[2]);
            }


            if (!strings[3].equals("No-Friends")) {
                String in = strings[3];
                String[] sub = in.substring(1, in.length() - 1).split(",");
                ArrayList<String> list = new ArrayList<>(Arrays.asList(sub));
                newUserBase.getMyFriends(list);
                newUserBase.getMyFriendsupdateRR(strings[3]);
                //System.out.println(list);

            }

        }
       





        //borders
        center.setStyle("-fx-background-color: #d8e2eb;-fx-background-radius: 4;");

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
        DismissButton.setOnAction(new ButtonHandler());
        DismissButton.setPadding(new Insets(10));

        //Temporary panes
        userRemovedPane.getChildren().add(userRemovedLabel);
        //errorMessagePane.getChildren().add(errorLabel);

        welcomeText.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        welcomeText.setFill(Color.WHITE);
        StackPane.setAlignment(imageWelcome, Pos.TOP_CENTER);
        StackPane.setAlignment(welcomeText, Pos.BOTTOM_CENTER);
        welcomePane.getChildren().addAll(welcomeText, imageWelcome);


        //setting up
        vertical.setStyle("-fx-background-color: #faeef9;-fx-background-radius: 4;");
        vertical.setSpacing(20); //doesnt work
        vertical.setPrefSize(300, 100);
        vertical.setAlignment(Pos.CENTER);
        vertical.setPadding(new Insets(0, 10, 0, 10));

        horizontal.setAlignment(Pos.CENTER);
        horizontal.setStyle("-fx-background-color: #faeef9;-fx-background-radius: 4;");
        horizontal.setPrefSize(100, 100);

        friendsVbox.setStyle("-fx-background-color: #d8e2eb;");
        friendsVbox.setPrefSize(50, 50);
        friendsVbox.setPadding(new Insets(0, 10, 0, 10));

        center.setVgap(8);
        center.setHgap(10);
       // center.setPadding(new Insets(10));
        center.getChildren().add(welcomePane);
        borderPane.setLeft(vertical);
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
            String userNOW = topText.getText();


            if (e.getSource() == addButton ) {
                boolean checker = true;
                String nameRN = topText.getText();
              if (e.getSource() == addButton) {
                boolean checker = true;
                String nameRN = topText.getText();
                if (!nameRN.isEmpty()) {
                    for (profileBase userAddress : users) {
                        if (Objects.equals(userAddress.getNameForButton(), nameRN)) {
                            errorLabel = new Label("THIS USER ALREADY EXISTS!");
                            ErrorPane(errorLabel);
                            checker = false;
                            break;
                        }

                    }
                    if (checker) {


                        profileBase newUserBase = new profileBase(nameRN);
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


                        center.getChildren().clear();

                        //center.getChildren().add(updateLabelPane, 0, center.getRowCount());
                        //stackPane.getChildren().clear();
                        //stackPane.getChildren().addAll(center,updateLabelPane);
                        center.getChildren().addAll(friendsVbox, nameLabelUpdated, newUserBase.getLabelFriends(), PicFrame, newUserBase.getStatusDefault());
                        //center.getChildren().add(newUserPane);
                        //borderPane.setCenter(stackPane);
                        borderPane.setCenter(center);

                    } else if (nameRN.isEmpty()) {
                        errorLabel = new Label("ENTER A PROFILE NAME FIRST!");
                        ErrorPane(errorLabel);

                    }
                }


            } else if (e.getSource() == deleteButton) {
                for (profileBase user : users) {

                    if (Objects.equals(user.getNameForButton(), userNOW)) {
                        users.remove(user);
                        center.getChildren().clear();
                        center.getChildren().add(userRemovedPane); //edit the pane
                    } else if (!users.contains(user)) {
                        center.getChildren().clear();
                        errorLabel = new Label("THIS USER DOES NOT EXIST!");
                        ErrorPane(errorLabel);
                    } else if (userNOW.isEmpty()) {
                        errorLabel = new Label("ENTER A NAME FIRST!");
                        ErrorPane(errorLabel);

                    }
                }
            } else if (e.getSource() == lookupButton) {
                boolean checkerButton = false;
                if (userNOW.isEmpty()) {
                    errorLabel = new Label("ENTER A NAME FIRST!");
                    ErrorPane(errorLabel);
                } else {
                    for (profileBase user : users) {
                        if (Objects.equals(user.getNameForButton(), userNOW)) {
                            checkerButton = true;
                            break;
                        }
                    }
                }
                if (checkerButton) {
                    for (profileBase user : users) {
                        if (Objects.equals(user.getNameForButton(), userNOW)) {
                            display(user);
                        }
                    }
                } else if (!checkerButton) {
                    errorLabel = new Label("THE USER DOES NOT EXIST!");
                    ErrorPane(errorLabel);
                }


            } else if (e.getSource() == changeStatus) {

                boolean checkerButton = false;
                if (userNOW.isEmpty()) {
                    errorLabel = new Label("ENTER A NAME FIRST!");
                    ErrorPane(errorLabel);
                } else if (changeStatusText.getText().isEmpty()) {
                    errorLabel = new Label("ENTER A STATUS FIRST!");
                    ErrorPane(errorLabel);
                } else {
                    for (profileBase user : users) {
                        if (Objects.equals(user.getNameForButton(), userNOW)) {
                            checkerButton = true;
                            break;
                        }
                    }
                }
                if (checkerButton) {
                    for (profileBase user : users) {
                        if (Objects.equals(user.getNameForButton(), userNOW)) {
                            user.statusDefault = new Label(changeStatusText.getText());
                            user.getFinStatus(changeStatusText.getText());
                            display(user);
                        }
                    }
                } else if (!checkerButton) {
                    errorLabel = new Label("THE USER DOES NOT EXIST!");
                    ErrorPane(errorLabel);
                }
            } else if (e.getSource() == changePicture) {
                // WE NEED A CONDITION FOR IF THE PIC DOES NOT EXIST
                boolean checkerButton = false;
                if (userNOW.isEmpty()) {
                    errorLabel = new Label("ENTER A VALID PROFILE NAME FIRST!!");
                    ErrorPane(errorLabel);
                } else if (changePicText.getText().isEmpty()) {
                    errorLabel = new Label("ENTER A PICTURE PATH!");
                    ErrorPane(errorLabel);
                } else {
                    for (profileBase user : users) {
                        if (Objects.equals(user.getNameForButton(), userNOW)) {
                            checkerButton = true;
                            break;
                        }
                    }
                    if (!checkerButton) {
                        errorLabel = new Label("THE USER DOES NOT EXIST!");
                        ErrorPane(errorLabel);
                    }
                }
                if (checkerButton) {
                    for (profileBase user : users) {
                        if (Objects.equals(user.getNameForButton(), userNOW)) {
                            user.profileDefault = new ImageView(new Image(changePicText.getText()));
                            user.getPicPath(changePicText.getText());
                            display(user);
                        }
                    }
                }


            } else if (e.getSource() == addFriend) {
                boolean checkerButtonForMainUser = false;
                boolean checkerButtonForAddedUser = false;


                if (userNOW.isEmpty()) {
                    errorLabel = new Label("ENTER A NAME FIRST!");
                    ErrorPane(errorLabel);
                } else if (addFriendText.getText().isEmpty()) {
                    errorLabel = new Label("ENTER A NAME FIRST!");
                    ErrorPane(errorLabel);


                }
                if (!checkerButtonForMainUser) {
                    for (profileBase user : users) {
                        if (Objects.equals(user.getNameForButton(), userNOW)) {
                            checkerButtonForMainUser = true;
                            break;
                        }
                    }
                }
                if (!checkerButtonForAddedUser) {
                    for (profileBase user : users) {
                        if (Objects.equals(user.getNameForButton(), addFriendText.getText())) {
                            checkerButtonForAddedUser = true;
                            break;
                        }
                    }
                }
                if (checkerButtonForMainUser && checkerButtonForAddedUser) {
                    for (profileBase user : users) {

                        if (Objects.equals(user.getNameForButton(), userNOW)) {
                            for (profileBase userADD : users) {
                                if (Objects.equals(userADD.getNameForButton(), addFriendText.getText())) {
                                    user.getMyFriends().add(userADD.getNameForButton());
                                    userADD.getMyFriends().add(user.getNameForButton());
                                    for(int i =0; i<userADD.getMyFriends().size();i++){

                                        userADD.getMyFriendsUpdate(userADD.getMyFriends().get(i));
                                    }
                                    for(int i =0; i<user.getMyFriends().size();i++){

                                        user.getMyFriendsUpdate(user.getMyFriends().get(i)) ;
                                    }
                                }
                            }
                        }
                        if (Objects.equals(user.getNameForButton(), userNOW)) {
                            display(user);
                        }
                    }
                } else if (!checkerButtonForAddedUser) {
                    errorLabel = new Label("THE USER YOU WANT TO ADD DOES NOT EXIST!");
                    ErrorPane(errorLabel);
                } else if (!checkerButtonForMainUser) {
                    errorLabel = new Label("ENTER A VALID PROFILE NAME FIRST!");
                    ErrorPane(errorLabel);
                }


            }
            if (e.getSource() == DismissButton) {
                stackPane.getChildren().remove(errorMessagePane);
                borderPane.getChildren().clear();
                borderPane.setLeft(vertical);
                borderPane.setTop(horizontal);
                borderPane.setCenter(stackPane);


            }

        }

    }


    public void display(profileBase user) {
        if (!(user.getProfileDefault()==null)){
            PicFrame.getChildren().clear();
            PicFrame.getChildren().add(user.getProfileDefault());

        }
        else{
            PicFrame.setPrefWidth(300);
            PicFrame.setPrefHeight(300);
            PicFrame.setStyle("-fx-border-color: black;");
            PicFrame.getChildren().add(NoimageLabel);

       }

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
        if (!user.getMyFriends().isEmpty()) {
            for (String userName: user.getMyFriends()) {
                if (users.contains(user)) {
                    Label label = new Label(userName);
                    friendsVbox.getChildren().add(label);

                } else {
                    errorLabel = new Label("The user you have chosen as a friend does not exist");
                    ErrorPane(errorLabel);
                }

            }
        }
        center.getChildren().clear();
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
