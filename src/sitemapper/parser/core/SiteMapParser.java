/**
Contributors: Nachi
 */
package sitemapper.parser.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import sitemapper.parser.beans.Url;
import sitemapper.parser.values.CONSTANTS;

public class SiteMapParser {

	private String xmlFileName;
	private XMLInputFactory inputFactory;
	private XMLEventReader eventReader;
	private List<Url> resultList;

	public SiteMapParser(String xmlFileName) {
		this.xmlFileName = xmlFileName;
		inputFactory = XMLInputFactory.newInstance();
	}

	public List<Url> parseAsList() throws FileNotFoundException, XMLStreamException{
		parse();
		return resultList;
	}

	public void parse() throws FileNotFoundException, XMLStreamException{
		readFile();
		Url urlObject = null;
		while(eventReader.hasNext()){
			XMLEvent event = null;
			try{
				event = eventReader.nextEvent();
			}catch(XMLStreamException xe){
				event = eventReader.nextTag();
			}
			if (event.isStartElement()) {
				/* If it is URL start tag */
				StartElement startElement = event.asStartElement();
				if (startElement.getName().getLocalPart() == (CONSTANTS.URL)) {
					urlObject = new Url();
				}
			}

			if (event.isStartElement()) {
				if (event.asStartElement().getName().getPrefix()
						.equals(CONSTANTS.IMAGE_LOC)) {
					event = eventReader.nextEvent();
					if(event.isStartElement()){
						if (event.asStartElement().getName().getLocalPart()
								.equals(CONSTANTS.LOC)) {
							event = eventReader.nextEvent();
							if(event.isCharacters())
								urlObject.setImage_loc(event.asCharacters().getData());
							continue;
						}
					}
					continue;
				}

				if (event.asStartElement().getName().getLocalPart()
						.equals(CONSTANTS.LOC)) {
					event = eventReader.nextEvent();
					if(event.isCharacters())
						urlObject.setLoc(event.asCharacters().getData());
					continue;
				}

				if (event.asStartElement().getName().getLocalPart()
						.equals(CONSTANTS.CHANGEFREQ)) {
					event = eventReader.nextEvent();
					if(event.isCharacters())
						urlObject.setChangefreq(event.asCharacters().getData());
					continue;
				}

				if (event.asStartElement().getName().getLocalPart()
						.equals(CONSTANTS.LASTMOD)) {
					event = eventReader.nextEvent();
					if(event.isCharacters())
						urlObject.setLastmod(event.asCharacters().getData());
					continue;
				}

				if (event.asStartElement().getName().getLocalPart()
						.equals(CONSTANTS.PRIORITY)) {
					event = eventReader.nextEvent();
					if(event.isCharacters())
						urlObject.setPriority(event.asCharacters().getData());
					continue;
				}

			}

			if(event.isEndElement()){
				/* If it is URL end tag */
				EndElement endElement = event.asEndElement();
				if (endElement.getName().getLocalPart()
						.equals(CONSTANTS.URL)) {
					actionOnEndOfUrl(urlObject);
				}

			}
		}
	}

	private void readFile() throws FileNotFoundException, XMLStreamException{
		InputStream iStream = new FileInputStream(xmlFileName);
		eventReader = inputFactory.createXMLEventReader(iStream);
	}

	public void actionOnEndOfUrl(Url urlObject){
		System.out.println(urlObject.toString());
	}

}
