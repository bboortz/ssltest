/**
 * 
 */
package eu.boortz.ssltest.testclient.clients;

import java.security.KeyStore;

import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;

/**
 * @author benni
 *
 */
public interface ICustomSSLClient extends IClient {
	
	public void setHostnameVerifier(X509HostnameVerifier verifier);
	public void setTrustStore(KeyStore trustStore);
	public void setTrustStrategie(TrustStrategy trustStrategy);
	public void setSSLProtocols(String[] protocols);
	public void setSSLCiphers(String[] ciphers);

}
