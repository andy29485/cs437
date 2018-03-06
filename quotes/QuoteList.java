package quotes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.io.*;
import javax.xml.stream.*;

/**
 * List of all the quotes.
 * @author Mongkoldech Rajapakdee & Jeff Offutt
 *       Date: Nov 2009
 */
public class QuoteList implements Iterable<Quote> {
  private ArrayList<Quote> quoteArray;

  // These constants are used in the servlet
  /* package */ static final int SearchAuthorVal = 0;
  /* package */ static final int SearchTextVal   = 1;
  /* package */ static final int SearchBothVal   = 2;

  // Node names in XML file
  public static final String QuoteListElem   = "quote-list";
  public static final String QuoteElem       = "quote";
  public static final String QuoteAuthorElem = "author";
  public static final String QuoteTextElem   = "quote-text";
  public static final String QuoteTagElem    = "tag";

  // For returning a random quote
  private Random randomGen;
  private final int seed = 19580427;

  // Default constructor creates a new list and initializes the random seed
  public QuoteList() {
    this.quoteArray = new ArrayList<Quote>();
    randomGen       = new Random(seed);
  }

  // Called when a quote is found, added to the array
  public void addQuote (Quote q) {
    this.quoteArray.add(q);
  }

   // Current size of the quote list
   public int getSize() {
    return this.quoteArray.size();
  }

  // Returns the ith quote from the list
  public Quote getQuote (int i){
    return (Quote) this.quoteArray.get(i);
  }

  /**
   * Search the quotes in the list, based on searchString
   * @param searchString String input for search
   * @param mode search in the author, quote, or both
   * @return QuoteList containing the search results (may be multiple quotes)
   */
  public QuoteList search (String searchString, int mode) {
    QuoteList returnQuote = new QuoteList();
    Quote quote;
    searchString = searchString.toLowerCase();

    for (int i = 0; i < quoteArray.size(); i++) {
      quote = quoteArray.get (i);
      String authorString = quote.getAuthor().toLowerCase();
      String quoteString  = quote.getQuoteText().toLowerCase();

      switch (mode) {
        case SearchAuthorVal:
          if(authorString.indexOf(searchString) != -1) {
            // Found a matching author, save it
            // System.out.println ("Matched Author");
            returnQuote.addQuote (quote);
          }
          break;
        case SearchTextVal:
          if(quoteString.indexOf(searchString) != -1) {
            // Found a matching author, save it
            // System.out.println ("Matched Text");
            returnQuote.addQuote (quote);
          }
          break;
        case SearchBothVal:
          if(authorString.indexOf(searchString) != -1 ||
             quoteString .indexOf(searchString) != -1) {
            // Found a matching author, save it
            // System.out.println ("Matched Both");
            returnQuote.addQuote (quote);
          }
          break;
      }
    }
    return returnQuote;
  }

  /**
   * Search the quotes in the list, based on keywords
   * @param tag keyword input for match
   * @return QuoteList containing the search results (may be multiple quotes)
   */
  public QuoteList searchTags (String tag) {
    QuoteList results = new QuoteList();
    for (Quote quote : this.quoteArray) {
      if(quote.hasTag(tag)) {
        results.addQuote(quote);
      }
    }
    return results;
  }

  /**
   * Retuen a random quote object from the list.
   * @return a random Quote
   */
  public Quote getRandomQuote () {
    return this.quoteArray.get(this.randomGen.nextInt(this.quoteArray.size()));
  }

  public String toXml() {
    XMLOutputFactory xof = XMLOutputFactory.newFactory();

    try {
      StringWriter sw = new StringWriter();
      XMLStreamWriter xsw = xof.createXMLStreamWriter(sw);

      xsw.writeStartDocument();
      xsw.writeCharacters("\n");
      xsw.writeStartElement(QuoteListElem);

      for(Quote quote : this.quoteArray) {
        xsw.writeCharacters("\n  ");
        quote.writeXml(xsw);
      }

      xsw.writeCharacters("\n");
      xsw.writeEndElement();
      xsw.writeCharacters("\n"); // new line at end of file
      xsw.writeEndDocument();

      return sw.toString();
    }
    catch (XMLStreamException e) { // error creating XML
      return "";
    }
  }

  public void load(String filename) {
    // create a temp parser object to to populate this list
    new QuoteSaxParser(filename, this);
  }

  public boolean save(String filename) {
    try {
      FileWriter     fw = new FileWriter(filename); // Open file
      BufferedWriter bw = new BufferedWriter(fw);   // and create a writer

      bw.write(this.toXml());
      bw.close();
    }
    catch (IOException e) {
      return false;
    }
    return true;
  }

  public Iterator<Quote> iterator() {
    Iterator<Quote> it = new Iterator<Quote>() {
      private int index = 0;

      @Override
      public boolean hasNext() {
        return this.index < getSize();
      }

      @Override
      public Quote next() {
        return getQuote(this.index++);
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
    return it;
  }
}
