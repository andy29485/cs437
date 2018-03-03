package quotes;

import org.junit.*;
import static org.junit.Assert.*;

public class QuoteListTest {
  QuoteList qlist;

  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("quotes.QuoteListTest");
  }

  @Before
  public void setup() {
    this.qlist = new QuoteList();

    this.qlist.addQuote(new Quote("Author 1", "Text 1", "tag1", "tag-all"));
    this.qlist.addQuote(new Quote("Author 2", "Text 2", "tag2", "tag-all"));
    this.qlist.addQuote(new Quote("Author 3", "Text 3", "tag3", "tag-all"));
    this.qlist.addQuote(new Quote("Author 4", "Text 4", "tag4", "tag-all"));
  }

  @Test
  public void testSearchNotFound() {
    int mode1 = QuoteList.SearchAuthorVal;
    int mode2 = QuoteList.SearchTextVal;
    int mode3 = QuoteList.SearchBothVal;
    String search = "DOES NOT EXIST";

    QuoteList result1 = this.qlist.search(search, mode1);
    QuoteList result2 = this.qlist.search(search, mode2);
    QuoteList result3 = this.qlist.search(search, mode3);

    assertEquals("QList 1 check empty", 0, result1.getSize());
    assertEquals("QList 2 check empty", 0, result2.getSize());
    assertEquals("QList 3 check empty", 0, result3.getSize());
  }

  @Test
  public void testSearchAuthor() {
    int mode1 = QuoteList.SearchAuthorVal;
    int mode2 = QuoteList.SearchTextVal;
    int mode3 = QuoteList.SearchBothVal;
    String search = "Author 2";

    QuoteList result1 = this.qlist.search(search, mode1);
    QuoteList result2 = this.qlist.search(search, mode2);
    QuoteList result3 = this.qlist.search(search, mode3);

    assertEquals("QList search author - mode author", 1, result1.getSize());
    assertEquals("QList search author - mode text",   0, result2.getSize());
    assertEquals("QList search author - mode both",   1, result3.getSize());
  }

  @Test
  public void testSearchTextMultipleResults() {
    int mode1 = QuoteList.SearchAuthorVal;
    int mode2 = QuoteList.SearchTextVal;
    int mode3 = QuoteList.SearchBothVal;
    String search = "Text";

    QuoteList result1 = this.qlist.search(search, mode1);
    QuoteList result2 = this.qlist.search(search, mode2);
    QuoteList result3 = this.qlist.search(search, mode3);

    assertEquals("QList search multiple - mode author", 0, result1.getSize());
    assertEquals("QList search multiple - mode text",   4, result2.getSize());
    assertEquals("QList search multiple - mode both",   4, result3.getSize());
  }

  @Test
  public void testSearchTextCase() {
    int mode1 = QuoteList.SearchAuthorVal;
    int mode2 = QuoteList.SearchTextVal;
    int mode3 = QuoteList.SearchBothVal;
    String search = "teXt";

    QuoteList result1 = this.qlist.search(search, mode1);
    QuoteList result2 = this.qlist.search(search, mode2);
    QuoteList result3 = this.qlist.search(search, mode3);

    assertEquals("QList search text case - mode author", 0, result1.getSize());
    assertEquals("QList search text case - mode text",   4, result2.getSize());
    assertEquals("QList search text case - mode both",   4, result3.getSize());
  }

  @Test
  public void testSearchForText() {
    int mode1 = QuoteList.SearchAuthorVal;
    int mode2 = QuoteList.SearchTextVal;
    int mode3 = QuoteList.SearchBothVal;

    QuoteList result1 = this.qlist.search("Text 1", mode1);
    QuoteList result2 = this.qlist.search("Text 2", mode2);
    QuoteList result3 = this.qlist.search("Text 3", mode3);

    assertEquals("wrong mode",  0, result1.getSize());
    assertEquals("Quote found", 1, result2.getSize());
    assertEquals("found",       1, result3.getSize());
  }

  @Test
  public void testNonExistantTag() {
    QuoteList results = this.qlist.searchTags("dne-tag");
    assertEquals("Matched quotes w/ non-existant tag", 0, results.getSize());
  }

  @Test
  public void testExistingTag() {
    QuoteList results = this.qlist.searchTags("tag1");
    assertEquals("Failed to find quote w/ unique tag", 1, results.getSize());
  }

  @Test
  public void testXMLSavesTags() {
    assertTrue("QuoteList does not save tags", this.qlist.toXml().contains("tag1"));
  }

  @Test
  public void testXMLLoadsTags() {
    this.qlist = new QuoteList();
    this.qlist.load("tests/quotes/quotes.xml");
    assertTrue("QuoteList does not load tags", this.qlist.toXml().contains("tag1"));
  }
}
