/**
 * 
 */
package eu.boortz.ssltest.webapp;

import eu.boortz.ssltest.lib.tester.ITester;

/**
 * @author benni
 *
 */
public class SSLTestOptionObj {
	
	private String name = null;
	private ITester obj = null;
	
	public SSLTestOptionObj(String name, ITester obj) {
		this.name = name;
		this.obj = obj;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ITester getCls() {
		return obj;
	}

	public void setCls(ITester obj) {
		this.obj = obj;
	}

}
