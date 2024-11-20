package rmi;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseClient {
    private static Connection connection;
    private static Statement statement ;
    //
    public DatabaseClient (){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/university", "root", "123456");
            statement = connection.createStatement();

        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e);}
    }
    private static void connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/university", "root", "123456");
            statement = connection.createStatement();

        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e);}

    }
    public static ArrayList<ArrayList<String>> selectAllStudents()  {
        ArrayList<ArrayList<String>> studentList = new ArrayList<>();

        ResultSet rSet = null;
        connect();
        try {
            rSet = statement.executeQuery("select * from members INNER JOIN students ON members.member_id=students.member_id ;");
            while(rSet.next()) {
                ArrayList<String> student = new ArrayList<>();
                student.add(rSet.getString(2));
                student.add(rSet.getString(3));
                student.add(rSet.getString(4));
                student.add(rSet.getDate(5).toString());
                student.add(rSet.getString(6));
                student.add(rSet.getString(7));
                student.add(rSet.getString(9));
                studentList.add(student);
            }
            getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return studentList;
    }
//    public void selectAllProfesssors( ArrayList<Professor> professorList) throws SQLException {
//        ResultSet rSet = statement.executeQuery("select * from members INNER JOIN professors ON members.member_id=professors.member_id ;");
//

    public static Connection getConnection() {
        return connection;
    }

    public static Statement getStatement() {
        return statement;
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
//        while(rSet.next()) {
////            System.out.println("dbDate="+rSet.getDate(5));
////            LocalDate localDate = LocalDate.parse(rSet.getString(5));
//            professorList.add(new Professor(rSet.getString(2), rSet.getString(3), rSet.getString(4), rSet.getDate(5), rSet.getString(6), rSet.getString(7), rSet.getString(9)));
//        }
////        connection.close();
//    }
//
//    public static  ArrayList<ArrayList<String>> getAllStudent () throws SQLException {
//        ResultSet rSet = null;
//        System.out.println("Before try");
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection connection = DriverManager.getConnection(
//                    "jdbc:mysql://127.0.0.1:3306/university", "root", "123456");
//            Statement statement = connection.createStatement();
//            rSet = statement.executeQuery("select * from members INNER JOIN students ON members.member_id=students.member_id ;");
//
//        } catch (ClassNotFoundException | SQLException e) {
//            System.out.println("CAught exception in DatabaseClient line 50");
//            System.out.println(e);
//        }
//
//        ArrayList<ArrayList<String>> studentList = new ArrayList<>();
//        System.out.println("Before while");
//        while (rSet.next()) {
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
//        return studentList;
//
////        return students;
//    }
}

