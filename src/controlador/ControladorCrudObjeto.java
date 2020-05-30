package controlador;

import excepciones.ClienteNoExisteException;
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

public class ControladorCrudObjeto implements ControladorVentana{

	private ObservableList<Objeto> objetoData = FXCollections.observableArrayList();

	@FXML
	private TableView<Objeto> tablaObjetos;
	@FXML
	private TableColumn<Objeto, String> nombreColumn;
	@FXML
	private TableColumn<Objeto, String> descripcionColumn;
	@FXML
	private TableColumn<Objeto, String> codigoColumn;
	@FXML
	private TableColumn<Objeto, String> estadoColumn;
	@FXML
	private TableColumn<Objeto, String> cantidadColumn;
	@FXML
	private TableColumn<Objeto, String> colorColumn;
	@FXML
	private TableColumn<Objeto, String> pesoColumn;
	@FXML
	private TableColumn<Objeto, String> precioPColmn;
	@FXML
	private TableColumn<Objeto, String> tipo;
	
	@FXML
	private TextField codigoText;
	@FXML
	private TextField nombreText;
	@FXML
	private TextField descripcionText;
	@FXML
	private ComboBox<String>estado;
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
	private ComboBox<String>tipoText;
	
	
	private boolean nuevo = true;

	@FXML
	private void initialize() {
		ControladorPrincipal.registrarControladorVentana(this);
		nombreColumn.setCellValueFactory(new PropertyValueFactory<Objeto, String>("nombre"));
		descripcionColumn.setCellValueFactory(new PropertyValueFactory<Objeto, String>("descripcion"));
		codigoColumn.setCellValueFactory(new PropertyValueFactory<Objeto, String>("codigo"));
		estadoColumn.setCellValueFactory(new PropertyValueFactory<Objeto, String>("estado"));
		objetoData = FXCollections.observableArrayList(ControladorEmpresa.getInstance().listarObjetos());

		tablaObjetos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			showObjetDetails(newValue);
			nuevo = false;
		});

		tablaObjetos.setItems(objetoData);
		
		inicializarEstados();
	}

	private void inicializarEstados() {
		estadoList.getItems().add(Objeto.ESTADO_DISPONIBLE);
		estadoList.getItems().add(Objeto.ESTADO_RESERVADO);
		estadoList.getItems().add(Objeto.ESTADO_PRESTADO);
	}

	/**
	 * Fills all text fields to show details about the person. If the specified
	 * person is null, all text fields are cleared.
	 * 
	 * @param person the person or null
	 */
	private void showObjetDetails(Objeto objeto) {
		if (objeto != null) {
			nombreText.setText(objeto.getNombre());
			descripcionText.setText(objeto.getDescripcion());
			codigoText.setText(objeto.getCodigo());
			estadoList.setValue( objeto.getEstado() );
		} else {
			nombreText.setText("");
			descripcionText.setText("");
			codigoText.setText("");
			estadoList.getSelectionModel().clearSelection();
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
				objeto = ControladorEmpresa.getInstance().crearObjeto(nombreText.getText(), descripcionText.getText(),
						codigoText.getText(), estadoList.getValue());
				tablaObjetos.getItems().add(objeto);
			} catch (ObjetoExisteException e) {
				AlertaUtil.mostrarMensajeError(e.getMessage());
			}
			
		} else {
			try {
			ControladorEmpresa.getInstance().actualizarObjeto(codigoText.getText(), nombreText.getText(),
						descripcionText.getText(), estadoList.getValue());
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

	/* (non-Javadoc)
	 * @see controlador.ControladorVentana#actualizarVentana()
	 */
	@Override
	public void actualizarVentana() {
		tablaObjetos.refresh();
	}

	
}
