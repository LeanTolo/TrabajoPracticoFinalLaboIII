package com.company;
import com.company.user.User;

import java.io.IOException;
import java.util.Scanner;

public class Menu {


    public void startMenu() {
        Scanner scanner = new Scanner(System.in);
        int option = -1; //option
        //Mientras la opción elegida sea 0, preguntamos al usuario
        while (option != 0) {
            //Try catch para que el programa no cierre
            try {
                printMenuOptions();
                //Recoger una variable por consola
                option = Integer.parseInt(scanner.nextLine());

                //Switch case en Java
                switch (option) {
                    case 1:
                        login();
                        break;
                    case 2:
                      //  loginAdmin();
                        break;
                    case 3:
                        register();
                        break;
                    case 0:
                        System.out.println("Thanks for use AEROTAXI® Service");
                        break;
                    default:
                        System.out.println("No option for number ingressed");
                        break;
                }

                System.out.println("\n");

            } catch (Exception e) {
                System.out.println("Uoops! Error!");
            }
        }
    }

    public void printMenuOptions(){
        System.out.println("************************\nWELCOME TO AEROTAXI® SYSTEM:" +
                "\n1.- Login" +
                "\n2.- Login As Admin\n" +
                "3.- Register\n" +
                "0.- EXIT\n************************");
    }

    public void register() throws IOException {

        Runtime.getRuntime().exec("cls");
        Scanner data = new Scanner(System.in);
        User newUser = new User();
        System.out.println("\nInsert Name: \n");
        newUser.setName(data.nextLine());
        System.out.println("\nInsert SurName: \n");
        newUser.setSurName(data.nextLine());
        System.out.println("\nInsert DNI: \n");
        newUser.setDni(data.nextInt());
        System.out.println("\nInsert Age: \n");
        newUser.setAge(data.nextInt());
        System.out.println("\nInsert Password: \n");
        newUser.setPassword(data.nextLine());
        newUser.addToFile(newUser);
        printMenuOptions();
    }

    public void login(){
        Scanner scan = new Scanner(System.in);
        String contraseña;
        boolean pass;
        int res=0;
        User logUser = null;
        if (logUser == null){
            System.out.println("Ingrese DNI:");
            //logUser = AeroTaxiSystem.validateUser(scan.nextInt());
        }
        do{
            System.out.println("Ingrese contraseña:");
            pass = scan.nextLine().equals(logUser.getPassword());
            if (!pass)
                System.out.println("Password Incorrect");
        } while(!pass);

       userMenu(logUser);
    }



    public void userMenu(User user) {
        int opt = 0;
        do {
            printUserMenu();
            Scanner scan = new Scanner(System.in);
            opt = scan.nextInt();
            switch (opt) {
                case 1:
                    // solicitar vuelo
                    break;
                case 2:
                    //cancelar vuelo
                    break;
                case 3:
                    // ver reservas
                    break;
            }
        } while(opt != 4);
    }

    public void printUserMenu(){
        System.out.println("*************************************");
        System.out.println("\n1. Request Flight");
        System.out.println("2. Cancel Flight");
        System.out.println("3. See Reservations");
        System.out.println("100. Pedro fijate si van mas cosas");
        System.out.println("4. Exit");
        System.out.println("*************************************");
    }
}


