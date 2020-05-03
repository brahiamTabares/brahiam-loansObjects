package logs;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class Seguimiento {
	private static final Logger logger = Logger.getLogger(Seguimiento.class.getName());
	private static final String nombreLog = "logs/seguimiento.log";

	private Seguimiento() {
		logger.setUseParentHandlers(false);
		logger.setLevel(Level.ALL);  
		try {
			FileHandler fileHandler = new FileHandler(nombreLog,true);
			fileHandler.setFormatter(new SimpleFormatter());
			fileHandler.setLevel(Level.ALL);
			logger.addHandler(fileHandler);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
	}

	private static class SingletonHelper {
		private static final Seguimiento instance = new Seguimiento();
	}

	public static Seguimiento getInstance() {
		return SingletonHelper.instance;
	}

	public void log(String mensaje) {
		logger.info(mensaje);
	}
	
}
