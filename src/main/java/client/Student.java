package client;

import java.util.ArrayList;

public class Student extends Member {
    ArrayList<String> data;
    public Student(String firstName, String lastName, String birthDate, String status, String activityField) {
        super(firstName, lastName, birthDate, status, activityField);
        compileData(firstName,lastName,super.getEmail(),birthDate, status, activityField, super.getRegistrationNumber());

    }
    public Student(String firstName, String lastName, String email, String birthDate, String status, String fieldActivity, String registrationNumber) {
        super(firstName, lastName, email, birthDate, status, fieldActivity, registrationNumber);
        compileData(firstName, lastName, email, birthDate, status, fieldActivity, registrationNumber);
    }

    private void  compileData(String firstName, String lastName, String email, String birthDate, String status, String activityField, String registrationNumber){
        data = new ArrayList<>();
        data.add(firstName);
        data.add(lastName);
        data.add(email);
        data.add(birthDate);
        data.add(status);
        data.add(activityField);
        data.add(registrationNumber);
    }
    public ArrayList<String>memberData(){
        return data;
    }

}
