package client;

import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

public class Student extends Member {

    public Student(String firstName, String lastName, String birthDate, String fieldActivity, String registrationNumber) {
        super(firstName, lastName, birthDate, fieldActivity, registrationNumber);
    }
    public Student(String firstName, String lastName, String email, String birthDate, String status, String fieldActivity, String registrationNumber) {
        super(firstName, lastName, email, birthDate, status, fieldActivity, registrationNumber);
    }

}
