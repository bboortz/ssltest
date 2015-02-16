/**
 * 
 */
package eu.boortz.ssltest.httpclient.clients;

import javax.net.ssl.SSLContext;

import org.apache.http.client.methods.HttpHead;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;

import eu.boortz.ssltest.httpclient.exception.ClientConnectException;
import eu.boortz.ssltest.httpclient.exception.PrepareConnectException;
import eu.boortz.ssltest.httpclient.factory.HttpClientFactory;
import eu.boortz.ssltest.httpclient.factory.SSLContextFactory;

/**
 * @author benni
 *
 */
public class DefaultSSLClient extends AbstractClient implements ISSLClient {
	
	
	
	
	/* (non-Javadoc)
	 * @see eu.boortz.ssltest.httpclient.IClient#getUri(java.lang.String)
	 */
	public HttpResponse headUri(String uri, String[] sslProtocols, String[] sslCiphers) throws ClientConnectException, PrepareConnectException {
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
//		HttpGet httpGet = new HttpGet(uri);
		HttpHead httpRequest = new HttpHead(uri);
			
			
		// execute request
		result = this.executeQuery(httpRequest, httpClient);
		

				
		return result;
	}

	
	

	
	
}
