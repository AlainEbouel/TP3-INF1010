package models;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public abstract class Member {
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty lastName ;
    private final SimpleStringProperty email;
    private final SimpleStringProperty birthDate;
    private final SimpleStringProperty status;
    private final SimpleStringProperty fieldActivity;

    public Member(String firstName, String lastName, LocalDate birthDate, String fieldActivity) {
        this.lastName = new SimpleStringProperty(firstName);
        this.firstName = new SimpleStringProperty(lastName);
        this.birthDate = new SimpleStringProperty(birthDate.toString());
        this.status = new SimpleStringProperty(Status.Actif.toString());
        this.email = new SimpleStringProperty(generateEmail());
        this.fieldActivity = new SimpleStringProperty(fieldActivity);
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.setValue(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.setValue(lastName);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.setValue(email);
    }

    public String getBirthDate() {
        return birthDate.get();
    }

    public String getStatus() {
        return status.get();
    }

    public String getFieldActivity() {
        return fieldActivity.get();
    }

    public void setFieldActivity(String fieldActivity) {
        this.fieldActivity.setValue(fieldActivity);
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public SimpleStringProperty birthDateProperty() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate.setValue(birthDate);
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.setValue(status);
    }

    public SimpleStringProperty fieldActivityProperty() {
        return fieldActivity;
    }


    private String generateEmail()
    {
        return (firstName.getValue() + "."+ lastName.getValue() + "@uqtr.ca").toLowerCase();
    }
}