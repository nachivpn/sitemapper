/**
Contributors: Nachi
*/
package sitemapper.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

public class FileUtil {

	public static XMLEventReader readFile(String xmlFileName) throws FileNotFoundException, XMLStreamException{
		InputStream iStream = new FileInputStream(xmlFileName);
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		return inputFactory.createXMLEventReader(iStream);
	}

}
