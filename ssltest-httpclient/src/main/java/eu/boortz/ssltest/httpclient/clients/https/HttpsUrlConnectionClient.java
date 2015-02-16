package eu.boortz.ssltest.httpclient.clients.https;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;

import org.apache.http.conn.ssl.X509HostnameVerifier;

import eu.boortz.ssltest.httpclient.clients.HttpResponse;
import eu.boortz.ssltest.httpclient.clients.settings.DefaultSettings;
import eu.boortz.ssltest.httpclient.exception.ClientConnectException;
import eu.boortz.ssltest.httpclient.exception.PrepareConnectException;
import eu.boortz.ssltest.lib.Log;

public class HttpsUrlConnectionClient extends AHttpsClient implements IHttpsClient {

	 
	
	protected String[] 				sslProtocols 		= DefaultSettings.SSL_PROTOCOLS;;
	protected String[] 				sslCiphers 			= DefaultSettings.SSL_CIPHERS;
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * eu.boortz.ssltest.httpclient.clients.https.IHttpClient#headUri(java.lang
	 * .String)
	 */
	@Override
	public HttpResponse headURI(String uri) throws ClientConnectException,
			PrepareConnectException {
		HttpResponse result = null;
		this.checkURI(uri);
	
		result = this.executeQuery(uri, METHOD_HEAD);
		
		return result;
	}
	
	private HttpResponse executeQuery(String uri, String method) throws ClientConnectException, PrepareConnectException {
		HttpResponse result = new HttpResponse();
		
		
		try {
			HttpsURLConnection conn = null;
			URL url = new URL(uri);
			InputStream is = null;

			try {
			    SSLSocketFactory sslSocketFactory = this.getSSLSocketFactory();
			    X509HostnameVerifier x509HostnameVerifier = this.getX509HostnameVerifier();
			    
			    
				conn = (HttpsURLConnection) url.openConnection();
				conn.setRequestMethod(method);
				conn.setDoOutput(false);
				conn.setDoInput(true);
				conn.setUseCaches(false);
				conn.setFollowRedirects(false);
				
				if (x509HostnameVerifier != null) {
					conn.setHostnameVerifier(x509HostnameVerifier);
				}
				if (sslSocketFactory != null) {
					conn.setSSLSocketFactory(sslSocketFactory);
				}
				
				
				// connect
				conn.connect();
				
				// retrieve response code
				result.setStatusCode( conn.getResponseCode() );
				
				// retrive header fields
				Map<String, List<String>> headerMap = conn.getHeaderFields();
				result.setStatusLine( headerMap.get(null).get(0) );
				
//				
//				for(Entry<String, List<String>> entry : headerMap.entrySet()) {
//				    String key = entry.getKey();
//				    List<String> value = entry.getValue();
//				    System.out.println("KEY: " + key);
//				    
//				    for(Iterator<String> i = value.iterator(); i.hasNext(); ) {
//				        String item = i.next();
//				        System.out.println(item);
//				    }
//				}
				
				
				
				is = conn.getInputStream();
				
//				InputStreamReader isr = new InputStreamReader(is);
//				BufferedReader in = new BufferedReader(isr);
//
//				String inputLine;
//
//				while ((inputLine = in.readLine()) != null) {
//					System.out.println("OUT: " + inputLine);
//					result.setStatusLine(inputLine);
//					String[] statusLineArr = inputLine.split(" ");
//					result.setStatusCode( Integer.parseInt(statusLineArr[1]) );
//				}
				// while ((inputLine = in.readLine()) != null)
				// {
				// System.out.println(inputLine);
				// }
			} catch (IOException e) {
				throw new ClientConnectException( getExceptionMessage(uri, "Cannot open connection because <" + e.getMessage() + ">") );
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (IOException e) {
						Log.logURISevere(uri,
								"Cannot close InputStream after connecting");
						e.printStackTrace();
					}
					is = null;
				}

			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	private void print_https_cert(HttpsURLConnection conn){
		 
	    if(conn!=null){
	 
	      try {
	 
		System.out.println("Response Code : " + conn.getResponseCode());
		System.out.println("Cipher Suite : " + conn.getCipherSuite());
		System.out.println("\n");
	 
//		conn.getLocalCertificates()
		
		Certificate[] certs = conn.getServerCertificates();
		for(Certificate cert : certs){
		   System.out.println("Cert Type : " + cert.getType());
		   System.out.println("Cert Hash Code : " + cert.hashCode());
		   System.out.println("Cert Public Key Algorithm : " 
	                                    + cert.getPublicKey().getAlgorithm());
		   System.out.println("Cert Public Key Format : " 
	                                    + cert.getPublicKey().getFormat());
		   System.out.println("\n");
		}
	 
		} catch (SSLPeerUnverifiedException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	 
	     }
	 
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

	@Override
	public void setSSLSocketFactory(SSLSocketFactory sslSocketFactory) {
		// TODO Auto-generated method stub
		
	}

	

	


}
