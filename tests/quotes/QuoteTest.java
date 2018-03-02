package quotes;

import org.junit.*;
import static org.junit.Assert.*;

public class QuoteTest {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("quotes.QuoteTest");
  }

  @Test
  public void testPlaceholder() {
    assertEquals(1, 1);
  }
}
