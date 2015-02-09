package eu.boortz.ssltest.testclient.tester;

import eu.boortz.ssltest.testclient.exception.ClientConnectException;
import eu.boortz.ssltest.testclient.exception.PrepareConnectException;

public interface ITester {

	public void testURI(String uri) throws ClientConnectException, PrepareConnectException;
	
	public String[] getSupportedSSLProtocols(String uri) throws ClientConnectException, PrepareConnectException;
	
	public String[] getSupportedSSLCiphers(String uri) throws ClientConnectException, PrepareConnectException;
	

}