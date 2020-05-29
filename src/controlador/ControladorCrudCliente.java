package controlador;

import excepciones.ClienteExisteException;
import excepciones.ClienteNoExisteException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
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
	private TableColumn<Cliente, String> tipoDocumentoColumn;
	@FXML
	private TableColumn<Cliente, String> numeroDColumn;
	@FXML
	private TableColumn<Cliente, String> telefonoColumn;
	@FXML
	private TableColumn<Cliente, String> celularColumn;
	
	@FXML
	private TableColumn<Cliente, String> direccionColumn;
	@FXML
	private TableColumn<Cliente, String> ciudadRColumn;
	@FXML
	private TableColumn<Cliente, String> departamentoColumn;
	@FXML
	private TableColumn<Cliente, String> paisColumn;
	@FXML
	private TableColumn<Cliente, String> profesionColumn;
	@FXML
	private TableColumn<Cliente, String> emailColumn;
	@FXML
	private TextField nombreText;
	@FXML
	private ComboBox<String> tipoDocumentoText;
	@FXML
	private TextField numeroDText;
	@FXML
	private TextField telefonoText;
	@FXML
	private TextField  celularText;
	@FXML
	private TextField direccionTest;
	@FXML
	private TextField ciudadRText;
	@FXML
	private TextField departamentoText;
	@FXML
	private TextField  paisText;
	@FXML
	private TextField profesionText;
	@FXML
	private TextField emailText;
	
	
	/*pruena 
	 * 
	 */
	
	private boolean nuevo = true;

	@FXML
	private void initialize() {
		
		
		inicializarTabla();
		inicializarTipoDocumento();

	}
		private void inicializarTabla() {
		ControladorPrincipal.registrarControladorVentana(this);

		nombreColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nombre"));
		tipoDocumentoColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("tipoDocumento"));
		numeroDColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("numeroDocumento"));
		telefonoColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefono"));
		celularColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("celular"));
		direccionColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Direccion"));
		departamentoColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("departamento"));
		paisColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("pais"));
	    profesionColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("profesion"));
	    emailColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("email"));
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
			tipoDocumentoText.setValue(cliente.getTipoDocumento());
			numeroDText.setText(cliente.getNumeroDocumento());
			telefonoText.setText(cliente.getTelefonoResidencia());
			celularText.setText(cliente.getTelefonoCelular());
			direccionTest.setText(cliente.getDireccion());
			ciudadRText.setText(cliente.getCiudadR());
			departamentoText.setText(cliente.getDepartamento());
			paisText.setText(cliente.getPais());
			profesionText.setText(cliente.getProfesion());
			emailText.setText(cliente.getEmail());
			
			
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
			profesionText.setText("");
			emailText.setText("");
			
			
			nuevo = true;
			tablaClientes.getSelectionModel().clearSelection();
			
		}
		
		tablaClientes.refresh();
	}
	private void inicializarTipoDocumento() {
		
        tipoDocumentoText.getItems().add(Cliente.getTipoContrasenia());	
		tipoDocumentoText.getItems().add(Cliente.getTipoCedula());
        tipoDocumentoText.getItems().add(Cliente.getTipoTarjeta());
		tipoDocumentoText.getItems().add(Cliente.getTipoExtranjera());
		
	}
	/*
	 
	 */

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
