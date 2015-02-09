/**
 * 
 */
package eu.boortz.ssltest.lib.tester;

import org.apache.http.HttpResponse;

import eu.boortz.ssltest.lib.Log;
import eu.boortz.ssltest.lib.clients.CustomSSLClient;
import eu.boortz.ssltest.lib.clients.ICustomSSLClient;
import eu.boortz.ssltest.lib.clients.settings.CustomSettings;
import eu.boortz.ssltest.lib.exception.ClientConnectException;
import eu.boortz.ssltest.lib.exception.PrepareConnectException;


/**
 * @author benni
 *
 */
public class TestSSLDefaultCipherAndCustomTrustChain implements ITester {
	private ICustomSSLClient client = new CustomSSLClient();
	
	
	public TestSSLDefaultCipherAndCustomTrustChain() {
		client.setTrustStore(CustomSettings.TRUST_STORE);
		client.setTrustStrategie(CustomSettings.TRUST_STRATEGY);
		client.setHostnameVerifier(CustomSettings.HOSTNAME_VERIFIER);
	}
	
	
	public void testURI(String uri) throws ClientConnectException, PrepareConnectException {
		HttpResponse response = client.getUri(uri);
		
		Log.logURIInfo(uri, response.getStatusLine().toString());
	}
	
	public String[] getSupportedSSLProtocols(String uri) throws ClientConnectException, PrepareConnectException {
		return client.getSupportedSSLProtocols(uri);
	}

	public String[] getSupportedSSLCiphers(String uri) throws ClientConnectException, PrepareConnectException {
		return client.getSupportedSSLCiphers(uri);
	}

}
