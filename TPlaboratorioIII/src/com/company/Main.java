package com.company;

import com.company.user.User;

public class Main {

    public static void main(String[] args) {



        User testUSUARIO = new User("Lean", "Tolo", 111111, 22, "123456");
        User testUSUARIO2 = new User("Lau", "Nieves", 222222, 22, "789456");
        User testUSUARIO3 = new User("Pedro", "Vago", 333333, 22, "123789");

        testUSUARIO.addUserToFile(testUSUARIO);
        testUSUARIO2.addUserToFile(testUSUARIO2);
        testUSUARIO3.addUserToFile(testUSUARIO3);

        testUSUARIO3.readFile();
    }
}
