package quotes;

import java.util.ArrayList;
import java.util.Random;
import java.io.*;

/**
 * List of all the quotes.
 * @author Mongkoldech Rajapakdee & Jeff Offutt
 *       Date: Nov 2009
 */
public class QuoteList {
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
   * @param mode search in the author, quotr, or both
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
   * Retuen a random quote object from the list.
   * @return a random Quote
   */
  public Quote getRandomQuote () {
    return this.quoteArray.get(this.randomGen.nextInt(this.quoteArray.size()));
  }

  public String toXml() {
    StringBuilder sb = new StringBuilder();

    sb.append("<?xml version=\"1.0\"?>\n");
    sb.append("<").append(QuoteListElem).append(">\n");
    for(Quote quote : this.quoteArray) {
      sb.append(quote.toXml());
    }
    sb.append("</").append(QuoteListElem).append(">\n");

    return sb.toString();
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
}
