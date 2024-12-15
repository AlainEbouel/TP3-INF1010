package client;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.*;
import java.util.ArrayList;


public class DirectoryView {
    private static final TableView<Student> studentTable = new TableView<>();;
    private static final TableView<Professor> professorTable = new TableView<>();;
//    private final ObservableList<Student> studentList;
//    private final ObservableList<Professor> professorList;
    public static void initDirectoryView()  {

//        studentTable = new TableView<>();
//        professorTable = new TableView<>();
//        this.studentList = studentList;
//        this.professorList = professorList;
//        pullMembers();
        tableStyle(studentTable);
        tableStyle(professorTable);
        createStudentColumns();
        createProfessorColumns();
//        loadStudents();
//        loadProfessors();


    }

    public static TableView<Student> getStudentTable() {
        return studentTable;
    }

    public static TableView<Professor> getProfessorTable() {
        return professorTable;
    }
//    public void pullMembers() throws SQLException {
//        DatabaseClient dbClient = new DatabaseClient();
//        dbClient.selectAllProfesssors(professorList);
//        dbClient.selectAllStudents(studentList);
//    }
    private static void createStudentColumns() {
        TableColumn<Student, String> column1 = new TableColumn<>("Prénom");
        column1.setCellValueFactory(c -> c.getValue().firstNameProperty());
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
        studentTable.getColumns().addAll(column1, column2, column3, column4, column5, column6, column7);


    }
    private static void createProfessorColumns() {
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
        column1.setStyle("");

    }

    public static void loadStudents(ArrayList<ArrayList<String>> students) {
        ArrayList<Student> studentList = new ArrayList<>();
        for (ArrayList<String> list : students) {
            studentList.add(new Student(list.get(0),list.get(1), list.get(2), list.get(3), list.get(4), list.get(5), list.get(6)));
        }
        studentTable.getItems().setAll(studentList);
    }

    public static void loadProfessors(ArrayList<ArrayList<String>> professors) {
        ArrayList<Professor> profList = new ArrayList<>();
        for (ArrayList<String> list : professors ) {
            System.out.println("professor: " + list.get(0) + " nom: " + list.get(1));
            profList.add(new Professor(list.get(0),list.get(1), list.get(2), list.get(3), list.get(4), list.get(5), list.get(6), list.get(7)));
        }
        professorTable.getItems().setAll(profList);
    }
    private static void tableStyle(TableView tableView) {
        tableView.setPrefWidth(1200);
        tableView.setStyle("-fx-background-color: #782626");

    }


}
