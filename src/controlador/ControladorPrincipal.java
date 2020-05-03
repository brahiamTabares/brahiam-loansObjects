package controlador;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;

public class ControladorPrincipal implements ControladorVentana {
	@FXML
	private TabPane tabpane;
	private static List<ControladorVentana> controladores = new ArrayList<ControladorVentana>();

	@FXML
	private void initialize() {
		tabpane.getSelectionModel().selectedItemProperty().addListener(t -> {
			actualizarVentana();

		});
	}

	public static void registrarControladorVentana(ControladorVentana controlador) {
		controladores.add(controlador);
	}

	@Override
	public void actualizarVentana() {
		for (ControladorVentana controladorVentana : controladores) {
			controladorVentana.actualizarVentana();
		}
	}
}
