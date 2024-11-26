package client;

import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

public class Professor extends Member{
    private final SimpleStringProperty phoneNumber;

    public Professor(String firstName, String lastName, String birthDate, String fieldActivity, String phoneNumber, String registrationNumber) {
        super(firstName, lastName, birthDate, fieldActivity, registrationNumber);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
    }

    public Professor(String firstName, String lastName, String email, String birthDate, String status, String fieldActivity, String phoneNumber, String registrationNumber) {
        super(firstName, lastName, email, birthDate, status, fieldActivity, registrationNumber);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
    }


    public SimpleStringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber.getValue();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.setValue(phoneNumber);
    }
}
