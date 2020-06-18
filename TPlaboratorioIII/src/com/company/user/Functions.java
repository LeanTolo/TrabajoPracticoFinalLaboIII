package com.company.user;

import com.company.IjsonManagement.IjsonManagement;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Functions implements IjsonManagement<Functions> {

    @Override
    public List readFile() throws IOException {
        List<User> usersFromJson = null;
        File file = new File("Users.json");
        ObjectMapper mapper = new ObjectMapper();

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
    public void showFile() throws IOException {

    }
}
