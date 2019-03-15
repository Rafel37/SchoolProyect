package controladoras;

import basedatos.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import utils.Persona;

import java.net.URL;
import java.util.ResourceBundle;

public class Controladora_Tabla implements Initializable {


    @FXML
    TableView tabla;
    @FXML
    TableColumn<Persona,String> tNombre, tApellido, tCorreo;


    ObservableList<Persona> listaAlumnos;
    Conexion conexion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        conexion = new Conexion();
        listaAlumnos = FXCollections.observableArrayList();
    }
}
