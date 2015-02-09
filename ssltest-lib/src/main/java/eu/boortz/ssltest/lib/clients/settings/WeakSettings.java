/**
 * 
 */
package eu.boortz.ssltest.lib.clients.settings;

import java.security.KeyStore;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;

import eu.boortz.ssltest.lib.factory.SSLContextFactory;

/**
 * @author benni
 *
 */
public class WeakSettings  {
	
	
	public static final KeyStore 				TRUST_STORE 		= null;
	public static final TrustStrategy 			TRUST_STRATEGY 		= new TrustSelfSignedStrategy();
	public static final X509HostnameVerifier 	HOSTNAME_VERIFIER 	= SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
	public static final String[]				SSL_PROTOCOLS		= SSLContextFactory.newInstance().createSSLEngine().getSupportedProtocols();
	public static final String[]				SSL_CIPHERS			= SSLContextFactory.newInstance().createSSLEngine().getSupportedCipherSuites();

}
