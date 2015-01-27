/**
Contributors: Nachi
*/
package sitemapper.parser.parents;

import java.io.FileNotFoundException;
import javax.xml.stream.XMLStreamException;

public abstract class Parser {

	protected String xmlFileName;
	protected boolean isParsed;
	
	public Parser(String xmlFileName) {
		this.xmlFileName = xmlFileName;
		this.isParsed = false;
	}

	/**
	 * Returns true if the file provided to 
	 * constructor has already been parsed
	 * @return isParsed
	 */
	protected boolean isParsed() {
		return isParsed;
	}

	/**
	 * Used to 'already parsed'  status of 
	 * the file provided to constructor
	 * @param isParsed the isParsed to set
	 */
	protected void setParsed(boolean isParsed) {
		this.isParsed = isParsed;
	}
	
	/**
	 * 
	 * @throws FileNotFoundException
	 * @throws XMLStreamException
	 */
	public void parse() throws FileNotFoundException, XMLStreamException{
		if(!isParsed()){
			parseFile();
			setParsed(true);
		}
	}
	
	protected abstract void parseFile() throws FileNotFoundException, XMLStreamException;
}
