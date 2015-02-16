/**
 * 
 */
package eu.boortz.ssltest.lib.tester.settings.trustchain;

import java.security.KeyStore;

import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;

import eu.boortz.ssltest.httpclient.clients.settings.SecureSettings;

/**
 * @author benni
 *
 */
public class SecureSSLTrustChain implements ISSLTrustChainSettings {

	@Override
	public X509HostnameVerifier getHostnameVerifier() {
		return SecureSettings.HOSTNAME_VERIFIER;
	}

	@Override
	public KeyStore getTrustStore() {
		return SecureSettings.TRUST_STORE;
	}

	@Override
	public TrustStrategy getTrustStrategy() {
		return SecureSettings.TRUST_STRATEGY;
	}
	

}
