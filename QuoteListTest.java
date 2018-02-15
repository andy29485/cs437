package quotes;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class QuoteListTest {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("QuoteListTest");
  }


  @Test (timeout=1000) public void search_1 (){
    String s = "Offut";
    int mode = 0;
    QuoteList q = QuoteList.search(s,mode);
    assertEquals("hamza",q);
  }




}
