package com.company;

import com.company.user.User;
import com.company.user.Functions;

import java.util.List;

public class AeroTaxiManagement {


    public User validateUser(int dni, String pass, List<User> usersData){
        User userRequest = null;
        int message = -1;
        for (User value:usersData) {
            if(value.getDni()==dni){
                if(value.getPassword().equals(pass)) {
                    userRequest = value;
                    break;
                }else{
                    System.out.println("Clave de ingreso erronea, por favor pruebe nuevamente");
                    message = 0;
                    break;
                }
            }

        }
        if(userRequest == null && message == -1){
                System.out.println("El Nombre de usuario ingresado no posee ninguna cuenta asociada, por favor registrese para ingresar");
            //call to register
        }
        return userRequest;
    }

    public void RegisterUser(int dni, String password, List<User>Users){

    }
}
