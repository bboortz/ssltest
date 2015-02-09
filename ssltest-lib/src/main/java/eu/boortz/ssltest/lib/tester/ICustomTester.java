package eu.boortz.ssltest.lib.tester;

import eu.boortz.ssltest.lib.tester.settings.sslcipher.ISSLCiphersSettings;
import eu.boortz.ssltest.lib.tester.settings.sslprotocol.ISSLProtocolsSettings;
import eu.boortz.ssltest.lib.tester.settings.trustchain.ISSLTrustChainSettings;


public interface ICustomTester extends ITester {

	public void setProtocolSettings(ISSLProtocolsSettings sslProtocolSettings);
	public void setCipherSettings(ISSLCiphersSettings sslCiphersSettings);
	public void setTrustChainSettings(ISSLTrustChainSettings trustChainSettings);
	

}