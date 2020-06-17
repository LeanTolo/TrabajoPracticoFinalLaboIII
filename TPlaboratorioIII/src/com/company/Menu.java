package com.company;
import com.company.user.Functions;
import com.company.user.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    List<User> users = new ArrayList<>();

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
        Scanner data = new Scanner(System.in);
        User newUser = new User();
        System.out.println("\nInsert Name: ");
        newUser.setName(data.nextLine());
        System.out.println("\nInsert SurName: ");
        newUser.setSurName(data.nextLine());
        System.out.println("\nInsert DNI: ");
        newUser.setDni(data.nextInt());
        System.out.println("\nInsert Password:");
        newUser.setPassword(data.next());
        System.out.println("\nInsert Age: ");
        newUser.setAge(data.nextInt());
        System.out.println("\nLos datos Ingresados son:"+newUser.toString()+"\nIngrese 0  para cambiar los datos, o cualquier numero para continuar:");
        int input = data.nextInt();
        if (input != 0) {
            if (AddNewUSer(newUser)) {
                printMenuOptions();
            }
        } else {
            register();
        }
    }

    public boolean validateNewUSer (User tovalidate) throws IOException {
        boolean res= true;
        Functions helper  = new Functions();
        users = helper.readFile();
        for (User element:users) {
            if (element.getDni()==tovalidate.getDni()){
                res=false;
                break;
            }
        }
        return res;
    }

    public boolean AddNewUSer (User toadd) throws IOException {
      boolean add = false;
      if(validateNewUSer(toadd)){
              toadd.addToFile(toadd);
              add = true;
              System.out.println("Registrado Correctamente!");
      }else{
              System.out.println("El DNI ingresado ya corresponde a una cuenta de Aerotaxi, por favor ingrese otro o recupere su contraseña");
      }
        return add;
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


