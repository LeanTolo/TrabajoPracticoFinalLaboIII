package com.company.airplane;

import com.company.IjsonManagement.IjsonManagement;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Silver extends Airplane implements IjsonManagement<Silver> {

    public Silver(){

    }

    public Silver(double fuelCapacity, double costPerKm, int maxPassengers, double maxVelocity, MotorType motorType) {
        super(fuelCapacity, costPerKm, maxPassengers, maxVelocity, motorType);
        setFixedFee(4000);
    }


    @Override
    public List<Silver> readFile () throws IOException {

        List<Silver> silversFromJson = null;
        File file = new File("Silver.json");
        ObjectMapper mapper = new ObjectMapper();

        if(file.exists()) {
            try {
                Silver[] silversArray = mapper.readValue(file,Silver[].class); // convert JSON array to Array objects
                silversFromJson = Arrays.asList(mapper.readValue(file, Silver[].class)); // convert JSON array to List of objects
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("File is Empty");
        }
        return silversFromJson;
    }

    @Override
    public void addToFile() {
        File file = new File("Silver.json");
        ObjectMapper mapper = new ObjectMapper();
        try {
            if (file.createNewFile()) {
                ArrayList<Silver> silversArrayList = new ArrayList<>();
                silversArrayList.add(this);
                mapper.writeValue(file, silversArrayList);
                System.out.println("--- Imprimiento en archivo ---\n");
            } else {
                ArrayList<Silver> silversArrayList = new ArrayList<Silver>(readFile());
                silversArrayList.add(this);
                mapper.writeValue(file, silversArrayList);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showFile () throws IOException {
        File file = new File("Silver.json");
        ObjectMapper mapper = new ObjectMapper();

        if(file.exists()) {
            System.out.println("--- Contenido del Archivo ---");
            try {

                Silver[] silversArray = mapper.readValue(file,Silver[].class); // convert JSON array to Array objects
                List<Silver> silvers = Arrays.asList(mapper.readValue(file, Silver[].class)); // convert JSON array to List of objects
                silvers.stream().forEach(x -> System.out.println(x)); // show lists of objects

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("Archivo vacio");
        }
    }

}
