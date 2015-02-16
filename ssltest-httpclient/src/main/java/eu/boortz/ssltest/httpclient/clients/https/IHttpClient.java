package eu.boortz.ssltest.httpclient.clients.https;

import eu.boortz.ssltest.httpclient.clients.HttpResponse;
import eu.boortz.ssltest.httpclient.exception.ClientConnectException;
import eu.boortz.ssltest.httpclient.exception.PrepareConnectException;

public interface IHttpClient {
	
	public HttpResponse headURI(String uri) throws ClientConnectException, PrepareConnectException;
	

}
