/**
 * 
 */
package eu.boortz.ssltest.webapp.options;

import eu.boortz.ssltest.lib.tester.settings.sslprotocol.ISSLProtocolsSettings;

/**
 * @author benni
 *
 */
public class SSLProtocolOption {
	
	private String name = null;
	private ISSLProtocolsSettings obj = null;
	
	public SSLProtocolOption(String name, ISSLProtocolsSettings obj) {
		this.name = name;
		this.obj = obj;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ISSLProtocolsSettings getCls() {
		return obj;
	}

	public void setCls(ISSLProtocolsSettings obj) {
		this.obj = obj;
	}

}
