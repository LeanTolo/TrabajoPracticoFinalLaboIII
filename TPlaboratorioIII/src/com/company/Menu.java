package com.company;
import com.company.airplane.Airplane;
import com.company.user.Functions;
import com.company.user.User;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

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
                        User run = loginUser();
                        if(run != null){
                            userMenu(run);
                        }
                        break;
                    case 2:
                        if (loginAdmin() == 0){
                            adminMenu();
                        }
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

    public final static void clearScreen() { //no limpia la pantalla
        try{
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            }
            else {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e) {
            //  Handle any exceptions.
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
        System.out.println("\nIngrese su Nombre: ");
        newUser.setName(data.nextLine());
        System.out.println("\nIngrese su Apellido: ");
        newUser.setSurName(data.nextLine());
        System.out.println("\nIngrese su DNI: ");
        newUser.setDni(data.nextInt());
        System.out.println("\nIngrese su Contraseña:");
        newUser.setPassword(data.next());
        System.out.println("\nIngrese su Edad: ");
        newUser.setAge(data.nextInt());
        newUser.setId(UUID.randomUUID());
        newUser.setAmountSpent(0);
        System.out.println("\nLos datos Ingresados son:"+newUser.toString()+"\nIngrese 0  para cambiar los datos, o cualquier numero para continuar:");
        int input = data.nextInt();
        if (input != 0) {
            if (AddNewUSer(newUser)) {
                clearScreen();
            }
        } else {
            register();
        }
    }

    public boolean validateNewUser (User tovalidate) throws IOException {
        boolean res= true;
        Functions helper  = new Functions();
        users = helper.readFile();
        if (users != null) {
            for (User element : users) {
                if (element.getDni() == tovalidate.getDni()) {
                    res = false;
                    break;
                }
            }
        }else{
            res = true;
        }
        return res;
    }


    public boolean AddNewUSer (User toadd) throws IOException {
      boolean add = false;
      if(validateNewUser(toadd)){
              toadd.addToFile();
              add = true;
              System.out.println("Registrado Correctamente!");
      }else{
              System.out.println("El DNI ingresado ya corresponde a una cuenta de Aerotaxi, por favor ingrese otro o recupere su contraseña");
      }
        return add;
    }

//    public void login(){
//        Scanner scan = new Scanner(System.in);
//        String contraseña;
//        boolean pass;
//        int res=0;
//        User logUser = null;
//        if (logUser == null){
//            System.out.println("Ingrese DNI:");
//            //logUser = AeroTaxiSystem.validateUser(scan.nextInt());
//        }
//        do{
//            System.out.println("Ingrese contraseña:");
//            pass = scan.nextLine().equals(logUser.getPassword());
//            if (!pass)
//                System.out.println("Contraseña Incorrecta");
//        } while(!pass);
//
//       userMenu(logUser);
//    }

    public User loginUser() throws IOException {
        Scanner data = new Scanner(System.in);
        User newUser = new User();
        System.out.println("\nIngrese su Usuario: ");
        newUser.setDni(data.nextInt());
        System.out.println("\nIngrese su contraseña: ");
        newUser.setPassword(data.next());
        User res = logUser(newUser);
        if(res!= null){
            System.out.println("Ingreso exitoso, bienvenido "+res.getName());
        }else{
            System.out.println("La cuenta o contraseña ingresada no es valida, por favor, intente nuevamente");
        }
        return res;
    }

    public User logUser (User request) throws IOException {
        User res =  null;
        Functions helper  = new Functions();
        users = helper.readFile();
        for (User element:users) {
            if (element.getDni()==request.getDni()){
                if (element.getPassword().equals(request.getPassword())){
                    res = element;
                    break;
                }
            }
        }
        return res;
    }



    public void userMenu(User user) throws IOException {
        int opt = 0;
        do {
            printUserMenu();
            Functions updater = new Functions();
            Scanner scan = new Scanner(System.in);
            opt = scan.nextInt();
            switch (opt) {
                case 1:
                    Request fly = new Request();
                    double amount;
                    amount = fly.generateTicket(user.getDni());
                    user.setAmountSpent(amount);
                    updater.updateUser(user);
                    break;
                case 2:
                    //cancelar vuelo
                    break;
                case 3:
                    updater.showUserTickets(user.getDni());
                    break;
            }
        } while(opt != 0);
    }

    public void printUserMenu(){
        clearScreen();
        System.out.println("*************************************");
        System.out.println("\n1. Solicitar Vuelo");
        System.out.println("2. Cancelar Vuelo");
        System.out.println("3. Ver Reservas");
        System.out.println("0. Salir");
        System.out.println("*************************************");
    }

    public int loginAdmin(){ //logueo de administrador: andando 10/10
        Scanner scan = new Scanner(System.in);
        String user,pass;
        int res = -1;
        System.out.println("Ingrese Usuario Administrador:");
        user = scan.nextLine();
        if (user.equals("admin")){
            System.out.println("Ingrese contraseña:");
            pass = scan.nextLine();
            if (pass.equals("admin")) {
                res = 0;
            }else{
                System.out.println("Contraseña incorrecta");
            }
        }else{
            System.out.println("Usuario incorrecta");
        }
        return res;
    }


    public void adminMenu() throws IOException {
        int opt = 0;
        do {
            printAdminMenu();
            Scanner scan = new Scanner(System.in);
            opt = scan.nextInt();
            switch (opt) {
                case 1:
                    Request showTick = new Request();
                    showTick.showFlightsByDate();

                    break;
                case 2:
                    Functions helper = new Functions();
                    helper.showFile();
                    break;
                case 3:
                    //Ver Destino
                    break;

                case 4:
                    Functions loader = new Functions();
                    List<Airplane> planes = loader.readfileAirplanes();
                    for (Airplane a:planes) {
                        System.out.println(a.toString());
                    }
                    break;
                case 5:
                    Functions add = new Functions();
                    do {
                        printFlightMenu();
                        Scanner scanFligth = new Scanner(System.in);
                        opt = scanFligth.nextInt();
                        switch (opt) {
                            case 1:
                                if(add.AddGoldPlane() == true){
                                    System.out.println("Avion agregado con exito");
                                }
                                break;
                            case 2:
                                if(add.AddSilverPlane() == true){
                                    System.out.println("Avion agregado con exito");
                                }
                                break;
                            case 3:
                                if(add.AddBronzePlane() == true){
                                    System.out.println("Avion agregado con exito");
                                }
                                break;
                        }
                    } while(opt != 0);
                break;
            }
        } while(opt != 0);
    }

    public void printAdminMenu(){
        clearScreen();
        System.out.println("*************************************");
        System.out.println("\n1. Listado de Vuelos En Fecha Especifica");
        System.out.println("2. Listado de Clientes");   //(Todos los datos personales.
                                                        // La categoría del mejor avión utilizado ( Gold, Silver o Bronze ).
                                                        //Total gastado de todos sus vuelos.
        System.out.println("3. Ver Destinos y Distancias");
        System.out.println("4. Listado de Aviones");
        System.out.println("5. Agregar Aviones");
        System.out.println("0. Exit");
        System.out.println("*************************************");
    }

    public void printFlightMenu(){
        clearScreen();
        System.out.println("*************************************");
        System.out.println("\n1. GOLD PLANE");
        System.out.println("\n2. SILVER PLANE");
        System.out.println("\n3. BRONZE PLANE");
        System.out.println("\n0. Exit");
        System.out.println("*************************************");
    }
}


