package org.example.examenjavafx.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import org.example.examenjavafx.dao.UsuarioDAO;
import org.example.examenjavafx.model.Usuario;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private TableView<Usuario> tableView;
    @FXML
    private TableColumn<Usuario, String> colCorreo;
    @FXML
    private TableColumn<Usuario, String> colPlataforma;
    @FXML
    private TableColumn<Usuario, String> colRol;
    @FXML
    private TableColumn<Usuario, String> colFecha;
    @FXML
    private TableColumn<Usuario, String> colHora;

    @FXML
    private TextField txtCorreo;
    @FXML
    private TextField txtPlataforma;
    @FXML
    private TextField txtVersion;
    @FXML
    private CheckBox chkAdmin;

    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnEliminar;

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final ObservableList<Usuario> usuariosList = FXCollections.observableArrayList();

    private Usuario usuarioSeleccionado = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCorreo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        colPlataforma.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPlataforma()));
        colRol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIs_admin() ? "Admin" : "Usuario"));
        colFecha.setCellValueFactory(cellData -> {
            String fechaCompleta = cellData.getValue().getFecha();
            String[] partes = fechaCompleta.split(" ");
            return new SimpleStringProperty(partes.length > 0 ? partes[0] : "");
        });
        colHora.setCellValueFactory(cellData -> {
            String fechaCompleta = cellData.getValue().getFecha();
            String[] partes = fechaCompleta.split(" ");
            return new SimpleStringProperty(partes.length > 1 ? partes[1] : "");
        });

        usuariosList.setAll(usuarioDAO.findAll());
        tableView.setItems(usuariosList);

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            usuarioSeleccionado = newSelection;
            mostrarDetalleUsuario();
        });
    }

    private void mostrarDetalleUsuario() {
        if (usuarioSeleccionado != null) {
            txtCorreo.setText(usuarioSeleccionado.getEmail());
            txtPlataforma.setText(usuarioSeleccionado.getPlataforma());
            txtVersion.setText(usuarioSeleccionado.getVersion());
            chkAdmin.setSelected(Boolean.TRUE.equals(usuarioSeleccionado.getIs_admin()));
            btnEliminar.setDisable(false);
        } else {
            txtCorreo.clear();
            txtPlataforma.clear();
            txtVersion.clear();
            chkAdmin.setSelected(false);
            btnEliminar.setDisable(true);
        }
    }

    @FXML
    private void handleGuardar() {
        String correo = txtCorreo.getText();
        String plataforma = txtPlataforma.getText();
        String version = txtVersion.getText();
        Boolean isAdmin = chkAdmin.isSelected();

        // Comprobación de datos obligatorios
        if (correo == null || correo.trim().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Datos incompletos");
            alert.setHeaderText(null);
            alert.setContentText("Faltan datos: el campo correo es obligatorio.");
            alert.showAndWait();
            return;
        }

        String fechaCompleta = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        if (usuarioSeleccionado == null) {
            Usuario nuevo = new Usuario(null, correo, plataforma, version, fechaCompleta, isAdmin);
            usuarioDAO.save(nuevo);
            usuariosList.setAll(usuarioDAO.findAll());
        } else {
            usuarioSeleccionado.setEmail(correo);
            usuarioSeleccionado.setPlataforma(plataforma);
            usuarioSeleccionado.setVersion(version);
            usuarioSeleccionado.setIs_admin(isAdmin);
            usuarioSeleccionado.setFecha(fechaCompleta);
            usuarioDAO.update(usuarioSeleccionado);
            usuariosList.setAll(usuarioDAO.findAll());
        }
        tableView.getSelectionModel().clearSelection();
        usuarioSeleccionado = null;
        mostrarDetalleUsuario();
    }

    @FXML
    private void handleEliminar() {
        if (usuarioSeleccionado != null) {
            usuarioDAO.delete(usuarioSeleccionado);
            usuariosList.setAll(usuarioDAO.findAll());
            tableView.getSelectionModel().clearSelection();
            usuarioSeleccionado = null;
            mostrarDetalleUsuario();
        }
    }

    @FXML
    private void handleNuevo() {
        tableView.getSelectionModel().clearSelection();
        usuarioSeleccionado = null;
        mostrarDetalleUsuario();
    }
}
