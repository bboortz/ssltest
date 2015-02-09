/**
 * 
 */
package eu.boortz.ssltest.webapp.options;

import eu.boortz.ssltest.lib.tester.settings.trustchain.ISSLTrustChainSettings;

/**
 * @author benni
 *
 */
public class SSLTrustChainOption {
	
	private String name = null;
	private ISSLTrustChainSettings obj = null;
	
	public SSLTrustChainOption(String name, ISSLTrustChainSettings obj) {
		this.name = name;
		this.obj = obj;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ISSLTrustChainSettings getCls() {
		return obj;
	}

	public void setCls(ISSLTrustChainSettings obj) {
		this.obj = obj;
	}

}
