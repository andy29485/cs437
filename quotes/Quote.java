package quotes;

import javax.xml.stream.*;

/**
 * Quote data object.
 * @author Mongkoldech Rajapakdee & Jeff offutt
 *         Date: Nov 2009
 * A quote has two parts, an author and a quoteText.
 * This bean class provides getters and setters for both, plus a toString()
 */
public class Quote {
  private String author;
  private String quoteText;

  // Default constructor initializes empty strings
  public Quote () {
    this("", "");
  }

  // Constructor that assigns both strings
  public Quote (String author, String quoteText) {
    this.author    = author;
    this.quoteText = quoteText;
  }

  // Getter and setter for author
  public String getAuthor () {
    return author;
  }

  public void setAuthor (String author) {
    this.author = author;
  }

  // Getter and setter for quoteText
  public String getQuoteText () {
    return quoteText;
  }

  public void setQuoteText (String quoteText) {
    this.quoteText = quoteText;
  }

  public void addQuoteText (String quoteText) {
    this.setQuoteText(quoteText);
  }

  @Override
  public String toString () {
    return quoteText + "\n  --" + author;
  }

  public void writeXml(XMLStreamWriter xsw) throws XMLStreamException {
    xsw.writeStartElement(QuoteList.QuoteElem);

    xsw.writeCharacters("\n    ");
    xsw.writeStartElement(QuoteList.QuoteTextElem);
    xsw.writeCharacters(this.quoteText);
    xsw.writeEndElement();

    xsw.writeCharacters("\n    ");
    xsw.writeStartElement(QuoteList.QuoteAuthorElem);
    xsw.writeCharacters(this.author);
    xsw.writeEndElement();

    xsw.writeCharacters("\n  ");
    xsw.writeEndElement();
  }

  public String toInternalFormat() { // Not JSON nor XML
    return "Quote {author=\'" + author + "\', quoteText=\'" + quoteText + "\'}";
  }
}
