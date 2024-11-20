package rmi;


import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class ActiveDirectoryServer implements IDirectory {
    @Override
    public ArrayList<ArrayList<String>> getStudents() throws RemoteException {
        return null;
    }

    @Override
    public int nbrStudent() throws RemoteException {
        return 0;
    }

    @Override
    public ArrayList<ArrayList<String>> selectAllStudents() throws RemoteException {
        return DatabaseClient.selectAllStudents();
    }
//    private final DatabaseClient dbClient = new DatabaseClient();
//    public ActiveDirectoryServer2() throws RemoteException {
////        super();
////        try {
////            this.url = new URL("http://localhost");
////        } catch (MalformedURLException e) {
////            System.out.println("---------------Malformed URL----------------");
////            throw new RuntimeException(e);
////        }
//        ;
//    }
//
//    @Override
//    public int nbrStudent() throws RemoteException {
//        return 100;
//    }
//    @Override
//    public ArrayList<ArrayList<String>> getStudents() throws RemoteException, SQLException {
//        DatabaseClient dbClient = new DatabaseClient();
//        ArrayList<ArrayList<String>> studentList = new ArrayList<>();
//
//        ResultSet rSet = dbClient.getStatement().executeQuery("select * from members INNER JOIN students ON members.member_id=students.member_id ;");
//
//        while(rSet.next()) {
//            ArrayList<String> student = new ArrayList<>();
//            student.add(rSet.getString(2));
//            student.add(rSet.getString(3));
//            student.add(rSet.getString(4));
//            student.add(rSet.getDate(5).toString());
//            student.add(rSet.getString(6));
//            student.add(rSet.getString(7));
//            student.add(rSet.getString(9));
//            studentList.add(student);
//        }
//        dbClient.closeConnection();
////        return new ArrayList<>();
//        return studentList;
//    }
//
//    @Override
//    public ArrayList<ArrayList<String>> selectAllStudents(){
////        ResultSet rSet = null;
////        System.out.println("Before try");
////        try {
////            Class.forName("com.mysql.cj.jdbc.Driver");
////            Connection connection = DriverManager.getConnection(
////                    "jdbc:mysql://127.0.0.1:3306/university", "root", "123456");
////            Statement statement = connection.createStatement();
////            rSet = statement.executeQuery("select * from members INNER JOIN students ON members.member_id=students.member_id ;");
////
////        } catch (ClassNotFoundException | SQLException e) {
////            System.out.println("CAught exception in DatabaseClient line 50");
////            System.out.println(e);
////        }
////
////        ArrayList<ArrayList<String>> studentList = new ArrayList<>();
////        System.out.println("Before while");
////        while (rSet.next()) {
////            ArrayList<String> student = new ArrayList<>();
////            student.add(rSet.getString(2));
////            student.add(rSet.getString(3));
////            student.add(rSet.getString(4));
////            student.add(rSet.getDate(5).toString());
////            student.add(rSet.getString(6));
////            student.add(rSet.getString(7));
////            student.add(rSet.getString(9));
////            studentList.add(student);
////        }
////        return studentList;
//        return new ArrayList<>();
//}
//
//
    public static void main(String[] args)  {
        try {
            ActiveDirectoryServer AD_Server = new ActiveDirectoryServer();
            IDirectory reference = (IDirectory) UnicastRemoteObject.exportObject(AD_Server, 0);
            Registry registry = LocateRegistry.getRegistry(1099);
            registry.rebind("ActiveDirectoryServer", reference);
            System.out.println("Server is ready...");
            ProcessBuilder pb = new ProcessBuilder();
            boolean isWindows = System.getProperty("os.name")
                    .toLowerCase().startsWith("windows");
            if (isWindows) {
                pb.command("cmd.exe", "/c", "echo 'Server ready...'");
            } else {
                pb.command("sh", "-c", "echo 'Server ready...'");
            }
        } catch (RemoteException e) {
            System.out.println("RemoteException in Directory constructor ");
            throw new RuntimeException(e);
        }


    }

}