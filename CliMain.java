package quotes;
import java.util.ArrayList;

public class CliMain {
  private static QuoteList quoteList;


  //private Random randomGen;
  public static void main(String[] args) {
    // Initiallize the quote list
    QuoteSaxParser qParser = new QuoteSaxParser ("quotes.xml");
    quoteList = qParser.getQuoteList();

    // If no args were given, return a random quote
    if (args.length == 0) {
      getRandom();
      System.exit(0);
    }

    String arg;
    // Parse command line options
    for (int i=0; i<args.length; ++i) {
      arg = args[i];
      if(arg.startsWith("-i") || arg.startsWith("--index")) {
        int index = Integer.valueOf(args[i+1]);
        getIndex(index);
        System.exit(0);
      }
      else if(arg.startsWith("-a") || arg.startsWith("--author")) {
        getSearch(join(args, i), QuoteList.SearchAuthorVal);
        System.exit(0);
      }
      else if(arg.startsWith("-q") || arg.startsWith("--quote")) {
        getSearch(join(args, i), QuoteList.SearchTextVal);
        System.exit(0);
      }
      else {
        getSearch(join(args, i), QuoteList.SearchBothVal);
        System.exit(0);
      }
    }
  }

  public static getIndex(int index) {
    Quote q = this.qoutelist.get(i);
    System.out.println(q);
  }

  public static getSearch(String terms, int mode) {


    QuoteList returnQuote = this.qoutelist.search(terms,mode);
    for(int i=0; i<returnQuote.getSize(); i++)
    {
      System.out.println(returnQuote.get(i));

    }
  }

  public static getRandom() {

    Quote q = this.qoutelistqgetRandomQuote();


//    Random r = new Random();
//    int rando = r.nextInt(this.qoutelist.size()-1);
//    Quote q = this.qoutelist.get(rand);
//    System.out.println(q);
  }
}
