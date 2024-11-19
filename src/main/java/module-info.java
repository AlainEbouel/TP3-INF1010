module com.tp3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires jdk.jdi;
    requires java.rmi;
    requires mysql.connector.j;

    opens com.tp3 to javafx.fxml;
    exports com.tp3;
    exports rmi;
    opens rmi to javafx.fxml;
    exports models;
    opens models to javafx.fxml;
}