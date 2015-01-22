/**
Contributors: Nachi
*/
package sitemapper.parser.beans;

import java.util.HashSet;
import java.util.Set;

public class UrlSet {

	private Set<Url> urlSet = new HashSet<Url>();
	
	/**
	 * @return the url
	 */
	public Set<Url> getSet() {
		return urlSet;
	}

	/**
	 * @param url the url to set
	 */
	public void add(Url url) {
		this.urlSet.add(url);
	}

}
