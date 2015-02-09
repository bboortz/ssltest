/**
 * 
 */
package eu.boortz.ssltest.testclient.clients.settings;

import java.security.KeyStore;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;

/**
 * @author benni
 *
 */
public class SecureSettings  {
	
	
	public static final KeyStore 				TRUST_STORE 		= null;
	public static final TrustStrategy 			TRUST_STRATEGY 		= null;
	public static final X509HostnameVerifier 	HOSTNAME_VERIFIER 	= SSLConnectionSocketFactory.STRICT_HOSTNAME_VERIFIER;
	public static final String[]				SSL_PROTOCOLS		= new String[] { 
		"TLSv1.2" 
		};
	public static final String[]				SSL_CIPHERS			= new String[] { 

		"TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384",
		"TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256",
//		"TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA",
//		"TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA",
//			    
		"TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384",
		"TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256",
//		"TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA",
//		"TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA",

	    "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384",
	    "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256",
//	    "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA",
//	    "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA",
	    
	    "TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384",
	    "TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256",
//	    "TLS_ECDH_RSA_WITH_AES_256_CBC_SHA",
//	    "TLS_ECDH_RSA_WITH_AES_128_CBC_SHA",
	    
	    "TLS_DHE_DSS_WITH_AES_256_CBC_SHA256",
	    "TLS_DHE_DSS_WITH_AES_128_CBC_SHA256",
//	    "TLS_DHE_DSS_WITH_AES_256_CBC_SHA",
//	    "TLS_DHE_DSS_WITH_AES_128_CBC_SHA",
	    
	    "TLS_DHE_RSA_WITH_AES_256_CBC_SHA256",
	    "TLS_DHE_RSA_WITH_AES_128_CBC_SHA256",
//	    "TLS_DHE_RSA_WITH_AES_256_CBC_SHA",
//	    "TLS_DHE_RSA_WITH_AES_128_CBC_SHA",
	    
	    "TLS_RSA_WITH_AES_256_CBC_SHA256",
	    "TLS_RSA_WITH_AES_128_CBC_SHA256",
//	    "TLS_RSA_WITH_AES_256_CBC_SHA",
//	    "TLS_RSA_WITH_AES_128_CBC_SHA",
			      
	    };
		


	
}
