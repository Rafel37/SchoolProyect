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
                    String query = "INSERT INTO %s (%s, %s, %s, %s, %s, %s) " +
                            "VALUES ('%s', '%s', '%s', '%s', '%s', '%s')";
                    st.execute(String.format(query
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


    public static int getIDPersona(Persona persona){
        ResultSet rs = null;
        int aux=0;
        try {
            connection = basedatos.Conexion.dbConnector();
            Statement st = connection.createStatement();
            String sql = "SELECT %s from %s WHERE %s = '%s'";
            rs = st.executeQuery(String.format(sql
                    , DatosBaseDatos.TAB_COL_ID
                    , DatosBaseDatos.TAB_TABLAALUMNOS
                    , DatosBaseDatos.TAB_COL_NOMBRE
                    , utils.Persona.getNombre()));

            while (rs.next()) {
                aux     = rs.getInt(DatosBaseDatos.TAB_COL_ID);
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
}
