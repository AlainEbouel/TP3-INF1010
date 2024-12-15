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
    private Button listProfessorButton;
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

    @FXML
    private ChoiceBox<String>  themeChoiceBox;

    private boolean adminConnected;
    private String adminPass = "uqtr";
    private ArrayList<Student> studentList;
    private ArrayList<Professor> professorList;
//    private DirectoryView directoryView;
    private IDirectory  activeDirectory;
    private ArrayList<Button> buttonArrayList;
    private ArrayList<Node> nodeArrayList;
    private  boolean isStudentTableView;


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
        isStudentTableView = true;

        loadStudents();
        buttonStyle();
        // Initialisation de la ChoiceBox des domaines d'activité
        initializeChoiceBox();
        setupThemeChoiceBox();
    }

    // Connexion de l'administrateur
    @FXML
    private void onLoginButtonAction(ActionEvent actionEvent) throws RemoteException {

        if(!adminConnected)
        {
            if (!Objects.equals(seConnecterPasswordField.getText(), adminPass)) {
//            if(false){
                new Alert(Alert.AlertType.ERROR, "Mot de passe incorrect!").show();
            }
            else {
                seConnectButton.setText("Déconnexion");
                adminConnected = true;

                seConnecterPasswordField.setVisible(false);
                seConnecterPasswordField.setDisable(true);

                putOnRedListMemberButton.setVisible(true);
                putOnRedListMemberButton.setDisable(false);

                removeFromRedListMemberButton.setVisible(true);
                removeFromRedListMemberButton.setDisable(false);

                addMemberButton.setVisible(true);
                addMemberButton.setDisable(false);

                modifyMemberButton.setVisible(true);
                modifyMemberButton.setDisable(false);

                deleteMemberButton.setVisible(true);
                deleteMemberButton.setDisable(false);
            }
            seConnecterPasswordField.clear();
        }
        else {
            Alert  alert  = new Alert(Alert.AlertType.CONFIRMATION, "Confirmez que vous souhaitez vous déconnecter!");
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
        String criteria = rechecherTextField.getText();
        try {
            ArrayList<ArrayList<String>> members = activeDirectory.SearchMembers(criteria);
            DirectoryView.loadStudents(members); // Assuming the results are loaded as students for simplicity
            mainBorderPane.setLeft(DirectoryView.getStudentTable());
            tableName.setText("Résultats de recherche : " + criteria);
        } catch (RemoteException e) {
            new Alert(Alert.AlertType.ERROR, "Erreur lors de la recherche : " + e.getMessage()).show();
            e.printStackTrace();
        }
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
        if(isStudentTableView)
            loadStudents();
        else loadProfessors();
//        DirectoryView.getStudentTable().refresh();
    }

    @FXML
    private void onModifyMemberButtonAction(ActionEvent actionEvent) {
        try {
            Object selectedMember = getSelectedMember();

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
        Object selectedMember = getSelectedMember();

        if (selectedMember != null) {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Voulez-vous vraiment supprimer ce membre ?");
            if (confirm.showAndWait().get().getButtonData().isDefaultButton()) {
                try {
                    if (selectedMember instanceof Student) {
                        activeDirectory.removeMember(((Student) selectedMember).getRegistrationNumber());
                    } else if (selectedMember instanceof Professor) {
                        activeDirectory.removeMember(((Professor) selectedMember).getRegistrationNumber());
                    }

                    new Alert(Alert.AlertType.INFORMATION, "Membre supprimé avec succès !").show();
                    if(isStudentTableView) loadStudents();
                    else loadProfessors();

                } catch (RemoteException e) {
                    new Alert(Alert.AlertType.ERROR, "Erreur lors de la suppression : " + e.getMessage()).show();
                    e.printStackTrace();
                }
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Aucun membre sélectionné !").show();
        }
    }

    @FXML
    private void onPutListeRougeButtonAction(ActionEvent actionEvent) {
        boolean isStudent = true;
        Object selectedMember = getSelectedMember();
        if (selectedMember != null) {
            if (Objects.equals(((Member) selectedMember).getStatus(), "Inactif")) {
                new Alert(Alert.AlertType.INFORMATION, "Ce membre est dèjà sur la liste rouge.").show();
                return ;
            }
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Voulez-vous vraiment ajouter ce membre à la liste rouge ?");
            if (confirm.showAndWait().get().getButtonData().isDefaultButton()) {
                try {
                    String id = ((Member) selectedMember).getRegistrationNumber();
                    activeDirectory.putOnRedList(id);
//                    new Alert(Alert.AlertType.INFORMATION, "Membre ajouté à la liste rouge !").show();
                    if (isStudentTableView) loadStudents();
                    else loadProfessors();

                } catch (RemoteException e) {
                    new Alert(Alert.AlertType.ERROR, "Erreur lors de la mise à jour : " + e.getMessage()).show();
                    e.printStackTrace();
                }
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Aucun membre sélectionné !").show();
        }
    }

    @FXML
    private void onRemoveFromRedListButtonAction(ActionEvent actionEvent) {
        Object selectedMember = getSelectedMember();
        if (selectedMember != null) {
            if (Objects.equals(((Member) selectedMember).getStatus(), "Actif")) {
                new Alert(Alert.AlertType.INFORMATION, "Ce membre n'est pas sur la liste rouge.").show();
                return ;
            }
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Voulez-vous vraiment retirer ce membre de la liste rouge ?");
            if (confirm.showAndWait().get().getButtonData().isDefaultButton()) {
                try {
                    String id = ((Member) selectedMember).getRegistrationNumber();
                    activeDirectory.removeFromRedList(id);
//                    new Alert(Alert.AlertType.INFORMATION, "Membre retiré de la liste rouge !").show();
                    if (isStudentTableView) loadStudents();
                    else loadProfessors();

                } catch (RemoteException e) {
                    new Alert(Alert.AlertType.ERROR, "Erreur lors de la mise à jour : " + e.getMessage()).show();
                    e.printStackTrace();
                }
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Aucun membre sélectionné !").show();
        }
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
//    public void onThemesMenuButtonAction(ActionEvent actionEvent) {
//        System.out.println("onThemesMenuButtonAction theme");
//        System.out.println("onThemesMenuButtonAction: " + themesMenuButton.getOnAction().toString());
//        System.out.println("onThemesMenuButtonAction: " + ((MenuItem)actionEvent.getSource()).getText());
//        EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
//            public void handle(ActionEvent e)
//            {
//                System.out.println(((MenuItem)e.getSource()).getText() + " selected");
//                System.out.println(((MenuItem)actionEvent.getSource()).getText() + " selected");
//            }
//        };
//    }

    private void initializeChoiceBox() {
        ArrayList<String> domains = new ArrayList<>();
        domains.add("Tous les professeurs"); // Option par défaut
        for (ActivityField field : ActivityField.getActivityFields()) {
            domains.add(field.getField());
        }
        listProfessorsChoiceBox.getItems().setAll(domains);
        listProfessorsChoiceBox.getSelectionModel().select("Tous les professeurs"); // Valeur par défaut
    }

    private Object getSelectedMember() {
        Object selectedMember ;

        if(mainBorderPane.getLeft() == DirectoryView.getStudentTable()) {
            selectedMember = DirectoryView.getStudentTable().getSelectionModel().getSelectedItem();
            isStudentTableView = true;
        }
        else  {
            selectedMember = DirectoryView.getProfessorTable().getSelectionModel().getSelectedItem();
            isStudentTableView = false;
        }
        return selectedMember;
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
        buttonArrayList.add(listProfessorButton);
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

    private void loadProfessors(){
        try {
            DirectoryView.loadProfessors(activeDirectory.listProfessors());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        mainBorderPane.setLeft(DirectoryView.getProfessorTable());
        tableName.setText("Professors");
    }


    private void buttonStyle() {
        loadArraylistNode();
        for (Button button : buttonArrayList) {
            button.setOnMouseEntered(e -> button.setStyle(" -fx-border-color: white;  -fx-text-fill: white;"));
            button.setOnMouseExited(e -> button.setStyle("-fx-border-color: #bdbbbb; -fx-text-fill: #bdbbbb; "));
        }
    }

    private void setupThemeChoiceBox() {
        // Adding themes to the ChoiceBox
        themeChoiceBox.getItems().addAll("Clair", "Sombre", "Bleu");
        themeChoiceBox.getSelectionModel().selectFirst(); // Default theme

        // Adding listener for theme changes
        themeChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> applyTheme(newValue));
    }

    private void applyTheme(String theme) {
        // Clearing current styles
        mainBorderPane.getStylesheets().clear();

        // Applying the selected theme
        switch (theme) {
            case "Clair":
                mainBorderPane.getStylesheets().add(getClass().getResource("/themes/light.css").toExternalForm());
                break;
            case "Sombre":
                mainBorderPane.getStylesheets().add(getClass().getResource("/themes/dark.css").toExternalForm());
                break;
            case "Bleu":
                mainBorderPane.getStylesheets().add(getClass().getResource("/themes/blue.css").toExternalForm());
                break;
        }
    }
}