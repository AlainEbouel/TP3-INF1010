package models;

import com.sun.jdi.ClassNotLoadedException;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.*;
import java.time.LocalDate;

public class Directory {
    private  TableView<Student> studentTable ;
    private  TableView<Professor> professorTable;

    public Directory(ObservableList<Student> studentsList, ObservableList<Professor> professorsList) {

        this.studentTable = new TableView<>();
        this.professorTable = new TableView<>();
        pullMembers(studentsList, professorsList);
        tableStyle(studentTable);
        tableStyle(professorTable);
        createStudentColumns();
        createProfessorColumns();
        loadStudents(studentsList);
        loadProfessors(professorsList);
    }

    public TableView<Student> getStudentTable() {
        return studentTable;
    }

    public TableView<Professor> getProfessorTable() {
        return professorTable;
    }
    private void pullMembers(ObservableList<Student> studentList, ObservableList<Professor> professorList){
        LocalDate today = LocalDate.now();
//        studentList.add(new Student("Alainaaaaaaaaaasss",  "Ebouel", today, "Informatique"));
//        studentList.add(new Student("Gael",  "Papito", today, "Science pure"));
//        ---

//        studentList.add(new Student("Diane", "Jih", LocalDate.of(1998, 10, 12), "informatique"));
//        studentList.add(new Student("Rogers", "Paul", LocalDate.of(1987, 1,5),  "Administration des affaires"));
//        studentList.add(new Student("David", "John", LocalDate.of(2002,11,9), "Education prescolaire"));
//        studentList.add(new Student("Maria", "Cisse", LocalDate.of(2007, 12,25), "Art visuel"));
//        studentList.add(new Student("Morris", "Kone",  LocalDate.of(1990,7,8), "Biochimie"));
//        studentList.add(new Student("Mohamed", "Bala", LocalDate.of(1995,11,11), "Biologie_medicale"));
//        studentList.add(new Student("Sanders", "Atangana", LocalDate.of(2001,6,28), "Chimie"));
//        studentList.add(new Student("Mark", "Bouchard",  LocalDate.of(1988,2,11), "Communication sociale"));
//        studentList.add(new Student("Morgan", "Couture", LocalDate.of(1997,3,15), "Etudes francaise"));
//        studentList.add(new Student("Paul", "Miller", LocalDate.of(2004,5,18),  "Genie electrique"));
//        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection con= DriverManager.getConnection(
//                    "jdbc:mysql://127.0.0.1:3306/university","root","123456");
//            //here sonoo is database name, root is username and password
//            Statement stmt=con.createStatement();
////            ResultSet rs2=stmt.executeQuery("source /home/aebouel@progi.local/pratiques1/java/TP3-INF1010/backup.sql; select * from members INNER JOIN professors ON members.member_id=professors.member_id ;");
//            ResultSet rs=stmt.executeQuery("select * from members INNER JOIN professors ON members.member_id=professors.member_id ;");
//            while(rs.next())
//                System.out.println(rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4));
//            con.close();
//        }catch(ClassNotFoundException | SQLException e){
//            System.out.println("Error found");
//            System.out.println(e);}

        professorList.add(new Professor("Jean",  "Dino", today, "Philosophie", "819 900 0500"));
        professorList.add(new Professor("Chanel",  "Atangana", today, "Science pure", "489 056 5656"));
    }
    private void createStudentColumns() {
        TableColumn<Student, String> column1 = new TableColumn<>("Prénom");
        column1.setCellValueFactory(c -> c.getValue().lastNameProperty());
        TableColumn<Student, String> column2 = new TableColumn<>("Nom");
        column2.setCellValueFactory(c -> c.getValue().lastNameProperty());
        TableColumn<Student, String> column3 = new TableColumn<>("Date de naissance");
        column3.setCellValueFactory(c -> c.getValue().birthDateProperty());
        TableColumn<Student, String> column4 = new TableColumn<>("Email");
        column4.setCellValueFactory(c -> c.getValue().emailProperty());
        TableColumn<Student, String> column5 = new TableColumn<>("Status");
        column5.setCellValueFactory(c -> c.getValue().statusProperty());
        TableColumn<Student, String> column6 = new TableColumn<>("Domaine d'activité");
        column6.setCellValueFactory(c -> c.getValue().activityFieldProperty());
        TableColumn<Student, String> column7 = new TableColumn<>("Matricule");
        column7.setCellValueFactory(c -> c.getValue().registrationNumberProperty());
        studentTable.getColumns().addAll(column2, column3, column4, column5, column6, column7);
    }
    private void createProfessorColumns() {
        TableColumn<Professor, String> column1 = new TableColumn<>("Prénom");
        column1.setCellValueFactory(c -> c.getValue().firstNameProperty());
        TableColumn<Professor, String> column2 = new TableColumn<>("Nom");
        column2.setCellValueFactory(c -> c.getValue().lastNameProperty());
        TableColumn<Professor, String> column3 = new TableColumn<>("Date de naissance");
        column3.setCellValueFactory(c -> c.getValue().birthDateProperty());
        TableColumn<Professor, String> column4 = new TableColumn<>("Email");
        column4.setCellValueFactory(c -> c.getValue().emailProperty());
        TableColumn<Professor, String> column5 = new TableColumn<>("Status");
        column5.setCellValueFactory(c -> c.getValue().statusProperty());
        TableColumn<Professor, String> column6 = new TableColumn<>("Domaine d'activité");
        column6.setCellValueFactory(c -> c.getValue().activityFieldProperty());
        TableColumn<Professor, String> column7 = new TableColumn<>("Téléphone");
        column7.setCellValueFactory(c -> c.getValue().phoneNumberProperty());
        professorTable.getColumns().addAll(column1, column2, column3, column4, column5, column6, column7);
    }
    private void loadStudents(ObservableList<Student> studentsList) {
        for (Student student : studentsList) {
            studentTable.getItems().add(student);
        }
    }
    private void loadProfessors(ObservableList<Professor> professorsList) {
        for (Professor professor : professorsList) {
            professorTable.getItems().add(professor);
        }
    }
    private void tableStyle(TableView tableView) {
        tableView.setPrefWidth(1200);

    }
}
