package quotes;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;
import javax.xml.stream.*;


public class QuoteListTest {
  QuoteList qlist;

  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("QuoteListTest");
  }

  @Before
  public void setup() {
    this.qlist = new QuoteList();

    this.qlist.addQuote(new Quote("Author 1", "Text 1"));
    this.qlist.addQuote(new Quote("Author 2", "Text 2"));
    this.qlist.addQuote(new Quote("Author 3", "Text 3"));
    this.qlist.addQuote(new Quote("Author 4", "Text 4"));
  }

  @Test
  public void testSearchNotFound() {
    int mode1 = QuoteList.SearchAuthorVal;
    int mode2 = QuoteList.SearchTextVal;
    int mode3 = QuoteList.SearchBothVal;

    QuoteList result1 = this.qlist.search("DOES NOT EXIST", mode1);
    QuoteList result2 = this.qlist.search("DOES NOT EXIST", mode2);
    QuoteList result3 = this.qlist.search("DOES NOT EXIST", mode3);

    assertTrue("QList 1 check empty", result1.getSize() == 0);
    assertTrue("QList 2 check empty", result2.getSize() == 0);
    assertTrue("QList 3 check empty", result3.getSize() == 0);
  }
}
