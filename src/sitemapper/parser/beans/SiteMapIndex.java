/**
Contributors: Nachi
*/
package sitemapper.parser.beans;

import java.util.HashSet;
import java.util.Set;

public class SiteMapIndex {

	private Set<SiteMap> indexSet = new HashSet<SiteMap>();
	
	/**
	 * @return the indexSet
	 */
	public Set<SiteMap> getSet() {
		return indexSet;
	}

	/**
	 * @param indexSet the indexSet to set
	 */
	public void add(SiteMap siteMap) {
		this.indexSet.add(siteMap);
	}
	
}
