package quotes;

import org.junit.*;
import static org.junit.Assert.*;

public class QuoteTest {
  private Quote quote;

  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("quotes.QuoteTest");
  }

  @Before
  public void setUp() {
    this.quote = new Quote("A", "T", "tagA", "tagB");
  }

  @Test
  public void testTagMatch() {
    assertTrue("Tag not matched", this.quote.hasTag("tagA"));
  }

  @Test
  public void testTagNotMatch() {
    assertFalse("Tag incorrectly matched", this.quote.hasTag("tag-dne"));
  }

  @Test
  public void testMultipleTags() {
    assertTrue("Multiple Tags not matched", this.quote.hasTag("tagA"));
    assertTrue("Multiple Tags not matched", this.quote.hasTag("tagB"));
  }

  @Test
  public void testAddTag() {
    this.quote.addTag("new-tag-that-wasn't-there");
    assertTrue("Tags not added", this.quote.hasTag("new-tag-that-wasn't-there"));
  }

  @Test
  public void testAddExistingTag() {
    int old_size = this.quote.tags.size();
    this.quote.addTag("tagA");
    assertEquals("Duplicate tag added", old_size, this.quote.tags.size());
  }
}
