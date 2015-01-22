/**
Contributors: Nachi
*/
package sitemapper.parser.beans;

import java.text.ParseException;
import java.util.Date;

import sitemapper.parser.w3cdate.W3CDateParser;

public class SiteMap {

	private String loc;
	private String lastmod;
	
	/**
	 * @return the loc
	 */
	public String getLoc() {
		return loc;
	}
	/**
	 * @param loc the loc to set
	 */
	public void setLoc(String loc) {
		this.loc = loc;
	}
	/**
	 * @return the lastmod
	 * @throws ParseException 
	 */
	public Date getLastmod() throws ParseException {
		W3CDateParser dateParser = new W3CDateParser(lastmod);
		return (lastmod!=null)?dateParser.parse():null;
	}
	/**
	 * @param lastmod the lastmod to set
	 */
	public void setLastmod(String lastmod) {
		this.lastmod = lastmod;
	}

}
