package server;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseClient {
    private static Connection connection;
    private static Statement statement ;

    private static void connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/university", "root", "123456");
            System.out.println("Connected to database: " + connection + " After");
            statement = connection.createStatement();

        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e);}

    }
    public static ArrayList<ArrayList<String>> selectAllStudents()  {
        ArrayList<ArrayList<String>> studentList = new ArrayList<>();

        ResultSet rSet = null;
        connect();
        try {
            rSet = statement.executeQuery("select * from students;");
            while(rSet.next()) {
                ArrayList<String> student = new ArrayList<>();
                student.add(rSet.getString(1));
                student.add(rSet.getString(2));
                student.add(rSet.getString(3));
                student.add(rSet.getDate(4).toString());
                student.add(rSet.getString(5));
                student.add(rSet.getString(6));
                student.add(rSet.getString(7));
                studentList.add(student);
            }
            getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return studentList;
    }

    public static ArrayList<ArrayList<String>> selectAllprofessors()  {
        /*TODO */
        // Cette méthode recupère  tous les professeurs
        // Cette méthode doit toujours tirer ces donnée depuis la base de donnees.
        return null;
    }
    public static ArrayList<ArrayList<String>> selectProfessorsFromActivityField(String activityField)  {
        /*TODO*/
        // Cette méthode recupère  tous les professeurs d'un domaine précis
        // Cette méthode doit toujours tirer ces donnée depuis la base de donnees.
        return null;
    }
    public static void putOnRedList(String registrationNumber)  {
        /*TODO*/
        // Cette met un etudiant ou un professeur sur la liste (rouge dans la base de données)

    }
    public static void removeFromRedList(String registrationNumber)  {
        /*TODO*/
        // Cette retire un etudiant ou un professeur de la liste (rouge dans la base de données)
    }
    public static ArrayList<ArrayList<String>> searchMembers(String criteria)  {
        /*TODO*/
        // Cette méthode permet de rechercher des membres selon un critère parmi les attributs du membres
        // La recherche est effectuée dans la base de donnée.
        return null;
    }
    public static void addStudent(ArrayList<String> studentData) {
       connect();
        try {
            System.out.println(studentData.get(0));
            System.out.println(studentData.get(1));
            System.out.println(studentData.get(2));
            System.out.println(studentData.get(3));
            System.out.println(studentData.get(4));
            System.out.println(studentData.get(5));
            System.out.println(studentData.get(6));
            statement.execute("INSERT INTO `students` (`first_name`, `last_name`, `email`, `birthdate`, `status`, `activityField`, `registration_number`) VALUES\n" +
                    "( '" + studentData.get(0) + "', '" + studentData.get(1) + "', '" + studentData.get(2) + "', '" + studentData.get(3) +
                    "', '" + studentData.get(4) + "', '" + studentData.get(5) + "', '" + studentData.get(6) + "');");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void addProfessor(ArrayList<String> profData) {
        /*TODO */
        // Cette meéthode insert un nouvel professeur dans la base de données
    }
    public static void modifyStudent(ArrayList<String> studentData)  {
        /*TODO */
        // Cette méthode modifie 1 ou plusieurs attributs d'un étudiant dans la base de données
    }
    public static void modifyProfessor(ArrayList<String> profData) {
        /*TODO */
        // Cette méthode modifie 1 ou plusieurs attributs d'un professor dans la base de données
    }
    public static void removeMember(String registrationNumber) {
        /*TODO */
        // Cette méthode supprime un membre dans la base de données
    }

    public static Connection getConnection() {
        return connection;
    }

    public static Statement getStatement() {
        return statement;
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
}

