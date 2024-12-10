package client;

import java.util.ArrayList;

public class Student extends Member {

    public Student(String firstName, String lastName, String birthDate, String status, String activityField) {
        super(firstName, lastName, birthDate, status, activityField);
    }

    public Student(String firstName, String lastName, String email, String birthDate, String status, String fieldActivity, String registrationNumber) {
        super(firstName, lastName, email, birthDate, status, fieldActivity, registrationNumber);
    }

    @Override
    public ArrayList<String> memberData() {
        ArrayList<String> data = new ArrayList<>();
        data.add(getFirstName());
        data.add(getLastName());
        data.add(getEmail());
        data.add(getBirthDate());
        data.add(getStatus());
        data.add(getActivityField());
        data.add(getRegistrationNumber()); // Conserve le numéro existant
        return data;
    }
}
