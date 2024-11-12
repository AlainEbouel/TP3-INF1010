package models;

import java.time.LocalDate;
import java.util.Date;

public class Professor extends Member{

    private String phoneNumber;
    public Professor(String firstName, String lastName, LocalDate birthDate, String fieldActivity, String phoneNumber) {
        super(firstName, lastName, birthDate, fieldActivity);
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String toString() {
        return  formatField(super.getFirstName(), 30) + " | " +
                formatField(super.getLastName(), 10) + " | " +
                formatField(super.getBirthDate().toString(), 10) + " |  " +
                formatField(super.getEmail(), 30) + " |" +
                formatField(super.getStatus().toString(),10) + "  |" +
                formatField(super.getFieldActivity(), 30) + " | " +
                formatField(phoneNumber,10);
    }

    @Override
    protected String formatField(String field, int maxLength) {
        int l = maxLength - field.length();
        return field + "0".repeat(Math.max(0, l));
    }
}
