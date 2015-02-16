package eu.boortz.ssltest.httpclient.clients.https;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;

import eu.boortz.ssltest.httpclient.clients.HttpResponse;
import eu.boortz.ssltest.httpclient.clients.settings.DefaultSettings;
import eu.boortz.ssltest.httpclient.exception.ClientConnectException;
import eu.boortz.ssltest.httpclient.exception.PrepareConnectException;
import eu.boortz.ssltest.httpclient.factory.HttpClientFactory;
import eu.boortz.ssltest.httpclient.factory.SSLContextFactory;
import eu.boortz.ssltest.lib.Log;

public class ApacheHttpClient implements IHttpClient {
	
	

	private KeyStore 		trustStore 		= DefaultSettings.TRUST_STORE;
	private TrustStrategy 	trustStrategy 	= DefaultSettings.TRUST_STRATEGY;
	


	protected X509HostnameVerifier 	hostnameVerifier 	= DefaultSettings.HOSTNAME_VERIFIER; 
	
	protected String[] 				sslProtocols 		= DefaultSettings.SSL_PROTOCOLS;;
	protected String[] 				sslCiphers 			= DefaultSettings.SSL_CIPHERS;
	
	
	
	
	@Override
	public HttpResponse headURI(String uri) throws ClientConnectException, PrepareConnectException {
		this.checkURI(uri);
		
		return this.headURI(uri, this.sslProtocols, this.sslCiphers);
	}

	
	public HttpResponse headURI(String uri, String[] sslProtocols, String[] sslCiphers) throws ClientConnectException, PrepareConnectException {
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
//		HttpGet httpRequest = new HttpGet(uri);
		HttpHead httpRequest = new HttpHead(uri);
		
		
		// execute request
		result = this.executeQuery(httpRequest, httpClient);
			
				
		return result;
	}
	
	
	private HttpResponse executeQuery(HttpUriRequest request, CloseableHttpClient httpClient) throws ClientConnectException, PrepareConnectException {
		HttpResponse result = new HttpResponse();
		CloseableHttpResponse response = null;
		
		String uri = request.getURI().toString();
		
		try {
			response = httpClient.execute(request);
			
			if (response == null) {
				Log.logURISevere(uri, "Response is null!");
			} else {
				result.setStatusCode( response.getStatusLine().getStatusCode() );
				result.setStatusLine( response.getStatusLine().toString() );
			}
			
		} catch (ClientProtocolException e) {
			throw new PrepareConnectException( getExceptionMessage(uri, "Malformed URI <" + e.getCause().getMessage() + ">") );
		} catch (UnknownHostException e) {
			throw new PrepareConnectException( getExceptionMessage(uri, "Unknown host because <" + e.getMessage() + ">") );
		} catch (SSLHandshakeException e) {	
			throw new ClientConnectException( getExceptionMessage(uri, "Cannot establish SSL handshake because <" + e.getMessage() + ">") );
		} catch (SocketException e) {	
			throw new ClientConnectException( getExceptionMessage(uri, "Cannot establish socket <" + e.getMessage() + ">") );
		} catch (IOException e) {
			Throwable cause = e.getCause();
			if (cause == null) {
				throw new ClientConnectException( getExceptionMessage(uri, "Cannot open connection because <" + e.getMessage() + ">") );
			} else if (cause instanceof SSLHandshakeException) {
				throw new ClientConnectException( getExceptionMessage(uri, "Cannot establish SSL handshake because <" + cause.getMessage() + ">") );
			} else {
				throw new ClientConnectException( getExceptionMessage(uri, "Cannot open connection because <" + cause.getMessage() + ">") );
			}
			
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					Log.logURISevere(uri, "Cannot close HttpResponse after connecting");
					e.printStackTrace();
				}
				response = null;
			}
			
		}
		
		
		return result;
	}
	
	

	protected void checkURI(String uri) throws PrepareConnectException {
		if (
				uri == null ||
				uri.equals("") ||
				! uri.startsWith("https://")
		) {
			throw new PrepareConnectException("SSL URI Malformed: " + uri);
			
		}
	}
		
	
	protected String getExceptionMessage(String uri, String msg) {
		return uri + " - " + msg;
	}


}
