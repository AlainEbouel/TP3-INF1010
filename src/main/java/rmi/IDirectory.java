package rmi;

import java.rmi.*;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IDirectory extends Remote {
//    public ObservableList<Student> getAllStudents() throws RemoteException;
//    public ObservableList<Professor> getAllProfessors() throws RemoteException;
    public ArrayList<ArrayList<String>> getStudents() throws RemoteException, SQLException;
    public int nbrStudent() throws RemoteException ;
    public ArrayList<ArrayList<String>> selectAllStudents() throws RemoteException;
}
