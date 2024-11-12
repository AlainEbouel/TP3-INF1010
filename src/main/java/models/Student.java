package models;

import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Student extends Member {
    private SimpleStringProperty registrationNumber;
//    final private ArrayList fieldActivities;


    public Student(String firstName, String lastName, LocalDate birthDate, String fieldActivity) {
        super(firstName, lastName, birthDate, fieldActivity);
        this.registrationNumber = new SimpleStringProperty(generateRegistrationNumber());
//        this.fieldActivities = new <String >ArrayList();
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

    public String toString() {
        return  formatField(super.getFirstName(), 30) + " | " +
                formatField(super.getLastName(), 10) + " | " +
                formatField(super.getBirthDate().toString(), 10) + " |  " +
                formatField(super.getEmail(), 30) + " |" +
                formatField(super.getStatus().toString(),10) + "  |" +
                formatField(super.getFieldActivity(), 30) + " | " +
                formatField(registrationNumber.getValue(),10);
    }

    private String generateRegistrationNumber(){
        return super.getLastName().substring(0,3).toUpperCase() + super.getFirstName().charAt(0) + super.getBirthDate().toString().replace("-", "");
    }

    @Override
    protected String formatField(String field, int maxLength) {
        int l = maxLength - field.length();
        return field + "0".repeat(Math.max(0, l));
    }
}
