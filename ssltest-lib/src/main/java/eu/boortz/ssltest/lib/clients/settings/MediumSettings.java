/**
 * 
 */
package eu.boortz.ssltest.lib.clients.settings;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;

import eu.boortz.ssltest.lib.factory.SSLContextFactory;

/**
 * @author benni
 *
 */
public class MediumSettings  {
	
	public static final String 			FORBIDDEN_PROTOCOL_TAGS 	= ".*SSL.*";
	public static final String 			FORBIDDEN_CIPHER_TAGS 		= ".*NULL.*|.*RC4.*|.*DES.*|.*MD2.*|.*MD4.*|.*MD5.*";
	
	
	public static final KeyStore 				TRUST_STORE 		= null;
	public static final TrustStrategy 			TRUST_STRATEGY 		= null;
	public static final X509HostnameVerifier 	HOSTNAME_VERIFIER 	= SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER;
	public static final String[]				_SSL_PROTOCOLS		= SSLContextFactory.newInstance().createSSLEngine().getSupportedProtocols();
	public static final String[]				_SSL_CIPHERS		= SSLContextFactory.newInstance().createSSLEngine().getSupportedCipherSuites();
	
	
	public static String[]				SSL_PROTOCOLS		= null;
	public static String[]				SSL_CIPHERS			= null;
	
	
	static {
		// fill protocols
		List<String> customProtocols = new ArrayList<String>();
		
		for (String protocol : _SSL_PROTOCOLS) {
			if (! protocol.matches(FORBIDDEN_PROTOCOL_TAGS)) {
				customProtocols.add(protocol);
			}
		}
		SSL_PROTOCOLS = customProtocols.toArray(new String[0]);
	}
	
	static {
		
				
		// fill ciphers
		List<String> customCiphers = new ArrayList<String>();
		
		for (String cipher : _SSL_CIPHERS) {
			if (! cipher.matches(FORBIDDEN_CIPHER_TAGS)) {
				customCiphers.add(cipher);
			}
		}
		SSL_CIPHERS = customCiphers.toArray(new String[0]);
		
	}
	
}
