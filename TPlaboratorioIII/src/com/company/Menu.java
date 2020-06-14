package com.company;
import java.util.Scanner;

public class Menu {


    public void startMenu() {
        Scanner scanner = new Scanner(System.in);
        int option = -1; //option
        //Mientras la opción elegida sea 0, preguntamos al usuario
        while (option != 0) {
            //Try catch para evitar que el programa termine si hay un error
            try {
                System.out.println("************************\nWELCOME TO AEROTAXI SYSTEM:" +
                        "\n1.- op1" +
                        "\n2.- op2\n" +
                        "3.- op3\n" +
                        "4.- op4\n" +
                        "0.- EXIT\n************************");
                //Recoger una variable por consola
                option = Integer.parseInt(scanner.nextLine());

                //Switch case en Java
                switch (option) {
                    case 1:
                        //
                        //
                        //
                        //
                        break;
                    case 2:
                        //
                        //
                        //
                        //
                        break;
                    case 3:
                        //
                        //
                        //
                        //
                        break;
                    case 4:
                        //
                        //
                        //
                        //
                        break;
                    case 0:
                        System.out.println("Good Bye");
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
}
