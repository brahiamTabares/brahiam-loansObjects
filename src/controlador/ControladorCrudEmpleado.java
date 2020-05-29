package controlador;


import excepciones.EmpleadoExisteException;
import excepciones.EmpleadoNoExisteException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Empleado;

public class ControladorCrudEmpleado implements ControladorVentana {

	private ObservableList<Empleado> empleadoData = FXCollections.observableArrayList();

	@FXML
	private TableView<Empleado> tablaEmpleado;
	@FXML
	private TableColumn<Empleado, String> nombreColumn;
	@FXML
	private TableColumn<Empleado, String> tipoDocumentoColumn;
	@FXML
	private TableColumn<Empleado, String> numeroDColumn;
	@FXML
	private TableColumn<Empleado, String> telefonoColumn;
	@FXML
	private TableColumn<Empleado, String> celularColumn;

	@FXML
	private TableColumn<Empleado, String> direccionColumn;
	@FXML
	private TableColumn<Empleado, String> ciudadRColumn;
	@FXML
	private TableColumn<Empleado, String> departamentoColumn;
	@FXML
	private TableColumn<Empleado, String> paisColumn;
	@FXML
	private TableColumn<Empleado, String> tipoEmpleadoColumn;
	@FXML
	private TableColumn<Empleado, String> emailColumn;
	@FXML
	private TextField nombreText;
	@FXML
	private ComboBox<String> tipoDocumentoText;
	@FXML
	private TextField numeroDText;
	@FXML
	private TextField telefonoText;
	@FXML
	private TextField celularText;
	@FXML
	private TextField direccionTest;
	@FXML
	private TextField ciudadRText;
	@FXML
	private TextField departamentoText;
	@FXML
	private TextField paisText;
	@FXML
	private TextField tipoEmpleadoText;
	@FXML
	private TextField emailText;

	/*
	 * Organizar contructores
	 */

	private boolean nuevo = true;

	@FXML
	private void initialize() {

		inicializarTabla();
		inicializarTipoDocumento();

	}

	private void inicializarTabla() {
		ControladorPrincipal.registrarControladorVentana(this);

		nombreColumn.setCellValueFactory(new PropertyValueFactory<Empleado, String>("nombre"));
		tipoDocumentoColumn.setCellValueFactory(new PropertyValueFactory<Empleado, String>("tipoDocumento"));
		numeroDColumn.setCellValueFactory(new PropertyValueFactory<Empleado, String>("numeroDocumento"));
		telefonoColumn.setCellValueFactory(new PropertyValueFactory<Empleado, String>("telefono"));
		celularColumn.setCellValueFactory(new PropertyValueFactory<Empleado, String>("celular"));
		direccionColumn.setCellValueFactory(new PropertyValueFactory<Empleado, String>("Direccion"));
		departamentoColumn.setCellValueFactory(new PropertyValueFactory<Empleado, String>("departamento"));
		paisColumn.setCellValueFactory(new PropertyValueFactory<Empleado, String>("pais"));
		tipoEmpleadoColumn.setCellValueFactory(new PropertyValueFactory<Empleado, String>("profesion"));
		emailColumn.setCellValueFactory(new PropertyValueFactory<Empleado, String>("email"));
		empleadoData = FXCollections.observableArrayList(ControladorEmpresa.getInstance().listarEmpleados());
		tablaEmpleado.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			showEmpleaDetails(newValue);
			nuevo = false;
		});

		System.out.println(empleadoData.getClass().getName());
		tablaEmpleado.setItems(empleadoData);

	}

	/**
	 * Fills all text fields to show details about the person. If the specified
	 * person is null, all text fields are cleared.
	 * 
	 * @param person the person or null
	 */
	private void showEmpleaDetails(Empleado empleado) {
		if (empleado != null) {
			nombreText.setText(empleado.getNombre());
			tipoDocumentoText.setValue(empleado.getTipoDocumento());
			numeroDText.setText(empleado.getNumeroDocumento());
			telefonoText.setText(empleado.getTelefonoResidencia());
			celularText.setText(empleado.getTelefonoCelular());
			direccionTest.setText(empleado.getDireccion());
			ciudadRText.setText(empleado.getCiudadR());
			departamentoText.setText(empleado.getDepartamento());
			paisText.setText(empleado.getPais());
			tipoEmpleadoText.setText(empleado.getTipoEmpleado());
			emailText.setText(empleado.getEmail());

		} else {

			nombreText.setText("");
			tipoDocumentoText.getSelectionModel().clearSelection();
			numeroDText.setText("");
			telefonoText.setText("");
			celularText.setText("");
			direccionTest.setText("");
			celularText.setText("");
			departamentoText.setText("");
			paisText.setText("");
		tipoEmpleadoColumn.setText("");
			emailText.setText("");

			nuevo = true;
			tablaEmpleado.getSelectionModel().clearSelection();

		}

		tablaEmpleado.refresh();
	}

	private void inicializarTipoDocumento() {

		tipoDocumentoText.getItems().add(Empleado.getTipoContrasenia());
		tipoDocumentoText.getItems().add(Empleado.getTipoCedula());
		tipoDocumentoText.getItems().add(Empleado.getTipoTarjeta());
		tipoDocumentoText.getItems().add(Empleado.getTipoExtranjera());

	}
	/*
	 
	 */

	@FXML
	public void agregarEmpleado() {
		showEmpleaDetails(null);
	}

	@FXML
	public void actualizarEmpleado() {
		Empleado empleado;
		if (nuevo) {
			try {
				empleado = ControladorEmpresa.getInstance().crearEmpleado(nombreText.getText(),
						tipoDocumentoText.getValue(), numeroDText.getText(), telefonoText.getText(),
						celularText.getText(),direccionTest.getText(), ciudadRText.getText(), departamentoText.getText(), paisText.getText(),
						tipoEmpleadoText.getText(), emailText.getText());
				
				
				tablaEmpleado.getItems().add(empleado);
			} catch (EmpleadoExisteException e ) {
				AlertaUtil.mostrarMensajeError(e.getMessage());
			}

		} else {
			try {
				ControladorEmpresa.getInstance().actualizarEmpleado(nombreText.getText(),
						tipoDocumentoText.getValue(), numeroDText.getText(), telefonoText.getText(),
						celularText.getText(),direccionTest.getText(), ciudadRText.getText(), departamentoText.getText(), paisText.getText(),
						tipoEmpleadoText.getText(), emailText.getText());

			} catch (EmpleadoNoExisteException e) {
				AlertaUtil.mostrarMensajeError(e.getMessage());
			}
		}
		showEmpleaDetails(null);
		tablaEmpleado.refresh();

	}

	@FXML
	public void eliminarEmpleado() {
		try {
			Empleado empleado = ControladorEmpresa.getInstance().eliminarEmpleado(numeroDText.getText());
			tablaEmpleado.getItems().remove(empleado);
		} catch (EmpleadoNoExisteException e) {
			AlertaUtil.mostrarMensajeError(e.getMessage());
		}
	}

	@Override
	public void actualizarVentana() {
		tablaEmpleado.refresh();
	}

}
