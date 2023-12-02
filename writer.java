package com.example.project1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class writer extends parent {

     static String FILE_NAME = "data.txt";



    public static void writeData(ArrayList<profileBase> users) {
         String previousData = "";

        Scanner input= new Scanner(FILE_NAME);
        try {
            new FileWriter(FILE_NAME, false).close();
            PrintWriter printWriter = new PrintWriter(new FileWriter(FILE_NAME, true));
            for (profileBase user : users) {
                if (user.getPicPathRR() == null)
                    user.getPicPath("No-Image");
                if (user.getFinSatusRR() == null)
                    user.getFinStatus("No-current-status");
                if (user.getMyFriendsRR().equals("["))
                    user.getMyFriendsUpdate("No-Friends");

                printWriter.println(user.getNameForButton() + "@" + user.getPicPathRR() + "@" + user.getFinSatusRR() + "@" + user.getMyFriendsRR());
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
