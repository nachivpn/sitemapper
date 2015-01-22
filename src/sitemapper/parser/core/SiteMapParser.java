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

import sitemapper.parser.beans.Url;
import sitemapper.parser.beans.UrlSet;
import sitemapper.parser.parents.Parser;
import sitemapper.parser.values.CONSTANTS;
import sitemapper.util.FileUtil;

public class SiteMapParser extends Parser{

	private XMLEventReader eventReader;
	private UrlSet urlSet;

	public SiteMapParser(String xmlFileName) {
		super(xmlFileName);
		urlSet = new UrlSet();
	}

	protected void parseFile() throws FileNotFoundException, XMLStreamException{
		eventReader = FileUtil.readFile(xmlFileName);
		Url urlObject = null;
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
				if (startElement.getName().getLocalPart() == (CONSTANTS.URL)) {
					urlObject = new Url();
				}
			}
			if (event.isStartElement()) {
				/* 
				 * If it is an any field's start tag, 
				 * assign the value to the URL object
				 */
				if (event.asStartElement().getName().getPrefix()
						.equals(CONSTANTS.IMAGE)) {
					/*image*/
					event = eventReader.nextEvent();
					if(event.isStartElement()){
						if (event.asStartElement().getName().getLocalPart()
								.equals(CONSTANTS.LOC)) {
							/*image locaton*/
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
					/*URL location*/
					event = eventReader.nextEvent();
					if(event.isCharacters())
						urlObject.setLoc(event.asCharacters().getData());
					continue;
				}
				if (event.asStartElement().getName().getLocalPart()
						.equals(CONSTANTS.CHANGEFREQ)) {
					/*URL change frequency*/
					event = eventReader.nextEvent();
					if(event.isCharacters())
						urlObject.setChangefreq(event.asCharacters().getData());
					continue;
				}
				if (event.asStartElement().getName().getLocalPart()
						.equals(CONSTANTS.LASTMOD)) {
					/*URL last modified date*/
					event = eventReader.nextEvent();
					if(event.isCharacters())
						urlObject.setLastmod(event.asCharacters().getData());
					continue;
				}
				if (event.asStartElement().getName().getLocalPart()
						.equals(CONSTANTS.PRIORITY)) {
					/*Ranking priority of URL*/
					event = eventReader.nextEvent();
					if(event.isCharacters())
						urlObject.setPriority(event.asCharacters().getData());
					continue;
				}
			}
			if(event.isEndElement()){
				/* If it is URL end tag, perform 'action' using the object  */
				EndElement endElement = event.asEndElement();
				if (endElement.getName().getLocalPart()
						.equals(CONSTANTS.URL)) {
					action(urlObject);
				}
			}
		}
	}

	/**
	 * Add the {@code urlObject} to {@code urlSet}
	 * @param urlObject
	 */
	public void action(Url urlObject){
		urlSet.add(urlObject);
	}
	
	/**
	 * Returns the entire sitemap as a UrlSet
	 * @return {@code resultList}
	 * @throws FileNotFoundException
	 * @throws XMLStreamException
	 */
	public UrlSet getUrlSet() throws FileNotFoundException, XMLStreamException{
		parse();
		return urlSet;
	}

}
