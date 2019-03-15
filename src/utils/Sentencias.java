package utils;

import basedatos.DatosBaseDatos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Sentencias {

    static Connection connection = null;

    public static void registrarPersona(Persona persona){
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    connection = basedatos.Conexion.dbConnector();
                    Statement st = connection.createStatement();
                    String query = "INSERT INTO %s (%s, %s, %s, %s) " +
                            "VALUES ('%s', '%s', '%s', '%s')";
                    if (st.execute(String.format(query
                            , DatosBaseDatos.TAB_TABLAUSUARIOS
                            , DatosBaseDatos.TAB_INICIO_COL_NOMBRE
                            , DatosBaseDatos.TAB_INICIO_COL_APELLIDO
                            , DatosBaseDatos.TAB_INICIO_COL_CORREO
                            , DatosBaseDatos.TAB_INICIO_COL_PASS
                            , utils.Persona.getNombre()
                            , utils.Persona.getApellido()
                            , utils.Persona.getCorreo()
                            , utils.Persona.getPassword()
                    ))){
                        System.out.println("INSERCION NO REALIZADA");
                    }
                    else {
                        System.out.println("INSERCION REALIZADA CORRECTAMENTE");
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
        thread.start();
    }

    public static void registrarCicloPersona(Persona persona) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    connection = basedatos.Conexion.dbConnector();
                    Statement st = connection.createStatement();
                    String query = "INSERT INTO %s (%s, %s, %s) " +
                            "VALUES ('%s', '%d', '%d')";
                    if (st.execute(String.format(query
                            , DatosBaseDatos.TAB_TABLACICLOS
                            , DatosBaseDatos.TAB_CICLOS_COL_CICLO
                            , DatosBaseDatos.TAB_CICLOS_ID_ALUMNO
                            , DatosBaseDatos.TAB_CICLOS_COL_CONOCIMIENTO
                            , utils.Persona.getCiclo()
                            , getIDPersona(persona)
                            , utils.Persona.getConocimientos()
                    ))) {
                        System.out.println("INSERCION NO REALIZADA");
                    } else {
                        System.out.println("INSERCION REALIZADA CORRECTAMENTE");
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        //connection.commit();
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }
    public static int getIDPersona(Persona persona){
        ResultSet rs = null;
        int aux=0;
        try {
            connection = basedatos.Conexion.dbConnector();
            Statement st = connection.createStatement();
            String sql = "SELECT %s from %s WHERE %s = '%s'";
            rs = st.executeQuery(String.format(sql
                    , DatosBaseDatos.TAB_INICIO_COL_ID
                    , DatosBaseDatos.TAB_TABLAUSUARIOS
                    , DatosBaseDatos.TAB_INICIO_COL_NOMBRE
                    , utils.Persona.getNombre()));

            while (rs.next()) {
                aux     = rs.getInt(DatosBaseDatos.TAB_INICIO_COL_ID);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs!=null){
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return aux;
    }


    public static boolean iniciarSesion(String persona, String contrasenia){
        ResultSet rs = null;
        try {
            connection = basedatos.Conexion.dbConnector();
            Statement st = connection.createStatement();
            String sql = "SELECT %s, %s from %s WHERE %s = '%s' AND %s = '%s'";
            rs = st.executeQuery(String.format(sql
                    , DatosBaseDatos.TAB_INICIO_COL_NOMBRE
                    , DatosBaseDatos.TAB_INICIO_COL_PASS
                    , DatosBaseDatos.TAB_TABLAUSUARIOS
                    , DatosBaseDatos.TAB_INICIO_COL_NOMBRE
                    , persona
                    , DatosBaseDatos.TAB_INICIO_COL_PASS
                    , contrasenia));
            return(rs.first());

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (rs!=null){
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
