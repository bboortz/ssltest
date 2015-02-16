/**
 * 
 */
package eu.boortz.ssltest.httpclient.factory;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import eu.boortz.ssltest.httpclient.x509.TrustAllX509TrustManager;

/**
 * @author benni
 *
 */
public class CustomSSLSocketFactory {
	
	private static final String sslProtocol = "TLSv1.2";
	
	/**
	 * 
	 */
	private CustomSSLSocketFactory() {
	}
	
	public static SSLSocketFactory newInstance() {
		SSLSocketFactory result = null;
		
		try {
			TrustManager[] trustManager = new TrustManager[] { 
					new TrustAllX509TrustManager() 
			}; 
		    final SSLContext sslContext = SSLContext.getInstance( sslProtocol );
		    sslContext.init( null, trustManager, new SecureRandom() );
		    result = sslContext.getSocketFactory();
			
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
}
