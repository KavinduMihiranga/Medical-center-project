package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Drug;
import model.Supplier;
import model.Test;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class PharmacyFormController {
    public JFXButton btnExit;
    public JFXTextField txtDrugName;
    public JFXTextField txtTradeName;
    public Label lblDrugId;
    public JFXButton btnUpdateDrug;
    public JFXButton btnDeleteDrug;
    public JFXButton btnAddDrug;
    public TableColumn colDrugId;
    public TableColumn colTradeName;
    public TableColumn colDrugName;
    public TableView<Drug> tblDrug;
    public TableColumn colSupplierId;
    public TableColumn colSupplierName;
    public JFXTextField txtSupplierName;
    public JFXButton btnUpdateSupplier;
    public JFXButton btnDeleteSupplier;
    public JFXButton btnAddSupplier;
    public Label lblSupplierId;
    public TableView<Supplier> tblSupplier;
    public TableView tblStock;
    public TableColumn colStockId;
    public TableColumn colStockDrugId;
    public TableColumn colDrugTradeName;
    public TableColumn colStockSupplierName;
    public TableColumn colCost;
    public TableColumn colSupplyDate;
    public TableColumn colQty;
    public JFXTextField txtDrugId;
    public JFXTextField txtDrugTradeName;
    public JFXTextField txtCost;
    public JFXTextField txtSupplyDate;
    public JFXTextField txtStockSupplierName;
    public JFXButton btnUpdateStock;
    public JFXButton btnDeleteStock;
    public JFXButton btnAddStock;
    ObservableList<Drug> drugsObservableList;
    ObservableList<Supplier> supplierObservableList;
    public void initialize() throws SQLException, ClassNotFoundException {
        colDrugId.setCellValueFactory(new PropertyValueFactory<>("DrugId"));
        colDrugName.setCellValueFactory(new PropertyValueFactory<>("DrugName"));
        colTradeName.setCellValueFactory(new PropertyValueFactory<>("TradeName"));

        setDrugTable();

        try {
            lblDrugId.setText(new DrugController().getDrugId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        tblDrug.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null)
                loadDrugData(newValue);

        });

        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));

        setSupplierTable();

        lblSupplierId.setText(new SupplierController().getSupplierId());

        tblSupplier.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null)
                loadSupplierData(newValue);
        });
    }

    private void loadSupplierData(Supplier newValue) {
        lblSupplierId.setText(newValue.getSupplierId());
        txtSupplierName.setText(newValue.getSupplierName());
    }

    private void setSupplierTable() throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> suppliers = new SupplierController().getAllSupplier();
        supplierObservableList = FXCollections.observableArrayList(suppliers);
        tblSupplier.setItems(supplierObservableList);
    }

    private void loadDrugData(Drug newValue) {
        lblDrugId.setText(newValue.getDrugId());
        txtDrugName.setText(newValue.getDrugName());
        txtTradeName.setText(newValue.getTradeName());

    }

    public void logOut(ActionEvent actionEvent) throws IOException {

    }

    public void updateDrug(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String drugId = lblDrugId.getText();
        String drugName = txtDrugName.getText();
        String tradeName = txtTradeName.getText();


        Drug drug = new Drug(drugId, drugName, tradeName);
        if (new DrugController().updateDrug(drug)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Update Drug Successfully...").show();
        }
        setDrugTable();
    }

    public void deleteDrug(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new DrugController().deleteDrug(lblDrugId.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }

        setDrugTable();
        tblSupplier.refresh();
    }

    public void addDrug(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String drugId = lblDrugId.getText();
        String drugName = txtDrugName.getText();
        String tradeName = txtTradeName.getText();


        Drug drug = new Drug(drugId, drugName, tradeName);
        if (new DrugController().saveDrug(drug)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Add Drug Successfully...").show();

        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again...").show();
        }
        setDrugTable();
        lblDrugId.setText("");
        txtDrugName.clear();
        txtTradeName.clear();
        lblDrugId.setText(new DrugController().getDrugId());
    }

    private void setDrugTable() throws SQLException, ClassNotFoundException {
        ArrayList<Drug> drugs = new DrugController().getAllDrug();
        drugsObservableList = FXCollections.observableArrayList(drugs);
        tblDrug.setItems(drugsObservableList);
    }

    public void updateSupplier(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String supplierId = lblSupplierId.getText();
        String supplierName = txtSupplierName.getText();


        Supplier supplier = new Supplier(supplierId, supplierName);
        if (new SupplierController().updateSupplier(supplier)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Update Supplier Successfully...").show();
        }
        setSupplierTable();
    }

    public void deleteSupplier(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new SupplierController().deleteSupplier(lblSupplierId.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }

        setDrugTable();
    }

    public void addSupplier(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String supplierId = lblSupplierId.getText();
        String supplierName = txtSupplierName.getText();


        Supplier supplier = new Supplier(supplierId,supplierName);
        if (new SupplierController().saveSupplier(supplier)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Add Supplier Successfully...").show();

        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again...").show();
        }
        setSupplierTable();
        lblSupplierId.setText("");
        txtSupplierName.clear();
        lblSupplierId.setText(new SupplierController().getSupplierId());
    }

    public void UpdateStock(ActionEvent actionEvent) {
    }

    public void DeleteStock(ActionEvent actionEvent) {
    }

    public void AddStock(ActionEvent actionEvent) {
    }
}
