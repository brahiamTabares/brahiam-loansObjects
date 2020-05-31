package controlador;


import excepciones.ObjetoExisteException;
import excepciones.ObjetoNoExisteException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Objeto;

public class ControladorCrudObjeto implements ControladorVentana {

	private ObservableList<Objeto> objetoData = FXCollections.observableArrayList();

	@FXML
	private TableView<Objeto> tablaObjetos;
	@FXML
	private TableColumn<Objeto, String> codigoColumn;
	@FXML
	private TableColumn<Objeto, String> nombreColumn;
	@FXML
	private TableColumn<Objeto, String> descripcionColumn;
	@FXML
	private TableColumn<Objeto, String> estadoColumn;
	@FXML
	private TableColumn<Objeto, String> cantidadColumn;
	@FXML
	private TableColumn<Objeto, String> colorColumn;
	@FXML
	private TableColumn<Objeto, String> pesoColumn;
	@FXML
	private TableColumn<Objeto, String> precioPColumn;
	@FXML
	private TableColumn<Objeto, String> tipoColumn;

	@FXML
	private TextField codigoText;
	@FXML
	private TextField nombreText;
	@FXML
	private TextField descripcionText;
	@FXML
	private ComboBox<String> estado;
	@FXML
	private ComboBox<String> estadoList;
	@FXML
	private TextField cantidadText;
	@FXML
	private TextField colorText;
	@FXML
	private TextField pesoText;
	@FXML
	private TextField precioText;
	@FXML
	private ComboBox<String> tipotext;
	int cantidad;

	private boolean nuevo = true;

	@FXML
	private void initialize() {
		
		ControladorPrincipal.registrarControladorVentana(this);
		inicializarEstados();
		//inicializarTiposObjetos();
	}
	
		
	 
	public void inicializarTablaObjetos()	{
		
		codigoColumn.setCellValueFactory(new PropertyValueFactory<Objeto, String>("codigo"));
		nombreColumn.setCellValueFactory(new PropertyValueFactory<Objeto, String>("nombre"));
		descripcionColumn.setCellValueFactory(new PropertyValueFactory<Objeto, String>("descripcion"));
		estadoColumn.setCellValueFactory(new PropertyValueFactory<Objeto, String>("estado"));
		cantidadColumn.setCellValueFactory(new PropertyValueFactory<Objeto, String>("cantidad"));
		colorColumn.setCellValueFactory(new PropertyValueFactory<Objeto, String>("color"));
		pesoColumn.setCellValueFactory(new PropertyValueFactory<Objeto, String>("peso"));
		precioPColumn.setCellValueFactory(new PropertyValueFactory<Objeto, String>("precio"));
		tipoColumn.setCellValueFactory(new PropertyValueFactory<Objeto, String>("tipo"));

		objetoData = FXCollections.observableArrayList(ControladorEmpresa.getInstance().listarObjetos());

		tablaObjetos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			showObjetDetails(newValue);
			nuevo = false;
		});

		tablaObjetos.setItems(objetoData);

	
		
	}  

	private void inicializarEstados() {
		estadoList.getItems().add(Objeto.ESTADO_DISPONIBLE);
		estadoList.getItems().add(Objeto.ESTADO_RESERVADO);
		estadoList.getItems().add(Objeto.ESTADO_PRESTADO);
	}

	
	/*
	 * //private void inicializarTiposObjetos() {
	 * 
	 * tipoText.getItems().add(Objeto.TIPO_ELECTRODOMESTICO); }
	 */
	/**
	 * Fills all text fields to show details about the person. If the specified
	 * person is null, all text fields are cleared.
	 * 
	 * @param person the person or null
	 */
	private void showObjetDetails(Objeto objeto) {
		if (objeto != null) {
			codigoText.setText(objeto.getCodigo());
			nombreText.setText(objeto.getNombre());
			descripcionText.setText(objeto.getDescripcion());
			estadoList.setValue(objeto.getEstado());
			cantidadText.setText("" + objeto.getCantidad());
			colorText.setText(objeto.getColor());
			pesoText.setText(objeto.getPeso());
			precioText.setText(objeto.getValorUnitario());
			tipotext.setValue(objeto.getTipo());

		} else {
			codigoText.setText("");
			nombreText.setText("");
			descripcionText.setText("");
			estadoList.getSelectionModel().clearSelection();
			cantidadText.setText("");
			colorText.setText("");
			precioText.setText("");
			tipotext.getSelectionModel().clearSelection();

			nuevo = true;
			tablaObjetos.getSelectionModel().clearSelection();
		}
	}

	@FXML
	public void agregarObjeto() {
		showObjetDetails(null);
	}

	@FXML
	public void actualizarObjeto() {
		Objeto objeto;
		if (nuevo) {
			try {
				cantidad = Integer.parseInt(cantidadText.getText());
				objeto = ControladorEmpresa.getInstance().crearObjeto(codigoText.getText(), nombreText.getText(),
						descripcionText.getText(), cantidad, colorText.getText(), pesoText.getText(), estado.getValue(),
						tipotext.getValue(), precioText.getText());
				tablaObjetos.getItems().add(objeto);
			} catch (ObjetoExisteException e) {
				AlertaUtil.mostrarMensajeError(e.getMessage());
			}

		} else {
			try {
				ControladorEmpresa.getInstance().actualizarObjeto(codigoText.getText(), nombreText.getText(),
						descripcionText.getText(), cantidad, colorText.getText(), pesoText.getText(), estado.getValue(),
						tipotext.getValue(), precioText.getText(), null);
			} catch (ObjetoNoExisteException e) {
				AlertaUtil.mostrarMensajeError(e.getMessage());
			}

		}
		tablaObjetos.refresh();
		showObjetDetails(null);
	}

	@FXML
	public void eliminarObjeto() {

		Objeto objeto;
		try {
			objeto = ControladorEmpresa.getInstance().eliminarObjeto(codigoText.getText());
			tablaObjetos.getItems().remove(objeto);
			tablaObjetos.refresh();
		} catch (ObjetoNoExisteException e) {
			AlertaUtil.mostrarMensajeError(e.getMessage());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controlador.ControladorVentana#actualizarVentana()
	 */
	@Override
	public void actualizarVentana() {
		tablaObjetos.refresh();
	}

}
