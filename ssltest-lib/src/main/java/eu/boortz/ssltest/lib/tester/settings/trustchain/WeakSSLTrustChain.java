/**
 * 
 */
package eu.boortz.ssltest.lib.tester.settings.trustchain;

import java.security.KeyStore;

import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;

import eu.boortz.ssltest.lib.clients.settings.WeakSettings;

/**
 * @author benni
 *
 */
public class WeakSSLTrustChain implements ISSLTrustChainSettings {

	@Override
	public X509HostnameVerifier getHostnameVerifier() {
		return WeakSettings.HOSTNAME_VERIFIER;
	}

	@Override
	public KeyStore getTrustStore() {
		return WeakSettings.TRUST_STORE;
	}

	@Override
	public TrustStrategy getTrustStrategy() {
		return WeakSettings.TRUST_STRATEGY;
	}
	

}
