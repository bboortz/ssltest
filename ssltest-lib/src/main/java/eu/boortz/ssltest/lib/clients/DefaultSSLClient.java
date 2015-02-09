/**
 * 
 */
package eu.boortz.ssltest.lib.clients;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;

import eu.boortz.ssltest.lib.exception.ClientConnectException;
import eu.boortz.ssltest.lib.exception.PrepareConnectException;
import eu.boortz.ssltest.lib.factory.HttpClientFactory;
import eu.boortz.ssltest.lib.factory.SSLContextFactory;

/**
 * @author benni
 *
 */
public class DefaultSSLClient extends AbstractClient implements IClient {
	
	
	
	
	/* (non-Javadoc)
	 * @see eu.boortz.ssltest.lib.IClient#getUri(java.lang.String)
	 */
	public HttpResponse getUri(String uri, String[] sslProtocols, String[] sslCiphers) throws ClientConnectException, PrepareConnectException {
		HttpResponse result = null;
	
		
        // prepare ssl connection
		SSLContext sslContext = SSLContextFactory.newInstance();
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslContext,
                sslProtocols,
                sslCiphers,
                this.hostnameVerifier);
		
		// prepare apache http client
		CloseableHttpClient httpClient = HttpClientFactory.newInstance(sslsf);
		HttpGet httpGet = new HttpGet(uri);
			
			
		// execute request
		result = this.executeQuery(httpGet, httpClient, httpGet);
		

				
		return result;
	}

	
	

	
	
}
