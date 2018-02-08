package quotes;

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

  // Default constructor does nothing
  public Quote ()
  {  }

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
  public void addQuoteText (String quoteText) {
    this.quoteText = quoteText;
  }

  @Override
  public String toString () {
    return quoteText + "\n  --" + author;
  }

  public String toXml() {
    StringBuilder sb = new StringBuilder();

    sb.append("  <").append(QuoteList.QuoteElem).append(">\n");
    sb.append("    <").append(QuoteList.QuoteTextElem).append(">")
                      .append(this.quoteText)
                      .append("</").append(QuoteList.QuoteTextElem)
                      .append(">\n");

    sb.append("    <").append(QuoteList.QuoteAuthorElem).append(">")
                      .append(this.author)
                      .append("</").append(QuoteList.QuoteAuthorElem)
                      .append(">\n");
    sb.append("  </").append(QuoteList.QuoteElem).append(">\n");

    return sb.toString();
  }

  public String toInternalFormat() { // Not JSON nor XML
    return "Quote {author=\'" + author + "\', quoteText=\'" + quoteText + "\'}";
  }
}
