package controlador;

import excepciones.ClienteExisteException;
import excepciones.ClienteNoExisteException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Cliente;

public class ControladorCrudCliente implements ControladorVentana {

	private ObservableList<Cliente> clienteData = FXCollections.observableArrayList();

	@FXML
	private TableView<Cliente> tablaClientes;
	@FXML
	private TableColumn<Cliente, String> nombreColumn;
	@FXML
	private TableColumn<Cliente, String> apellidoColumn;
	@FXML
	private TableColumn<Cliente, String> cedulaColumn;
	@FXML
	private TableColumn<Cliente, String> direccionColumn;
	@FXML
	private TextField nombreText;
	@FXML
	private TextField apellidoText;
	@FXML
	private TextField cedulaText;
	@FXML
	private TextField direccionTest;
	private boolean nuevo = true;

	@FXML
	private void initialize() {

		ControladorPrincipal.registrarControladorVentana(this);

		nombreColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nombre"));
		apellidoColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("apellido"));
		cedulaColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("cedula"));
		direccionColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Direccion"));
		clienteData = FXCollections.observableArrayList(ControladorEmpresa.getInstance().listarClientles());
		tablaClientes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			showClientDetails(newValue);
			nuevo = false;
		});

		System.out.println(clienteData.getClass().getName());
		tablaClientes.setItems(clienteData);

	}

	/**
	 * Fills all text fields to show details about the person. If the specified
	 * person is null, all text fields are cleared.
	 * 
	 * @param person the person or null
	 */
	private void showClientDetails(Cliente cliente) {
		if (cliente != null) {
			nombreText.setText(cliente.getNombre());
			apellidoText.setText(cliente.getApellido());
			cedulaText.setText(cliente.getCedula());
			direccionTest.setText(cliente.getDireccion());
		} else {
			nombreText.setText("");
			apellidoText.setText("");
			cedulaText.setText("");
			direccionTest.setText("");
			nuevo = true;
			tablaClientes.getSelectionModel().clearSelection();
		}
	}

	@FXML
	public void agregarCliente() {
		showClientDetails(null);
	}

	@FXML
	public void actualizarCliente() {
		Cliente cliente;
		if (nuevo) {
			try {
				cliente = ControladorEmpresa.getInstance().crearCliente(nombreText.getText(), apellidoText.getText(),
						cedulaText.getText(), direccionTest.getText());
				tablaClientes.getItems().add(cliente);
			} catch (ClienteExisteException e) {
				AlertaUtil.mostrarMensajeError(e.getMessage());
			}

		} else {
			try {
				ControladorEmpresa.getInstance().actualizarCliente(cedulaText.getText(), nombreText.getText(),
						apellidoText.getText(), direccionTest.getText());

			} catch (ClienteNoExisteException e) {
				AlertaUtil.mostrarMensajeError(e.getMessage());
			}
		}
		showClientDetails(null);
		tablaClientes.refresh();

	}

	@FXML
	public void eliminarCliente() {
		try {
			Cliente cliente = ControladorEmpresa.getInstance().eliminarCliente(cedulaText.getText());
			tablaClientes.getItems().remove(cliente);
		} catch (ClienteNoExisteException e) {
			AlertaUtil.mostrarMensajeError(e.getMessage());
		}
	}

	@Override
	public void actualizarVentana() {
		tablaClientes.refresh();
	}

}
