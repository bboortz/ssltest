/**
 * 
 */
package eu.boortz.ssltest.client;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLContexts;

/**
 * @author benni
 *
 */
public class InfoLauncher {

	/**
	 * 
	 */
	public InfoLauncher() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

//		sslContext.getSupportedSSLParameters()
		
		SSLContext sslContext = null;;
		String[] ciphers = null;
		try {
			sslContext = SSLContexts.custom().useProtocol("TLSv1.2").build();

//			sslContext = SSLContexts.custom().useTLS().build();
//			SSLContext sc = SSLContext.getInstance("TLSv1.2");
			
			System.out.println("*** DEFAULT PROTOCOLS ***");
	        ciphers = sslContext.getDefaultSSLParameters().getProtocols();
	        for (String string : ciphers) {
	        	System.out.println("    " + string);
			}
	        System.out.println();
	        
	        
	        System.out.println("*** Endpoint Identification Algorithm ***");
	        String algorithm = sslContext.getDefaultSSLParameters().getEndpointIdentificationAlgorithm();
	        System.out.println("    " + algorithm);
	        System.out.println();
	        
	        
	        System.out.println("*** Client Authentication ***");
	        boolean want = sslContext.getDefaultSSLParameters().getWantClientAuth();
	        boolean need = sslContext.getDefaultSSLParameters().getNeedClientAuth();
	        System.out.println("    want: " + want);
	        System.out.println("    need: " + need);
	        System.out.println();
	        
	        
	        System.out.println("*** DEFAULT CIHPERS ***");
			ciphers = sslContext.getDefaultSSLParameters().getCipherSuites();
	        for (String string : ciphers) {
	        	System.out.println("    " + string);
			}
	        System.out.println();
	        
	        
	        System.out.println("*** ENABLED CIHPERS ***");
			ciphers = SSLContext.getDefault().createSSLEngine().getEnabledCipherSuites(); 
	        for (String string : ciphers) {
	        	System.out.println("    " + string);
			}
	        System.out.println();
			
	        
			System.out.println("*** SERVER SOCKET DEFAULT CIHPERS ***");
			ciphers = sslContext.getServerSocketFactory().getDefaultCipherSuites();
	        for (String string : ciphers) {
	        	System.out.println("    " + string);
			}
	        System.out.println();
	        
	        
	        System.out.println("*** SERVER SOCKET SUPPORTED CIHPERS ***");
	        ciphers = sslContext.getServerSocketFactory().getSupportedCipherSuites();
	        for (String string : ciphers) {
	        	System.out.println("    " + string);
			}
	        System.out.println();
	        

		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		
		
		

	}

}
