package rmi;

import java.rmi.*;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IDirectory extends Remote {

    public ArrayList<ArrayList<String>> getStudents() throws RemoteException;
    public int nbrStudent() throws RemoteException ;
    public ArrayList<ArrayList<String>> selectAllStudents() throws RemoteException;
}
