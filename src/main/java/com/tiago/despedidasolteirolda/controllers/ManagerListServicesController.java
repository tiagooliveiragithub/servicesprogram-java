package com.tiago.despedidasolteirolda.controllers;

import com.tiago.despedidasolteirolda.data.FileManager;
import com.tiago.despedidasolteirolda.entities.Service;
import com.tiago.despedidasolteirolda.entities.enums.Localidades;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class ManagerListServicesController implements Initializable {

    @FXML private TableView<Service> tableView;
    @FXML private ComboBox<Localidades> local;
    @FXML private DatePicker startDateField;
    @FXML private DatePicker endDateField;

    private Collection<Service> services = new HashSet<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        search();

        MenuItem editService = new MenuItem("Editar");
        editService.setOnAction(event -> {
            Service service = tableView.getSelectionModel().getSelectedItem();
            if (service != null) {

            }
        });


        MenuItem activeService = new MenuItem("Mudar Estado");
        activeService.setOnAction(event -> {
            Service service = tableView.getSelectionModel().getSelectedItem();

            Alert alertStateChange = new Alert(Alert.AlertType.CONFIRMATION);
            alertStateChange.setTitle("Confirmação");
            alertStateChange.setHeaderText("Queres mesmo alterar o estado do serviço?");
            Optional<ButtonType> result = alertStateChange.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                boolean currentState = service.getActive();
                service.setActive(!currentState);
                service.update();
                tableView.refresh();
            }
        });
        tableView.setContextMenu(new ContextMenu(editService, activeService));

    }

    void search() {
        local.getItems().setAll(Localidades.values());
        FileManager fl = FileManager.getFileManager();
        Collection<Service> services = fl.getServices().values();
        tableView.getItems().setAll(services);
        this.services = services;
    }

    @FXML
    void filterClear(MouseEvent event) {
        tableView.getItems().setAll(services);
    }

    @FXML
    void filterServices(MouseEvent event) {
        Localidades selectedLocation = local.getValue();
        LocalDate startDate = startDateField.getValue();
        LocalDate endDate = endDateField.getValue();

        // Check if any filters are selected
        boolean hasLocationFilter = selectedLocation != null;
        boolean hasStartDateFilter = startDate != null;
        boolean hasEndDateFilter = endDate != null;

        Set<Service> filteredServices = new HashSet<>();

        for (Service service : services) {
            // Check if the service matches the location filter
            boolean locationMatch = !hasLocationFilter || service.getLocal() == selectedLocation;

            // Check if the service has any markings that match the start date filter
            boolean startDateMatch = !hasStartDateFilter || service.getMarkings().stream()
                    .anyMatch(marking -> marking.getMarkingDay().isEqual(startDate) || marking.getMarkingDay().isAfter(startDate));

            // Check if the service has any markings that match the end date filter
            boolean endDateMatch = !hasEndDateFilter || service.getMarkings().stream()
                    .anyMatch(marking -> marking.getMarkingDay().isEqual(endDate) || marking.getMarkingDay().isBefore(endDate.plusDays(1)));

            if (locationMatch && startDateMatch && endDateMatch) {
                filteredServices.add(service);
            }
        }

        // Update the TableView with the filtered services
        tableView.getItems().setAll(filteredServices);
    }

    @FXML
    void backScene(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("managerMenu.fxml"));
            Scene menuScene = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(menuScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
