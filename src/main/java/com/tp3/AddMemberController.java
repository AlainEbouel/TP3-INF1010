package com.tp3;

import client.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import server.IDirectory;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddMemberController implements Initializable {
    @FXML
    private MenuButton menuButtonActivityField;
    @FXML
    private DatePicker birthdayDatePicker;
    @FXML
    private VBox phoneNumberVBox;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private RadioButton studentRadioButton;
    @FXML
    private RadioButton profRadioButton;
    @FXML
    private TextField firstnameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField activityFieldextField;
    @FXML
    private RadioButton statusRadioButton;

    private IDirectory  activeDirectory;
    private String selectedActivityField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rmiConnect();
        setActivityFieldsMenuButton();
    }

    @FXML
    private void onStudentRadioButtonAction(ActionEvent actionEvent) {
        profRadioButton.setSelected(false);
        phoneNumberVBox.setVisible(false);
    }

    @FXML
    private void onProfRadioButtonAction(ActionEvent actionEvent) {
        studentRadioButton.setSelected(false);
        phoneNumberVBox.setVisible(true);
    }
    @FXML
    private void onDeleteAllButtonAction(ActionEvent actionEvent) {
        firstnameTextField.clear();
        lastNameTextField.clear();
        birthdayDatePicker.setValue(null);
        birthdayDatePicker.cancelEdit();
        activityFieldextField.clear();
    }
    @FXML
    private void onCreateButtonAction(ActionEvent actionEvent) throws RemoteException {
        String birthday = "";
        try {
            birthday = birthdayDatePicker.getValue().toString();
        }catch (NullPointerException | DateTimeParseException e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "La date doit être au format AAAA-MM-JJ");
            alert.show();
            return;
        }

        if(selectedActivityField == null) {
           new Alert(Alert.AlertType.ERROR, "Veuillez sélectionner un domaine d'activité svp!").show();
           return;
        }

        String firstname = firstnameTextField.getText();
        String lastname = lastNameTextField.getText();

        System.out.println("f:"+ firstname + " l:" + lastname);
        Status status = statusRadioButton.isSelected() ? Status.Actif : Status.Inactif ;
        String activityField = selectedActivityField;
        String phoneNumber = phoneNumberTextField.getText();

        if(studentRadioButton.isSelected()) {
            Student student = new Student(firstname, lastname, birthday, status.toString(), activityField);
            activeDirectory.AddStudent(student.memberData());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez-vous créer ce nouvel étudiant ?");
            if (Objects.equals(alert.showAndWait().get().getButtonData().toString(), "OK_DONE")) {
                mainBorderPane.getScene().getWindow().hide();
                DirectoryView.loadStudents(activeDirectory.ListAllStudents());
            }
        }
        else if(profRadioButton.isSelected()) {
            Professor professor = new Professor(firstname, lastname, birthday, status.toString(), activityField, phoneNumber);
            activeDirectory.AddProfessor(professor.memberData());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez-vous créer ce nouvel professeur ?");
            if (Objects.equals(alert.showAndWait().get().getButtonData().toString(), "OK_DONE")) {
                mainBorderPane.getScene().getWindow().hide();
                DirectoryView.loadProfessors(activeDirectory.listProfessors());
            }
        }

    }

    private void rmiConnect(){
        try {
            Registry registry = LocateRegistry.getRegistry();
            activeDirectory = (IDirectory) registry.lookup("ActiveDirectory");
        } catch (RemoteException | NotBoundException e) {
            System.out.println("Caught not bound exception in Controller");
        }
    }

    private void setActivityFieldsMenuButton(){
        ArrayList<MenuItem> items = new ArrayList<>();
        for (ActivityField activityField : ActivityField.getActivityFields()){
            MenuItem menuItem = new MenuItem(activityField.getField());
            menuItem.setOnAction(actionEvent -> {
                selectedActivityField = ((MenuItem)actionEvent.getSource()).getText();
                menuButtonActivityField.setText(selectedActivityField);
            });
            items.add(menuItem);
        }
        menuButtonActivityField.getItems().setAll(items);
    }
}
