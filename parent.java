package com.example.project1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * FaceLite is a JavaFX social app that allows users to create profiles, make changes to them, add friends and pictures, and more.
 *
 * @author Dena Alharbi and Atheer Almomtan
 * F12
 */


public class parent extends Application {

    //Declarations
    static String FILE_NAME = "data.txt";
    int numberOfAccounts = 0;

    //Arraylists
    ArrayList<profileBase> users = new ArrayList<>();
    ArrayList<String[]> Retriever = new ArrayList<>();
    ArrayList<String> quotes = new ArrayList<>() {{
        add("Our life is what our thoughts make it. — Marcus Aurelius");
        add("Every day brings new choices. — Martha Beck");
        add("Work hard, stay positive, and get up early. — It's a beautiful day");
        add("The only way to do great work is to love what you do. -Steve Jobs");
        add("The best way to predict the future is to create it. -Abraham Lincoln");
        add("You miss 100% of the shots you don’t take. -Wayne Gretzky");
    }};

    //Texts
    String topLast = "", addLast = "", pathLast = "", statusLast = "", addGuestLast = "";
    Text welcome = new Text("Welcome"), updateText = new Text();

    Label NoimageLabel = new Label("No Image"), usernameLabel = new Label("What is your name (The host)?"), nameEventLabel = new Label("What is the name of the event?"), bottomStatus, errorLabel = new Label("Error - You have to pick a profile first"), nameLabel = new Label("Name:");

    //Buttons
    Button addFriendEvent = new Button("Add Friend to event"), addButton = new Button("Add"), deleteButton = new Button("Delete");
    Button lookupButton = new Button("Lookup"), DismissButton = new Button("X"), changeStatus = new Button("Change Status");
    Button changePicture = new Button("Change Picture"), returnButton = new Button("Return to profile"), addFriend = new Button("Add Friend");
    Button images = new Button("Image Gallery"), statusGenerator = new Button("Quote of the day"), createGroup = new Button("Create an event blast"), addEvent = new Button("Add Friend"), submit = new Button("Submit");
    Button Users = new Button("FaceLite users");


    //Text Fields
    TextField changePicText = new TextField(), changeStatusText = new TextField(), addFriendText = new TextField(), userHost = new TextField(), EventName = new TextField(), addEventText = new TextField(), topText = new TextField();
    ImageView imageView = new ImageView(new Image("f.png"));

    //Panes
    BorderPane borderPane = new BorderPane();
    GridPane center = new GridPane();
    StackPane PicFrame = new StackPane(), stackPane = new StackPane();
    HBox horizontal = new HBox(10, nameLabel, topText, addButton, deleteButton, lookupButton), errorMessage = new HBox();
    VBox vertical = new VBox(30, statusGenerator, createGroup, images, Users, new VBox(addFriendText, addFriend), new VBox(changePicText, changePicture), new VBox(changeStatusText, changeStatus)), friendsVbox = new VBox();

