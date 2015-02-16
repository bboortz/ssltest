/**
 * 
 */
package eu.boortz.ssltest.lib.tester.predefined;

import eu.boortz.ssltest.httpclient.clients.CustomSSLClient;
import eu.boortz.ssltest.lib.tester.ATester;
import eu.boortz.ssltest.lib.tester.ITester;
import eu.boortz.ssltest.lib.tester.settings.sslcipher.ISSLCiphersSettings;
import eu.boortz.ssltest.lib.tester.settings.sslcipher.SecureSSLCiphers;
import eu.boortz.ssltest.lib.tester.settings.sslprotocol.ISSLProtocolsSettings;
import eu.boortz.ssltest.lib.tester.settings.sslprotocol.SecureSSLProtocols;
import eu.boortz.ssltest.lib.tester.settings.trustchain.ISSLTrustChainSettings;
import eu.boortz.ssltest.lib.tester.settings.trustchain.WeakSSLTrustChain;


/**
 * @author benni
 *
 */
public class TestSSLSecureCiphersAndWeakTrustChain extends ATester implements ITester {
	
	ISSLProtocolsSettings protocolSettings = new SecureSSLProtocols();
	ISSLCiphersSettings cipherSettings = new SecureSSLCiphers();
	ISSLTrustChainSettings trustChainSettings = new WeakSSLTrustChain();
	
	
	public TestSSLSecureCiphersAndWeakTrustChain() {
		this.client = new CustomSSLClient();
		
		this.client.setSSLProtocols( protocolSettings.getSSLProtocols() );
		this.client.setSSLCiphers( cipherSettings.getSSLCiphers() );
		
		this.client.setTrustStore( trustChainSettings.getTrustStore() );
		this.client.setTrustStrategie( trustChainSettings.getTrustStrategy());
		this.client.setHostnameVerifier( trustChainSettings.getHostnameVerifier() );
	}
	

}
