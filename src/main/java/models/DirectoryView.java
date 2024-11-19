package models;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.*;


public class DirectoryView {
    private final TableView<Student> studentTable ;
    private final TableView<Professor> professorTable;
    private final ObservableList<Student> studentList;
    private final ObservableList<Professor> professorList;
    public DirectoryView(ObservableList<Student> studentList, ObservableList<Professor> professorList) throws SQLException {

        this.studentTable = new TableView<>();
        this.professorTable = new TableView<>();
        this.studentList = studentList;
        this.professorList = professorList;
//        pullMembers();
        tableStyle(studentTable);
        tableStyle(professorTable);
        createStudentColumns();
        createProfessorColumns();
        loadStudents();
        loadProfessors();


    }

    public TableView<Student> getStudentTable() {
        return studentTable;
    }

    public TableView<Professor> getProfessorTable() {
        return professorTable;
    }
//    public void pullMembers() throws SQLException {
//        DatabaseClient dbClient = new DatabaseClient();
//        dbClient.selectAllProfesssors(professorList);
//        dbClient.selectAllStudents(studentList);
//    }
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
        studentTable.getColumns().addAll(column1, column2, column3, column4, column5, column6, column7);
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
    private void loadStudents() {
        for (Student student : studentList) {
            studentTable.getItems().add(student);
        }
    }
    private void loadProfessors() {
        for (Professor professor : professorList) {
            professorTable.getItems().add(professor);
        }
    }
    private void tableStyle(TableView tableView) {
        tableView.setPrefWidth(1200);
    }


}
