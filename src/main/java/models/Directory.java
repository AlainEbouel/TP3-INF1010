package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Directory {

    //private static ArrayList<Reservation> reservationArrayList = new ArrayList<>();
    private static ObservableList<Member> membersObsList = FXCollections.observableArrayList();
    private static BorderPane mainBorderPane;
    private static Pane reservationPane;
    private static ChoiceBox choiceBoxActivite;
    private static boolean statutSauvegarde;


    public Directory() throws IOException, ClassNotFoundException {
        chargerReservation();
//        membersObsList.add(new Student("Alain", "Ebouel",new DatePicker()));
    }

    //Connexion avec la BorderPane de l'nterface Accueil
    public static void setAcceuilBorderPane(BorderPane acceuilBorderPane) {
        Directory.mainBorderPane = acceuilBorderPane;
    }

    //Connexion avec la Pane Reservation, dans AccueilController
    public static void SetReservationPane(Pane reservationPane) {
        Directory.reservationPane = reservationPane;
    }

    //Connexion avec la ChoiceBox de l'interface Reservation
    public static void connectWithChoiceBoxActivite(ChoiceBox choiceBoxActivite) {
        Directory.choiceBoxActivite = choiceBoxActivite;
    }

    //Positionne l'interface Nouvelle Reservation
    public static void setReservationOnCenter(String activite) {
        choiceBoxActivite.setValue(activite);
        mainBorderPane.setCenter(reservationPane);
    }


    //    public static ArrayList<Reservation> getReservationArrayList() {
//        return reservationArrayList;
//    }
    public static ObservableList<Member> getMembersObsList() {
        return membersObsList;
    }

    //Chargement de la liste des réservations à partir du fichier de sauvegarde
    public static void chargerReservation() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("sauvegarde.txt");
        ObjectInputStream input = new ObjectInputStream(fis);
        //reservationArrayList = (ArrayList<Reservation>) input.readObject();
    }

}