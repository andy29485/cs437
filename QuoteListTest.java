package quotes;


import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;
import javax.xml.stream.*;


public class QuoteListTest {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("QuoteListTest");
  }
  
  
  //QuoteList qlist = new QuoteList();
  
  @Test (timeout=1000) public void search_1 (){
    String s = "Offut";
    int mode = 0;
    QuoteList qlist = new QuoteList();
    qlist.search(s,mode);
    assertEquals("hamza", qlist.search(s,mode));
    
  }
  
  
  
  
}
