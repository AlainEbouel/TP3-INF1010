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
        ArrayList<ArrayList<String>> professorList = new ArrayList<>();
        connect();
        try {
            ResultSet rSet = statement.executeQuery("SELECT * FROM professors;");
            while (rSet.next()) {
                ArrayList<String> professor = new ArrayList<>();
                professor.add(rSet.getString("first_name"));
                professor.add(rSet.getString("last_name"));
                professor.add(rSet.getString("email"));
                professor.add(rSet.getDate("birthdate").toString());
                professor.add(rSet.getString("status"));
                professor.add(rSet.getString("activityField"));
                professor.add(rSet.getString("phone_number"));
                professor.add(rSet.getString("registration_number"));
                professorList.add(professor);
            }
            getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return professorList;
    }
    public static ArrayList<ArrayList<String>> selectProfessorsFromActivityField(String activityField)  {
        ArrayList<ArrayList<String>> professorList = new ArrayList<>();
        connect();
        try {
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM professors WHERE activityField = ?;"
            );
            pstmt.setString(1, activityField);
            ResultSet rSet = pstmt.executeQuery();
            while (rSet.next()) {
                ArrayList<String> professor = new ArrayList<>();
                professor.add(rSet.getString("first_name"));
                professor.add(rSet.getString("last_name"));
                professor.add(rSet.getString("email"));
                professor.add(rSet.getDate("birthdate").toString());
                professor.add(rSet.getString("status"));
                professor.add(rSet.getString("activityField"));
                professor.add(rSet.getString("phone_number"));
                professor.add(rSet.getString("registration_number"));
                professorList.add(professor);
            }
            pstmt.close();
            getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return professorList;
    }
    public static void putOnRedList(String registrationNumber)  {
        connect();
        try {
            String sql = "UPDATE students SET status = 'Inactif' WHERE registration_number = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, registrationNumber);
            pstmt.executeUpdate();

            sql = "UPDATE professors SET status = 'Inactif' WHERE registration_number = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, registrationNumber);
            pstmt.executeUpdate();

            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void removeFromRedList(String registrationNumber)  {
        connect();
        try {
            String sql = "UPDATE students SET status = 'Actif' WHERE registration_number = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, registrationNumber);
            pstmt.executeUpdate();

            sql = "UPDATE professors SET status = 'Actif' WHERE registration_number = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, registrationNumber);
            pstmt.executeUpdate();

            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static ArrayList<ArrayList<String>> searchMembers(String criteria)  {
        connect();
        ArrayList<ArrayList<String>> members = new ArrayList<>();
        try {
            String sql = "SELECT * FROM students WHERE first_name LIKE ? OR last_name LIKE ? OR email LIKE ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, "%" + criteria + "%");
            pstmt.setString(2, "%" + criteria + "%");
            pstmt.setString(3, "%" + criteria + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ArrayList<String> student = new ArrayList<>();
                student.add(rs.getString("first_name"));
                student.add(rs.getString("last_name"));
                student.add(rs.getString("email"));
                student.add(rs.getDate("birthdate").toString());
                student.add(rs.getString("status"));
                student.add(rs.getString("activityField"));
                student.add(rs.getString("registration_number"));
                members.add(student);
            }

            sql = "SELECT * FROM professors WHERE first_name LIKE ? OR last_name LIKE ? OR email LIKE ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, "%" + criteria + "%");
            pstmt.setString(2, "%" + criteria + "%");
            pstmt.setString(3, "%" + criteria + "%");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ArrayList<String> professor = new ArrayList<>();
                professor.add(rs.getString("first_name"));
                professor.add(rs.getString("last_name"));
                professor.add(rs.getString("email"));
                professor.add(rs.getDate("birthdate").toString());
                professor.add(rs.getString("status"));
                professor.add(rs.getString("activityField"));
                professor.add(rs.getString("phone_number"));
                professor.add(rs.getString("registration_number"));
                members.add(professor);
            }

            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return members;
    }

    public static void addStudent(ArrayList<String> studentData) {
       connect();
        try {
            statement.execute("INSERT INTO `students` (`first_name`, `last_name`, `email`, `birthdate`, `status`, `activityField`, `registration_number`) VALUES\n" +
                    "( '" + studentData.get(0) + "', '" + studentData.get(1) + "', '" + studentData.get(2) + "', '" + studentData.get(3) +
                    "', '" + studentData.get(4) + "', '" + studentData.get(5) + "', '" + studentData.get(6) + "');");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void addProfessor(ArrayList<String> profData) {
        connect();
        try {
            String sql = "INSERT INTO professors (first_name, last_name, email, birthdate, status, activityField, phone_number, registration_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            for (int i = 0; i < profData.size(); i++) {
                pstmt.setString(i + 1, profData.get(i));
            }

            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void modifyStudent(ArrayList<String> studentData)  {
        connect(); // Assurez-vous que la connexion à la base de données est établie
        try {
            // Préparer la requête SQL de mise à jour
            String sql = "UPDATE students SET first_name = ?, last_name = ?, email = ?, birthdate = ?, status = ?, activityField = ? WHERE registration_number = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            // Remplir les paramètres de la requête
            pstmt.setString(1, studentData.get(0)); // first_name
            pstmt.setString(2, studentData.get(1)); // last_name
            pstmt.setString(3, studentData.get(2)); // email
            pstmt.setString(4, studentData.get(3)); // birthdate
            pstmt.setString(5, studentData.get(4)); // status
            pstmt.setString(6, studentData.get(5)); // activityField
            pstmt.setString(7, studentData.get(6)); // registration_number (identifiant unique)

            // Afficher la requête SQL générée
            System.out.println("Requête SQL : " + pstmt.toString());

            // Exécuter la requête
            int rowsAffected = pstmt.executeUpdate();

            // Vérifier si la mise à jour a réussi
            if (rowsAffected > 0) {
                System.out.println("Étudiant modifié avec succès !");
            } else {
                System.out.println("Aucun étudiant trouvé avec ce numéro d'enregistrement.");
            }

            // Fermer le PreparedStatement
            pstmt.close();
            connection.close();

        } catch (SQLException e) {
            System.err.println("Erreur lors de la modification de l'étudiant : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    public static void modifyProfessor(ArrayList<String> profData) {
        connect();
        try {
            String sql = "UPDATE professors SET first_name = ?, last_name = ?, email = ?, birthdate = ?, status = ?, activityField = ?, phone_number = ? WHERE registration_number = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);

            // Remplir les paramètres
            pstmt.setString(1, profData.get(0)); // first_name
            pstmt.setString(2, profData.get(1)); // last_name
            pstmt.setString(3, profData.get(2)); // email
            pstmt.setString(4, profData.get(3)); // birthdate
            pstmt.setString(5, profData.get(4)); // status
            pstmt.setString(6, profData.get(5)); // activityField
            pstmt.setString(7, profData.get(6)); // phone_number
            pstmt.setString(8, profData.get(7)); // registration_number (clé primaire)

            System.out.println("Requête SQL : " + pstmt.toString());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Professeur modifié avec succès !");
            } else {
                System.out.println("Aucun professeur trouvé avec ce numéro d'enregistrement.");
            }

            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la modification du professeur : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    public static void removeMember(String registrationNumber) {
        connect();
        try {
            String sql = "DELETE FROM students WHERE registration_number = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, registrationNumber);
            pstmt.executeUpdate();

            sql = "DELETE FROM professors WHERE registration_number = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, registrationNumber);
            pstmt.executeUpdate();

            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

