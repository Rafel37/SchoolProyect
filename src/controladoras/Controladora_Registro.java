package controladoras;

import basedatos.DatosBaseDatos;
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
import utils.Ciclo;
import utils.Persona;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Controladora_Registro implements Initializable, EventHandler<ActionEvent> {

    ObservableList<Ciclo> listaCombo;

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
        listaCombo.add(new Ciclo("ASIR"));
        listaCombo.add(new Ciclo("DAM"));
        listaCombo.add(new Ciclo("DAW"));
        listaCombo.add(new Ciclo("AF"));
        comboRegistro.setItems(listaCombo);
        comboRegistro.setValue(listaCombo.get(0));
        Ciclo ciclo = (Ciclo) comboRegistro.getSelectionModel().getSelectedItem();
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

        while (nombreRegistro.getText().isEmpty() &&
                apellidosRegistro.getText().isEmpty() &&
                correo1Registro.getText().isEmpty()  &&
                correo2Registro.getText().equals(correo1Registro.getText()) &&
                passRegistro.getText().isEmpty() &&
                comboRegistro.getItems().isEmpty()) {
            bRegistrarRegistro.setDisable(false);
        }
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
                  passRegistro.getText().isEmpty() &&
                  comboRegistro.getItems().isEmpty() ){


                JOptionPane.showMessageDialog(null, "Rellene todos los datos, por favor.", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                System.out.println("faltan campos por rellenar o los correos no coinciden");
                    }

            // TODOS LOS CAMPOS RELLENOS CORRECTAMENTE
            else {
                try {
                    int aux;
                    if (conocimientosCheckRegistro.isSelected()) {
                        aux = 1;
                    } else {
                        aux = 0;
                    }
                    Persona p = new Persona(nombreRegistro.getText(),
                            apellidosRegistro.getText(),
                            correo1Registro.getText(),
                            passRegistro.getText(),
                            comboRegistro.getValue().toString(),
                            aux);
                    registrarPersona(p);


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
    public static void registrarPersona(Persona persona){
        Thread thread = new Thread(){
            @Override
            public void run() {

                Connection connection = null;
                try {
                    connection = basedatos.Conexion.dbConnector();
                    String query = "INSERT INTO %s (%s, %s, %s, %s, %s, %s)"+
                                   "VALUES ('%s', '%s', '%s', '%s', '%s', '%s');";
                    Statement st = connection.createStatement();
                    st.executeQuery(String.format(query
                            , DatosBaseDatos.TAB_TABLAALUMNOS
                            , DatosBaseDatos.TAB_COL_NOMBRE
                            , DatosBaseDatos.TAB_COL_APELLIDO
                            , DatosBaseDatos.TAB_COL_CORREO
                            , DatosBaseDatos.TAB_COL_PASS
                            , DatosBaseDatos.TAB_COL_CICLO
                            , DatosBaseDatos.TAB_COL_CONOCIMIENTO
                            , utils.Persona.getNombre()
                            , utils.Persona.getApellido()
                            , utils.Persona.getCorreo()
                            , utils.Persona.getPassword()
                            , utils.Persona.getCiclo()
                            , utils.Persona.getConocimientos()
                    ));
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }


}

