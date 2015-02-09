/**
 * 
 */
package eu.boortz.ssltest.lib.tester.settings.sslcipher;

import eu.boortz.ssltest.lib.clients.settings.SecureSettings;

/**
 * @author benni
 *
 */
public class SecureSSLCiphers implements ISSLCiphersSettings {

	@Override
	public String[] getSSLCiphers() {
		return SecureSettings.SSL_CIPHERS;
	}

}
