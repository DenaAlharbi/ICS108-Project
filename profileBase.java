package com.example.ics108project;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class profileBase extends parent{

        private Label nameLabel;
        private String nameForButton;
        private ImageView profileImage;
        private Label statusLabel;

        public profileBase(String name) {
            this.nameLabel= new Label(name);
            this.nameForButton= name;
            //Label labelName =new Label(user.getName());
            //Label labelName = new Label(user.getName());
            //profileImage = new ImageView(new Image(user.getProfilePicture()));
            //statusLabel = new Label(user.getStatus());

            //getChildren().addAll(nameLabel, profileImage, statusLabel);
        }
        //Label labelName =new Label(user.getName());
        Label labelFriends = new Label("Friends ");

        ImageView profileDefault = new ImageView(new Image("C:\\Users\\denaa\\JavaProjects231\\ICS108-project\\src\\main\\java\\com\\example\\ics108project\\defaultPIC.png"));
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
    public Label getNameLabel(){
        return nameLabel;
    }

    public String getNameForButton(){
        return nameForButton;
    }
}
