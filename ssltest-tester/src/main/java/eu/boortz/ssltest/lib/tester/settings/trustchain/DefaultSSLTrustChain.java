/**
 * 
 */
package eu.boortz.ssltest.lib.tester.settings.trustchain;

import java.security.KeyStore;

import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;

import eu.boortz.ssltest.httpclient.clients.settings.DefaultSettings;

/**
 * @author benni
 *
 */
public class DefaultSSLTrustChain implements ISSLTrustChainSettings {

	@Override
	public X509HostnameVerifier getHostnameVerifier() {
		return DefaultSettings.HOSTNAME_VERIFIER;
	}

	@Override
	public KeyStore getTrustStore() {
		return DefaultSettings.TRUST_STORE;
	}

	@Override
	public TrustStrategy getTrustStrategy() {
		return DefaultSettings.TRUST_STRATEGY;
	}

	

}
