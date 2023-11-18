package com.example.ics108project;

import javafx.scene.layout.Pane;

public class User {
    private final Pane pane;

    private int id;
    private String name,profilePic, status;
    // private User[] friends;
    public User(){
        pane = new Pane();
        id = (int)(Math.random() * 90000) + 10000;
    }
    public Pane getPane() {
        return pane;
    }
    public int getID() {
        return id;
    }



}
