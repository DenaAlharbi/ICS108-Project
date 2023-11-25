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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class parent extends Application {
    ArrayList<profileBase> users = new ArrayList<profileBase>();
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
    ImageView imageWelcome = new ImageView(new Image("C:\\Users\\denaa\\JavaProjects231\\ICS108-project\\src\\main\\java\\com\\example\\ics108project\\WelcomePic.png"));
    int IDnum = 0;
    HBox horizontal = new HBox(10, nameLabel, topText, addButton, deleteButton, lookupButton);
    VBox vertical = new VBox(30, new VBox(addFriendText, addFriend), new VBox(changePicText, changePicture), new VBox(changeStatusText, changeStatus));


    @Override
    public void start(Stage stage) throws IOException {
        //borders
        center.setStyle("-fx-background-color: #2e5730;-fx-background-radius: 4;");

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
        vertical.setStyle("-fx-background-color: #b6d9aa;-fx-background-radius: 4;");
        vertical.setSpacing(20); //doesnt work
        vertical.setPrefSize(300, 100);
        vertical.setAlignment(Pos.CENTER);
        vertical.setPadding(new Insets(0, 10, 0, 10));

        horizontal.setAlignment(Pos.CENTER);
        horizontal.setStyle("-fx-background-color: #b6d9aa;-fx-background-radius: 4;");
        horizontal.setPrefSize(100, 100);

        friendsVbox.setStyle("-fx-background-color: #c2cec3;");
        friendsVbox.setPrefSize(50, 50);
        friendsVbox.setPadding(new Insets(0, 10, 0, 10));

        center.setVgap(8);
        center.setHgap(10);
        center.getChildren().add(welcomePane);
        borderPane.setLeft(vertical);
        borderPane.setTop(horizontal);
        stackPane.getChildren().add(center);
        borderPane.setCenter(stackPane);


        Scene scene = new Scene(borderPane, 1100, 800);
        stage.setTitle("FaceLite");
        stage.setScene(scene);
        stage.show();
    }
    public class ButtonHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            String userNOW = topText.getText();


            if (e.getSource() == addButton) {
                boolean checker = true;

                String nameRN = topText.getText();
                /*if (!users.isEmpty()) {
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
                        IDnum++;
                        newUserBase.getId(IDnum);
                        Label nameLabelUpdated = newUserBase.getNameLabel();
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
                        updateLabel = new Label("A new user has been added");
                        updateLabelPane.getChildren().add(updateLabel);
                        GridPane.setHalignment(updateLabelPane, HPos.CENTER);
                        GridPane.setValignment(updateLabelPane, VPos.BOTTOM);

                        center.getChildren().clear();
                        //center.getChildren().add(updateLabelPane, 0, center.getRowCount());


                        center.getChildren().addAll(updateLabelPane, friendsVbox, nameLabelUpdated, newUserBase.getLabelFriends(), PicFrame, newUserBase.getStatusDefault());
                        //center.getChildren().add(newUserPane);
                        borderPane.setCenter(center);

                    }*/


                if (!nameRN.isEmpty()) {
                    profileBase newUserBase = new profileBase(nameRN);
                    users.add(newUserBase);
                    IDnum++;
                    newUserBase.getId(IDnum);
                    Label nameLabelUpdated = newUserBase.getNameLabel();
                    PicFrame.setPrefWidth(300);
                    PicFrame.getChildren().add(newUserBase.getProfileDefault());

                    GridPane.setConstraints(nameLabelUpdated, 0, 0);
                    GridPane.setConstraints(newUserBase.getLabelFriends(), 1, 0);
                    GridPane.setConstraints(PicFrame, 0, 1);
                    GridPane.setConstraints(newUserBase.getStatusDefault(), 0, 2);
                    GridPane.setConstraints(friendsVbox, 1, 1);
                    GridPane.setConstraints(updateText, 0, 3);
                    nameLabelUpdated.setPadding(new Insets(10));
                    newUserBase.getLabelFriends().setPadding(new Insets(10));
                    newUserBase.getStatusDefault().setPadding(new Insets(10));
                    newUserBase.getProfileDefault().setFitWidth(300);
                    newUserBase.getProfileDefault().setPreserveRatio(true);
                    friendsVbox.getChildren().clear();
                    updateText = new Text("A new user has been added");
                    updateLabelPane.getChildren().add(updateText);
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
                                    user.getMyFriends().add(userADD);
                                    userADD.getMyFriends().add(user);
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
        if (!user.getMyFriends().isEmpty()) {
            for (profileBase userAddress : user.getMyFriends()) {
                if (users.contains(userAddress)) {
                    Label label = new Label(userAddress.getNameForButton());
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

