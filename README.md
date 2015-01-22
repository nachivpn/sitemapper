sitemapper
==========

I couldn't find a suitable sitemap parser. So I wrote it anyway.

Pre-requisite
-------------
Overall understanding of:
* [Sitemap protocol](http://www.sitemaps.org/protocol.html)
* Java 

Parsing a Sitemap
-----------------

The SiteMapParser provides a method called parse() which parses the sitemap file. When every "url" tag/node has been parsed successfully, an action() method is called.
By default, the action() method is defined to add the Url objects to a set which can be obtained using getUrlSet().getSet() method. 

Sample Usage:

```java
	SiteMapParser parser = new SiteMapParser("file");
	Iterator <Url> iterator = parser.getUrlSet().getSet().iterator();
	while(iterator.hasNext()){
		Url url = iterator.next();
		System.out.println(url.getLoc());
		System.out.println(url.getLastmod());
		System.out.println(url.getChangefreq());
		System.out.println(url.getPriority());
	}
```

Custom Usage:

```java

/* Define a class: CustomSiteMapParser extends SiteMapParser and override action() method */

@Override
	public void action(Url urlObject){
		/*Process and Store contents of the urlObject in DB*/
	}
```

Parsing a Sitemap index
-----------------------
The SiteIndexParser provides a similar method called parse(), which parses the sitemap index file. When every "sitemap" tag/node has been parsed succesfully, an action() methods is called.
By default, the action() method adds all the SiteMap objects to a set which can be obtained using getIndexSet().getSet() method.

Sample usage:

```java
	SiteIndexParser parser = new SiteIndexParser("file");
	Iterator <SiteMap> iterator = parser.getIndexSet().getSet().iterator();
	while(iiterator.hasNext()){
		SiteMap sm = iterator.next();
		System.out.println(sm.getLoc());
		System.out.println(sm.getLastmod());
	}
```

Finding if a file is a sitemap or index 
---------------------------------------

Sample Usage:

```java
	String file = "files/sitemap-project.xml";
	try {
		SiteMapUtil.TYPE fileType = SiteMapUtil.getFileType(file);
		switch(fileType){
		case SITEMAPFILE:
			System.out.println("Sitemap File");
			break;
		case INDEXFILE:
			System.out.println("Index file");
			break;
		case INVALID:
			System.out.println("Invalid file!");
			break;
		}
	} catch (FileNotFoundException | XMLStreamException | ParseException e) {
		e.printStackTrace();
	}
```
