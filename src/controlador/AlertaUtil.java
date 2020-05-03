package controlador;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class AlertaUtil {
	public static void mostrarMensajeError(String mensaje) {
		Alert alert = new Alert(AlertType.ERROR, mensaje, ButtonType.OK);
		alert.showAndWait();
	}
}
