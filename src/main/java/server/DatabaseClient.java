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
        /*TODO*/
        // Cette méthode recupère  tous les professeurs d'un domaine précis
        // Cette méthode doit toujours tirer ces donnée depuis la base de donnees.
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
        /*TODO */
        // Cette méthode modifie 1 ou plusieurs attributs d'un professor dans la base de données
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

