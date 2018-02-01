package quotes;
import java.util.ArrayList;

public class CliMain {
  private static QuoteList quoteList;

  //private Random randomGen;
  public static void main(String[] args) {
    // Initiallize the quote list
    QuoteSaxParser qParser = new QuoteSaxParser ("quotes.xml");
    CliMain.quoteList = qParser.getQuoteList();

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

   public static String join(String[] strings, int ignore_index) {
   // tmp storage ignoring the string at index `ignore_index`
   ArrayList<String> strList = new ArrayList<String>();
   // add strings to list except the one index
   for(int i=0; i<strings.length; i++) {
     if(i != ignore_index) strList.add(strings[i]);
   }
   // return the strings joined by a space
   return String.join(" ", strList);
 }


  public static void getIndex(int index) {
    Quote q = CliMain.quoteList.getQuote(index);
    System.out.println(q);
  }

  public static void getSearch(String terms, int mode) {


    QuoteList returnQuote = CliMain.quoteList.search(terms,mode);
    for(int i=0; i<returnQuote.getSize(); i++)
    {
      System.out.println(returnQuote.getQuote(i));

    }
  }

  public static void getRandom() {

    Quote q = CliMain.quoteList.getRandomQuote();
    System.out.println(q);


//    Random r = new Random();
//    int rando = r.nextInt(CliMain.quoteList.size()-1);
//    Quote q = CliMain.quoteList.get(rand);
//    System.out.println(q);
  }
}
