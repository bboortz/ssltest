/**
 * 
 */
package eu.boortz.ssltest.lib.tester;

import eu.boortz.ssltest.lib.clients.CustomSSLClient;
import eu.boortz.ssltest.lib.tester.settings.sslcipher.ISSLCiphersSettings;
import eu.boortz.ssltest.lib.tester.settings.sslprotocol.ISSLProtocolsSettings;
import eu.boortz.ssltest.lib.tester.settings.trustchain.ISSLTrustChainSettings;

/**
 * @author benni
 *
 */
public class CustomTester extends ATester implements ICustomTester {
	
	ISSLProtocolsSettings protocolSettings = null;
	ISSLCiphersSettings cipherSettings = null;
	ISSLTrustChainSettings trustChainSettings = null;
	
	
	public CustomTester() {
		this.client = new CustomSSLClient();
		
	}


	@Override
	public void setProtocolSettings(ISSLProtocolsSettings sslProtocolSettings) {
		this.protocolSettings = sslProtocolSettings;
		this.client.setSSLProtocols( protocolSettings.getSSLProtocols() );
	}


	@Override
	public void setCipherSettings(ISSLCiphersSettings sslCiphersSettings) {
		this.cipherSettings = sslCiphersSettings;
		this.client.setSSLCiphers( cipherSettings.getSSLCiphers() );
	}


	@Override
	public void setTrustChainSettings(ISSLTrustChainSettings trustChainSettings) {
		this.trustChainSettings = trustChainSettings;
		this.client.setTrustStore( trustChainSettings.getTrustStore() );
		this.client.setTrustStrategie( trustChainSettings.getTrustStrategy());
		this.client.setHostnameVerifier( trustChainSettings.getHostnameVerifier() );
	}
	
	
}
