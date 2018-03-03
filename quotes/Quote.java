package quotes;

import javax.xml.stream.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Quote data object.
 * @author Mongkoldech Rajapakdee & Jeff offutt
 *         Date: Nov 2009
 * A quote has two parts, an author and a quoteText.
 * This bean class provides getters and setters for both, plus a toString()
 */
public class Quote {
    private   String       author;
    private   String       quoteText;
    protected ArrayList<String> tags;

    // Default constructor initializes empty strings
    public Quote () {
        this("", "");
    }

    // varg constructor for easier use when testing
    public Quote (String author, String quoteText, ArrayList<String> tags) {
        this(author, quoteText, tags= new ArrayList<String>());
    }

    // Constructor that assigns both strings
    public Quote (String author, String quoteText, ArrayList<String> tags) {
        this.author    = author;
        this.quoteText = quoteText;
        this.tags      = tags;
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

    public boolean hasTag(String t) {
        for(String tag : this.tags) {
            if(tag.equalsIgnoreCase(t)) {
                return true;
            }
        }
        return false;
    }

    public void addTag(String t) {
        if(hasTag(t)==true)
        {
            return;
        }
        this.tags.add(t);
    }

    @Override
    public String toString () {
        return quoteText + "\n  --" + author;
    }

    public void writeXml(XMLStreamWriter xsw) throws XMLStreamException {
        xsw.writeStartElement(QuoteList.QuoteElem);

        // Write text
        xsw.writeCharacters("\n    ");
        xsw.writeStartElement(QuoteList.QuoteTextElem);
        xsw.writeCharacters(this.quoteText);
        xsw.writeEndElement();

        // Write author name
        xsw.writeCharacters("\n    ");
        xsw.writeStartElement(QuoteList.QuoteAuthorElem);
        xsw.writeCharacters(this.author);
        xsw.writeEndElement();

        // Write keywords (outher list)
        xsw.writeCharacters("\n    ");
        xsw.writeStartElement(QuoteList.QuoteTagElem+"s");
        for (String tag : this.tags) {
            // Write keywords (inner individual)
            xsw.writeCharacters("\n      ");
            xsw.writeStartElement(QuoteList.QuoteTagElem);
            xsw.writeCharacters(tag);
            xsw.writeEndElement();
        }
        // end keywords outer
        xsw.writeCharacters("\n    ");
        xsw.writeEndElement();

        // end quote
        xsw.writeCharacters("\n  ");
        xsw.writeEndElement();
    }

    public String toInternalFormat() { // Not JSON nor XML
        return "Quote {author=\'" + author + "\', quoteText=\'" + quoteText + "\'}";
    }
}
