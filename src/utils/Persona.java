package utils;

public class Persona {

    static String nombre;
    static String apellido;
    static String correo;
    static String password;
    static String ciclo;
    static int conocimientos;

    public Persona(String nombre, String apellido, String correo, String password, String ciclo, int conocimientos) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.password = password;
        this.ciclo = ciclo;
        this.conocimientos = conocimientos;
    }

    public static String getNombre() {
        return nombre;
    }

    public static String getApellido() {
        return apellido;
    }

    public static String getCorreo() {
        return correo;
    }

    public static String getPassword() {
        return password;
    }

    public static String getCiclo() {
        return ciclo;
    }

    public static int getConocimientos() {
        return conocimientos;
    }
}
