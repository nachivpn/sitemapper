/**
Contributors: Nachi
 */
package sitemapper.parser.w3cdate;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

public class W3CDateParser {

	String dateString;

	String dateAndTime;
	String tmz;

	private final int noTimeZoneLength = 3;

	String datePatterns[]={
			"yyyy-MM-dd", 
			"yyyy-MM-dd'T'HH:mm:ss",
			"yyyy-MM-dd'T'HH:mm:ss.S",
			"yyyy-MM-dd'T'HH:mm:ss.SS",
			"yyyy-MM-dd'T'HH:mm:ss.SSS", 
			"yyyy-MM-dd'T'HH:mm:ss.SSSz"
	};

	public W3CDateParser(String dateString) {
		this.dateString = dateString;
	}

	public Date parse() throws ParseException{
		Date resultDate = null;
		if(tmzExists())
			splitAndSet();
		resultDate = DateUtils.parseDate(dateString, datePatterns);
		return resultDate;

	}

	private boolean tmzExists(){
		return dateString.split(":").length > noTimeZoneLength;
	}

	private void splitAndSet(){
		dateAndTime = dateString.substring(0, dateString.length()-6);
		tmz = dateString.substring(dateString.length()-6);
		tmz = tmz.replace(":", "");
		dateString = dateAndTime + tmz;
	}

}
