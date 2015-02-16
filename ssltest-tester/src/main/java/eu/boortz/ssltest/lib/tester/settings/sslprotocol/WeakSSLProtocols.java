/**
 * 
 */
package eu.boortz.ssltest.lib.tester.settings.sslprotocol;

import eu.boortz.ssltest.httpclient.clients.settings.WeakSettings;

/**
 * @author benni
 *
 */
public class WeakSSLProtocols implements ISSLProtocolsSettings {

	@Override
	public String[] getSSLProtocols() {
		return WeakSettings.SSL_PROTOCOLS;
	}

}
