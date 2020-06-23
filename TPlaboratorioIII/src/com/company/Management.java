package com.company;

import com.company.IjsonManagement.IjsonManagement;
import com.company.airplane.*;
import com.company.airplane.type.Silver;
import com.company.airplane.type.Gold;
import com.company.airplane.type.Bronze;
import com.company.ticket.Ticket;
import com.company.ticket.TicketManagement;
import com.company.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class Management implements IjsonManagement<Management> {

    public double cancelTicket(int userDni) throws IOException {
        List<Ticket> ticketList = readFileTickets();
        TicketManagement helper = new TicketManagement();
        String serialNumber;
        ticketList.sort(Ticket::compareTo);
        double amountSpent = 0;
        int i = 0;
        int op;
        HashSet<Integer> o = new HashSet<>();
        for(Ticket ticket : ticketList){
            if(ticket.getUserDni() == userDni){
                System.out.println(i+":"+ticket.toString());
                o.add(i);
            }
            i++;
        }
        System.out.println("Ingrese ticket a cancelar");
        do{
            op = enterNumber();
            if(!o.contains(op)){
                System.out.println("Ingrese un valor valido");
            }
        }while(!o.contains(op));
        Ticket ticket = ticketList.get(op);
        if(checkDate(ticket.getDate())){
            ticket.setCanceled(true);
            updateTicket(ticket);
            amountSpent = ticket.getPrice();
            serialNumber = ticket.getAirplaneSerialNumber();
            helper.removeDatePlane(serialNumber,ticket.getDate());
        }
        return amountSpent;
    }

    private boolean checkDate(LocalDate date1){
        LocalDate date = LocalDate.now();
        int i = date1.compareTo(date);
        boolean rta = false;
        switch (i){
            case 0:
                System.out.println("No puedes cancelar el mismo dia del vuelo");
                break;
            case 1:
                if (date.getYear() == date1.getYear()) {
                    if (date.getMonth() == date1.getMonth()) {
                        if (date.getDayOfMonth()+1 == date1.getDayOfMonth()) {
                            System.out.println("No puedes cancelar solo con un dia de anticipacion");
                        }
                    }
                }
                break;
            default:
                rta = true;
                break;
        }
       return rta;
    }//Chequea que haya mas de un dia de anticipacion para cancelar, retorna true si se puede.

    private int enterNumber(){
        int option;
        Scanner scanner = new Scanner(System.in);
        option = scanner.nextInt();
        return option;
    }

    public void showUserTickets(int userDni) throws IOException {
        List<Ticket> ticketList = readFileTickets();
        ticketList.sort(Ticket::compareTo);
        int i=0;
        for(Ticket ticket : ticketList){
            if(ticket.getUserDni() == userDni && ticket.getCanceled() == false){
                i++;
                System.out.println(ticket.toString());
            }
        }
        if(i == 0){
            System.out.println("Usted no tiene reservas");
        }
    }

    public List readfileAirplanes () throws IOException {
        List<Airplane> airplanes = new ArrayList<>();
        airplanes = addGoldToArray(airplanes);
        airplanes = addSilverToArray(airplanes);
        airplanes = addBronzeToArray(airplanes);

        return airplanes;
    }

    public List addGoldToArray(List<Airplane> airplanes) throws IOException {
        Gold gold = new Gold();
        List<Gold> golds = gold.readFile();
        if(golds != null){
            airplanes.addAll(golds);
        }
        return airplanes;
    }

    public List addSilverToArray(List<Airplane> airplanes) throws IOException {
        Silver silv = new Silver();
        List<Silver> silvers = silv.readFile();
        if(silvers != null){
            airplanes.addAll(silvers);
        }
        return airplanes;
    }

    public List addBronzeToArray(List<Airplane> airplanes) throws IOException {
        Bronze bron = new Bronze();
        List<Bronze> bronzes = bron.readFile();
        if(bronzes != null){
            airplanes.addAll(bronzes);
        }
        return airplanes;
    }

    public List readFileTickets() throws IOException {
        List<Ticket> ticketList = new ArrayList<>();
        ticketList = addTicketToList(ticketList);
        return ticketList;
    }

    public List addTicketToList(List<Ticket> ticketList) throws IOException{
        Ticket ticket = new Ticket();
        List<Ticket> tickets = ticket.readFile();
        if(tickets != null){
            ticketList.addAll(tickets);
        }
        return ticketList;
    }

    public void updateUser (User toUpdate){
        File file = new File("Users.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {
            ArrayList<User> userArrayList = new ArrayList<User>(readFile());
            int i = 0;
            for (User a:userArrayList) {
                if(a.getDni()==toUpdate.getDni()){
                    userArrayList.set(i,toUpdate);
                }
                i++;
            }
            mapper.writeValue(file, userArrayList);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateTicket (Ticket toUpdate){
        File file = new File("Ticket.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {
            ArrayList<Ticket> ticketArrayList = new ArrayList<Ticket>(readFileTickets());
            int i = 0;
            for (Ticket a:ticketArrayList) {
                if(a.equals(toUpdate)){
                    ticketArrayList.set(i,toUpdate);
                }
                i++;
            }
            mapper.writeValue(file, ticketArrayList);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User refresh(User toRefresh) throws IOException {
        User res = null;
        List<User> users = readFile();
        for ( User element:users) {
            if (element.getDni()==toRefresh.getDni()){
             res = element;
            }
        }
        return res;
    }

    public void updateGold (Gold toUpdate){
        File file = new File("Gold.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {
            Gold aux = new Gold();
            ArrayList<Gold> goldArrayList = new ArrayList<Gold>(aux.readFile());
            int i = 0;
            for (Gold a:goldArrayList) {
                if(a.equals(toUpdate)){
                    goldArrayList.set(i,toUpdate);
                }
                i++;
            }
            mapper.writeValue(file, goldArrayList);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateSilver (Silver toUpdate){
        File file = new File("Silver.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {
            Silver aux = new Silver();
            ArrayList<Silver> silverArrayList = new ArrayList<Silver>(aux.readFile());
            int i = 0;
            for (Silver a:silverArrayList) {
                if(a.equals(toUpdate)){
                    silverArrayList.set(i,toUpdate);
                }
                i++;
            }
            mapper.writeValue(file, silverArrayList);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateBronze (Bronze toUpdate){
        File file = new File("Bronze.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {
            Bronze aux = new Bronze();
            ArrayList<Bronze> bronzeArrayList = new ArrayList<Bronze>(aux.readFile());
            int i = 0;
            for (Bronze a:bronzeArrayList) {
                if(a.equals(toUpdate)){
                    bronzeArrayList.set(i,toUpdate);
                }
                i++;
            }
            mapper.writeValue(file, bronzeArrayList);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean addGoldPlane(){
        boolean res = false;
        Scanner data = new Scanner(System.in);
        Gold golden = new Gold();
        golden = (Gold) formPlanes(golden);
        golden.setDatesEmpty();
        golden.setCatering(true);
        System.out.println("\nLos datos Ingresados son:\n"+golden.toString()+"\nIngrese 0  para cambiar los datos, o cualquier numero para continuar:");
        int input = data.nextInt();
        if (input != 0) {
            golden.addToFile();
            res = true;
        } else {
            addGoldPlane();
        }
        return res;
    }

    public boolean addSilverPlane(){
        boolean res = false;
        Scanner data = new Scanner(System.in);
        Silver silverNew  = new Silver();
        silverNew = (Silver) formPlanes(silverNew);
        silverNew.setDatesEmpty();
        silverNew.setCatering(true);
        System.out.println("\nLos datos Ingresados son:\n"+silverNew.toString()+"\nIngrese 0  para cambiar los datos, o cualquier numero para continuar:");
        int input = data.nextInt();
        if (input != 0) {
            silverNew.addToFile();
            res = true;
        } else {
            addSilverPlane();
        }
        return res;
    }

    public boolean addBronzePlane(){
        boolean res = false;
        Scanner data = new Scanner(System.in);
        Bronze bronzeNew  = new Bronze();
        bronzeNew = (Bronze) formPlanes (bronzeNew);
        bronzeNew.setDatesEmpty();
        System.out.println("\nLos datos Ingresados son:\n"+bronzeNew.toString()+"\nIngrese 0  para cambiar los datos, o cualquier numero para continuar:");
        int input = data.nextInt();
        if (input != 0) {
            bronzeNew.addToFile();
            res = true;
        } else {
            addBronzePlane();
        }
        return res;
    }

    public boolean checkCost(double cost){
        boolean res = false;
        if(cost>=150 && cost<=300){
            res = true;
        }
        return res;
    }

    public Airplane formPlanes (Airplane toAdd){
        Scanner data = new Scanner(System.in);
        System.out.println("\nIngrese SerialNumber:");
        toAdd.setSerialNumber(data.nextLine());
        System.out.println("\nIngrese capacidad de combustible: ");
        toAdd.setFuelCapacity(data.nextDouble());
        System.out.println("\nIngrese costo por kilometros: ");
        Double value = data.nextDouble();
        Boolean cost = checkCost(value);
        while (!cost){
            System.out.println("El valor debe estar entre 150 y 300, ingrese un valor valido:");
            value = data.nextDouble();
            cost = checkCost(value);
        }
        toAdd.setCostPerKm(value);
        System.out.println("\nIngrese cantidad maxima de pasajeros: ");
        toAdd.setMaxPassengers(data.nextInt());
        System.out.println("\nIngrese velocidad maxima:");
        toAdd.setMaxVelocity(data.nextDouble());
        addFixedFee(toAdd);
        System.out.println("\nIngrese tipo de motor\n1-REACCION\n2-HELICE\n3-PISTONES:");
        int motor = data.nextInt();
        while (!optionCheck(motor)){
            System.out.println("\nOpcion ingresada no valida.");
            System.out.println("\nIngrese tipo de motor\n1-REACCION\n2-HELICE\n3PISTONES:");
            motor = data.nextInt();
        }
        toAdd.setMotorType(getMotor(motor));
        if( toAdd instanceof Gold){
            System.out.println("\nPosee WIFI? 1-SI  AnyNumber-NO:");
            if (data.nextInt()==1){
                ((Gold) toAdd).setWifiConnection(true);
            }else{
                ((Gold) toAdd).setWifiConnection(false);
            }
        }
        return toAdd;
    }

    public void addFixedFee (Airplane toAdd){
        if (toAdd instanceof Gold){
            toAdd.setFixedFee(6000);
        }else{
            if (toAdd instanceof Silver){
                toAdd.setFixedFee(4000);
            }else{
                toAdd.setFixedFee(3000);
            }
        }
    }

    public Boolean optionCheck (int choice){
        if (choice == 1 || choice == 2 || choice == 3){
            return true;
        }
        return false;
    }

    public MotorType getMotor(int choice){
        MotorType res = null;
        if (choice == 1){
            res = MotorType.REACCION;
        }else {
            if (choice == 2) {
                res = MotorType.HELICE;
            } else {
                if (choice == 3) {
                    res = MotorType.PISTONES;
                }
            }
        }
        return res;
    }

   public void updateUserCategory(String SerialPlane, int dni) throws IOException {
        User toupdate = new User();
        List<Airplane> planes = readfileAirplanes();
       for (Airplane element: planes) {
           if (element.getSerialNumber().equals(SerialPlane)){
               String claseToAdd = toupdate.checkClass(element);
               addBestClass(claseToAdd,dni);
           }
       }
   }

    public void addBestClass (String bestClass, int dni) throws IOException {
        List<User> users = readFile();
        for (User element:users) {
            if (element.getDni()==dni){
                setClass(bestClass,element.getBestClass(),element);
            }
        }
    }

    public void setClass(String toAdd, String toCompare, User toUpdate){
        Management updater = new Management();
        if (toCompare.equals("Empty")){
            toUpdate.setBestClass(toAdd);
        }else {
            if(toCompare.equals("Bronze")){
                if(toAdd.equals("Gold")||toAdd.equals("Silver")){
                    toUpdate.setBestClass(toAdd);
                }
            }else{
                if (toCompare.equals("Silver")){
                    if (toAdd.equals("Gold")){
                        toUpdate.setBestClass(toAdd);
                    }
                }
            }
        }
        updater.updateUser(toUpdate);
    }

    @Override
    public List readFile() throws IOException {
        return getList();
    }

    public static List getList() {
        List<User> usersFromJson = null;
        File file = new File("Users.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        if(file.exists()) {
            try {
                User[] userArray = mapper.readValue(file,User[].class); // convert JSON array to Array objects
                usersFromJson = Arrays.asList(mapper.readValue(file, User[].class)); // convert JSON array to List of objects
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("File is Empty");
        }
        return usersFromJson;
    }

    @Override
    public void addToFile() {

    }

    @Override
    public void showFile () throws IOException {
        File file = new File("Users.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        if(file.exists()) {
            System.out.println("--- Contenido del Archivo ---");
            showFileDoubleCode(file, mapper);
        }else{
            System.out.println("Archivo vacio");
        }
    }

    public static void showFileDoubleCode(File file, ObjectMapper mapper) {
        try{
            User[] userArray = mapper.readValue(file,User[].class); // convert JSON array to Array objects
            List<User> users = Arrays.asList(mapper.readValue(file, User[].class)); // convert JSON array to List of objects
            users.stream().forEach(x -> System.out.println(x)); // show lists of objects
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
