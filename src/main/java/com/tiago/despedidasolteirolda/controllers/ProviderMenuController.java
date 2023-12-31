package com.tiago.despedidasolteirolda.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProviderMenuController {

    @FXML
    void createService(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("providerCreateService.fxml"));
            Scene createScene = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(createScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void listServices(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("providerListServices.fxml"));
            Scene listScene = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(listScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void listMarkings(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("providerListMarkings.fxml"));
            Scene listScene = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(listScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
