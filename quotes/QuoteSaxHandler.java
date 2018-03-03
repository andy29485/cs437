package quotes;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

/**
 * SAX handler for SAX Parser
 * @author Mongkoldech Rajapakdee & Jeff Offutt
 *         Date: Nov 2009
 * Uses the sax parser to parse an XML file into a list of quotes
*/

public class QuoteSaxHandler extends DefaultHandler {
  private QuoteList quoteList      = new QuoteList();
  private Quote     quoteTmp       = null; // temporary Quote
  private String    currentElement = null; // current element name

  public QuoteSaxHandler() {
    this(null);
  }

  public QuoteSaxHandler(QuoteList quoteList) {
    super();
    if (quoteList != null) {
      this.quoteList = quoteList;
    }
  }

  public QuoteList getQuoteList() {
    return this.quoteList;
  }

  @Override
  public void startDocument () {
    // System.out.println ("Start document"); // For testing
  }

  @Override
  public void endDocument () {
    // System.out.println ("End document"); // For testing
  }

  @Override
  public void startElement (String uri, String name,
                            String qName, Attributes atts) {
    if (qName.equalsIgnoreCase (QuoteList.QuoteListElem)) {
      currentElement = QuoteList.QuoteListElem;
    }
    else if (qName.equalsIgnoreCase(QuoteList.QuoteElem)) {
      currentElement = QuoteList.QuoteElem;
      quoteTmp = new Quote();
    }
    else if (qName.equalsIgnoreCase (QuoteList.QuoteAuthorElem)) {
      currentElement = QuoteList.QuoteAuthorElem;
    }
    else if (qName.equalsIgnoreCase (QuoteList.QuoteTextElem)) {
      currentElement = QuoteList.QuoteTextElem;
    }
    else if (qName.equalsIgnoreCase (QuoteList.QuoteTagElem)) {
      currentElement = QuoteList.QuoteTagElem;
    }
  }

  @Override
  public void endElement (String uri, String name, String qName) {
    if (qName.equalsIgnoreCase (QuoteList.QuoteElem)) {
      quoteList.addQuote (quoteTmp);
      quoteTmp = null;
   }
  }

  @Override
  public void characters (char ch[], int start, int length) {
    String value = new String (ch, start, length);
    if (!value.trim().equals("")) {
      if (currentElement.equalsIgnoreCase (QuoteList.QuoteTextElem)) {
        quoteTmp.addQuoteText (value);
      }
      else if (currentElement.equalsIgnoreCase (QuoteList.QuoteAuthorElem)) {
        quoteTmp.setAuthor (value);
      }
      else if (currentElement.equalsIgnoreCase (QuoteList.QuoteTagElem)) {
        quoteTmp.addTag (value);
      }
    }
  }

}
