package com.company.airplane.type;

import com.company.IjsonManagement.IjsonManagement;
import com.company.airplane.Airplane;
import com.company.airplane.MotorType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Gold extends Airplane implements IjsonManagement<Gold>{
        private boolean wifiConnection;


        public Gold(String serialNumber,double fuelCapacity, double costPerKm, int maxPassengers, double maxVelocity, MotorType motorType) {
            super(serialNumber,fuelCapacity, costPerKm, maxPassengers, maxVelocity, motorType);
            wifiConnection = true;
            setFixedFee(6000);
        }

        public Gold() {
            super();
        }

        public void setWifiConnection(boolean set){
            this.wifiConnection = set;
        }

        public boolean getWifiConnection(){
            return wifiConnection;
        }

        @Override
        public List<Gold> readFile () throws IOException {

            List<Gold> goldsFromJson = null;
            File file = new File("Gold.json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            if(file.exists()) {
                try {
                    Gold[] goldsArray = mapper.readValue(file, Gold[].class); // convert JSON array to Array objects
                    goldsFromJson = Arrays.asList(mapper.readValue(file, Gold[].class)); // convert JSON array to List of objects
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                System.out.println("File is Empty");
            }
            return goldsFromJson;
        }

        @Override
        public void addToFile() {
            File file = new File("Gold.json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            try {
                if (file.createNewFile()) {
                    ArrayList<Gold> goldsArrayList = new ArrayList<>();
                    goldsArrayList.add(this);
                    mapper.writeValue(file, goldsArrayList);
                    System.out.println("--- Imprimiento en archivo ---\n");
                } else {
                    ArrayList<Gold> goldsArrayList = new ArrayList<Gold>(readFile());
                    goldsArrayList.add(this);
                    mapper.writeValue(file, goldsArrayList);
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void showFile () throws IOException {
            File file = new File("Gold.json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            if(file.exists()) {
                System.out.println("--- Contenido del Archivo ---");
                try {

                    Gold[] goldsArray = mapper.readValue(file, Gold[].class); // convert JSON array to Array objects
                    List<Gold> golds = Arrays.asList(mapper.readValue(file, Gold[].class)); // convert JSON array to List of objects
                    golds.stream().forEach(x -> System.out.println(x)); // show lists of objects

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                System.out.println("File is Empty");
            }
        }

    @Override
    public String toString() {
        return super.toString()+"\nWifiConnection:"+this.getWifiConnection()+"\nClass: Gold"+"\n----------------";
    }
}
