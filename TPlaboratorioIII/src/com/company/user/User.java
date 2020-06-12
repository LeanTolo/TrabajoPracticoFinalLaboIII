package com.company.user;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {


    private String name;
    private String surName;
    private int dni;
    private int age;
    private UUID id;
    private String password;

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

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", dni=" + dni +
                ", age=" + age +
                ", id=" + id +
                ", password='" + password + '\'' +
                '}';
    }


    public List<User> readFile(){
      //  File myfile = new File(path);
        return null;
    }

    public boolean writeFile(User element){
        File file = new File("archives\\Users.json");

        try{
            if (file.createNewFile()){ //Crea el archivo si no existe
                ArrayList<User> usersRead = new ArrayList<>();
                usersRead.add(element);
            }else{
                ArrayList<User> usersRead = new ArrayList<User>(readFile()); //creo usersRead con los usuarios existentes en el archivo
                usersRead.add(element);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean writeArrayToFile(List<User> elements) {
        File file = new File("archives\\Users.json");

        try {
            if(file.createNewFile()){//Crea el archivo si no existe
                ArrayList<User> usersArrayList = new ArrayList<>();
                usersArrayList.addAll(elements);
            }else{
                ArrayList<User> readedValues = new ArrayList<User>(readFile());
                readedValues.addAll(elements);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
