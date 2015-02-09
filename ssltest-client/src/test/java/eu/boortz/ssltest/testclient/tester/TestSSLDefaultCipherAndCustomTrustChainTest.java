/**
 * 
 */
package eu.boortz.ssltest.testclient.tester;

import static org.junit.Assert.fail;

import org.junit.Test;

import eu.boortz.ssltest.testclient.TestSettings;

/**
 * @author benni
 *
 */
public class TestSSLDefaultCipherAndCustomTrustChainTest {

	private static ITester tester = new TestSSLDefaultCipherAndCustomTrustChain();

	/**
	 * Test method for {@link eu.boortz.ssltest.testclient.tester.TestSSLCiphers#testURI(java.lang.String)}.
	 */
	@Test
	public void testTestURIGood() {
		try {
			tester.testURI(TestSettings.TEST_URI_GOOD);
		} catch (Exception e) {
			fail("Exception occurred: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Test method for {@link eu.boortz.ssltest.testclient.tester.TestSSLCiphers#testURI(java.lang.String)}.
	 */
//	@Test
//	public void testTestURIBad() {
//		boolean exceptionBool = false;
//		try {
//			tester.testURI(TestSettings.TEST_URI_BAD);
//		} catch (ClientConnectException e) {
//			exceptionBool = true;
//		} catch (Exception e) {
//			fail("Exception occurred: " + e.getMessage());
//			e.printStackTrace();
//		}
//		
//		if (!exceptionBool) {
//			fail("No exception was thrown but one was expected!");
//		}
//	}
	
	/**
	 * Test method for {@link eu.boortz.ssltest.testclient.tester.TestSSLCiphers#testURI(java.lang.String)}.
	 */
	@Test
	public void testGetSupportedSSLProtocolsGood() {
		try {
			tester.getSupportedSSLProtocols(TestSettings.TEST_URI_GOOD);
		} catch (Exception e) {
			fail("Exception occurred: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Test method for {@link eu.boortz.ssltest.testclient.tester.TestSSLCiphers#testURI(java.lang.String)}.
	 */
//	@Test
//	public void testGetSupportedSSLProtocolsBad() {
//		boolean exceptionBool = false;
//		try {
//			tester.getSupportedSSLProtocols(TestSettings.TEST_URI_BAD);
//		} catch (ClientConnectException e) {
//			exceptionBool = true;
//		}  catch (Exception e) {
//			fail("Exception occurred: " + e.getMessage());
//			e.printStackTrace();
//		}
//		
//		if (!exceptionBool) {
//			fail("No exception was thrown but one was expected!");
//		}
//	}
	
	/**
	 * Test method for {@link eu.boortz.ssltest.testclient.tester.TestSSLCiphers#testURI(java.lang.String)}.
	 */
	@Test
	public void testGetSupportedSSLCiphersGood() {
		try {
			tester.getSupportedSSLCiphers(TestSettings.TEST_URI_GOOD);
		} catch (Exception e) {
			fail("Exception occurred: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Test method for {@link eu.boortz.ssltest.testclient.tester.TestSSLCiphers#testURI(java.lang.String)}.
	 */
//	@Test
//	public void testGetSupportedSSLCiphersBad() {
//		boolean exceptionBool = false;
//		try {
//			tester.getSupportedSSLCiphers(TestSettings.TEST_URI_BAD);
//		} catch (ClientConnectException e) {
//			exceptionBool = true;
//		}  catch (Exception e) {
//			fail("Exception occurred: " + e.getMessage());
//			e.printStackTrace();
//		}
//		
//		if (!exceptionBool) {
//			fail("No exception was thrown but one was expected!");
//		}
//	}
}
