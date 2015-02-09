/**
 * 
 */
package eu.boortz.ssltest.lib.clients.settings;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

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
	
	
	public static String[] getTrustedCertificateStrings() {
		ArrayList<String> result = new ArrayList<String>();
		
		X509Certificate[] certs = getTrustedCertificates();
		for (X509Certificate cert : certs) {
			result.add(cert.getIssuerDN().getName());
		}
		
		return result.toArray(new String[0]);
	}
	
	public static X509Certificate[] getTrustedCertificates() {
		X509Certificate[] result = null;
		
		TrustManagerFactory tmf;
		try {
			tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());			
			tmf.init((KeyStore) null);
			
			// get the x509 trust manager
			X509TrustManager x509Tm = null;
			for (TrustManager tm : tmf.getTrustManagers()) {
			    if (tm instanceof X509TrustManager) {
			        x509Tm = (X509TrustManager) tm;
			        break;
			    }
			}
			
			result = x509Tm.getAcceptedIssuers();
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}

		return result;
	}
			    
	
}
