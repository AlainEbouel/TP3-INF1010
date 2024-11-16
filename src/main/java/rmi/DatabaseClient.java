package rmi;

import javafx.collections.ObservableList;
import models.Professor;
import models.Student;

import java.sql.*;

public class DatabaseClient {
    private Connection connection;
    private Statement statement ;

    public DatabaseClient (){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/university","root","123456");
            this.statement = connection.createStatement();

        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e);}
    }
    public void selectAllStudents(ObservableList<Student> studentList) throws SQLException {
        ResultSet rSet = statement.executeQuery("select * from members INNER JOIN students ON members.member_id=students.member_id ;");

        while(rSet.next()) {
           studentList.add(new Student(rSet.getString(2), rSet.getString(3), rSet.getString(4), rSet.getDate(5), rSet.getString(6), rSet.getString(7), rSet.getString(9)));
        }
    }
    public void selectAllProfesssors( ObservableList<Professor> professorList) throws SQLException {
        ResultSet rSet = statement.executeQuery("select * from members INNER JOIN professors ON members.member_id=professors.member_id ;");

        while(rSet.next()) {
            professorList.add(new Professor(rSet.getString(2), rSet.getString(3), rSet.getString(4), rSet.getDate(5), rSet.getString(6), rSet.getString(7), rSet.getString(9)));
        }
//        connection.close();
    }
}

