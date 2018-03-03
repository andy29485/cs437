package quotes;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class CliTest {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("quotes.CliTest");
  }

  @Before
  public void setUp() {
    CliMain.quoteList = new QuoteList();
    CliMain.quoteList.addQuote(new Quote("Author 1", "Text 1", "t1", "t-all"));
    CliMain.quoteList.addQuote(new Quote("Author 2", "Text 2", "t2", "t-all"));
    CliMain.quoteList.addQuote(new Quote("Author 3", "Text 3", "t3", "t-all"));
    CliMain.quoteList.addQuote(new Quote("Author 4", "Text 4", "t4", "t-all"));
  }

  @Test
  public void testSearchByTags() {
    List<String> tags = new ArrayList<String>();
    tags.add("t1");
    QuoteList results = CliMain.search("", QuoteList.SearchBothVal, tags);

    assertEquals("Not finding quote with tag", 1, results.getSize());
  }
}
