package com.company.user;
import java.util.Scanner;

public class User {

    private String name;
    private String surName;
    private int dni;
    private int age;




    public void registerUser(){
            Scanner in = new Scanner (System.in);
            System.out.println("Enter Your Name: \n");
            this.name = in.nextLine();
            System.out.println("Enter Your SurName: \n");
            this.surName = in.nextLine();
            System.out.println("Enter Your DNI: \n");
            this.dni = in.nextInt();
            System.out.println("Enter Your Age: \n");
            this.age = in.nextInt();
            //// SAVE ON JSON
    }

}
