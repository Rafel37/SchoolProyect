package controladoras;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import utils.Persona;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controladora_Registro implements Initializable, EventHandler<ActionEvent> {

    ObservableList<Persona> listaCombo;

    @FXML
    JFXButton bRegistrarRegistro, bIndice;

    @FXML
    JFXTextField nombreRegistro, apellidosRegistro, correo1Registro, correo2Registro;

    @FXML
    JFXPasswordField passRegistro;

    @FXML
    JFXCheckBox conocimientosCheckRegistro;

    @FXML
    JFXComboBox comboRegistro;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instancias();
        rellenarLista();
        acciones();
    }

    private void rellenarLista() {
        listaCombo.add(new Persona("Rafa", "Perez", 123));
        listaCombo.add(new Persona("Cuvi", "Iglesias", 321));
        listaCombo.add(new Persona("Paquito", "Francis", 345));
//        listaCombo.addAll(new Persona("Rafa", "Perez", 123),
//                          new Persona("Rafa", "Perez", 123));
        comboRegistro.setItems(listaCombo);
        comboRegistro.setValue(listaCombo.get(0));
        Persona persona = (Persona) comboRegistro.getSelectionModel().getSelectedItem();
    }

    private void instancias() {
        listaCombo = FXCollections.observableArrayList();
    }

    private void acciones() {

        // BOTON REGISTRAR
        bRegistrarRegistro.setOnAction(this);
        // VOLVER BOTON FLECHA
        bIndice.setOnAction(this);
        // COMBO BOX
        comboRegistro.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println("tecla pulsada");
            }
        });
    }

    @Override
    public void handle(ActionEvent event) {
        Parent root;
        Scene scene;
        Stage stage;

        // REGISTRARSE
        if (event.getSource() == bRegistrarRegistro) {

            // FALTAN CAMPOS O CORREOS DEFERENTES
            if (  nombreRegistro.getText().isEmpty() &&
                  apellidosRegistro.getText().isEmpty() &&
                  correo1Registro.getText().isEmpty()  &&
                  correo2Registro.getText().equals(correo1Registro.getText()) &&
                  passRegistro.getText().isEmpty()  ) {

                System.out.println("faltan campos por rellenar o los correos no coinciden");
            }

            // TODOS LOS CAMPOS RELLENOS CORRECTAMENTE
            else {
                try {
                    root = FXMLLoader.load(getClass().getResource("../vistas/login.fxml"));
                    scene = new Scene(root, 800, 800);
                    stage = (Stage) bRegistrarRegistro.getScene().getWindow();
                    stage.setScene(scene);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // VOLVER
        else if (event.getSource() == bIndice) {
            try {
                root = FXMLLoader.load(getClass().getResource("../vistas/login.fxml"));
                scene = new Scene(root, 800, 800);
                stage = (Stage) bIndice.getScene().getWindow();
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}

