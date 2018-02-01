package quotes;
import java.util.ArrayList;

public class CliMain {


  //private Random randomGen;
  public static void main(String[] args) {
    // Parse command line options
    for (String arg : args) {
      // parse arguments
    }
  }

  public static getIndex(int index) {
    Quote q = this.qoutelist.get(i);
    System.out.println(q);



    // TODO - return quote with index `index`
  }

  public static getSearch(String terms, int mode) {


    QuoteList returnQuote = this.qoutelist.search(terms,mode);
    for(int i=0; i<returnQuote.getSize(); i++)
    {
      System.out.println(returnQuote.get(i));

    }


    // TODO - return quotes matching search
  }

  public static getRandom() {

    Quote q = this.qoutelistqgetRandomQuote();


//    Random r = new Random();
//    int rando = r.nextInt(this.qoutelist.size()-1);
//    Quote q = this.qoutelist.get(rand);
//    System.out.println(q);

    // TODO - return a random quote
  }
}
