package controlador;

import java.time.LocalDate;

import excepciones.PrestamoExisteException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Cliente;
import modelo.Empleado;
import modelo.Objeto;
import modelo.Prestamo;

public class ControladorPrestamo implements ControladorVentana {

	private ControladorEmpresa empresa;
	private ObservableList<Objeto> objetoData = FXCollections.observableArrayList();
	

	@FXML
	private TableView<Objeto> tablaObjetosAp;
	@FXML
	private TableColumn<Objeto, String> nombreColumn2;
	@FXML
	private TableColumn<Objeto, String> codigoColumn2;
	@FXML
	private TableView<Objeto> tablaObjetosD;
	@FXML
	private TableColumn<Objeto, String> nombre1Column;
	@FXML
	private TableColumn<Objeto, String> codigo1Column;
	@FXML
	private TableView<Prestamo> tablaPrestamos;
	@FXML
	private TableColumn<Prestamo, Integer> prestamoColumn;
	@FXML
	private TableColumn<Prestamo, String> fechaInColumn;
	@FXML
	private TableColumn<Prestamo, String> fechaFinColumn;
	@FXML
	private TableColumn<Prestamo, String> clienteColumn;
	@FXML
	private TableColumn<Prestamo, String> empleadoColumn;
	@FXML
	private TableColumn<Prestamo, String> estadoColumn;
	@FXML
	private TableColumn<Prestamo, Integer> valorColumn;
	@FXML
	private TextField numeroPrestamoText;
	@FXML
	private TextField estadoPrestamoText;
	@FXML
	private TextField valorPrestamoText;
	@FXML
	private DatePicker fechaPrestamoText;
	@FXML
	private DatePicker fechaEntregaText;
	@FXML
	private ComboBox<Empleado> empleadoAsociado;

	@FXML
	private ComboBox<Cliente> clienteAsociado;

	public ControladorPrestamo() {
		ControladorPrincipal.registrarControladorVentana(this);
	}

	@FXML
	private void initialize() {

		empresa = ControladorEmpresa.getInstance();

		inicializarCamposTextos();
		inicializarTablaD();

//		tablaObjetosD.getSelectionModel().selectedItemProperty()
//				.addListener((observable, oldValue, newValue) -> showObjetDetails(newValue));

		inicializarTablaAp();
		inicializarTablaPrestamos();
		cargarClientes();
		cargarEmpleados();
	}

	private void inicializarCamposTextos() {
		numeroPrestamoText.setText("");
		estadoPrestamoText.setText("");
		valorPrestamoText.setText("");
		fechaPrestamoText.setValue(LocalDate.now());
		fechaEntregaText.setValue(LocalDate.now());
	}

	private void inicializarTablaD() {
		empresa.getListaObjetosDisponibles().clear();
		for (Objeto objeto : empresa.listarObjetos()) {
			if (objeto.getEstado().equalsIgnoreCase(Objeto.ESTADO_DISPONIBLE))
				empresa.getListaObjetosDisponibles().add(objeto);
		}
		nombre1Column.setCellValueFactory(new PropertyValueFactory<Objeto, String>("nombre"));
		codigo1Column.setCellValueFactory(new PropertyValueFactory<Objeto, String>("codigo"));
		objetoData = FXCollections.observableArrayList(empresa.getListaObjetosDisponibles());
		tablaObjetosD.setItems(objetoData);
	}

	private void inicializarTablaPrestamos() {
		prestamoColumn.setCellValueFactory(new PropertyValueFactory<Prestamo, Integer>("numero"));
		fechaInColumn.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("fechaPrestamo"));
		fechaFinColumn.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("fechaEntrega"));
		clienteColumn.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("clienteAsociado"));
		empleadoColumn.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("empleadoAsociado"));
		estadoColumn.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("estado"));
		valorColumn.setCellValueFactory(new PropertyValueFactory<Prestamo, Integer>("valorPrestamo"));
		tablaPrestamos.setItems(FXCollections.observableArrayList(ControladorEmpresa.getInstance().listarPrestamos()));
	}

	private void inicializarTablaAp() {
		nombreColumn2.setCellValueFactory(new PropertyValueFactory<Objeto, String>("nombre"));
		codigoColumn2.setCellValueFactory(new PropertyValueFactory<Objeto, String>("codigo"));
		tablaObjetosAp.setItems(FXCollections.observableArrayList());
	}

	private void cargarClientes() {
		clienteAsociado.getItems().clear();
		clienteAsociado.getItems().addAll(empresa.listarClientles());
	}

	private void cargarEmpleados() {
		empleadoAsociado.getItems().clear();
		empleadoAsociado.getItems().addAll(empresa.listarEmpleado());
	}

	
	@FXML
	public void seleccionarParaPrestamo() {
		Objeto objeto = tablaObjetosD.getSelectionModel().getSelectedItem();
		if (objeto != null) {
			objeto.setEstado(Objeto.ESTADO_RESERVADO);
			tablaObjetosD.getItems().remove(objeto);
			tablaObjetosAp.getItems().add(objeto);
		}
	}

	@FXML
	public void devolverPrestamo() {
		Objeto objeto = tablaObjetosAp.getSelectionModel().getSelectedItem();
		if (objeto != null) {
			objeto.setEstado(Objeto.ESTADO_DISPONIBLE);
			tablaObjetosAp.getItems().remove(objeto);
			tablaObjetosD.getItems().add(objeto);
		}
	}

	@FXML
	public void crearPrestamo() {

		int numeroPrestamo = Integer.parseInt(numeroPrestamoText.getText());
		LocalDate fechaPrestamo = fechaPrestamoText.getValue();
		String estadoPrestamo = estadoPrestamoText.getText();
		int valorPrestamo = Integer.parseInt(valorPrestamoText.getText());
	   LocalDate fechaEntrega = fechaEntregaText.getValue();
		Empleado empleado = empleadoAsociado.getValue();
		Cliente cliente = clienteAsociado.getValue();

		if (tablaObjetosAp.getItems().size() > 0) {
			Prestamo prestamo;
			try {
				prestamo = empresa.crearPrestamon(numeroPrestamo, fechaPrestamo, fechaEntrega, valorPrestamo,
						estadoPrestamo, cliente, empleado, tablaObjetosAp.getItems());
				if (prestamo != null) {
					tablaPrestamos.getItems().add(prestamo);
				}

			} catch (PrestamoExisteException e) {
				AlertaUtil.mostrarMensajeError(e.getMessage());

			}
			initialize();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controlador.ControladorVentana#actualizarVentana()
	 */
	@Override
	public void actualizarVentana() {
		inicializarTablaD();
		cargarClientes();
		cargarEmpleados();
	}

}
