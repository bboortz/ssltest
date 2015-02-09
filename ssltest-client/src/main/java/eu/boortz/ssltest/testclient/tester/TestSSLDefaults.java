/**
 * 
 */
package eu.boortz.ssltest.testclient.tester;

import org.apache.http.HttpResponse;

import eu.boortz.ssltest.testclient.Log;
import eu.boortz.ssltest.testclient.clients.IClient;
import eu.boortz.ssltest.testclient.clients.DefaultSSLClient;
import eu.boortz.ssltest.testclient.exception.ClientConnectException;
import eu.boortz.ssltest.testclient.exception.PrepareConnectException;


/**
 * @author benni
 *
 */
public class TestSSLDefaults implements ITester  {
	private IClient client = new DefaultSSLClient();
	
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
