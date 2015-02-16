/**
 * 
 */
package eu.boortz.ssltest.lib.tester.settings.sslprotocol;

import eu.boortz.ssltest.httpclient.clients.settings.MediumSettings;

/**
 * @author benni
 *
 */
public class MediumSSLProtocols implements ISSLProtocolsSettings {

	@Override
	public String[] getSSLProtocols() {
		return MediumSettings.SSL_PROTOCOLS;
	}

}
