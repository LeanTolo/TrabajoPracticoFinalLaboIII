package com.company;

import com.company.user.User;

import java.util.ArrayList;

public class AeroTaxiManagement {


    public User validateUser(int dni, String pass, ArrayList<User> usersData){
        User userRequest = null;
        int i =0;
       while(i < usersData.size() && userRequest == null){
           if (usersData.get(i).getDni()==dni){
               if(usersData.get(i).getPassword()== pass) {
                   userRequest = usersData.get(i);
               }else{
                   System.out.println("Clave de ingreso erronea, por favor pruebe nuevamente");
               }
           }
           i++;
       }
        if(userRequest == null){
            System.out.println("El Nombre de usuario ingresado no posee ninguna cuenta asociada, por favor registrese para ingresar");
            //call to register
        }
        return userRequest;
    }
}
