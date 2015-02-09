/**
 * 
 */
package eu.boortz.ssltest.webapp.options;

import eu.boortz.ssltest.lib.tester.settings.sslcipher.ISSLCiphersSettings;

/**
 * @author benni
 *
 */
public class SSLCipherOption {
	
	private String name = null;
	private ISSLCiphersSettings obj = null;
	
	public SSLCipherOption(String name, ISSLCiphersSettings obj) {
		this.name = name;
		this.obj = obj;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ISSLCiphersSettings getCls() {
		return obj;
	}

	public void setCls(ISSLCiphersSettings obj) {
		this.obj = obj;
	}

}
