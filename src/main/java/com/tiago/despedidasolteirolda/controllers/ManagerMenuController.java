package com.tiago.despedidasolteirolda.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ManagerMenuController {

    @FXML
    void listServices(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("managerListServices.fxml"));
            Scene listServicesScene = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(listServicesScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void listMarkings(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("managerListMarkings.fxml"));
            Scene listMarkingsScene = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(listMarkingsScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void aproveRegister(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("managerListRegister.fxml"));
            Scene listMarkingsScene = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(listMarkingsScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
