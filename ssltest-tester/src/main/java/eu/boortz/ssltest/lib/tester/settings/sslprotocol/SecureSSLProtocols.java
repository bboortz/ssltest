/**
 * 
 */
package eu.boortz.ssltest.lib.tester.settings.sslprotocol;

import eu.boortz.ssltest.httpclient.clients.settings.SecureSettings;

/**
 * @author benni
 *
 */
public class SecureSSLProtocols implements ISSLProtocolsSettings {

	@Override
	public String[] getSSLProtocols() {
		return SecureSettings.SSL_PROTOCOLS;
	}

}
