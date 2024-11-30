package client;
import javafx.beans.property.SimpleStringProperty;
import java.util.Date;

public abstract class Member {
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty lastName ;
    private final SimpleStringProperty email;
    private final SimpleStringProperty birthDate;
    private final SimpleStringProperty status;
    private final SimpleStringProperty activityField;
    private final SimpleStringProperty registrationNumber;

    public Member(String firstName, String lastName, String birthDate,  String status, String activityField) {
        this.lastName = new SimpleStringProperty(firstName);
        this.firstName = new SimpleStringProperty(lastName);
        this.birthDate = new SimpleStringProperty(birthDate);
        this.status = new SimpleStringProperty(status);
        this.email = new SimpleStringProperty(generateEmail());
        this.activityField = new SimpleStringProperty(activityField);
        this.registrationNumber = new SimpleStringProperty(generateRegistrationNumber());
    }
    public Member(String firstName, String lastName, String email, String birthDate, String status, String activityField, String registrationNumber) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.birthDate = new SimpleStringProperty(birthDate);
        this.status = new SimpleStringProperty(status);
        this.email = new SimpleStringProperty(email);
        this.activityField = new SimpleStringProperty(activityField);
        this.registrationNumber = new SimpleStringProperty(registrationNumber);
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public String getFirstName() {
        return firstName.getValue();
    }

    public void setFirstName(String firstName) {
        this.firstName.setValue(firstName);
    }

    public String getLastName() {
        return lastName.getValue();
    }

    public void setLastName(String lastName) {
        this.lastName.setValue(lastName);
    }

    public String getEmail() {
        return email.getValue();
    }

    public void setEmail(String email) {
        this.email.setValue(email);
    }

    public String getRegistrationNumber() {
        return registrationNumber.getValue();
    }



    public String getBirthDate() {
        return birthDate.getValue();
    }

    public String getStatus() {
        return status.getValue();
    }

    public String getActivityField() {
        return activityField.getValue();
    }

    public void setActivityField(String activityField) {
        this.activityField.setValue(activityField);
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

    public SimpleStringProperty activityFieldProperty() {
        return activityField;
    }
    public SimpleStringProperty registrationNumberProperty() {
        return registrationNumber;
    }
    public void setRegistrationNumber(String registrationNumber) {this.registrationNumber.setValue(registrationNumber);}


    private String generateEmail()
    {
        return (firstName.getValue() + "."+ lastName.getValue() + "@uqtr.ca").toLowerCase();
    }

    private String generateRegistrationNumber(){
        return lastName.getValue().substring(0,3).toUpperCase() + firstName.getValue().toUpperCase().charAt(0) + birthDate.getValue().replace("-", "");
    }
}