/**
Contributors: Nachi
 */
package sitemapper.util;

import java.io.FileNotFoundException;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import sitemapper.parser.values.CONSTANTS;
import sitemapper.util.FileUtil;

public class SiteMapUtil {

	public static enum TYPE {
		INDEXFILE, SITEMAPFILE, INVALID
	}

	public static final TYPE getFileType(String xmlFileName) throws FileNotFoundException, XMLStreamException{
		XMLEventReader eventReader = FileUtil.readFile(xmlFileName);
		while(eventReader.hasNext()){
			XMLEvent event = null;
			try{
				event = eventReader.nextEvent();
			}catch(XMLStreamException xe){
				event = eventReader.nextTag();
			}
			if (event.isStartElement()) {
				/* If it is URL start tag, initialize Url object */
				StartElement startElement = event.asStartElement();
				if (startElement.getName().getLocalPart() == (CONSTANTS.URLSET)) {
					return TYPE.SITEMAPFILE;
				}
				else if(startElement.getName().getLocalPart() == CONSTANTS.SITEMAPINDEX){
					return TYPE.INDEXFILE;
				}
			}
		}
		return TYPE.INVALID;
	}
}
