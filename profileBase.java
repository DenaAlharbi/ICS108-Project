package com.example.project1;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class profileBase extends Parent {

    private Label nameLabel;
    private String nameForButton;
    private ImageView profileImage;
    private Label statusLabel;
    private ArrayList<profileBase> myFriends = new ArrayList<>();
    private int id;

    public profileBase(String name) {
        this.nameLabel = new Label(name);
        this.nameForButton = name;
    }

    Label labelFriends = new Label("Friends ");

    ImageView profileDefault = new ImageView(new Image("defaultPIC.png"));
    Label statusDefault = new Label("No current status");

    public Label getLabelFriends() {
        return labelFriends;
    }

    public ImageView getProfileDefault() {
        return profileDefault;
    }

    // public Label getLabelName() {
    //return labelName;
    // }

    public Label getStatusDefault() {
        return statusDefault;
    }

    public Label getNameLabel() {
        return nameLabel;
    }

    public String getNameForButton() {
        return nameForButton;
    }

    public ArrayList<profileBase> getMyFriends() {
        return myFriends;
    }

    public int getId(int num) {
        return id = num;
    }
}