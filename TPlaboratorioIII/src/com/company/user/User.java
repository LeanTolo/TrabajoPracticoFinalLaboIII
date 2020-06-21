package com.company.user;

import com.company.IjsonManagement.IjsonManagement;
import com.company.airplane.Airplane;
import com.company.airplane.Bronze;
import com.company.airplane.Gold;
import com.company.airplane.Silver;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class User implements IjsonManagement<User> {


    private String name;
    private String surName;
    private int dni;
    private int age;
    private UUID id;
    private String password;
    private double amountSpent;
    private String bestClass;

    public User(){}

    public User(String name, String surName, int dni, int age, String password) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.surName = surName;
        this.dni = dni;
        this.age = age;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setAmountSpent(double spent){ this.amountSpent += spent;}

    public Double getAmount(){return amountSpent;};

    public void setBestClass(Airplane best){this.bestClass = checkClass(best);}

    public String getBestClass(){return bestClass;}

    public String  checkClass(Airplane best){
        if(best instanceof Gold){
            return "Gold";
        }else {
            if (best instanceof Silver) {
                return "Silver";
            }else {
                if (best instanceof Bronze){
                    return "Bronze";
                }
                else{
                    return "";
                }
            }
        }
    }



    @Override
    public String toString() {
        return "\n--- User ---" +
                "\nName:" + name +
                "\nSurName:" + surName +
                "\nDni:" + dni +
                "\nAge:" + age +
                "\nId:" + id +
                "\nPassword:" + password+
                "\nTotal Spent:" + amountSpent+
                "\nBest Plane:" + bestClass+
                "\n------------";
    }





    @Override
    public List<User> readFile () throws IOException {

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
        File file = new File("Users.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {
            if (file.createNewFile()) {
                ArrayList<User> userArrayList = new ArrayList<>();
                userArrayList.add(this);
                mapper.writeValue(file, userArrayList);
               // System.out.println("--- Imprimiento en archivo ---\n");
            } else {
                ArrayList<User> userArrayList = new ArrayList<User>(readFile());
                userArrayList.add(this);
                mapper.writeValue(file, userArrayList);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showFile () throws IOException {
        File file = new File("Users.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        if(file.exists()) {
            //System.out.println("--- Contenido del Archivo ---");
            try {

                User[] userArray = mapper.readValue(file,User[].class); // convert JSON array to Array objects
                List<User> users = Arrays.asList(mapper.readValue(file, User[].class)); // convert JSON array to List of objects
                users.stream().forEach(x -> System.out.println(x)); // show lists of objects

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("Archivo vacio");
        }
    }

}
