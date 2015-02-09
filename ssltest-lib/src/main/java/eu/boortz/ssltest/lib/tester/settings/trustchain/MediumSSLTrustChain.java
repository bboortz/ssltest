/**
 * 
 */
package eu.boortz.ssltest.lib.tester.settings.trustchain;

import java.security.KeyStore;

import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;

import eu.boortz.ssltest.lib.clients.settings.MediumSettings;

/**
 * @author benni
 *
 */
public class MediumSSLTrustChain implements ISSLTrustChainSettings {

	@Override
	public X509HostnameVerifier getHostnameVerifier() {
		return MediumSettings.HOSTNAME_VERIFIER;
	}

	@Override
	public KeyStore getTrustStore() {
		return MediumSettings.TRUST_STORE;
	}

	@Override
	public TrustStrategy getTrustStrategy() {
		return MediumSettings.TRUST_STRATEGY;
	}
	

}
