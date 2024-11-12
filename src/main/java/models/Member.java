package models;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public abstract class Member {
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName ;
    private SimpleStringProperty email;
    private SimpleStringProperty birthDate;
    private SimpleStringProperty status;
    private SimpleStringProperty fieldActivity;

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




    /*
     *    ** **  Public methods **
     * */



//    @Override
//    public String toString() {
//        return  formatField(firstName, 30) + " |" +
//                formatField(lastName, 30) + " |" +
//                formatField(birthDate.toString(), 10) + " |" +
//                formatField(email, 30) + " |     " +
//                formatField(status.toString(),10) + " |" +
//                formatField(fieldActivity, 30) + " |" ;
//    }

    /*
    *    ** **  Privates methods **
    * */
    private String generateEmail()
    {
        return (firstName.getValue() + "."+ lastName.getValue() + "@uqtr.ca").toLowerCase();
    }

    protected abstract String formatField(String date, int maxLength);
}