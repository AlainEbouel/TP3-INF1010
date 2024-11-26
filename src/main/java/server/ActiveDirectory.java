package server;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class ActiveDirectory implements IDirectory {

    @Override
    public ArrayList<ArrayList<String>>  ListAllStudents() {
        return DatabaseClient.selectAllStudents();
    }

    @Override
    public ArrayList<ArrayList<String>>  listProfessors() {
        return DatabaseClient.selectAllprofessors();
    }

    @Override
    public ArrayList<ArrayList<String>> listProfessorsFromActivityField(String activityField)  {
        return DatabaseClient.selectProfessorsFromActivityField(activityField);
    }

    @Override
    public ArrayList<ArrayList<String>> SearchMembers(String criteria) {
        return DatabaseClient.searchMembers(criteria);
    }

    @Override
    public void AddStudent(ArrayList<String> studentData) {
        DatabaseClient.addStudent(studentData);
    }
    @Override
    public void AddProfessor(ArrayList<String> profData) {
        DatabaseClient.addProfessor(profData);
    }

    @Override
    public void modifyStudent(ArrayList<String> studentData)  {
        DatabaseClient.modifyStudent(studentData);
    }

    @Override
    public void modifyProfessor(ArrayList<String> profData) {
        DatabaseClient.modifyProfessor(profData);
    }

    @Override
    public void removeMember(String registrationNumber) {
        DatabaseClient.removeMember(registrationNumber);
    }

    @Override
    public void putOnRedList(String registrationNumber) {
        DatabaseClient.putOnRedList(registrationNumber);
    }

    @Override
    public void removeFromRedList(String registrationNumber) {
        DatabaseClient.removeFromRedList(registrationNumber);
    }
}
