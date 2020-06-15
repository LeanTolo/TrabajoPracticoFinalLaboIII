package com.company.IjsonManagement;

import java.io.IOException;
import java.util.List;

public interface IjsonManagement<G> {

    List<G> readFile() throws IOException;
    void addToFile(G element);
    void showFile() throws IOException;

    /* CLASE PARA APLICAR GENERICIDAD
    public class JsonFunctions<G> {

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


    }*/

}
