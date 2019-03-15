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
import java.util.ResourceBundle;

public class Controladora_Tabla implements Initializable {


    @FXML
    TableView tabla;
    @FXML
    TableColumn<Persona,String> tNombre, tApellido, tCorreo;


    ObservableList<Persona> listaTabla;
    FilteredList<Persona> listaFiltrada;
    Conexion conexion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instancias();
        configurarTabla();
        rellenarTabla();
        conexion = new Conexion();
        listaTabla = FXCollections.observableArrayList();
    }
    private void instancias() {
        listaTabla = FXCollections.observableArrayList();
        listaFiltrada = new FilteredList<Persona>(listaTabla, null);
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
                    String query = "SELECT DISTINCT %s FROM %s";
                    ResultSet rs = connection.createStatement()
                            .executeQuery(String.format(query
                                    , DatosBaseDatos.TAB_COL_NOMBRE
                                    , DatosBaseDatos.TAB_COL_APELLIDO
                                    , DatosBaseDatos.TAB_COL_CORREO));

                    while (rs.next()) {
//                        listaTabla.add(rs.getString(DatosBaseDatos.TAB_TABLAALUMNOS));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

}}
