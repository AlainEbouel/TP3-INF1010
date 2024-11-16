package models;

import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.util.Date;

public class Professor extends Member{
    private final SimpleStringProperty phoneNumber;

    public Professor(String firstName, String lastName, Date birthDate, String fieldActivity, String phoneNumber) {
        super(firstName, lastName, birthDate, fieldActivity);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
    }

    public Professor(String firstName, String lastName, String email, Date birthDate, String status, String fieldActivity, String phoneNumber) {
        super(firstName, lastName, email, birthDate, status, fieldActivity);
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
