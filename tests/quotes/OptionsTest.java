package quotes;

import org.junit.*;
import static org.junit.Assert.*;

public class OptionsTest {
  private Options options;

  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("quotes.OptionsTest");
  }

  @Before
  public void setUp() {
    this.options = new Options();
  }

  @Test
  public void testKeywordsNoneGiven() {
    assertEquals("Keywords are non-empty", 0, this.options.keywords.size());
  }

  @Test
  public void testKeywordsOneGiven() {
    this.options.parseArgs(new String[]{"-k", "tag1"});
    assertEquals("Keywords 1 given", 1, this.options.keywords.size());
  }

  @Test
  public void testKeywordsMultipleGiven() {
    this.options.parseArgs(new String[]{"-k", "t1", "-k", "t2", "-k", "t3"});
    assertEquals("Keywords 3 given", 3, this.options.keywords.size());
  }
}
