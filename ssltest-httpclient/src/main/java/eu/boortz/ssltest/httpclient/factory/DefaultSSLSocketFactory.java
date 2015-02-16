/**
 * 
 */
package eu.boortz.ssltest.httpclient.factory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

/**
 * @author benni
 *
 */
public class DefaultSSLSocketFactory {
	
	
	/**
	 * 
	 */
	private DefaultSSLSocketFactory() {
	}
	
	public static SSLSocketFactory newInstance() {
		SSLSocketFactory result = null;
		
//		result = SSLContextFactory.newInstance().getSocketFactory();
		result = HttpsURLConnection.getDefaultSSLSocketFactory();
		
		
		return result;
	}
	
	
}
