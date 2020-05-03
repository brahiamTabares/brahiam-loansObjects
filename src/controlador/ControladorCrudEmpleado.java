package controlador;

import excepciones.ClienteNoExisteException;
import excepciones.EmpleadoExisteException;
import excepciones.EmpleadoNoExisteException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Empleado;;

public class ControladorCrudEmpleado implements ControladorVentana {

	private ObservableList<Empleado> empleadoData = FXCollections.observableArrayList();

	@FXML
	private TableView<Empleado> tablaEmpleado;
	@FXML
	private TableColumn<Empleado, String> nombreColumn;
	@FXML
	private TableColumn<Empleado, String> apellidoColumn;
	@FXML
	private TableColumn<Empleado, String> cedulaColumn;
	@FXML
	private TableColumn<Empleado, String> direccionColumn;
	@FXML
	private TextField nombreText;
	@FXML
	private TextField apellidoText;
	@FXML
	private TextField cedulaText;
	@FXML
	private TextField direccionText;
	private boolean nuevo = true;

	@FXML
	private void initialize() {
		ControladorPrincipal.registrarControladorVentana(this);
		nombreColumn.setCellValueFactory(new PropertyValueFactory<Empleado, String>("nombre"));
		apellidoColumn.setCellValueFactory(new PropertyValueFactory<Empleado, String>("apellido"));
		cedulaColumn.setCellValueFactory(new PropertyValueFactory<Empleado, String>("cedula"));
		direccionColumn.setCellValueFactory(new PropertyValueFactory<Empleado, String>("Direccion"));
		empleadoData = FXCollections.observableArrayList(ControladorEmpresa.getInstance().listarEmpleados());

		tablaEmpleado.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			showEmpleadoDetails(newValue);

			nuevo = false;
		});

		tablaEmpleado.setItems(empleadoData);

	}

	/**
	 * Fills all text fields to show details about the person. If the specified
	 * person is null, all text fields are cleared.
	 * 
	 * @param person the person or null
	 */
	private void showEmpleadoDetails(Empleado empleado) {
		if (empleado != null) {
			nombreText.setText(empleado.getNombre());
			apellidoText.setText(empleado.getApellido());
			cedulaText.setText(empleado.getCedula());
			direccionText.setText(empleado.getDireccion());
		} else {
			nombreText.setText("");
			apellidoText.setText("");
			cedulaText.setText("");
			direccionText.setText("");
			tablaEmpleado.getSelectionModel().clearSelection();
			nuevo = true;
		}
	}

	@FXML
	public void agregarEmpleado() {

		showEmpleadoDetails(null);

	}

	@FXML
	public void actualizarEmpleado() {

		Empleado empleado;

		if (nuevo) {

			try {
				empleado = ControladorEmpresa.getInstance().crearEmpleado(nombreText.getText(), apellidoText.getText(),
						cedulaText.getText(), direccionText.getText());
				tablaEmpleado.getItems().add(empleado);
			} catch (EmpleadoExisteException e) {
				AlertaUtil.mostrarMensajeError(e.getMessage());
			}

		} else {
			try { 
			
				ControladorEmpresa.getInstance().actualizarEmpleado(cedulaText.getText(), nombreText.getText(),
						apellidoText.getText(), direccionText.getText());
			} catch (EmpleadoNoExisteException e) {
				AlertaUtil.mostrarMensajeError(e.getMessage());
			
		}

		showEmpleadoDetails(null);
		tablaEmpleado.refresh();
		}
	}

	@FXML
	public void eliminarEmpleado() {
	
		try {
			Empleado empleado = ControladorEmpresa.getInstance().eliminarEmpleado(cedulaText.getText());
			tablaEmpleado.getItems().remove(empleado);
		} catch (EmpleadoNoExisteException e) {
			AlertaUtil.mostrarMensajeError(e.getMessage());
		}

	}

	public void buscarEmpleado() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controlador.ControladorVentana#actualizarVentana()
	 */
	@Override
	public void actualizarVentana() {
		tablaEmpleado.refresh();
	}

}