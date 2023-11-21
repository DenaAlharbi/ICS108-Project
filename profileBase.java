package com.example.ics108project;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class profileBase extends parent{

        private Label nameLabel;
        private ImageView profileImage;
        private Label statusLabel;

        public profileBase(User user) {
            nameLabel = new Label(user.getName());
            //profileImage = new ImageView(new Image(user.getProfilePicture()));
            statusLabel = new Label(user.getStatus());

            //getChildren().addAll(nameLabel, profileImage, statusLabel);
        }


}
