package controladoras;

import basedatos.Conexion;
import basedatos.DatosBaseDatos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.Persona;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import static basedatos.DatosBaseDatos.TAB_TABLAALUMNOS;

public class Controladora_Tabla implements Initializable {


    @FXML
    TableView<Persona> tabla;
    @FXML
    TableColumn<Persona,String> tNombre, tApellido, tCorreo;


    ObservableList<Persona> listaTabla;
    FilteredList<Persona> listaFiltrada;
    Conexion conexion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instancias();
        rellenarTabla();
        conexion = new Conexion();
        listaTabla = FXCollections.observableArrayList();
    }
    private void instancias() {
        listaTabla = FXCollections.observableArrayList();
        listaFiltrada = new FilteredList<Persona>(listaTabla);
        configurarTabla();
        tabla.setItems(listaFiltrada);
    }
    private void configurarTabla() {
        tNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        tCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
    }

    private void rellenarTabla() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Connection connection = null;
                try {
                    connection = Conexion.dbConnector();
                    String query;
                    Statement st = connection.createStatement();
                    query = "SELECT * FROM %s";
                    ResultSet rs = st.executeQuery(String.format(query, TAB_TABLAALUMNOS));
                    while (rs.next()) {
                        System.out.println(rs.getString(DatosBaseDatos.TAB_COL_NOMBRE));
                        listaTabla.add(new Persona(rs.getString(DatosBaseDatos.TAB_COL_NOMBRE), rs.getString(DatosBaseDatos.TAB_COL_APELLIDO),rs.getString(DatosBaseDatos.TAB_COL_CORREO)));
                        System.out.println(listaTabla.get(0).getNombre());
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        };
        thread.start();
}}
