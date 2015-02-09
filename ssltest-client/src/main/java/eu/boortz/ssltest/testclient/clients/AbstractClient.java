package eu.boortz.ssltest.testclient.clients;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.net.ssl.SSLHandshakeException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;

import eu.boortz.ssltest.testclient.Log;
import eu.boortz.ssltest.testclient.clients.settings.DefaultSettings;
import eu.boortz.ssltest.testclient.exception.ClientConnectException;
import eu.boortz.ssltest.testclient.exception.PrepareConnectException;

public abstract class AbstractClient implements IClient {
	
	protected X509HostnameVerifier 	hostnameVerifier 	= DefaultSettings.HOSTNAME_VERIFIER; 
	
	protected String[] 				sslProtocols 		= DefaultSettings.SSL_PROTOCOLS;;
	protected String[] 				sslCiphers 			= DefaultSettings.SSL_CIPHERS;
	
	
	/* (non-Javadoc)
	 * @see eu.boortz.ssltest.testclient.IClient#getUri(java.lang.String)
	 */
	public HttpResponse getUri(String uri) throws ClientConnectException, PrepareConnectException {
		this.checkURI(uri);
		
		return this.getUri(uri, this.sslProtocols, this.sslCiphers);
	}
	
	
	public String[] getSupportedSSLProtocols(String uri)
			throws ClientConnectException, PrepareConnectException {
		String[] result = new String[] { };
		ArrayList<String> list = new ArrayList<String>();
		
		// pretest (is a connection possible? if not no further tests are needed)
		this.checkURI(uri);
		this.getUri(uri);

		// test loop
		for (String sslProtocol : this.sslProtocols) {
			try {
				this.getUri(uri, new String[] { sslProtocol }, this.sslCiphers);
				list.add(sslProtocol);
			} catch (Exception e) {
//				e.printStackTrace();
			}
		}
	
		return list.toArray(result);
	}
	
	public String[] getSupportedSSLCiphers(String uri)
			throws ClientConnectException, PrepareConnectException {
		String[] result = new String[] { };
		ArrayList<String> list = new ArrayList<String>();
		
		// pretest (is a connection possible? if not no further tests are needed)
		this.checkURI(uri);
		this.getUri(uri);
		
		// test loop
		for (String sslCipher : this.sslCiphers) {
			try {
				this.getUri(uri, this.sslProtocols, new String[] { sslCipher } );
				list.add(sslCipher);
			} catch (Exception e) {
//				e.printStackTrace();
			}
		}
	
		return list.toArray(result);
	}
	
	
	
	
	public HttpResponse executeQuery(HttpUriRequest request, CloseableHttpClient httpClient, HttpGet httpGet) throws ClientConnectException, PrepareConnectException {
		HttpResponse result = null;
		CloseableHttpResponse response = null;
		
		String uri = request.getURI().toString();
		
		try {
			response = httpClient.execute(request);
			
			if (response == null) {
				Log.logURISevere(uri, "Response is null!");
			} else {
				result = response;
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
