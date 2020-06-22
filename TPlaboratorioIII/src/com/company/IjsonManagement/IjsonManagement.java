package com.company.IjsonManagement;

import com.company.tickets.Ticket;

import java.io.IOException;
import java.util.List;

public interface IjsonManagement<G> {

    List<G> readFile() throws IOException;
    void addToFile();

    void showFile() throws IOException;

}
