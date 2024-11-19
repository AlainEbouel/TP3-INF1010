package com.tp3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import models.* ;
import models.DirectoryView;
import rmi.IDirectory;


public class MainController implements Initializable {
    public DatePicker datepicker;
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
    private DirectoryView directoryView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        studentList = FXCollections.observableArrayList();
        professorList = FXCollections.observableArrayList();
        try {
            directoryView = new DirectoryView(studentList, professorList);
//            directoryView.pullMembers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        mainBorderPane.setLeft(directoryView.getProfessorTable());
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
        mainBorderPane.setLeft(directoryView.getStudentTable());
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
        try {
            Registry registry = LocateRegistry.getRegistry();
            IDirectory ref = (IDirectory) registry.lookup("ActiveDirectoryServer");
                     ArrayList<ArrayList<String>> students = ref.getStudents();
            for(ArrayList<String> student : students) {
                for( String s : student ){
                    System.out.print("/ " + s);
                }
                System.out.println();
//                System.out.println("STUDENT= " + s);
            }

        } catch (RemoteException | NotBoundException e) {
            System.out.println("CAught not bound exception in directory");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


//
//    }
    }


}