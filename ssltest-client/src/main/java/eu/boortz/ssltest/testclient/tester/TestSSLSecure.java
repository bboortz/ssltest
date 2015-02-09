/**
 * 
 */
package eu.boortz.ssltest.testclient.tester;

import org.apache.http.HttpResponse;

import eu.boortz.ssltest.testclient.Log;
import eu.boortz.ssltest.testclient.clients.CustomSSLClient;
import eu.boortz.ssltest.testclient.clients.ICustomSSLClient;
import eu.boortz.ssltest.testclient.clients.settings.SecureSettings;
import eu.boortz.ssltest.testclient.exception.ClientConnectException;
import eu.boortz.ssltest.testclient.exception.PrepareConnectException;

/**
 * @author benni
 *
 */
public class TestSSLSecure implements ITester {
	private ICustomSSLClient client = new CustomSSLClient();
	
	
	public TestSSLSecure() {
		client.setSSLCiphers(SecureSettings.SSL_CIPHERS);
		client.setSSLProtocols(SecureSettings.SSL_PROTOCOLS);
		
		client.setTrustStore(SecureSettings.TRUST_STORE);
		client.setTrustStrategie(SecureSettings.TRUST_STRATEGY);
		client.setHostnameVerifier(SecureSettings.HOSTNAME_VERIFIER);
	}
	
	/* (non-Javadoc)
	 * @see eu.boortz.ssltest.testclient.tester.ITester#testURI(java.lang.String)
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
