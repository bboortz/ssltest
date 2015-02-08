/**
 * 
 */
package eu.boortz.ssltest.testclient.tester;

import org.apache.http.HttpResponse;

import eu.boortz.ssltest.testclient.Log;
import eu.boortz.ssltest.testclient.clients.CustomSSLClient;
import eu.boortz.ssltest.testclient.clients.ICustomSSLClient;
import eu.boortz.ssltest.testclient.exception.ClientConnectException;
import eu.boortz.ssltest.testclient.exception.PrepareConnectException;


/**
 * @author benni
 *
 */
public class TestSSLDefaultCipherAndDefaultTrustChain implements ITester  {
	private ICustomSSLClient client = new CustomSSLClient();
	
	public TestSSLDefaultCipherAndDefaultTrustChain() {
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
