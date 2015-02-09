/**
 * 
 */
package eu.boortz.ssltest.lib.clients;

import java.security.KeyStore;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;

import eu.boortz.ssltest.lib.clients.settings.DefaultSettings;
import eu.boortz.ssltest.lib.exception.ClientConnectException;
import eu.boortz.ssltest.lib.exception.PrepareConnectException;
import eu.boortz.ssltest.lib.factory.HttpClientFactory;
import eu.boortz.ssltest.lib.factory.SSLContextFactory;

/**
 * @author benni
 *
 */
public class CustomSSLClient extends AbstractClient implements ICustomSSLClient {
	
	
	private KeyStore 		trustStore 		= DefaultSettings.TRUST_STORE;
	private TrustStrategy 	trustStrategy 	= DefaultSettings.TRUST_STRATEGY;
	
	
	
	
	/* (non-Javadoc)
	 * @see eu.boortz.ssltest.lib.IClient#getUri(java.lang.String)
	 */
	public HttpResponse getUri(String uri, String[] sslProtocols, String[] sslCiphers) throws ClientConnectException, PrepareConnectException {
		HttpResponse result = null;
			
		
        // prepare ssl connection
		SSLContext sslContext = null;
		if (
			this.trustStore == DefaultSettings.TRUST_STORE && 
			this.trustStrategy == DefaultSettings.TRUST_STRATEGY
			) {
			sslContext = SSLContextFactory.newInstance();
		} else {
			sslContext = SSLContextFactory.newInstance(this.trustStore, this.trustStrategy);
		}
		
		
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
	
	


	

	public void setHostnameVerifier(X509HostnameVerifier verifier) {
		this.hostnameVerifier = verifier;
		
	}

	public void setTrustStore(KeyStore trustStore) {
		this.trustStore = trustStore;
		
	}

	public void setSSLProtocols(String[] protocols) {
		this.sslProtocols = protocols;
		
	}

	public void setSSLCiphers(String[] ciphers) {
		this.sslCiphers = ciphers;
		
	}

	public void setTrustStrategie(TrustStrategy trustStrategy) {
		this.trustStrategy = trustStrategy;
		
	}


	
}
