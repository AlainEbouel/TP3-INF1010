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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import models.* ;


public class MainController implements Initializable {
    @FXML
    private ChoiceBox listProfessorsChoiceBox;
    @FXML
    private BorderPane mainBorderPane;
    private ObservableList<Student> studentList;
    private ObservableList<Professor> professorList;
    private Directory directory;

//    public Controller() {
//        // ListeReservationController stockageListeDansClasse = new ListeReservationController();
//        //reservation = stockageListeDansClasse.getListeOfficielReservation();
////        members = Directory.getMembersObsList();
//
//    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/university","root","password");
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
        directory = new Directory(studentList, professorList);
        mainBorderPane.setLeft(directory.getStudentTable());

        Label l = new Label("This is a choice box");
        Label l1 = new Label("nothing selected");

        // string array
        String st[] = { "Arnab", "Andrew", "Ankit", "None" };

        // create a choiceBox
//        ChoiceBox.set  (FXCollections.observableArrayList(st));
        listProfessorsChoiceBox.setItems(FXCollections.observableArrayList(st));
        // add a listener
        listProfessorsChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

            // if the item of the list is changed
            public void changed(ObservableValue ov, Number value, Number new_value)
            {

                // set the text for the label to the selected item
//                l1.setText(st[new_value.intValue()] + " selected");
//                System.out.println(listProfessorsChoiceBox.getSelectionModel().getSelectedItem());
//                System.out.println("new value="+ ov.getValue());
//                System.out.println("value=" + value);
//                System.out.println("vov=" + ov.getValue());

            }
        });
//        listProfessorsChoiceBox.setOnAction((event) -> {
//            int selectedIndex = listProfessorsChoiceBox.getSelectionModel().getSelectedIndex();
//            Object selectedItem = listProfessorsChoiceBox.getSelectionModel().getSelectedItem();
//
//            System.out.println("Selection made: [" + selectedIndex + "] " + selectedItem);
//            System.out.println("   ChoiceBox.getValue(): " + listProfessorsChoiceBox.getValue());
//        });

    }

    // Pull from database


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

    public void onListStudentButtonAction(ActionEvent actionEvent) {
        mainBorderPane.setLeft(directory.getStudentTable());

    }

    public void onListProfessorButtonAction(ActionEvent actionEvent) {
        mainBorderPane.setLeft(directory.getProfessorTable());

    }

    public void OnlistProfessorContextMenuRequested(ContextMenuEvent contextMenuEvent) {
        System.out.println(contextMenuEvent.toString());
        System.out.println(contextMenuEvent.getSource().toString());
    }

    public void OnListProfessorMenuButtonAction(ActionEvent actionEvent) {
    }

    public void onListProfessorInputKeyReleased(KeyEvent keyEvent) {
        System.out.println("onListProfessorInputMethodTextChanged");
        listProfessorsChoiceBox.setOnAction((event) -> {
            int selectedIndex = listProfessorsChoiceBox.getSelectionModel().getSelectedIndex();
            Object selectedItem = listProfessorsChoiceBox.getSelectionModel().getSelectedItem();

            System.out.println("Selection made: [" + selectedIndex + "] " + selectedItem);
            System.out.println("   ChoiceBox.getValue(): " + listProfessorsChoiceBox.getValue());
        });
    }

    public void onListProfessorContextMenueRequested(ContextMenuEvent contextMenuEvent) {
        System.out.println("onListProfessorInputMethodTextChanged");
        listProfessorsChoiceBox.setOnAction((event) -> {
            int selectedIndex = listProfessorsChoiceBox.getSelectionModel().getSelectedIndex();
            Object selectedItem = listProfessorsChoiceBox.getSelectionModel().getSelectedItem();

            System.out.println("Selection made: [" + selectedIndex + "] " + selectedItem);
            System.out.println("   ChoiceBox.getValue(): " + listProfessorsChoiceBox.getValue());
        });
    }

    public void onChooseProfessorFieldMouseReleased(MouseEvent mouseEvent) {
//        System.out.println("onListProfessorInputMethodTextChanged2");

        listProfessorsChoiceBox.setOnAction((event) -> {
            int selectedIndex = listProfessorsChoiceBox.getSelectionModel().getSelectedIndex();

            System.out.println(listProfessorsChoiceBox.getSelectionModel().getSelectedItem());
        });
    }

    public void onListProfessorTouchMoved(TouchEvent touchEvent) {
    }
}