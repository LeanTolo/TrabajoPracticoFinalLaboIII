package com.company;

import com.company.airplane.*;
import com.company.tickets.City;
import com.company.tickets.Ticket;
//import org.graalvm.compiler.asm.sparc.SPARCAssembler;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) throws IOException {

        Menu menu = new Menu();
        menu.startMenu();
    }
}
