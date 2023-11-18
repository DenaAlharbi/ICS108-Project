package com.example.ics108project;

import javafx.scene.layout.Pane;

public class User {
    private final Pane pane;

    private int id;
    private String name,profilePic, status;
    // private User[] friends;
    public User(){
        pane = new Pane();
    }
    public Pane getPane() {
        return pane;
    }



}
