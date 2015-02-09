/**
 * 
 */
package eu.boortz.ssltest.lib.clients;

import org.apache.http.HttpResponse;

import eu.boortz.ssltest.lib.exception.ClientConnectException;
import eu.boortz.ssltest.lib.exception.PrepareConnectException;

/**
 * @author benni
 *
 */
public interface ISSLClient {
	
	public HttpResponse headUri(String uri) throws ClientConnectException, PrepareConnectException;
	
	public HttpResponse headUri(String uri, String[] sslProtocols, String[] sslCiphers) throws ClientConnectException, PrepareConnectException;
	
	public String[] getSupportedSSLProtocols(String uri) throws ClientConnectException, PrepareConnectException;
	
	public String[] getSupportedSSLCiphers(String uri) throws ClientConnectException, PrepareConnectException;
	

}
