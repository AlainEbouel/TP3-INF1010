package com.tp3;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import client.* ;
import client.DirectoryView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import server.IDirectory;


public class MainController implements Initializable {


    @FXML
    private MenuButton themesMenuButton;
    @FXML
    private VBox nodeSearch;
    @FXML
    private VBox nodeAdmin;
    @FXML
    private VBox nodeAddModSup;
    @FXML
    private VBox nodeRedList;
    @FXML
    private Label tableName;
    @FXML
    private HBox adminSection;
    @FXML
    private Button listStudentButton;
    @FXML
    private Button rechercherButton;
    @FXML
    private Button seConnectButton;
    @FXML
    private Button addMemberButton;
    @FXML
    private Button modifyMemberButton;
    @FXML
    private Button deleteMemberButton;
    @FXML
    private Button putOnRedListMemberButton;
    @FXML
    private Button removeFromRedListMemberButton;
    @FXML
    private TextField rechecherTextField;
    @FXML
    private PasswordField seConnecterPasswordField;;
    @FXML
    private ChoiceBox listProfessorsChoiceBox;
    @FXML
    private BorderPane mainBorderPane;


    private boolean adminConnected;
    private String adminPass = "uqtr";
    private ArrayList<Student> studentList;
    private ArrayList<Professor> professorList;
//    private DirectoryView directoryView;
    private IDirectory  activeDirectory;
    private ArrayList<Button> buttonArrayList;
    private ArrayList<Node> nodeArrayList;

    private void initializeChoiceBox() {
        ArrayList<String> domains = new ArrayList<>();
        domains.add("Tous les professeurs"); // Option par défaut
        for (ActivityField field : ActivityField.getActivityFields()) {
            domains.add(field.getField());
        }
        listProfessorsChoiceBox.getItems().setAll(domains);
        listProfessorsChoiceBox.getSelectionModel().select("Tous les professeurs"); // Valeur par défaut
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rmiConnect();
        adminConnected = false;
        studentList = new ArrayList<>();
        professorList = new ArrayList<>();
//        directoryView = new DirectoryView();
        DirectoryView.initDirectoryView();
        buttonArrayList = new ArrayList<>();
        nodeArrayList = new ArrayList<>();

        loadStudents();
        buttonStyle();
        // Initialisation de la ChoiceBox des domaines d'activité
        initializeChoiceBox();
    }



