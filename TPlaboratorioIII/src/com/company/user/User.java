package com.company.user;

import com.company.archives.JsonFunctions;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User extends JsonFunctions {


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




    public void writeFile(User element)      {
        //String path = "C:\\Users\\Lnieves\\IdeaProjects\\TrabajoPracticoFinalLaboIII\\TPlaboratorioIII\\archives\\users.txt";
        File file = new File("Users.json");
        ObjectMapper mapper = new ObjectMapper();

        if (file.exists()) {
            try {
                System.out.println("--- Imprimiento en archivo ---\n" + element);
                mapper.writeValue(file, element);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("El archivo no existe, crealo papu");
        }
    }

    public void readFile ()  {
        File file = new File("Users.json");
        ObjectMapper mapper = new ObjectMapper();
        if(file.exists()) {
            System.out.println("--- Contenido del Archivo ---");
            try {
                User usuario = mapper.readValue(file, User.class);
                System.out.println("" + usuario.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("Archivo vacio");
        }
    }

    public boolean writeArrayToFile(List<User> elements) {
        File file = new File("archives\\"+"Users.json");
        ObjectMapper mapper = new ObjectMapper();

        try {

            if(file.createNewFile()){//Crea el archivo si no existe
                ArrayList<User> usersArrayList = new ArrayList<>();
                usersArrayList.addAll(elements);
                mapper.writeValue(file,usersArrayList);
            }else{
            //
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
