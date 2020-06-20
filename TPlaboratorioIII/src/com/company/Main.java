package com.company;

import com.company.airplane.*;
//import org.graalvm.compiler.asm.sparc.SPARCAssembler;

import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {

        //Menu menu = new Menu();
        //menu.startMenu();
        Gold avion = new Gold(150,300,5,5000, MotorType.HELICE);
        Bronze avion2 = new Bronze(0,0,5,0, MotorType.HELICE);
        Request request = new Request();
        request.addAirplaneToList(avion);
        request.generateTicket();


    }
}
