/**
 * 
 */
package eu.boortz.ssltest.lib.tester.settings.trustchain;

import java.security.KeyStore;

import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;

/**
 * @author benni
 *
 */
public interface ISSLTrustChainSettings {
	
	public X509HostnameVerifier getHostnameVerifier();
	public KeyStore getTrustStore();
	public TrustStrategy getTrustStrategy();

}
