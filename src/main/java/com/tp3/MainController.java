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
import java.sql.SQLException;
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