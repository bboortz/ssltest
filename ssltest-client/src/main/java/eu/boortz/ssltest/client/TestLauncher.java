/**
 * 
 */
package eu.boortz.ssltest.client;

import java.util.Arrays;

import eu.boortz.ssltest.lib.Log;
import eu.boortz.ssltest.lib.tester.CustomTester;
import eu.boortz.ssltest.lib.tester.ICustomTester;
import eu.boortz.ssltest.lib.tester.ITester;
import eu.boortz.ssltest.lib.tester.predefined.TestSSLWeakCiphersAndWeakTrustChain;
import eu.boortz.ssltest.lib.tester.predefined.TestSSLSecureCiphersAndWeakTrustChain;
import eu.boortz.ssltest.lib.tester.predefined.TestSSLDefaultCipherAndWeakTrustChain;
import eu.boortz.ssltest.lib.tester.predefined.TestSSLDefaultCipherAndDefaultTrustChain;
import eu.boortz.ssltest.lib.tester.predefined.TestSSLDefaults;
import eu.boortz.ssltest.lib.tester.predefined.TestSSLMedium;
import eu.boortz.ssltest.lib.tester.predefined.TestSSLSecure;
import eu.boortz.ssltest.lib.tester.predefined.TestSSLWeak;
import eu.boortz.ssltest.lib.tester.settings.sslcipher.MediumSSLCiphers;
import eu.boortz.ssltest.lib.tester.settings.sslcipher.SecureSSLCiphers;
import eu.boortz.ssltest.lib.tester.settings.sslcipher.WeakSSLCiphers;
import eu.boortz.ssltest.lib.tester.settings.sslprotocol.MediumSSLProtocols;
import eu.boortz.ssltest.lib.tester.settings.sslprotocol.SecureSSLProtocols;
import eu.boortz.ssltest.lib.tester.settings.sslprotocol.WeakSSLProtocols;
import eu.boortz.ssltest.lib.tester.settings.trustchain.WeakSSLTrustChain;


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
		
		ICustomTester tester = null;
//		tester = new TestSSLDefaults();
//		tester = new TestSSLWeak();
//		tester = new TestSSLMedium();
//		tester = new TestSSLSecure();
//		tester = new TestSSLDefaultCipherAndDefaultTrustChain();
//		tester = new TestSSLDefaultCipherAndWeakTrustChain();
//		tester = new TestSSLSecureCiphersAndWeakTrustChain();
//		tester = new TestSSLWeakCiphersAndWeakTrustChain();
		tester = new CustomTester();
		tester.setProtocolSettings( new SecureSSLProtocols());
		tester.setCipherSettings( new SecureSSLCiphers() );
		tester.setTrustChainSettings( new WeakSSLTrustChain() );
		
		
		
		
		
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

