package quotes;
import java.util.ArrayList;

public class CliMain {
  private static QuoteList quoteList;

  //private Random randomGen;
  public static void main(String[] args) {
    // Initiallize the quote list
    String quotefile = "quotes/quotes.xml";
    ArrayList<Integer> ignore = new ArrayList<Integer>();
    int type = 0;
    int mode = QuoteList.SearchBothVal;

    String arg;
    // Parse command line options
    for (int i=0; i<args.length; ++i) {
      arg = args[i];
      if(arg.startsWith("-f") || arg.startsWith("--file")) {
        ignore.add(i);
        ignore.add(i+1);
        quotefile = args[++i];
      }
      else if(arg.startsWith("-i") || arg.startsWith("--index")) {
        ignore.add(i);
        ignore.add(i+1);
        mode = Integer.valueOf(args[++i]);
        type = 1;
      }
      else if(arg.startsWith("-a") || arg.startsWith("--author")) {
        ignore.add(i);
        type = 2;
        mode = QuoteList.SearchAuthorVal;
      }
      else if(arg.startsWith("-q") || arg.startsWith("--quote")) {
        ignore.add(i);
        type = 2;
        mode = QuoteList.SearchTextVal;
      }
      else if(arg.startsWith("-b") || arg.startsWith("--both")) {
        ignore.add(i);
        type = 2;
        mode = QuoteList.SearchBothVal;
      }
      else {
        type = 2;
      }
    }

    QuoteSaxParser qParser = new QuoteSaxParser (quotefile);
    CliMain.quoteList = qParser.getQuoteList();

    switch(type) {
      case 0:
        getRandom();
        break;
      case 1:
        getIndex(mode);
        break;
      case 2:
        getSearch(join(args, ignore), mode);
        break;
    }

    System.exit(0);
  }

  public static String join(String[] strings, ArrayList<Integer> ignore_indices) {
    // tmp storage ignoring the strings at indicies `ignore_indices`
    ArrayList<String> strList = new ArrayList<String>();

    // add strings to list except the ones to ignore
    for(int i=0; i<strings.length; i++) {
      if(ignore_indices.indexOf(i) == -1) strList.add(strings[i]);
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
    for(int i=0; i<returnQuote.getSize(); i++) {
      System.out.println(returnQuote.getQuote(i));
    }
  }

  public static void getRandom() {
    Quote q = CliMain.quoteList.getRandomQuote();
    System.out.println(q);
  }
}
