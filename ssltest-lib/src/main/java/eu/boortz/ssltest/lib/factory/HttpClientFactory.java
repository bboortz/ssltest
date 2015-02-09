/**
 * 
 */
package eu.boortz.ssltest.lib.factory;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * @author benni
 *
 */
public class HttpClientFactory {

	/**
	 * 
	 */
	private HttpClientFactory() {
	}
	
	public static CloseableHttpClient newInstance(SSLConnectionSocketFactory sslsf) {
		CloseableHttpClient result = null;
		
		// prepare apache http client 
		result = HttpClientBuilder.create()
				.setSSLSocketFactory(sslsf)
				.disableAutomaticRetries()
				.disableRedirectHandling()
				.build();
		
		
		return result;
	}

}
