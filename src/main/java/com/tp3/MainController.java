package com.tp3;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import models.* ;
import rmi.Directory;


public class MainController implements Initializable {
    @FXML
    private HBox adminSection;
    @FXML
    private Button listStudentButton;
    @FXML
    private Button rechercherButton;
    @FXML
    private TextField rechecherTextField;
    @FXML
    private TextField seConnecterTextField;
    @FXML
    private ChoiceBox listProfessorsChoiceBox;
    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private Button seConnectButton;

    private ObservableList<Student> studentList;
    private ObservableList<Professor> professorList;
    private Directory directory;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/university","root","123456");
            //here sonoo is database name, root is username and password
            Statement stmt=con.createStatement();
//            ResultSet rs2=stmt.executeQuery("source /home/aebouel@progi.local/pratiques1/java/TP3-INF1010/backup.sql; select * from members INNER JOIN professors ON members.member_id=professors.member_id ;");
            ResultSet rs=stmt.executeQuery("select * from members INNER JOIN professors ON members.member_id=professors.member_id ;");
            String s = "";
            while(rs.next())
//                System.out.println(rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4));
                s += rs.getString(2)+"  \n"+rs.getString(3)+"\n  "+rs.getString(4);

            TextArea textArea = new TextArea();
            textArea.setText(s);
            mainBorderPane.setRight(textArea);
            con.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Error found");
            System.out.println(e);}


        studentList = FXCollections.observableArrayList();
        professorList = FXCollections.observableArrayList();
        try {
            directory = new Directory(studentList, professorList);
            directory.pullMembers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        mainBorderPane.setLeft(directory.getProfessorTable());
//        mainBorderPane.setLeft(directory.getProfessorTable());
    }

    @FXML
    protected void onOUVVRIRmodificationReservationClick() throws IOException {

    }

    @FXML
    private void OnSeConnecterButtonAction(ActionEvent actionEvent) {
        /*  TODO  */
        //seConnecterTextField.setVisible(false);
        /*  TODO  */
       // adminSection.setVisible(false);
        /*  TODO  */
    }

    @FXML
    private void onListStudentButtonAction(ActionEvent actionEvent) {
        mainBorderPane.setLeft(directory.getStudentTable());
    }

    @FXML
    private void OnlistProfessorContextMenuRequested(ContextMenuEvent contextMenuEvent) {
        /* TODO */
    }

    @FXML
    private void onRechercherButtonAction(ActionEvent actionEvent) {
//        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ModificationView2.fxml"));
//
//        Scene scene = new Scene(fxmlLoader.load(), 600, 440);
//        Stage stage = new Stage();
//        stage.setTitle("Modification Reservation");
//        stage.setScene(scene);
//
//        stage.setOnHidden(e -> {
//            // listeReservation.refresh();
//        });
//        stage.show();

//        int index = memberListView.getSelectionModel().getSelectedIndex();

//        ModifReservation2Controller boum = fxmlLoader.getController();
//        boum.initializeData(reservation.get(index));
        /* TODO */
    }

    @FXML
    private void onAjouterButtonAction(ActionEvent actionEvent) {
        /* TODO */
    }

    @FXML
    private void onModifierButtonAction(ActionEvent actionEvent) {
        /* TODO */
    }

    @FXML
    private void onSupprimerButtonAction(ActionEvent actionEvent) {
        /* TODO */
    }

    @FXML
    private void onMettreListeRougeButtonAction(ActionEvent actionEvent) {
        /* TODO */
    }

    @FXML
    private void onRetirerListeRougeButtonAction(ActionEvent actionEvent) {
        /* TODO */
    }
}