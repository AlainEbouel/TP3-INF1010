package com.tp3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
import client.* ;
import client.DirectoryView;
import server.IDirectory;


public class MainController implements Initializable {
    @FXML
    private Label tableName;
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

    private ArrayList<Student> studentList;
    private ArrayList<Professor> professorList;
    private DirectoryView directoryView;
    private  IDirectory  activeDirectory;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rmiConnect();
        studentList = new ArrayList<>();
        professorList = new ArrayList<>();
        directoryView = new DirectoryView();
        try {
            directoryView.loadStudents(activeDirectory.ListAllStudents());

        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
//        mainBorderPane.setLeft(directoryView.getStudentTable());
        tableName.setText("Étudiants");
    }

    // Connexion au registre RMI
    private void rmiConnect(){
        try {
            Registry registry = LocateRegistry.getRegistry();
            activeDirectory = (IDirectory) registry.lookup("ActiveDirectory");
        } catch (RemoteException | NotBoundException e) {
            System.out.println("Caught not bound exception in Controller");
        }
    }

    // Connexion de l'administrateur
    @FXML
    private void OnLoginButtonAction(ActionEvent actionEvent) throws RemoteException {
        /*TODO*/
    }

    // Lister tous les etudiants
    @FXML
    private void onListStudentButtonAction(ActionEvent actionEvent) throws RemoteException {
        directoryView.loadStudents(activeDirectory.ListAllStudents());
        mainBorderPane.setLeft(directoryView.getStudentTable());
        tableName.setText("Étudiants");
    }

    // Lister tous les professeurs ou uniquement ceux d'une catégorie
    @FXML
    private void OnlistProfessorContextMenuRequested(ContextMenuEvent contextMenuEvent) {
        /* TODO */
        // Cette méthode affiche  les professeurs d'un domaine precis ou  tous les professeurs
        // Prévoir une étiquette `Tous les professeurs` dans la liste de la ChoiceBox
        // Cette méthode doit toujours tirer ces donnée depuis la base de donnees.
    }

    //Rechercher un membre (Etudiant ou prof)
    @FXML
    private void onSearchMemberButtonAction(ActionEvent actionEvent) {
        /* TODO */
        //Cette méthode permet de rechercher un membre
        //Elle doit afficher tous les membres correspondant au critère de recherche.
        //L'utilisateur devra choisir s'il veut rechercher un étudiant ou un professeur.
    }

    // Ajouter un etudiant ou un prof
    @FXML
    private void onAddMemberButtonAction(ActionEvent actionEvent) throws RemoteException {
        /* TODO */
        // Cette méthode doit lancer une fenêtre, pour saisir les informations du membre à créer
        // L'ajout doit être communiqué à la vue principale(TableView) et à la base de donnée(`univertisy` database).

    }

    @FXML
    private void onModifyMemberButtonAction(ActionEvent actionEvent) {
        /* TODO */
        // Cette méthode doit lancer une fenêtre affichant les informations de l'étudiant ou du prof sélectionné.
        // Ensuite, on doit pouvoir modifier un ou plusieurs champs et enrégistrer.
        // La modification doit être communiquée à la vue principale(TableView) et à la base de donnée(`univertisy` database).
        // Il faut uniquement traiter les informations qui ont changé
    }

    @FXML
    private void onRemoveMemberButtonAction(ActionEvent actionEvent) {
        /* TODO */
        //Cette méthode supprime le membre (Étudiant ou professeur ) sélectionné dans le vue principale(TableView)
        // La suppression doit être faite à la fois dans la vue principale(TableView) et dans la base de donnée(`univertisy` database).
    }

    @FXML
    private void onPutListeRougeButtonAction(ActionEvent actionEvent) {
        /* TODO */
        // Cette méthode met sur liste rouge le membre (Étudiant ou professeur ) sélectionné dans le vue principale(TableView)
        // La modification se reflêter dans la vue principale(TableView) et dans la base de donnée(`univertisy` database).
        // Un membre qui est sur la liste rouge a le status `Inactif` dans la base de donnee
    }

    @FXML
    private void onRemoveFromRedListButtonAction(ActionEvent actionEvent) {
        /* TODO */
        //Cette méthode retire un membre de la liste rouge(Inactif =>Actif)
        // La base de donnnees doit etre mise a jour.
    }


}