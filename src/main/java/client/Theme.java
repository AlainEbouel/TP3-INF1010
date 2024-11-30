package client;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;

public class Theme {
    public void darkTheme(BorderPane borderPane, ArrayList<Button> buttonArrayList, Label tableLabel, Label adminLabel, ArrayList<Node>nodeArrayList) {
        borderPane.setStyle("-fx-background-color: #121111");
        tableLabel.setStyle("-fx-background-color: #302d2d");
        for (Button button : buttonArrayList) {
            button.setStyle("-fx-background-color: #121111");
        }
        for (Node node : nodeArrayList) {
            node.setStyle("-fx-background-color: #302d2d");
        }

    }
}
