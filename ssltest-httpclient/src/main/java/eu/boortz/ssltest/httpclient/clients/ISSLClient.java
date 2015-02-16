/**
 * 
 */
package eu.boortz.ssltest.httpclient.clients;


import eu.boortz.ssltest.httpclient.exception.ClientConnectException;
import eu.boortz.ssltest.httpclient.exception.PrepareConnectException;

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
