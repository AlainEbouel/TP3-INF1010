package models;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
        studentList.add(new Student("Alainaaaaaaaaaasss",  "Ebouel", today, "Informatique"));
        studentList.add(new Student("Gael",  "Papito", today, "Science pure"));
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
        column6.setCellValueFactory(c -> c.getValue().fieldActivityProperty());
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
        column6.setCellValueFactory(c -> c.getValue().fieldActivityProperty());
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
