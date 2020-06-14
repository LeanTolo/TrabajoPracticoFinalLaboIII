package com.company;

import com.company.user.User;

public class AeroTaxiManagement {


    public User validateUser(int dni){
        User user = null;
        int i =0;
     //   while(i < LISTA DE USUARIOS DEL ARCHIVO.size() && user == null){
     //       if(LISTA DE USUARIOS DEL ARCHIVO.get(i).getDni() == dni) user = this.LISTA DE USUARIOS DEL ARCHIVO.get(i);
    //        i++;
      //  }
        if(user == null){
            System.out.println("El DNI ingresado no se encuentra en el sistema.");
        }
        return user;
    }


}
