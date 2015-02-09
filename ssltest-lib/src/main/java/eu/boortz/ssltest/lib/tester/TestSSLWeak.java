/**
 * 
 */
package eu.boortz.ssltest.lib.tester;

import org.apache.http.HttpResponse;

import eu.boortz.ssltest.lib.Log;
import eu.boortz.ssltest.lib.clients.CustomSSLClient;
import eu.boortz.ssltest.lib.clients.ICustomSSLClient;
import eu.boortz.ssltest.lib.clients.settings.WeakSettings;
import eu.boortz.ssltest.lib.exception.ClientConnectException;
import eu.boortz.ssltest.lib.exception.PrepareConnectException;

/**
 * @author benni
 *
 */
public class TestSSLWeak implements ITester {
	private ICustomSSLClient client = new CustomSSLClient();
	
	
	public TestSSLWeak() {
		client.setSSLCiphers(WeakSettings.SSL_CIPHERS);
		client.setSSLProtocols(WeakSettings.SSL_PROTOCOLS);
		
		client.setTrustStore(WeakSettings.TRUST_STORE);
		client.setTrustStrategie(WeakSettings.TRUST_STRATEGY);
		client.setHostnameVerifier(WeakSettings.HOSTNAME_VERIFIER);
	}
	
	/* (non-Javadoc)
	 * @see eu.boortz.ssltest.lib.tester.ITester#testURI(java.lang.String)
	 */
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
