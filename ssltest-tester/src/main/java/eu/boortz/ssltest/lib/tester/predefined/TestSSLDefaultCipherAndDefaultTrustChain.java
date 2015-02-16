/**
 * 
 */
package eu.boortz.ssltest.lib.tester.predefined;


import eu.boortz.ssltest.httpclient.clients.CustomSSLClient;
import eu.boortz.ssltest.lib.tester.ATester;
import eu.boortz.ssltest.lib.tester.ITester;
import eu.boortz.ssltest.lib.tester.settings.sslcipher.DefaultSSLCiphers;
import eu.boortz.ssltest.lib.tester.settings.sslcipher.ISSLCiphersSettings;
import eu.boortz.ssltest.lib.tester.settings.sslprotocol.DefaultSSLProtocols;
import eu.boortz.ssltest.lib.tester.settings.sslprotocol.ISSLProtocolsSettings;
import eu.boortz.ssltest.lib.tester.settings.trustchain.DefaultSSLTrustChain;
import eu.boortz.ssltest.lib.tester.settings.trustchain.ISSLTrustChainSettings;


/**
 * @author benni
 *
 */
public class TestSSLDefaultCipherAndDefaultTrustChain extends ATester implements ITester  {
	
	ISSLProtocolsSettings protocolSettings = new DefaultSSLProtocols();
	ISSLCiphersSettings cipherSettings = new DefaultSSLCiphers();
	ISSLTrustChainSettings trustChainSettings = new DefaultSSLTrustChain();
	
	
	public TestSSLDefaultCipherAndDefaultTrustChain() {
		this.client = new CustomSSLClient();
		
		this.client.setSSLProtocols( protocolSettings.getSSLProtocols() );
		this.client.setSSLCiphers( cipherSettings.getSSLCiphers() );
		
		this.client.setTrustStore( trustChainSettings.getTrustStore() );
		this.client.setTrustStrategie( trustChainSettings.getTrustStrategy());
		this.client.setHostnameVerifier( trustChainSettings.getHostnameVerifier() );
	}
	
	
}
