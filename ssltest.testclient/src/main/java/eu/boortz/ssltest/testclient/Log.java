/**
 * 
 */
package eu.boortz.ssltest.testclient;

import java.util.logging.Logger;

/**
 * @author benni
 *
 */
public class Log {
	
	private static Logger LOGGER = Logger.getLogger(Log.class.getClass().getPackage().getName());

	
	public static void logInfo(String msg) {
		LOGGER.info(msg);
	}
	
	public static void logSevere(String msg) {
		LOGGER.severe(msg);
	}
	
	
	public static void logURIInfo(String uri, String msg) {
		logInfo(uri + " - " + msg);
	}
	
	public static void logURISevere(String uri, String msg) {
		logSevere(uri + " - " + msg);
	}

}
