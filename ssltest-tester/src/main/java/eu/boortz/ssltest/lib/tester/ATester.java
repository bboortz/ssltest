/**
 * 
 */
package eu.boortz.ssltest.lib.tester;


import eu.boortz.ssltest.httpclient.clients.HttpResponse;
import eu.boortz.ssltest.httpclient.clients.ICustomSSLClient;
import eu.boortz.ssltest.httpclient.exception.ClientConnectException;
import eu.boortz.ssltest.httpclient.exception.PrepareConnectException;
import eu.boortz.ssltest.lib.Log;

/**
 * @author benni
 *
 */
public class ATester implements ITester {
	
	protected ICustomSSLClient client;
	
	
	public ATester() {
	}

	
	/* (non-Javadoc)
	 * @see eu.boortz.ssltest.lib.tester.ITester#testURI(java.lang.String)
	 */
	public HttpResponse testURI(String uri) throws ClientConnectException, PrepareConnectException {
		HttpResponse response = this.client.headUri(uri);
		
		Log.logURIInfo(uri, response.getStatusLine().toString());
		
		return response;
	}

	public String[] getSupportedSSLProtocols(String uri) throws ClientConnectException, PrepareConnectException {
		return this.client.getSupportedSSLProtocols(uri);
	}

	public String[] getSupportedSSLCiphers(String uri) throws ClientConnectException, PrepareConnectException {
		return this.client.getSupportedSSLCiphers(uri);
	}


}