    // Connexion de l'administrateur
    @FXML
    private void onLoginButtonAction(ActionEvent actionEvent) throws RemoteException {

        if(!adminConnected)
        {
//            if (!Objects.equals(seConnecterPasswordField.getText(), adminPass)) {
            if(false){
            new Alert(Alert.AlertType.ERROR, "Mot de passe incorrect!").show();
            }
            else {
                seConnecterPasswordField.setVisible(false);
                seConnectButton.setText("Déconnexion");
                adminConnected = true;
                seConnecterPasswordField.setDisable(true);
                putOnRedListMemberButton.setVisible(true);
                removeFromRedListMemberButton.setVisible(true);
                addMemberButton.setVisible(true);
                modifyMemberButton.setVisible(true);
                deleteMemberButton.setVisible(true);
            }
            seConnecterPasswordField.clear();
        }
        else {
            Alert  alert  = new Alert(Alert.AlertType.CONFIRMATION, "Confirmez que Vous souhaitez vous déconnecter!");
            if (Objects.equals(alert.showAndWait().get().getButtonData().toString(), "OK_DONE")) {
                seConnectButton.setText("Connexion");
                seConnecterPasswordField.setDisable(false);
                seConnecterPasswordField.setVisible(true);
                adminConnected = false;
                putOnRedListMemberButton.setVisible(false);
                removeFromRedListMemberButton.setVisible(false);
                addMemberButton.setVisible(false);
                modifyMemberButton.setVisible(false);
                deleteMemberButton.setVisible(false);
            }
        }
    }
    @FXML
    private void onEnterPasswordFieldPressed(KeyEvent keyEvent) throws RemoteException {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            onLoginButtonAction(new ActionEvent());
        }
    }

    // Lister tous les etudiants
    @FXML
    private void onListStudentButtonAction(ActionEvent actionEvent) throws RemoteException {
        loadStudents();
    }

    // Lister tous les professeurs ou uniquement ceux d'une catégorie
    @FXML
    private void OnlistProfessorContextMenuRequested(ActionEvent actionEvent) {
        /* TODO */
        // Cette méthode affiche  les professeurs d'un domaine precis ou  tous les professeurs
        // Prévoir une étiquette `Tous les professeurs` dans la liste de la ChoiceBox
        // Cette méthode doit toujours tirer ces donnée depuis la base de donnees.
        try {
            // Récupération du domaine sélectionné dans la ChoiceBox
            String selectedDomain = (String) listProfessorsChoiceBox.getSelectionModel().getSelectedItem();

            // Vérification du choix (Tous les professeurs ou un domaine spécifique)
            ArrayList<ArrayList<String>> professors;
            if ("Tous les professeurs".equals(selectedDomain)) {
                // Appel au service pour récupérer tous les professeurs
                professors = activeDirectory.listProfessors();
            } else {
                // Appel au service pour récupérer les professeurs d'un domaine précis
                professors = activeDirectory.listProfessorsFromActivityField(selectedDomain);
            }

            // Chargement des professeurs dans la vue
            DirectoryView.loadProfessors(professors);

            // Mise à jour de l'affichage dans l'interface
            mainBorderPane.setLeft(DirectoryView.getProfessorTable());
            tableName.setText("Professeurs (" + (selectedDomain.equals("Tous les professeurs") ? "Tous" : selectedDomain) + ")");
        } catch (RemoteException e) {
            // Gestion des erreurs RMI
            new Alert(Alert.AlertType.ERROR, "Erreur lors de la récupération des professeurs : " + e.getMessage()).show();
            e.printStackTrace();
        } catch (Exception e) {
            // Gestion des autres erreurs
            new Alert(Alert.AlertType.ERROR, "Erreur inattendue : " + e.getMessage()).show();
            e.printStackTrace();
        }
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
    private void onAddMemberButtonAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("addMemberView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 440);
        Stage stage = new Stage();
        stage.setTitle("Ajouter un nouveau membre!");
        stage.setScene(scene);
        stage.show();
        loadStudents();
        DirectoryView.getStudentTable().refresh();
    }

    @FXML
    private void onModifyMemberButtonAction(ActionEvent actionEvent) {
        try {
            Object selectedMember = null;

            // Vérifiez si un étudiant est sélectionné
            if (DirectoryView.getStudentTable().getSelectionModel().getSelectedItem() != null) {
                selectedMember = DirectoryView.getStudentTable().getSelectionModel().getSelectedItem();
            }
            // Sinon, vérifiez si un professeur est sélectionné
            else if (DirectoryView.getProfessorTable().getSelectionModel().getSelectedItem() != null) {
                selectedMember = DirectoryView.getProfessorTable().getSelectionModel().getSelectedItem();
            }

            // Si aucun membre n'est sélectionné, affichez un avertissement
            if (selectedMember == null) {
                new Alert(Alert.AlertType.WARNING, "Veuillez sélectionner un membre à modifier !").show();
                return;
            }

            // Ouvrir la fenêtre de modification
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("modifyMemberView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 440);
            Stage stage = new Stage();
            stage.setTitle("Modifier un membre");

            // Passez les données au contrôleur de la fenêtre de modification
            ModifyMemberController controller = fxmlLoader.getController();
            if (selectedMember instanceof Student) {
                controller.setMemberData((Student) selectedMember);
            } else if (selectedMember instanceof Professor) {
                controller.setMemberData((Professor) selectedMember);
            }

            stage.setScene(scene);
            stage.showAndWait();

            // Réinitialisez la sélection dans les deux tableaux
            DirectoryView.getStudentTable().getSelectionModel().clearSelection();
            DirectoryView.getProfessorTable().getSelectionModel().clearSelection();

            // Rafraîchir les tables après modification
            DirectoryView.getStudentTable().refresh();
            DirectoryView.getProfessorTable().refresh();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Erreur lors du chargement de la fenêtre de modification : " + e.getMessage()).show();
            e.printStackTrace();
        }
    }


    @FXML
    private void onRemoveMemberButtonAction(ActionEvent actionEvent) {
        TableView.TableViewSelectionModel selectionModel =  DirectoryView.getStudentTable().getSelectionModel();
        System.out.println("Selected: " +  ((Student)selectionModel.getSelectedItem()).getFirstName());
//        if(deleteMemberButton.isDisabled()){
//            deleteMemberButton.setDisable(false);
//            Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez d'abord sélectionner un membre svp!");
//            alert.show();
//        }
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
    @FXML
    private void onThemesContextMenuRequested(ContextMenuEvent contextMenuEvent) {
        System.out.println("theme2");
        System.out.println("onThemesContextMenuRequested: " + themesMenuButton.getText());
        System.out.println("onThemesContextMenuRequested: " + themesMenuButton.getOnAction().toString());
        System.out.println("onThemesMenuButtonAction: " + ((MenuItem)contextMenuEvent.getSource()).getText());
        EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                System.out.println(((MenuItem)e.getSource()).getText() + " selected");
            }
        };
        System.out.println(((MenuItem)contextMenuEvent.getSource()).getText() + " selected");
    }
    public void onThemesMenuButtonAction(ActionEvent actionEvent) {
        System.out.println("onThemesMenuButtonAction theme");
        System.out.println("onThemesMenuButtonAction: " + themesMenuButton.getOnAction().toString());
        System.out.println("onThemesMenuButtonAction: " + ((MenuItem)actionEvent.getSource()).getText());
        EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                System.out.println(((MenuItem)e.getSource()).getText() + " selected");
                System.out.println(((MenuItem)actionEvent.getSource()).getText() + " selected");
            }
        };
    }


    public void onThemesMouseClicked(MouseEvent mouseEvent) {
        System.out.println("onThemesMenuButtonAction theme");
        System.out.println("onThemesMenuButtonAction: " + themesMenuButton.getOnContextMenuRequested().toString());
//        System.out.println("onThemesMenuButtonAction: " + ((MenuItem)mouseEvent.getSource()).getText());
        EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                System.out.println(((MenuItem)e.getSource()).getText() + " selected");
            }
        };

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

 // Peuplement des listes
    private void loadArraylistNode() {
        buttonArrayList.add(listStudentButton);
        buttonArrayList.add(rechercherButton);
        buttonArrayList.add(seConnectButton);
        buttonArrayList.add(addMemberButton);
        buttonArrayList.add(modifyMemberButton);
        buttonArrayList.add(deleteMemberButton);
        buttonArrayList.add(putOnRedListMemberButton);
        buttonArrayList.add(removeFromRedListMemberButton);
        nodeArrayList.add(nodeAdmin);
        nodeArrayList.add(nodeAddModSup);
        nodeArrayList.add(nodeRedList);
        nodeArrayList.add(nodeSearch);
    }

    private void loadStudents(){
        try {
            DirectoryView.loadStudents(activeDirectory.ListAllStudents());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        mainBorderPane.setLeft(DirectoryView.getStudentTable());
        tableName.setText("Étudiants");
    }


    private void buttonStyle() {
        loadArraylistNode();
        for (Button button : buttonArrayList) {
            button.setOnMouseEntered(e -> button.setStyle(" -fx-border-color: white;  -fx-text-fill: white"));
            button.setOnMouseExited(e -> button.setStyle("-fx-border-color: #bdbbbb; -fx-text-fill: #bdbbbb "));
        }
    }


}