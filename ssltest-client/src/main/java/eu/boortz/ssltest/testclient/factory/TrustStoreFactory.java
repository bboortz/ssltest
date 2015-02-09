/**
 * 
 */
package eu.boortz.ssltest.testclient.factory;

import java.security.KeyStore;
import java.security.KeyStoreException;

/**
 * @author benni
 *
 */
public class TrustStoreFactory {
	

	/**
	 * 
	 */
	private TrustStoreFactory() {
	}
	
	public static KeyStore newInstance() {
		KeyStore result = null;
		
		try {
			result = KeyStore.getInstance(KeyStore.getDefaultType());
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	

}
