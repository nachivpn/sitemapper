/**
Contributors: Nachi
*/
package sitemapper.parser.beans;

import java.text.ParseException;
import java.util.Date;

import sitemapper.parser.w3cdate.W3CDateParser;

public class Url {
	private String loc;
	private String lastmod;
	private String changefreq;
	private String priority;
	private String image_loc;
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
	/**
	 * @return the changefreq
	 */
	public String getChangefreq() {
		return changefreq;
	}
	/**
	 * @param changefreq the changefreq to set
	 */
	public void setChangefreq(String changefreq) {
		this.changefreq = changefreq;
	}
	/**
	 * @return the priority
	 */
	public Double getPriority() {
		return (priority!=null)?Double.parseDouble(priority):null;
	}
	/**
	 * @param priority the priority to set
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	/**
	 * @return the image_loc
	 */
	public String getImage_loc() {
		return image_loc;
	}
	/**
	 * @param image_loc the image_loc to set
	 */
	public void setImage_loc(String image_loc) {
		this.image_loc = image_loc;
	}
	
	/**
	 * Returns 'NA' for lastmod when date is corrupt
	 */
	@Override
	public String toString(){
		String result = null;
		try {
			result =  "URL: loc="+getLoc()+", lastmod="+getLastmod()+
					", changefreq="+getChangefreq()+", priority="+getPriority()+", img:loc="+getImage_loc();
		} catch (ParseException e) {
			result =  "URL: loc="+getLoc()+", lastmod="+"NA"+
					", changefreq="+getChangefreq()+", priority="+getPriority()+", img:loc="+getImage_loc();
		}
		return result;
	}
	
}
