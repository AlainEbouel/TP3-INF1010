package com.tp3;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import models.Client;
import models.* ;


public class Controller implements Initializable {

    //Liste des membres dans le système.
    ObservableList<Member> members;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Label InfoUser;

    //Affichage de la liste des commandes
//    @FXML
//    private ListView<Member> memberListView;

    @FXML
    private TableView<Student> memberTableView;


    //Acceder aux listes stockées dans la classe ListeReservationController
    public Controller() {
        // ListeReservationController stockageListeDansClasse = new ListeReservationController();
        //reservation = stockageListeDansClasse.getListeOfficielReservation();
        members = Directory.getMembersObsList();

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatePicker dPicker = new DatePicker();
        dPicker.setValue(LocalDate.now());
        //Peuplement de la liste des reservation

//        memberListView.getItems().addAll(members);
//        memberListView.setItems(members);

        TableColumn<Student, String> column1 = new TableColumn<>("Prenom");
        column1.setCellValueFactory(c -> c.getValue().firstNameProperty());

        TableColumn<Student, String> column2 = new TableColumn<>("Nom");
        column2.setCellValueFactory(c -> c.getValue().lastNameProperty());

        TableColumn<Student, String> column3 = new TableColumn<>("Date de naissance");
        column3.setCellValueFactory(c -> c.getValue().birthDateProperty());

        TableColumn<Student, String> column4 = new TableColumn<>("Email");
        column4.setCellValueFactory(c -> c.getValue().emailProperty());

        TableColumn<Student, String> column5 = new TableColumn<>("Status");
        column5.setCellValueFactory(c -> c.getValue().statusProperty());

        TableColumn<Student, String> column6 = new TableColumn<>("Domaine");
        column6.setCellValueFactory(c -> c.getValue().fieldActivityProperty());

        TableColumn<Student, String> column7 = new TableColumn<>("Matricule");
        column7.setCellValueFactory(c -> c.getValue().registrationNumberProperty());

        memberTableView.getColumns().add(column1);
        memberTableView.getColumns().add(column2);
        memberTableView.getColumns().add(column3);
        memberTableView.getColumns().add(column4);
        memberTableView.getColumns().add(column5);
        memberTableView.getColumns().add(column6);

        memberTableView.getItems().add(
                new Student("Alain",  "Ebouel", dPicker.getValue(), "Informatique"));
//        memberTableView.getItems().add(
//                new  Professor("Maximilienne",  "Ebouel", dPicker.getValue(), "Litterature Anglaise", "581 309 0955"));

//
//        memberTableView.getItems().addAll(members);
//        memberTableView.setItems(members);

//        Member r1 = new Student("Alain",  "Ebouel", dPicker.getValue(), "Informatique");
//        Member r3 = new Student("Alain",  "Ebouel", dPicker.getValue(), "Informatique");
//        Member r2 = new Professor("Maximilienne",  "Ebouel", dPicker.getValue(), "Litterature Anglaise", "581 309 0955");
////        Member r3 = new Member("00000",  "1H00", "FOOT");
////        Member r4 = new Member("000000",  "H00", "Baseball");
//        Directory.getMembersObsList().add(r1);
//        Directory.getMembersObsList().add(r2);
//        Directory.getMembersObsList().add(r3);
//        Directory.getMembersObsList().add(r4);
//        for (int i = 0; i < 10; i++) {
//            Directory.getMembersObsList().add(new Student("Alain",  "Ebouel", dPicker.getValue(), "Informatique"));
//        }
//        System.out.println("r1=" + r1.toString());
//        System.out.println("r2=" + r2.toString());
    }
//    @FXML
//    protected void initialize() {
//
//    }

    @FXML
    protected void onOUVVRIRmodificationReservationClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ModificationView2.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 600, 440);
        Stage stage = new Stage();
        stage.setTitle("Modification Reservation");
        stage.setScene(scene);

        stage.setOnHidden(e -> {
            // listeReservation.refresh();
        });
        stage.show();

//        int index = memberListView.getSelectionModel().getSelectedIndex();

//        ModifReservation2Controller boum = fxmlLoader.getController();
//        boum.initializeData(reservation.get(index));
    }


}