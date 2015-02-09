/**
 * 
 */
package eu.boortz.ssltest.lib.clients.settings;

import java.security.KeyStore;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;

import eu.boortz.ssltest.lib.factory.SSLContextFactory;
import eu.boortz.ssltest.lib.factory.TrustStoreFactory;

/**
 * @author benni
 *
 */
public class DefaultSettings  {
	
	
	public static final KeyStore 				TRUST_STORE 		= TrustStoreFactory.newInstance();
	public static final TrustStrategy 			TRUST_STRATEGY 		= null;
	public static final X509HostnameVerifier 	HOSTNAME_VERIFIER 	= SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER;
	public static final String[]				SSL_PROTOCOLS		= SSLContextFactory.newInstance().getDefaultSSLParameters().getProtocols(); 	
	public static final String[]				SSL_CIPHERS			= SSLContextFactory.newInstance().getDefaultSSLParameters().getCipherSuites(); 
			    
	
}
