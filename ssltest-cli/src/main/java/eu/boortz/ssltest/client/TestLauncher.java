/**
 * 
 */
package eu.boortz.ssltest.client;


import eu.boortz.ssltest.httpclient.clients.HttpResponse;
import eu.boortz.ssltest.httpclient.clients.https.HttpsUrlConnectionClient;
import eu.boortz.ssltest.httpclient.clients.https.IHttpClient;
import eu.boortz.ssltest.lib.Log;
import eu.boortz.ssltest.lib.tester.ICustomTester;


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
				"https://google.de",
//				"https://test.de",
//				"https://microsoft.de",
//				"https://www.blaufotograph.de:80",
//				"https://boortz.eu",
//				"https://localhost:9443"
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
//		tester = new CustomTester();
//		tester.setProtocolSettings( new SecureSSLProtocols());
//		tester.setCipherSettings( new SecureSSLCiphers() );
//		tester.setTrustChainSettings( new WeakSSLTrustChain() );
		
		IHttpClient httpClient = new HttpsUrlConnectionClient();
		
		
		
		
		for (String uri : uris) {
		
			try {
				HttpResponse response = httpClient.headURI(uri);
				System.out.println("Statusline: " + response.getStatusLine());
				
				
//				tester.testURI(uri);
//				
//				String[] result = null;
//				
//				result = tester.getSupportedSSLProtocols(uri);
//				Log.logURIInfo(uri, "Supported SSL Protocols: " + Arrays.toString(result));
//				
//				result = tester.getSupportedSSLCiphers(uri);
//				Log.logURIInfo(uri, "Supported SSL Ciphers: " + Arrays.toString(result));
			} catch (Exception e) {
				Log.logSevere(e.getMessage());
//				e.printStackTrace();
			}
			
		}

	

	}
 

}

