package controlador;

import java.io.IOException;

import excepciones.AutenticacionException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ControladorLogin {
	@FXML
	private TextField login;
	@FXML
	private PasswordField password;
	@FXML
	private Label mensajes;

	@FXML
	private void initialize() {
	}

	@FXML
	public void autenticar() {
		
		try {
			if (ControladorEmpresa.getInstance().autenticar(login.getText(), password.getText())) {
				abrirPrincipal();
			}
		} catch (AutenticacionException e) {
			AlertaUtil.mostrarMensajeError(e.getMessage());
		} 
	}

	private void abrirPrincipal() {
		String vistaActiva = "/vista/vistaPrincipal.fxml";
		try {
			Stage principal = new Stage();
			Pane root = (Pane) FXMLLoader.load(getClass().getResource(vistaActiva));
			Scene scene = new Scene(root, 800, 650);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			principal.setScene(scene);
			principal.show();
			( (Stage) login.getScene().getWindow() ).close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
