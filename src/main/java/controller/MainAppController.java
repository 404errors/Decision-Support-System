package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MainAppController {
    public Button btnImport;
    public Button btnVisualize;
    public Button btnPredict;
    public Button btnAbout;
    public Button btnExit;
    public StackPane contentStack;
    @FXML
    AnchorPane content;
    private ObservableList<Node> childrent;

    public void showImportScene(ActionEvent actionEvent) throws IOException {
        btnVisualize.setStyle("-fx-background-color: #425252; ");
        btnAbout.setStyle("-fx-background-color: #425252; ");
        btnPredict.setStyle("-fx-background-color: #425252; ");
        btnImport.setStyle("-fx-background-color: #1D7070; ");
        content.getChildren().clear();
        content.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("/fxml/Import.fxml")));
    }

    public void showVisualizeScene(ActionEvent actionEvent) throws IOException {
        btnVisualize.setStyle("-fx-background-color: #1D7070; ");
        btnAbout.setStyle("-fx-background-color: #425252; ");
        btnPredict.setStyle("-fx-background-color: #425252; ");
        btnImport.setStyle("-fx-background-color: #425252; ");
        content.getChildren().clear();
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/fxml/Visualization.fxml"));
        content.getChildren().add(anchorPane);

    }

    public void showPredictScene(ActionEvent actionEvent) throws IOException {
        btnVisualize.setStyle("-fx-background-color: #425252; ");
        btnAbout.setStyle("-fx-background-color: #425252; ");
        btnPredict.setStyle("-fx-background-color: #1D7070; ");
        btnImport.setStyle("-fx-background-color: #425252; ");
        content.getChildren().clear();
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/fxml/Prediction.fxml"));
        content.getChildren().add(anchorPane);
    }

    public void showAboutScene(ActionEvent actionEvent) throws IOException {
        btnVisualize.setStyle("-fx-background-color: #425252; ");
        btnAbout.setStyle("-fx-background-color: #1D7070; ");
        btnPredict.setStyle("-fx-background-color: #425252; ");
        btnImport.setStyle("-fx-background-color: #425252; ");
//        Node test = (Node) FXMLLoader.load(getClass().getResource("../views/about.fxml"));

//        content = (AnchorPane) FXMLLoader.load()
        content.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("/fxml/About.fxml")));
//        contentStack.getChildren().add(content);
    }


    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }


}
