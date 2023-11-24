package com.example.ics108project;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class writer extends parent {

        private static final String FILE_NAME = "data.txt";

        public static void writeData(ArrayList<profileBase> users) {
            try (PrintWriter writer = new PrintWriter(FILE_NAME)) {
                for (profileBase user : users) {
                    writer.println(user.getNameForButton()+ "@" + user.getProfileDefault() + "@" + user.getStatusDefault() + "@" + user.getMyFriends());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

}
