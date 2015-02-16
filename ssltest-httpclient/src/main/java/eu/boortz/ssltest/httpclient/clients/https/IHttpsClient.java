package eu.boortz.ssltest.httpclient.clients.https;

import javax.net.ssl.SSLSocketFactory;

import org.apache.http.conn.ssl.X509HostnameVerifier;

public interface IHttpsClient extends IHttpClient {
	
	public void setSSLSocketFactory(SSLSocketFactory sslSocketFactory);
	
	public SSLSocketFactory getSSLSocketFactory();
	
	public void setX509HostnameVerifier(X509HostnameVerifier x509HostnameVerifier);
	
	public X509HostnameVerifier getX509HostnameVerifier();

}
