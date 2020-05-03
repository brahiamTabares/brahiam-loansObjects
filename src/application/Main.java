package application;
	
import controlador.ControladorEmpresa;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) { // configura el inicio de sesion
		try {
			String vistaActiva = "/vista/loginVista.fxml";
			Pane root = (Pane)FXMLLoader.load(getClass().getResource(vistaActiva));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	/* (non-Javadoc)
	 * @see javafx.application.Application#stop()
	 */
	@Override
	public void stop() throws Exception { // configura finalizacion de la aplicacion
		ControladorEmpresa.getInstance().guardarDatos();
		super.stop();
	}
	
	
}
