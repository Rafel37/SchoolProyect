package controladoras;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controladora_Login implements Initializable {

    @FXML
    Label registroLogin;
    @FXML
    Label olvidoLogin;
    @FXML
    JFXTextField usuarioLogin;
    @FXML
    JFXPasswordField passLogin;
    @FXML
    JFXButton inicioLogin;
    @FXML
    CheckBox recordarLogin;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        acciones();
        lecuraPass();
    }

    private void acciones() {

        // INICIAR SESION
        inicioLogin.setOnMouseExited(new ManejoRaton());
        inicioLogin.setOnMouseEntered(new ManejoRaton());
        inicioLogin.setOnMousePressed(new ManejoRaton());
        inicioLogin.setOnMouseReleased(new ManejoRaton());

        // RECORDAR CREDENCIALES
        recordarLogin.setOnMouseExited(new ManejoRaton());
        recordarLogin.setOnMouseEntered(new ManejoRaton());
        recordarLogin.setOnMousePressed(new ManejoRaton());
        recordarLogin.setOnMouseReleased(new ManejoRaton());

        //CONTRASEÑA OLVIDADA
        olvidoLogin.setOnMouseExited(new ManejoRaton());
        olvidoLogin.setOnMouseEntered(new ManejoRaton());
        olvidoLogin.setOnMousePressed(new ManejoRaton());
        olvidoLogin.setOnMouseReleased(new ManejoRaton());

        // REGISTRARSE
        registroLogin.setOnMouseExited(new ManejoRaton());
        registroLogin.setOnMouseEntered(new ManejoRaton());
        registroLogin.setOnMousePressed(new ManejoRaton());
        registroLogin.setOnMouseReleased(new ManejoRaton());
        registroLogin.setOnMouseClicked(new ManejoRaton());

        // SESIONES
        inicioLogin.setOnAction(new ManejoAction());
        recordarLogin.setOnAction(new ManejoAction());
    }


    class ManejoRaton implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {

            // INICIAR SESION
            if (event.getSource() == inicioLogin) {
                if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
                    inicioLogin.setTextFill(Color.BLUE);
                    inicioLogin.getScene().setCursor(Cursor.OPEN_HAND);
                } else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
                    inicioLogin.setTextFill(Color.BLACK);
                    inicioLogin.getScene().setCursor(Cursor.DEFAULT);
                } else if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                    System.out.println("Login");
                    inicioLogin.getScene().setCursor(Cursor.CLOSED_HAND);

                } else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
                    inicioLogin.getScene().setCursor(Cursor.OPEN_HAND);
                }
            }

            // RECORDAR CREDENCIALES
            else if (event.getSource() == recordarLogin) {
                if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
                    recordarLogin.setTextFill(Color.BLUE);
                    recordarLogin.getScene().setCursor(Cursor.OPEN_HAND);
                } else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
                    recordarLogin.setTextFill(Color.BLACK);
                    recordarLogin.getScene().setCursor(Cursor.DEFAULT);
                } else if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                    System.out.println("Recordar");
                    recordarLogin.getScene().setCursor(Cursor.CLOSED_HAND);
                } else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
                    recordarLogin.getScene().setCursor(Cursor.OPEN_HAND);
                }
            }

            //CONTRASEÑA OLVIDADA
            else if (event.getSource() == olvidoLogin) {
                if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
                    olvidoLogin.setTextFill(Color.BLUE);
                    olvidoLogin.getScene().setCursor(Cursor.OPEN_HAND);
                } else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
                    olvidoLogin.setTextFill(Color.BLACK);
                    olvidoLogin.getScene().setCursor(Cursor.DEFAULT);
                } else if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                    System.out.println("Olvidada");
                    olvidoLogin.getScene().setCursor(Cursor.CLOSED_HAND);
                } else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
                    olvidoLogin.getScene().setCursor(Cursor.OPEN_HAND);
                }
            }

            // REGISTRARSE
            else if (event.getSource() == registroLogin) {
                if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
                    registroLogin.setTextFill(Color.BLUE);
                    registroLogin.getScene().setCursor(Cursor.OPEN_HAND);
                } else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
                    registroLogin.setTextFill(Color.BLACK);
                    registroLogin.getScene().setCursor(Cursor.DEFAULT);
                } else if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                    System.out.println("Registrar");
                    registroLogin.getScene().setCursor(Cursor.CLOSED_HAND);
                } else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
                    registroLogin.getScene().setCursor(Cursor.OPEN_HAND);
                }

                // CREAR FICHERO DE RECORDAR CONTRASEÑA
                else if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
                    Parent root;
                    Scene scene;
                    Stage stage;
                    try {
                        root = FXMLLoader.load(getClass().getResource("../vistas/registro.fxml"));
                        scene = new Scene(root, 900, 900);
                        stage = (Stage) registroLogin.getScene().getWindow();
                        stage.setScene(scene);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }


    class ManejoAction implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {


            // RECORDAR CONTRASEÑA
            if (event.getSource() == inicioLogin && event.getSource() == recordarLogin) {

                File f = null;
                f = new File("src/sesiones/sesiones.txt");
                FileWriter fw = null;
                BufferedWriter bw;

                try {
                    fw = new FileWriter(f);
                    bw = new BufferedWriter(fw);

                    bw.write(usuarioLogin.getText());
                    bw.newLine();
                    bw.write(passLogin.getText());
                    System.out.println("done");
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void lecuraPass() {

        // LEER CONTRASEÑA RECORDADA
        try {
            String usuario, pass;
            FileReader fr = new FileReader("src/sesiones/sesiones.txt");
            BufferedReader br = new BufferedReader(fr);
            usuario = br.readLine();
            pass = br.readLine();
            usuarioLogin.setText(usuario);
            passLogin.setText(pass);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
