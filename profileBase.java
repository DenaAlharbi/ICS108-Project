package com.example.project1;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class profileBase extends parent {

    private Label nameLabel;
    private String nameForButton, picPath,myFriendsRR= "[",finStatus;

    private ArrayList<String> myFriends = new ArrayList<>();
    private int id;

    public profileBase(String name) {
        this.nameLabel = new Label(name);
        this.nameForButton = name;
    }

    Label labelFriends = new Label("Friends ");

    ImageView profileDefault = null;
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

    public ArrayList<String> getMyFriends() {
        return myFriends;
    }
    public String getMyFriendsUpdate(String NEW) {
        if (NEW.equals("No-Friends")) {
            myFriendsRR="No-Friends";
        }
        else if(myFriendsRR.equals("["))
            myFriendsRR = myFriendsRR+ NEW;
        else if (!myFriendsRR.equals("[")&& !myFriendsRR.endsWith("]"))
            myFriendsRR = myFriendsRR+"," +NEW ;
        else if (myFriendsRR.endsWith("]")) {
            myFriendsRR=myFriendsRR.substring(0,myFriendsRR.length()-1);
            myFriendsRR = myFriendsRR+"," +NEW+"]";
        }
        return myFriendsRR;


    }
    public String getMyFriendsRR() {
        return myFriendsRR;
    }


    public int getId(int num) {
        return id = num;
    }
    public String getPicPath(String path){
        return picPath= path;
    }
    public String getPicPathRR(){
        return picPath;

    }
    public String getFinStatus(String stat){
        return finStatus= stat;
    }
    public String getFinSatusRR(){
        return finStatus;

    }

}