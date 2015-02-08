/**
 * 
 */
package eu.boortz.ssltest.testclient.clients;

import org.apache.http.HttpResponse;

import eu.boortz.ssltest.testclient.exception.ClientConnectException;
import eu.boortz.ssltest.testclient.exception.PrepareConnectException;

/**
 * @author benni
 *
 */
public interface IClient {
	
	public HttpResponse getUri(String uri) throws ClientConnectException, PrepareConnectException;
	
	public HttpResponse getUri(String uri, String[] sslProtocols, String[] sslCiphers) throws ClientConnectException, PrepareConnectException;
	
	public String[] getSupportedSSLProtocols(String uri) throws ClientConnectException, PrepareConnectException;
	
	public String[] getSupportedSSLCiphers(String uri) throws ClientConnectException, PrepareConnectException;
	

}
