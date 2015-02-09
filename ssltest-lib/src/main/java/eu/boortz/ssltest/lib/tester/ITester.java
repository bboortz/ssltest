package eu.boortz.ssltest.lib.tester;

import eu.boortz.ssltest.lib.exception.ClientConnectException;
import eu.boortz.ssltest.lib.exception.PrepareConnectException;

public interface ITester {

	public void testURI(String uri) throws ClientConnectException, PrepareConnectException;
	
	public String[] getSupportedSSLProtocols(String uri) throws ClientConnectException, PrepareConnectException;
	
	public String[] getSupportedSSLCiphers(String uri) throws ClientConnectException, PrepareConnectException;
	

}