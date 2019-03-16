package utils;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Persona {

    SimpleStringProperty nombre;
    SimpleStringProperty apellido;
    SimpleStringProperty correo;
    SimpleStringProperty password;
    SimpleStringProperty ciclo;
    SimpleIntegerProperty conocimientos;

    public Persona(String nombre, String apellido, String correo, String password, String ciclo, int conocimientos) {
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.correo = new SimpleStringProperty(correo);
        this.password = new SimpleStringProperty(password);
        this.ciclo = new SimpleStringProperty(ciclo);
        this.conocimientos = new SimpleIntegerProperty(conocimientos);
    }

    public Persona(String nombre, String apellido, String correo) {
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.correo = new SimpleStringProperty(correo);
    }

    public  SimpleStringProperty getNombre() {
        return nombre;
    }

    public  SimpleStringProperty getApellido() {
        return apellido;
    }

    public  SimpleStringProperty getCorreo() {
        return correo;
    }

    public  SimpleStringProperty getPassword() {
        return password;
    }

    public  SimpleStringProperty getCiclo() {
        return ciclo;
    }

    public  SimpleIntegerProperty getConocimientos() {
        return conocimientos;
    }
}
