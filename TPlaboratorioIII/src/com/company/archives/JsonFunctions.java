package com.company.archives;
import com.company.airplane.type.Bronze;
import com.company.airplane.type.Gold;
import com.company.airplane.type.Silver;
import com.company.user.User;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
//import jdk.internal.org.objectweb.asm.TypeReference;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;








public class JsonFunctions<G> {
/*
    private Class<G> gClass;


    public List<G> readFile(Class className) throws ClassNotFoundException {
        String fileName = "archives\\";

        if (className.getName().equals(User.class.getName())){//CON ELSE IF E IMPLEMENTANDO LO MISMO, PUEDEN CREARSE DISTINTOS ARCHIVOS (GENERICIDAD PAPA)
            fileName += "Users.json";
        }else if (className.getName().equals(Gold.class.getName())){
            fileName += "GoldType.json";
        }else if (className.getName().equals(Silver.class.getName())){
            fileName += "SilverType.json";
        }else if (className.getName().equals(Bronze.class.getName())){
            fileName += "BronzeType.json";
        }else{
            throw new ClassNotFoundException("ERROR: Can't Find CLASS support");
        }

        File file = new File(fileName);
        ObjectMapper mapper = new ObjectMapper();
        List<G> readFileElements = null;

        if (file.exists()){
            try {
                FileInputStream fileInputStream = new FileInputStream(file);


             //   JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, test);
               // readFileElements = mapper.readValue(fileInputStream, type);

                //User[] userArray = mapper.readValue(file,User[].class); // convert JSON array to Array objects
                readFileElements = Arrays.asList(mapper.readValue(file,gClass));

            } catch (IOException e) {
                e.printStackTrace();
            }
            return readFileElements;
        }
        else
            return null;
    }

*/

}
