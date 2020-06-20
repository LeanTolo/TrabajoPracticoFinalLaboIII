package com.company;

import com.company.airplane.Bronze;
import com.company.airplane.Gold;
import com.company.airplane.MotorType;
import com.company.airplane.Silver;
import com.company.user.User;
//import org.graalvm.compiler.asm.sparc.SPARCAssembler;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) throws IOException {


        Gold avion = new Gold(0,0,5,0, MotorType.HELICE);
        Silver avion1 = new Silver(0,0,5,0, MotorType.HELICE);
        Bronze avion2 = new Bronze(0,0,5,0, MotorType.HELICE);
        Request request = new Request();
        request.addAirplaneToList(avion);
        request.addAirplaneToList(avion1);
        request.addAirplaneToList(avion2);

        Menu menu = new Menu();
        menu.startMenu();
    }
}
