package com.tp3;

import client.ActivityField;
import client.Member;
import client.Student;
import client.Professor;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import server.IDirectory;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ModifyMemberController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private DatePicker birthDateField;
    @FXML
    private ChoiceBox<String> statusChoiceBox;
    @FXML
    private ChoiceBox<String> activityFieldChoiceBox;
    @FXML
    private TextField phoneNumberField;

    private Member memberToModify;
    private IDirectory activeDirectory;

    public void initialize() {
        try {
            Registry registry = LocateRegistry.getRegistry();
            activeDirectory = (IDirectory) registry.lookup("ActiveDirectory");
        } catch (Exception e) {
            System.out.println("Erreur de connexion RMI : " + e.getMessage());
        }

        // Initialisation des domaines dans la ChoiceBox
        for (ActivityField field : ActivityField.values()) {
            activityFieldChoiceBox.getItems().add(field.getField());
        }
    }

    public void setMemberData(Member member) {
        this.memberToModify = member;
        firstNameField.setText(member.getFirstName());
        lastNameField.setText(member.getLastName());
        emailField.setText(member.getEmail());
        birthDateField.setValue(java.time.LocalDate.parse(member.getBirthDate()));
        statusChoiceBox.setValue(member.getStatus());
        if (member instanceof Student) {
            activityFieldChoiceBox.setValue(((Student) member).getActivityField());
            phoneNumberField.setVisible(false); // Les étudiants n'ont pas de téléphone
        } else if (member instanceof Professor) {
            activityFieldChoiceBox.setValue(((Professor) member).getActivityField());
            phoneNumberField.setText(((Professor) member).getPhoneNumber());
            phoneNumberField.setVisible(true);
        }
    }



    @FXML
    private void onSaveButtonAction() {
        try {
            // Affichez les anciennes données
            System.out.println("Données avant modification : " + memberToModify.memberData());
            memberToModify.setFirstName(firstNameField.getText());
            memberToModify.setLastName(lastNameField.getText());
            memberToModify.setEmail(emailField.getText());
            memberToModify.setBirthDate(birthDateField.getValue().toString());
            memberToModify.setStatus(statusChoiceBox.getValue());
            memberToModify.setActivityField(activityFieldChoiceBox.getValue()); // Récupérez la sélection

            // Affichez les nouvelles données
            System.out.println("Données après modification : " + memberToModify.memberData());

            if (memberToModify instanceof Professor) {
                ((Professor) memberToModify).setPhoneNumber(phoneNumberField.getText());
                activeDirectory.modifyProfessor(((Professor) memberToModify).memberData());
            } else if (memberToModify instanceof Student) {
                activeDirectory.modifyStudent(((Student) memberToModify).memberData());
            }

            new Alert(Alert.AlertType.INFORMATION, "Membre modifié avec succès !").show();
            closeWindow();
        } catch (RemoteException e) {
            new Alert(Alert.AlertType.ERROR, "Erreur lors de la modification du membre : " + e.getMessage()).show();
            e.printStackTrace();
        }
    }

    @FXML
    private void onCancelButtonAction() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) firstNameField.getScene().getWindow();
        stage.close();
    }
}
