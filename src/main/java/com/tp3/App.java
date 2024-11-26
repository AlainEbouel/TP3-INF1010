package com.tp3;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class App extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws IOException, SQLException {

        restoreDatabase("databaseBackups/universityDB.sql", "127.0.0.1:3306");
        restoreDatabase("databaseBackups/students.sql", "127.0.0.1:3306/university");
        restoreDatabase("databaseBackups/professors.sql", "127.0.0.1:3306/university");

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("mainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1600, 720);
        stage.setTitle("Annuaire universitaire");
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void stop() {
        backupDatabase();
    }

    public static void main(String[] args) {
        launch();
    }

    // Restauration d'une base de donnée à partir d'un fichier .sql
    private static void restoreDatabase(String backupFilePath, String databaseAddr) throws SQLException {
        Path path = Paths.get(backupFilePath);

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://" + databaseAddr, "root", "123456");
            Statement statement = connection.createStatement();
//            if(statement.execute("select * from students;"))
//            if(databaseAddr.contains("university"))
//                try{
//                   int  result = statement.executeQuery("select count(*) from information_schema.tables where table_schema= 'students'").getFetchSize();
//                   System.out.println("tableExists =" + result );
//                }
//            catch (NullPointerException e){}


            try {
                BufferedReader br = Files.newBufferedReader(path);
                StringBuilder query = new StringBuilder();
                while (br.ready()) {
                    String line = br.readLine();
                    if (!line.startsWith("--")) {
                        query.append(line);
                        if (line.endsWith(";")){
                            statement.execute(query.toString());
                            query = new StringBuilder();
                        }
                    }
                }
                br.close();
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            connection.close();
        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e);}
    }

    // Sauvegarde de la base de données à l'arrêt de l'application
    private void backupDatabase(){
        /*TODO*/
        // Cette méthode sauvegarde les tables `students` et `professors` dans 2 fichiers distints `databaseBackups/students.sql `databaseBackups/professors.sql`
    }

    private boolean tableExists(String tableName){
        return false;
    }
}
