/**
 * 
 */
package eu.boortz.ssltest.lib.factory;

import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;

/**
 * @author benni
 *
 */
public class SSLContextFactory {
	
	/**
	 * 
	 */
	private SSLContextFactory() {
	}
	
	public static SSLContext newInstance() {
		SSLContext result = null;
		
		// prepare ssl connection
		result = SSLContexts.createDefault();
//		try {
//			result = SSLContexts.custom()
//					.useProtocol("TLSv1.2")
//					.build();
			
//		} catch (KeyManagementException e) {
//			e.printStackTrace();
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		}
		
		return result;
	}
	
	public static SSLContext newInstance(KeyStore trustStore, TrustStrategy trustStrategy) {
		SSLContext result = null;
		
		// prepare ssl connection
		try {
			result = SSLContexts.custom()
					.useProtocol("TLSv1.2")
					.loadTrustMaterial(trustStore, trustStrategy)
					.build();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
