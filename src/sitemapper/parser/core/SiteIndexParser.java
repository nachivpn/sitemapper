/**
Contributors: Nachi
 */
package sitemapper.parser.core;

import java.io.FileNotFoundException;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import sitemapper.parser.beans.SiteMap;
import sitemapper.parser.beans.SiteMapIndex;
import sitemapper.parser.parents.Parser;
import sitemapper.parser.values.CONSTANTS;
import sitemapper.util.FileUtil;

public class SiteIndexParser extends Parser{

	private XMLEventReader eventReader;
	private SiteMapIndex indexSet;

	public SiteIndexParser(String xmlFileName) {
		super(xmlFileName);
		indexSet = new SiteMapIndex();
	}

	public void parseFile() throws FileNotFoundException, XMLStreamException{
		eventReader = FileUtil.readFile(xmlFileName);
		SiteMap siteMapObject = null;
		while(eventReader.hasNext()){
			XMLEvent event = null;
			try{
				event = eventReader.nextEvent();
			}catch(XMLStreamException xe){
				event = eventReader.nextTag();
			}
			if (event.isStartElement()) {
				/* If it is sitemap start tag */
				StartElement startElement = event.asStartElement();
				if (startElement.getName().getLocalPart() == (CONSTANTS.SITEMAP)) {
					siteMapObject = new SiteMap();
				}
			}
			if (event.isStartElement()) {
				if (event.asStartElement().getName().getLocalPart()
						.equals(CONSTANTS.LOC)) {
					/*Sitemap location*/
					event = eventReader.nextEvent();
					if(event.isCharacters())
						siteMapObject.setLoc(event.asCharacters().getData());
					continue;
				}
				if (event.asStartElement().getName().getLocalPart()
						.equals(CONSTANTS.LASTMOD)) {
					/*Sitemap last modified date*/
					event = eventReader.nextEvent();
					if(event.isCharacters())
						siteMapObject.setLastmod(event.asCharacters().getData());
					continue;
				}
			}
			if(event.isEndElement()){
				/*if the end tag is sitemap*/
				EndElement endElement = event.asEndElement();
				if (endElement.getName().getLocalPart()
						.equals(CONSTANTS.SITEMAP)) {
					/*execute action using siteMapObject*/
					action(siteMapObject);
				}
			}
		}
	}

	/**
	 * add the {@code siteMapObject} to {@code indexSet}
	 * @param siteMapObject
	 */
	public void action(SiteMap siteMapObject) {
		indexSet.add(siteMapObject);
	}
	
	/**
	 * @return the {@code indexSet}
	 * @throws XMLStreamException 
	 * @throws FileNotFoundException 
	 */
	public SiteMapIndex getIndexSet() throws FileNotFoundException, XMLStreamException {
		parse();
		return indexSet;
	}

}
