package server;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.ContextMenuEvent;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public interface IDirectory extends Remote {

    public ArrayList<ArrayList<String>>  ListAllStudents() throws RemoteException;
    public ArrayList<ArrayList<String>> listProfessors()  throws RemoteException;
    public ArrayList<ArrayList<String>> listProfessorsFromActivityField(String activityField)  throws RemoteException;
    public ArrayList<ArrayList<String>> SearchMembers(String criteria)  throws RemoteException;
    public void AddStudent(ArrayList<String> studentData)  throws RemoteException;
    public void AddProfessor(ArrayList<String> profData)  throws RemoteException;
    public void modifyStudent(ArrayList<String> studentData)  throws RemoteException;
    public void modifyProfessor(ArrayList<String> profData)  throws RemoteException;
    public void removeMember(String Id)  throws RemoteException;
    public void putOnRedList(String Id)  throws RemoteException;
    public void removeFromRedList(String Id)  throws RemoteException;
}
