/**
 * 
 */
package eu.boortz.ssltest.httpclient.clients.https;

import javax.net.ssl.SSLSocketFactory;

import org.apache.http.conn.ssl.X509HostnameVerifier;

/**
 * @author benni
 *
 */
public abstract class AHttpsClient implements IHttpsClient {
	
	private SSLSocketFactory		sslSocketFactory 		= null;
	private X509HostnameVerifier 	x509HostnameVerifier 	= null;

	protected static final String METHOD_HEAD = "HEAD";
	

	public SSLSocketFactory getSSLSocketFactory() {
		return this.sslSocketFactory;
	}
	

	/* (non-Javadoc)
	 * @see eu.boortz.ssltest.httpclient.clients.https.IHttpsClient#setSSLSocketFactory(javax.net.ssl.SSLSocketFactory)
	 */
	@Override
	public void setSSLSocketFactory(SSLSocketFactory sslSocketFactory) {
		this.sslSocketFactory = sslSocketFactory;
	}


	@Override
	public void setX509HostnameVerifier(
			X509HostnameVerifier x509HostnameVerifier) {
		this.x509HostnameVerifier = x509HostnameVerifier;
		
	}

	@Override
	public X509HostnameVerifier getX509HostnameVerifier() {
		return this.x509HostnameVerifier;
	}
}
