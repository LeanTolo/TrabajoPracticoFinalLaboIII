package com.company.airplane;

import com.company.IjsonManagement.IjsonManagement;
import com.company.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bronze extends Airplane implements IjsonManagement<Bronze> {
    public Bronze(double fuelCapacity, double costPerKm, int maxPassengers, double maxVelocity, MotorType motorType) {
        super(fuelCapacity, costPerKm, maxPassengers, maxVelocity, motorType, 3000);
    }


    @Override
    public List<Bronze> readFile () throws IOException {

        List<Bronze> bronzesFromJson = null;
        File file = new File("Bronze.json");
        ObjectMapper mapper = new ObjectMapper();

        if(file.exists()) {
            try {
                Bronze[] bronzesArray = mapper.readValue(file,Bronze[].class); // convert JSON array to Array objects
                bronzesFromJson = Arrays.asList(mapper.readValue(file, Bronze[].class)); // convert JSON array to List of objects
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("File is Empty");
        }
        return bronzesFromJson;
    }

    @Override
    public void addToFile(Bronze element) {
        File file = new File("Bronze.json");
        ObjectMapper mapper = new ObjectMapper();
        try {
            if (file.createNewFile()) {
                ArrayList<Bronze> bronzesArrayList = new ArrayList<>();
                bronzesArrayList.add(element);
                mapper.writeValue(file, bronzesArrayList);
                System.out.println("--- Imprimiento en archivo ---\n");
            } else {
                ArrayList<Bronze> bronzesArrayList = new ArrayList<Bronze>(readFile());
                bronzesArrayList.add(element);
                mapper.writeValue(file, bronzesArrayList);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showFile () throws IOException {
        File file = new File("Bronze.json");
        ObjectMapper mapper = new ObjectMapper();

        if(file.exists()) {
            System.out.println("--- Contenido del Archivo ---");
            try {

                Bronze[] bronzesArray = mapper.readValue(file,Bronze[].class); // convert JSON array to Array objects
                List<Bronze> bronzes = Arrays.asList(mapper.readValue(file, Bronze[].class)); // convert JSON array to List of objects
                bronzes.stream().forEach(x -> System.out.println(x)); // show lists of objects

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("Archivo vacio");
        }
    }

}
