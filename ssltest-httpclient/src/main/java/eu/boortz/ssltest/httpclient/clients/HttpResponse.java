/**
 * 
 */
package eu.boortz.ssltest.httpclient.clients;

/**
 * @author benni
 *
 */
public class HttpResponse {
	
	private int statusCode = 0;
	private String statusLine = null;
	/**
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}
	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	/**
	 * @return the statusLine
	 */
	public String getStatusLine() {
		return statusLine;
	}
	/**
	 * @param statusLine the statusLine to set
	 */
	public void setStatusLine(String statusLine) {
		this.statusLine = statusLine;
	}
	

}
