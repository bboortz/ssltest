/**
 * 
 */
package eu.boortz.ssltest.lib.tester.settings.sslcipher;

import eu.boortz.ssltest.httpclient.clients.settings.DefaultSettings;

/**
 * @author benni
 *
 */
public class DefaultSSLCiphers implements ISSLCiphersSettings {

	@Override
	public String[] getSSLCiphers() {
		return DefaultSettings.SSL_CIPHERS;
	}

}
