package eu.boortz.ssltest.lib.tester;



import eu.boortz.ssltest.httpclient.clients.HttpResponse;
import eu.boortz.ssltest.httpclient.exception.ClientConnectException;
import eu.boortz.ssltest.httpclient.exception.PrepareConnectException;

public interface ITester {

	public HttpResponse testURI(String uri) throws ClientConnectException, PrepareConnectException;
	
	public String[] getSupportedSSLProtocols(String uri) throws ClientConnectException, PrepareConnectException;
	
	public String[] getSupportedSSLCiphers(String uri) throws ClientConnectException, PrepareConnectException;
	

}