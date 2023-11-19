package com.example.ics108project;

import javafx.scene.layout.Pane;

public class User {
    private final Pane pane;

    private int id;
    private String name,profilePic, status;
    // private User[] friends;
    public User(String name){
        pane = new Pane();
        id = (int)(Math.random() * 90000) + 10000;
        this.name=name;
    }
    public Pane getPane() {

        return pane;
    }
    public int getID() {

        return id;
    }

    public String getName() {
        return name;
    }
}
