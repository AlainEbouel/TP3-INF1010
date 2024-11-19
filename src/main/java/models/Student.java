package models;

import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.rmi.*;

public class Student extends Member {
    private final SimpleStringProperty registrationNumber;

    public Student(String firstName, String lastName, Date birthDate, String fieldActivity) {
        super(firstName, lastName, birthDate, fieldActivity);
        this.registrationNumber = new SimpleStringProperty(generateRegistrationNumber());
    }
    public Student(String firstName, String lastName, String email, Date birthDate, String status, String fieldActivity, String phoneNumber) {
        super(firstName, lastName, email, birthDate, status, fieldActivity);
        this.registrationNumber = new SimpleStringProperty(phoneNumber);
    }


    public String getRegistrationNumber() {
        return registrationNumber.getValue();
    }

    public SimpleStringProperty registrationNumberProperty() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber.setValue(registrationNumber);
    }

    public void setNewRegistrationNumber() {
        this.registrationNumber.setValue(generateRegistrationNumber());
    }

    private String generateRegistrationNumber(){
        return super.getLastName().substring(0,3).toUpperCase() + super.getFirstName().charAt(0) + super.getBirthDate().replace("-", "");
    }
}
