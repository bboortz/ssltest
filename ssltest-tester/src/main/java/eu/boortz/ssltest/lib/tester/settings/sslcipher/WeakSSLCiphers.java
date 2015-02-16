/**
 * 
 */
package eu.boortz.ssltest.lib.tester.settings.sslcipher;

import eu.boortz.ssltest.httpclient.clients.settings.WeakSettings;

/**
 * @author benni
 *
 */
public class WeakSSLCiphers implements ISSLCiphersSettings {

	@Override
	public String[] getSSLCiphers() {
		return WeakSettings.SSL_CIPHERS;
	}

}
