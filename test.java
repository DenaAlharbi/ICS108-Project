package com.example.ics108project;

import java.util.ArrayList;

public class test {
    public void main(String[] args) {

        ArrayList<user> users = new ArrayList<user>();
        users.add(new user("dena"));
        users.add(new user("Atheer"));
        for (user name : users){
            System.out.print(name);

        }



    }
    class user {
        private String name;
        public user(String name){
            this.name= name;
        }
    }


   
}