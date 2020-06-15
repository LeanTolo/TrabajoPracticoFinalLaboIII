package com.company;

import com.company.tickets.City;
import com.company.tickets.Ticket;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class Request {
    private TreeSet<Ticket> treeSet;
    private Ticket ticket;

    public Request() {
        treeSet = new TreeSet<>();
        ticket = new Ticket();
    }




    //4. Ahora el usuario debe seleccionar un avión. El sistema se encargará de
    //mostrar los aviones disponibles para esa fecha y el usuario elige el deseado.
    //5. Por último, el sistema debe mostrar el costo total del vuelo y el usuario
    //deberá confirmar para generar el vuelo.



    public int choosePassengersQuantity(){
        System.out.println("Ingrese cantidad de pasajeros:");
        int rta;
        do{
            rta = enterNumber();
            if(rta < 1){
                System.out.println("Ingrese una cantidad valida");
            }
        }while(rta < 1);
        return rta;
    } //3. El usuario debe indicar la cantidad de acompañantes que tendrá en el vuelo.

    public City chooseOrigin(){ //2. Seleccionar el origen de su vuelo y posteriormente el destino.
        int op;
        int i = 1;
        City city = null;
        for (City value: EnumSet.allOf(City.class)) {
            System.out.println(i+":"+value);
            i++;
        }
        do{
            op = enterNumber();
            switch (op) {
                case 1:
                    city = City.BUENOSAIRES;
                    break;
                case 2:
                    city = City.MONTEVIDEO;
                    break;
                case 3:
                    city = City.CORDOBA;
                    break;
                case 4:
                    city = City.SANTIAGO;
                    break;
                default:
                    System.out.println("Ciudad no existente");
                    break;
            }
        }while(op < 1 || op > 4) ;
        return city;
    }  //No es el codigo mas lindo que hice en mi vida
    public City chooseDestination(City origin){
        int op;
        int i = 1;
        City city = null;
        EnumSet<City> enumSet = EnumSet.allOf(City.class);
        enumSet.remove(origin);
        Iterator<City> iterator = enumSet.iterator();
        for (City value: enumSet) {
            System.out.println(i+":"+value);
            i++;
        }
        do{
            op = enterNumber();
            switch (op) {
                case 1:
                    city = iterator.next();
                    break;
                case 2:
                    iterator.next();
                    city = iterator.next();
                    break;
                case 3:
                    iterator.next();
                    iterator.next();
                    city = iterator.next();
                    break;
                default:
                    System.out.println("Ciudad no existente");
                    break;
            }
            if(op < 1 || op > 3){
                iterator = enumSet.iterator();
            }
        }while(op < 1 || op > 3) ;
        System.out.println(city);
        return city;
    }  //Repito, es horrendo, pero funciona, y muy bien...

    public LocalDate chooseDate(){ //1. Inicialmente indicar la fecha deseada para realizar un vuelo.
        int year = pickYear();
        int month = pickMonth();
        int day = pickDay();
        LocalDate date = LocalDate.of(year,month,day);
        return date;
    }
    private int pickYear(){
        System.out.println("Ingrese el año que desea viajar");
        int op = enterNumber();
        while(op < 2020 || op > 2030){
            System.out.println("Fecha no valida\n Ingrese una valida:");
            op = enterNumber();
        }
        return op;
    }
    private int pickMonth(){
        System.out.println("Ingrese el mes que desea viajar");
        int op = enterNumber();
        while(op < 1 || op > 12){
            System.out.println("Fecha no valida\n Ingrese una valida:");
            op = enterNumber();
        }
        return op;
    }
    private int pickDay(){
        System.out.println("Ingrese el dia que desea viajar");
        int op = enterNumber();
        while(op < 1 || op > 31){
            System.out.println("Fecha no valida\n Ingrese una valida:");
            op = enterNumber();
        }
        return op;
    }

    private int enterNumber(){
        int option;
        Scanner scanner = new Scanner(System.in);
        option = Integer.parseInt(scanner.nextLine());
        return option;
    }


}