    //Overriding the Javafx start method
    @Override
    public void start(Stage stage) throws IOException {

        //Retrieving the previous data from the text file
        try (Scanner input = new Scanner(new File(FILE_NAME))) {
            while (input.hasNextLine()) {
                String newString = input.nextLine();
                if (!newString.isEmpty()) {
                    String[] arr = newString.split("@");
                    Retriever.add(arr);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.format("File not found: %s%n", FILE_NAME);
        }
        for (String[] strings : Retriever) {
            profileBase newUserBase = new profileBase(strings[0]);
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

        //Size altering
        topText.setMinWidth(200);
        nameLabel.setMinWidth(50);
        nameLabel.setMinHeight(100);
        addFriendText.setMinWidth(100);
        changePicText.setMinWidth(100);
        changeStatusText.setMinWidth(100);
        changePicture.setMinWidth(300);
        changeStatus.setMinWidth(300);
        addFriend.setMinWidth(300);
        createGroup.setMinWidth(200);
        images.setMinWidth(200);
        statusGenerator.setMinWidth(200);

        // setonAction statements
        addButton.setOnAction(new ButtonHandler());
        submit.setOnAction(new ButtonHandler());
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
        addEvent.setOnAction(new ButtonHandler());
        returnButton.setOnAction(new ButtonHandler());
        statusGenerator.setOnAction(new ButtonHandler());
        Users.setOnAction(new ButtonHandler());

        //styling the buttons
        Users.setStyle("-fx-effect: dropshadow(three-pass-box, rgb(60,84,89), 10, 0, 0, 0);-fx-background-radius: 20;");
        statusGenerator.setStyle("-fx-effect: dropshadow(three-pass-box, rgb(60,84,89), 10, 0, 0, 0);-fx-background-radius: 20;");
        DismissButton.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        addButton.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        images.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);-fx-background-radius: 20;");
        deleteButton.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        lookupButton.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        changeStatus.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        changePicture.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        addFriend.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        createGroup.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);-fx-background-radius: 20;");
        addFriendEvent.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        addEvent.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        returnButton.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
        submit.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");

        //The code for making the buttons pressed when the Enter Key is pressed
        addFriendText.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                addFriend.fire();
            }
        });

        changePicText.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                changePicture.fire();
            }
        });

        changeStatusText.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                changeStatus.fire();
            }
        });

        //setting up the main panes
        vertical.setStyle("-fx-background-color: #dfeffa;-fx-background-radius: 4;");
        vertical.setPrefSize(300, 100);
        vertical.setAlignment(Pos.CENTER);
        vertical.setPadding(new Insets(0, 10, 0, 10));

        horizontal.setAlignment(Pos.CENTER);
        horizontal.setStyle("-fx-background-color: #dfeffa;-fx-background-radius: 4;");
        horizontal.setPrefSize(100, 100);

        friendsVbox.setPrefSize(300, 200);
        friendsVbox.setPadding(new Insets(0, 10, 0, 10));

        center.setStyle("-fx-background-color:#dfeffa;-fx-background-size: cover;-fx-background-position: center;-fx-border-color: black; -fx-border-width: 2px; -fx-border-radius: 5px;");
        center.setVgap(10);
        center.setHgap(10);
        center.setAlignment(Pos.CENTER);
        center.setPadding(new Insets(25, 25, 25, 25));
        center.getChildren().clear();

        welcome.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        center.add(welcome, 0, 0, 2, 1);

        center.add(nameLabel, 0, 1);
        center.add(topText, 1, 1);
        center.add(addButton, 0, 2);
        center.add(lookupButton, 1, 2);
        imageView.setFitWidth(650);
        imageView.setFitHeight(800);
        borderPane.setRight(imageView);
        borderPane.setCenter(center);
        borderPane.setStyle("-fx-background-color: #041e41;-fx-border-color: black; -fx-border-width: 1px; ");

        //starting the program
        Scene scene = new Scene(borderPane, 1100, 800);
        stage.setTitle("FaceLite");
        stage.setScene(scene);
        stage.show();
    }

    public class ButtonHandler implements EventHandler<ActionEvent> {

        //Method to copy the path of an image by a  mouseclick
        private void copyImagePath(String imgPath) {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(imgPath);
            clipboard.setContent(content);
        }

        //Extra declarations needed
        Label numOfAccounts = new Label("");
        int checkerHost = 0;
        String[] paths = {
                "pic1.jpeg",
                "pic3.jpeg",
                "pic4.jpeg",
                "pic5.jpeg",
                "pic6.jpeg",
                "pic7.jpeg"
        };


        public void handle(ActionEvent e) {

            //Making the text-fields appear empty after an action
            if (!topText.getText().isEmpty()) {

                // This variable is to make sure the data entered is not lost until it is not required anymore
                topLast = topText.getText();
                topText.clear();
            }
            if (!addFriendText.getText().isEmpty()) {
                addLast = addFriendText.getText();
                addFriendText.clear();
            }
            if (!changePicText.getText().isEmpty()) {
                pathLast = changePicText.getText();
                changePicText.clear();
            }
            if (!changeStatusText.getText().isEmpty()) {
                statusLast = changeStatusText.getText();
                changeStatusText.clear();
            }
            if (!addEventText.getText().isEmpty()) {
                addGuestLast = addEventText.getText();
                addEventText.clear();
            }

            //This will be used later in all the buttons to check if certain requirements are met
            boolean checker;

            // Add new users
            if (e.getSource() == addButton) {
                checker = true;

                if (!topLast.isEmpty()) {

                    //checks if the user is already in the system
                    for (profileBase userAddress : users) {
                        if (Objects.equals(userAddress.getUserName(), topLast)) {
                            errorLabel = new Label("THIS USER ALREADY EXISTS!");
                            ErrorPane(errorLabel);
                            checker = false;
                            break;
                        }

                    }
                    if (checker) {

                        //adding the user
                        profileBase newUserBase = new profileBase(topLast);
                        users.add(newUserBase);
                        Text nameLabelUpdated = newUserBase.getNameLabel();
                        PicFrame.getChildren().clear();
                        PicFrame.setPrefWidth(300);
                        PicFrame.setPrefHeight(300);
                        PicFrame.setStyle("-fx-border-color: #000000; -fx-border-radius: 10; -fx-border-width: 2; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
                        PicFrame.getChildren().add(NoimageLabel);

                        GridPane.setConstraints(nameLabelUpdated, 0, 0);
                        GridPane.setConstraints(newUserBase.getLabelFriends(), 1, 0);
                        GridPane.setConstraints(PicFrame, 0, 1);
                        GridPane.setConstraints(newUserBase.getStatusDefault(), 0, 2);
                        GridPane.setConstraints(friendsVbox, 1, 1);
                        GridPane.setConstraints(updateText, 0, 3);

                        newUserBase.getLabelFriends().setPadding(new Insets(10));
                        newUserBase.getStatusDefault().setPadding(new Insets(10));
                        newUserBase.getNameLabel().setStyle("-fx-font-size: 24; -fx-fill: black;");


                        friendsVbox.getChildren().clear();
                        center.getChildren().clear();

                        bottomStatus = new Label("New Profile Created");
                        bottomStatus.setFont(Font.font("Arial", 20));
                        bottomStatus.setStyle("-fx-text-fill: #F3E3D3;");
                        center.add(bottomStatus, 1, 35);

                        center.setStyle("-fx-background-image: url('/proBack1.jpg');-fx-background-size: cover;-fx-background-position: center;");
                        center.getChildren().addAll(friendsVbox, nameLabelUpdated, newUserBase.getLabelFriends(), PicFrame, newUserBase.getStatusDefault());
                        borderPane.getChildren().clear();
                        stackPane.getChildren().clear();
                        center.setAlignment(Pos.TOP_LEFT);
                        HBox horizontal = new HBox(10, nameLabel, topText, addButton, deleteButton, lookupButton);
                        topText.setMinWidth(200);
                        nameLabel.setMinWidth(50);
                        nameLabel.setMinHeight(100);
                        horizontal.setAlignment(Pos.CENTER);
                        horizontal.setStyle("-fx-background-color: #dfeffa;-fx-background-radius: 4;");
                        horizontal.setPrefSize(100, 100);
                        stackPane.getChildren().add(center);
                        borderPane.setLeft(vertical);
                        borderPane.setTop(horizontal);
                        borderPane.setCenter(stackPane);
                    } else if (topLast.isEmpty()) {
                        errorLabel = new Label("ENTER A PROFILE NAME!");
                        ErrorPane(errorLabel);
                    }
                }

                //Delete users
            } else if (e.getSource() == deleteButton) {
                checker = false;

                if (topLast.isEmpty()) {
                    errorLabel = new Label("ENTER A PROFILE NAME!");
                    ErrorPane(errorLabel);
                } else {
                    for (profileBase user : users) {
                        if (Objects.equals(user.getUserName(), topLast)) {
                            checker = true;
                            users.remove(user);
                            center.getChildren().clear();
                            borderPane.getChildren().clear();
                            stackPane.getChildren().clear();
                            center.setAlignment(Pos.TOP_LEFT);
                            HBox horizontal = new HBox(10, nameLabel, topText, addButton, deleteButton, lookupButton);
                            topText.setMinWidth(200);
                            nameLabel.setMinWidth(50);
                            nameLabel.setMinHeight(100);
                            horizontal.setAlignment(Pos.CENTER);
                            horizontal.setStyle("-fx-background-color: #dfeffa;-fx-background-radius: 4;");
                            horizontal.setPrefSize(100, 100);
                            stackPane.getChildren().add(center);
                            borderPane.setLeft(vertical);
                            borderPane.setTop(horizontal);
                            borderPane.setCenter(stackPane);
                            break;
                        }
                    }
                    center.setAlignment(Pos.CENTER);

                    //Display what is going on at the bottom of the app
                    bottomStatus = new Label("Profile of " + topLast + " has been deleted");
                    bottomStatus.setFont(Font.font("Arial", 20));
                    bottomStatus.setStyle("-fx-text-fill: #F3E3D3;");
                    center.add(bottomStatus, 2, 62);
                    topLast = "";
                    if (!checker) {
                        center.getChildren().clear();
                        errorLabel = new Label("PROFILE DOES NOT EXIST!");
                        ErrorPane(errorLabel);
                    }
                }

                //Look for users that exist
            } else if (e.getSource() == lookupButton) {
                checker = false;

                if (topLast.equals("")) {
                    center.getChildren().remove(bottomStatus);
                    errorLabel = new Label("ENTER A PROFILE NAME!");
                    ErrorPane(errorLabel);
                } else {

                    //check if they exist or else an error will pop up later
                    for (profileBase user : users) {
                        if (Objects.equals(user.getUserName(), topLast)) {
                            checker = true;
                            break;
                        }
                    }
                }
                if (checker) {
                    for (profileBase user : users) {
                        if (Objects.equals(user.getUserName(), topLast)) {
                            display(user);
                        }
                    }
                    bottomStatus = new Label("Displaying " + topLast);
                    bottomStatus.setFont(Font.font("Arial", 20));
                    bottomStatus.setStyle("-fx-text-fill: #F3E3D3;");
                    center.add(bottomStatus, 1, 35);

                } else if (!checker && borderPane.getChildren().contains(vertical)) {
                    center.getChildren().remove(bottomStatus);
                    bottomStatus = new Label("A profile with the name " + topLast + " does not exist");
                    bottomStatus.setFont(Font.font("Arial", 20));
                    bottomStatus.setStyle("-fx-text-fill: #f3e3d3;");
                    center.add(bottomStatus, 1, 62);
                } else {
                    errorLabel = new Label("THE PROFILE DOES NOT EXIST!");
                    ErrorPane(errorLabel);
                }

                //One of the ways to change the status
            } else if (e.getSource() == changeStatus) {
                checker = false;

                if (topLast.isEmpty()) {
                    center.getChildren().remove(bottomStatus);
                    errorLabel = new Label("ENTER A PROFILE NAME!");
                    ErrorPane(errorLabel);
                } else if (statusLast.isEmpty()) {
                    errorLabel = new Label("UPDATE YOUR STATUS!");
                    ErrorPane(errorLabel);
                } else {
                    for (profileBase user : users) {
                        if (Objects.equals(user.getUserName(), topLast)) {
                            checker = true;
                            break;
                        }
                    }
                    if (!checker) {
                        errorLabel = new Label("PROFILE DOES NOT EXIST!");
                        ErrorPane(errorLabel);
                    }
                }
                if (checker) {
                    for (profileBase user : users) {
                        if (Objects.equals(user.getUserName(), topLast)) {
                            user.statusDefault = new Label(statusLast);
                            user.getFinStatus(statusLast);
                            display(user);
                        }
                    }
                    bottomStatus = new Label("Status updated to: " + statusLast);
                    bottomStatus.setFont(Font.font("Arial", 20));
                    bottomStatus.setStyle("-fx-text-fill: #F3E3D3;");
                    center.add(bottomStatus, 1, 35);
                }

                //change the picture of a profile
            } else if (e.getSource() == changePicture) {
                checker = false;

                if (topLast.equals("")) {
                    center.getChildren().remove(bottomStatus);
                    errorLabel = new Label("ENTER A PROFILE NAME!");
                    ErrorPane(errorLabel);
                } else if (pathLast.isEmpty()) {
                    errorLabel = new Label("ENTER A PICTURE PATH!");
                    ErrorPane(errorLabel);
                } else {
                    for (profileBase user : users) {
                        if (Objects.equals(user.getUserName(), topLast)) {
                            checker = true;
                            break;
                        }
                    }
                    if (!checker) {
                        errorLabel = new Label("PROFILE DOES NOT EXIST!");
                        ErrorPane(errorLabel);
                    }
                }
                if (checker) {
                    if (!Arrays.asList(paths).contains(pathLast)) {
                        checker = false;
                        errorLabel = new Label("NO SUCH IMAGE IN GALLERY!");
                        ErrorPane(errorLabel);
                    }
                }
                if (checker) {

                    for (profileBase user : users) {
                        if (Objects.equals(user.getUserName(), topLast)) {
                            user.profileDefault = new ImageView(new Image(pathLast));
                            user.getPicPath(pathLast);
                            display(user);
                        }
                    }
                    bottomStatus = new Label("Picture updated");
                    bottomStatus.setFont(Font.font("Arial", 20));
                    bottomStatus.setStyle("-fx-text-fill: #F3E3D3;");
                    center.add(bottomStatus, 1, 35);
                }

                //add a friend to the profiles friends list
            } else if (e.getSource() == addFriend) {
                checker = false;

                if (topLast.equals("") || addLast.isEmpty()) {
                    center.getChildren().remove(bottomStatus);

                    errorLabel = new Label("ENTER A PROFILE NAME!");
                    ErrorPane(errorLabel);
                }
                for (profileBase user : users) {
                    if (Objects.equals(user.getUserName(), topLast)) {
                        checker = true;
                        break;
                    }
                }
                if (!checker) {
                    errorLabel = new Label("PROFILE DOES NOT EXIST!");
                    ErrorPane(errorLabel);
                }
                if (checker) {
                    checker = false;

                    for (profileBase user : users) {
                        if (Objects.equals(user.getUserName(), addLast)) {
                            checker = true;
                            break;
                        }
                    }
                    if (!checker) {
                        errorLabel = new Label("PROFILE DOES NOT EXIST!");
                        ErrorPane(errorLabel);
                    }
                }
                if (checker) {
                    checker = false;

                    if (!topLast.equals(addLast)) {
                        checker = true;
                    } else {
                        errorLabel = new Label("YOU CAN NOT ADD YOURSELF!");
                        ErrorPane(errorLabel);
                    }
                }
                if (checker) {
                    for (profileBase user : users) {
                        if (Objects.equals(user.getUserName(), topLast)) {
                            if (!user.getMyFriends().isEmpty()) {
                                for (String userName : user.getMyFriends()) {
                                    if (Objects.equals(addLast, userName)) {
                                        errorLabel = new Label("PROFILE IS ADDED!");
                                        ErrorPane(errorLabel);
                                        checker = false;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                if (checker) {
                    for (profileBase user : users) {
                        if (Objects.equals(user.getUserName(), topLast)) {
                            for (profileBase userADD : users) {
                                if (Objects.equals(userADD.getUserName(), addLast)) {
                                    user.getMyFriends().add(userADD.getUserName());
                                    userADD.getMyFriends().add(user.getUserName());
                                    for (int i = 0; i < userADD.getMyFriends().size(); i++) {
                                        userADD.getMyFriendsUpdate(userADD.getMyFriends().get(i));
                                    }
                                    for (int i = 0; i < user.getMyFriends().size(); i++) {
                                        user.getMyFriendsUpdate(user.getMyFriends().get(i));
                                    }
                                }
                            }
                        }
                        if (Objects.equals(user.getUserName(), topLast)) {
                            display(user);
                            bottomStatus = new Label(addLast + " added as a friend");
                            bottomStatus.setFont(Font.font("Arial", 20));
                            bottomStatus.setStyle("-fx-text-fill: #F3E3D3;");
                            center.add(bottomStatus, 1, 35);
                        }
                    }
                }
            }
            if (e.getSource() == DismissButton) {
                stackPane.getChildren().remove(errorMessage);
                if (borderPane.getChildren().contains(vertical)) {
                    borderPane.getChildren().clear();
                    stackPane.getChildren().clear();
                    HBox horizontal = new HBox(10, nameLabel, topText, addButton, deleteButton, lookupButton);
                    topText.setMinWidth(200);
                    nameLabel.setMinWidth(50);
                    nameLabel.setMinHeight(100);
                    horizontal.setAlignment(Pos.CENTER);
                    horizontal.setStyle("-fx-background-color: #dfeffa;-fx-background-radius: 4;");
                    horizontal.setPrefSize(100, 100);
                    stackPane.getChildren().add(center);
                    borderPane.setLeft(vertical);
                    borderPane.setTop(horizontal);
                    borderPane.setCenter(stackPane);
                } else {
                    borderPane.getChildren().clear();
                    center.getChildren().clear();
                    center.setStyle("-fx-background-color:#dfeffa;-fx-background-size: cover;-fx-background-position: center;-fx-border-color: black; -fx-border-width: 2px; -fx-border-radius: 5px;");
                    center.add(welcome, 0, 0, 2, 1);
                    center.add(nameLabel, 0, 1);
                    center.add(topText, 1, 1);
                    center.add(addButton, 0, 2);
                    center.add(lookupButton, 1, 2);
                    borderPane.setRight(imageView);
                    borderPane.setCenter(center);
                    borderPane.setStyle("-fx-background-color: #041e41;-fx-border-color: black; -fx-border-width: 1px; ");
                }
            }
            if (e.getSource() == images) {
                center.getChildren().clear();
                borderPane.getChildren().clear();
                stackPane.getChildren().clear();
                center.setAlignment(Pos.TOP_LEFT);
                HBox horizontal = new HBox(10, nameLabel, topText, addButton, deleteButton, lookupButton);
                topText.setMinWidth(200);
                nameLabel.setMinWidth(50);
                nameLabel.setMinHeight(100);
                horizontal.setAlignment(Pos.CENTER);
                horizontal.setStyle("-fx-background-color: #dfeffa;-fx-background-radius: 4;");
                horizontal.setPrefSize(100, 100);
                stackPane.getChildren().add(center);
                borderPane.setLeft(vertical);
                borderPane.setTop(horizontal);
                borderPane.setCenter(stackPane);
                center.add(returnButton, 0, 3);

                int column = 0;
                int row = 0;

                for (String path : paths) {
                    ImageView imageView = new ImageView(new Image(path));
                    imageView.setFitWidth(240);
                    imageView.setFitHeight(240);
                    center.add(imageView, column, row);
                    imageView.setOnMouseClicked(event -> copyImagePath(path));
                    column++;
                    if (column >= 3) {
                        column = 0;
                        row++;
                    }
                    if (row >= 2) {
                        break;
                    }
                }
            }

            if (e.getSource() == returnButton) {
                checker = false;
                for (profileBase user : users) {
                    if (Objects.equals(user.getUserName(), topLast)) {
                        checker = true;
                        display(user);
                    }
                }
                if (!checker) {
                    errorLabel = new Label("PROFILE DOES NOT EXIST!");
                    ErrorPane(errorLabel);
                }
            }

            if (e.getSource() == Users) {
                center.setAlignment(Pos.TOP_LEFT);
                center.getChildren().clear();
                center.add(returnButton, 0, 3);
                VBox userNames = new VBox();
                numOfAccounts.setStyle("-fx-font-size: 24; -fx-fill: black;");
                numberOfAccounts = users.size();
                numOfAccounts.setText("The Number Of Accounts is: " + numberOfAccounts);
                userNames.getChildren().add(numOfAccounts);
                userNames.setPadding(new Insets(10));
                userNames.setSpacing(10);
                for (profileBase user : users) {
                    Label name = new Label(user.getUserName());
                    userNames.getChildren().add(name);
                }
                center.getChildren().add(userNames);
            }

            if (e.getSource() == createGroup) {
                borderPane.getChildren().clear();
                center.getChildren().clear();
                stackPane.getChildren().clear();
                center.setAlignment(Pos.TOP_LEFT);
                HBox horizontal = new HBox(10, nameLabel, topText, addButton, deleteButton, lookupButton);
                topText.setMinWidth(200);
                nameLabel.setMinWidth(50);
                nameLabel.setMinHeight(100);
                horizontal.setAlignment(Pos.CENTER);
                horizontal.setStyle("-fx-background-color: #dfeffa;-fx-background-radius: 4;");
                horizontal.setPrefSize(100, 100);
                stackPane.getChildren().add(center);
                borderPane.setLeft(vertical);
                borderPane.setTop(horizontal);
                borderPane.setCenter(stackPane);
                addEvent.setGraphic(addEventText);
                center.setAlignment(Pos.CENTER);
                center.setHgap(10);
                center.setVgap(10);
                center.add(new Text("Create an event blast \nand invite your friends to join you!\nSimply enter your profile name, the name of the event,\nand the name of the friend you want to invite.\nOnce you submit this information, \nyour friend’s status will be updated to show that they are attending the event. \nIt’s that easy!"), 0, 0);
                center.add(usernameLabel, 0, 1);
                center.add(userHost, 1, 1);
                center.add(nameEventLabel, 0, 2);
                center.add(EventName, 1, 2);
                center.add(addEvent, 0, 3);
                center.add(submit, 0, 4);
                center.add(returnButton, 0, 5);
            }
            if (e.getSource() == addEvent) {

                if (addGuestLast.isEmpty()) {
                    errorLabel = new Label("ENTER A NAME FIRST!");
                    ErrorPane(errorLabel);
                } else {
                    if (checkerHost == 0) {
                        for (profileBase user : users) {
                            if (Objects.equals(user.getUserName(), userHost.getText())) {
                                checkerHost = 1;
                                String u = user.statusDefault.getText();
                                if (!Objects.equals(u, "No current status")) {
                                    user.statusDefault = new Label(u + " and I am hosting a " + EventName.getText() + " event");
                                    user.getFinStatus(u + " and I am hosting a " + EventName.getText() + " event");
                                } else {
                                    user.statusDefault = new Label("I am hosting a " + EventName.getText() + " event");
                                    user.getFinStatus(" I am hosting a " + EventName.getText() + " event");
                                }
                                break;
                            }
                        }
                    }
                }
                if (checkerHost == 1) {
                    checker = false;
                    for (profileBase user : users) {
                        if (Objects.equals(user.getUserName(), addGuestLast)) {
                            checker = true;
                            String u = user.statusDefault.getText();
                            if (!Objects.equals(u, "No current status")) {
                                user.statusDefault = new Label(u + " and I have a " + EventName.getText() + " hosted by " + userHost.getText());
                                user.getFinStatus(u + " and I have a " + EventName.getText() + " hosted by " + userHost.getText());
                            } else {
                                user.statusDefault = new Label("I have a " + EventName.getText() + " hosted by " + userHost.getText());
                                user.getFinStatus("I have a " + EventName.getText() + " hosted by " + userHost.getText());
                            }
                        }
                    }
                    if (!checker) {
                        errorLabel = new Label("FRIEND PROFILE DOES NOT EXIST!");
                        ErrorPane(errorLabel);
                    }
                } else {
                    errorLabel = new Label("PROFILE DOES NOT EXIST!");
                    ErrorPane(errorLabel);
                }
            }
            if (e.getSource() == submit) {
                checkerHost = 0;
            }
            if (e.getSource() == statusGenerator) {
                Random ran = new Random();
                String q = quotes.get(ran.nextInt(quotes.size()));
                for (profileBase user : users) {
                    if (Objects.equals(user.getUserName(), topLast)) {
                        user.statusDefault = new Label(q);
                        user.getFinStatus(q);
                        display(user);
                        break;
                    }
                }
            }
        }
    }

    //Method for displaying profiles
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
            PicFrame.setStyle("-fx-border-color: #000000; -fx-border-radius: 10; -fx-border-width: 2; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
            PicFrame.getChildren().add(NoimageLabel);
        }
        center.getChildren().clear();
        center.setAlignment(Pos.TOP_LEFT);
        center.setStyle("-fx-background-image: url('/proBack1.jpg');-fx-background-size: cover;-fx-background-position: center;");
        GridPane.setConstraints(PicFrame, 0, 1);
        GridPane.setConstraints(friendsVbox, 1, 1);
        GridPane.setConstraints(user.getNameLabel(), 0, 0);
        GridPane.setConstraints(user.getLabelFriends(), 1, 0);
        GridPane.setConstraints(user.getStatusDefault(), 0, 2);
        user.getNameLabel().setStyle("-fx-font-size: 24; -fx-fill: black;");
        user.getLabelFriends().setPadding(new Insets(10));
        user.getStatusDefault().setPadding(new Insets(10));
        friendsVbox.getChildren().clear();
        if (!user.getMyFriends().isEmpty()) {
            for (String userName : user.getMyFriends()) {
                for (profileBase user1 : users) {
                    if (user1.getUserName().equals(userName)) {
                        Label label = new Label(userName);
                        friendsVbox.getChildren().add(label);
                    }
                }
            }
        }
        HBox horizontal = new HBox(10, nameLabel, topText, addButton, deleteButton, lookupButton);
        topText.setMinWidth(200);
        nameLabel.setMinWidth(50);
        nameLabel.setMinHeight(100);
        horizontal.setAlignment(Pos.CENTER);
        horizontal.setStyle("-fx-background-color: #dfeffa;-fx-background-radius: 4;");
        horizontal.setPrefSize(100, 100);
        center.getChildren().addAll(friendsVbox, user.getNameLabel(), user.getLabelFriends(), PicFrame, user.getStatusDefault());
        stackPane.getChildren().clear();
        stackPane.getChildren().add(center);
        borderPane.getChildren().clear();
        borderPane.setLeft(vertical);
        borderPane.setTop(horizontal);
        borderPane.setCenter(stackPane);
    }

    public void ErrorPane(Label errorLabel) {
        errorMessage = new HBox(10, DismissButton, errorLabel);
        errorMessage.setPadding(new Insets(10));
        errorMessage.setAlignment(Pos.CENTER);
        errorMessage.setStyle("-fx-background-color: #e8e7e7;-fx-background-radius: 10;-fx-border-color: #da4d4d;-fx-border-radius: 10");
        errorMessage.getChildren().clear();
        errorMessage.getChildren().addAll(errorLabel, DismissButton);
        errorMessage.setMinSize(200, 50);
        errorMessage.setMaxSize(300, 50);
        errorLabel.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(errorLabel, Priority.ALWAYS);
        stackPane.getChildren().clear();
        stackPane.getChildren().addAll(center, errorMessage);
        borderPane.setCenter(stackPane);
    }

    public void stop() throws Exception {

        //Save user data
        writer.saveData(users);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
