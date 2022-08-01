package com.jackson.json.demo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class Driver {
    public static void main(String[] args) {
        try{
            // create object mapper
            ObjectMapper objectMapper =new ObjectMapper();

            // read JSON file and map/convert to Java POJO
            // data/sample-lite.json
            Student student = objectMapper.readValue(new File("data/sample-full.json"),Student.class);

            // print student values
            System.out.println("Student name is: "+student.getFirstName()+" "+student.getLastName());

            Address tempAddress = student.getAddress();
            System.out.println("Street: "+tempAddress.getStreet()+", City: "+tempAddress.getCity());


            for(String lsn : student.getLanguages())
                System.out.print(lsn+" ");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
