/**
 * 
 */
package eu.boortz.ssltest.testclient.clients.settings;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;

/**
 * @author benni
 *
 */
public class CustomSettings  {
	
	
	public static final KeyStore 				TRUST_STORE 		= null;
	public static final TrustStrategy 			TRUST_STRATEGY 		= new TrustSelfSignedStrategy();
	public static final X509HostnameVerifier 	HOSTNAME_VERIFIER 	= SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
	public static final String[]				_SSL_PROTOCOLS		= new String[] { 
		"TLSv1", 
		"TLSv1.1", 
		"TLSv1.2" 
		};
	public static final String[]				_SSL_CIPHERS			= new String[] { 
		"TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384",
		"TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256",
		"TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA",
		"TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA",
			    
		"TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384",
		"TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256",
		"TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA",
		"TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA",

	    "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384",
	    "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256",
	    "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA",
	    "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA",
	    
	    "TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384",
	    "TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256",
	    "TLS_ECDH_RSA_WITH_AES_256_CBC_SHA",
	    "TLS_ECDH_RSA_WITH_AES_128_CBC_SHA",
	    
	    "TLS_DHE_DSS_WITH_AES_256_CBC_SHA256",
	    "TLS_DHE_DSS_WITH_AES_128_CBC_SHA256",
	    "TLS_DHE_DSS_WITH_AES_256_CBC_SHA",
	    "TLS_DHE_DSS_WITH_AES_128_CBC_SHA",
	    
	    "TLS_DHE_RSA_WITH_AES_256_CBC_SHA256",
	    "TLS_DHE_RSA_WITH_AES_128_CBC_SHA256",
	    "TLS_DHE_RSA_WITH_AES_256_CBC_SHA",
	    "TLS_DHE_RSA_WITH_AES_128_CBC_SHA",
	    
	    "TLS_RSA_WITH_AES_256_CBC_SHA256",
	    "TLS_RSA_WITH_AES_128_CBC_SHA256",
	    "TLS_RSA_WITH_AES_256_CBC_SHA",
	    "TLS_RSA_WITH_AES_128_CBC_SHA",
			      
	    };
		
	
	public static String[]				SSL_PROTOCOLS		= null;
	public static String[]				SSL_CIPHERS			= null;
	
	static {
		// fill protocols
		List<String> defaultProtocols = Arrays.asList(DefaultSettings.SSL_PROTOCOLS);
		List<String> customProtocols = new ArrayList<String>();
		
		for (String protocol : _SSL_PROTOCOLS) {
			if (defaultProtocols.contains(protocol)) {
				customProtocols.add(protocol);
			}
		}
		SSL_PROTOCOLS = customProtocols.toArray(new String[0]);
	}
	
	static {
		
				
		// fill ciphers
		List<String> defaultCiphers = Arrays.asList(DefaultSettings.SSL_CIPHERS);
		List<String> customCiphers = new ArrayList<String>();
		
		for (String cipher : _SSL_CIPHERS) {
			if (defaultCiphers.contains(cipher)) {
				customCiphers.add(cipher);
			}
		}
		SSL_CIPHERS = customCiphers.toArray(new String[0]);
		
	}
	
	
	

	
}
