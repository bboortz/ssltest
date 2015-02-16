/**
 * 
 */
package eu.boortz.ssltest.lib.tester.predefined;

import eu.boortz.ssltest.httpclient.clients.DefaultSSLClient;
import eu.boortz.ssltest.httpclient.clients.ISSLClient;
import eu.boortz.ssltest.lib.tester.ATester;
import eu.boortz.ssltest.lib.tester.ITester;



/**
 * @author benni
 *
 */
public class TestSSLDefaults extends ATester implements ITester  {
	
	protected ISSLClient client;
	
	public TestSSLDefaults() {
		this.client = new DefaultSSLClient();
	}
	
}
