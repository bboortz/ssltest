/**
 * 
 */
package eu.boortz.ssltest.testclient;

import java.util.Arrays;

import eu.boortz.ssltest.testclient.tester.ITester;
import eu.boortz.ssltest.testclient.tester.TestSSLCustomCiphersAndCustomTrustChain;
import eu.boortz.ssltest.testclient.tester.TestSSLCustomCiphersAndDefaultTrustChain;
import eu.boortz.ssltest.testclient.tester.TestSSLDefaultCipherAndCustomTrustChain;
import eu.boortz.ssltest.testclient.tester.TestSSLDefaultCipherAndDefaultTrustChain;
import eu.boortz.ssltest.testclient.tester.TestSSLDefaults;
import eu.boortz.ssltest.testclient.tester.TestSSLSecure;
import eu.boortz.ssltest.testclient.tester.TestSSLWeak;


/**
 * @author benni
 *
 */
public class TestLauncher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
//		System.setProperty("javax.net.debug", "ssl");
//		-Djavax.net.debug=ssl:packet
		
		String[] uris = {
//				"https://google.de",
//				"https://test.de",
//				"https://microsoft.de",
//				"https://www.blaufotograph.de:80",
//				"https://boortz.eu",
				"https://localhost:9443"
//				"https://volkswagen.de"
		};
		
		ITester tester = null;
//		tester = new TestSSLDefaults();
//		tester = new TestSSLWeak();
//		tester = new TestSSLSecure();
//		tester = new TestSSLDefaultCipherAndDefaultTrustChain();
		tester = new TestSSLDefaultCipherAndCustomTrustChain();
//		tester = new TestSSLCustomCiphersAndDefaultTrustChain();
		tester = new TestSSLCustomCiphersAndCustomTrustChain();
		
		
		
		
		for (String uri : uris) {
		
			try {
				tester.testURI(uri);
				
				String[] result = null;
				
				result = tester.getSupportedSSLProtocols(uri);
				Log.logURIInfo(uri, "Supported SSL Protocols: " + Arrays.toString(result));
				
				result = tester.getSupportedSSLCiphers(uri);
				Log.logURIInfo(uri, "Supported SSL Ciphers: " + Arrays.toString(result));
			} catch (Exception e) {
				Log.logSevere(e.getMessage());
//				e.printStackTrace();
			}
			
		}

	

	}
 

}

