import java.util.ArrayList;


public class CliMain {
  private static QuoteList quoteList;

  public enum Action {INDEX, SEARCH, RANDOM, HELP, ADDQUOTE}

  ;

  //private Random randomGen;
  public static void main(String[] args) {
    // Parse arguments
    Options options = Options.parseArgs(args);

    // Load quotes
    QuoteSaxParser qParser = new QuoteSaxParser(options.quotefile);
    CliMain.quoteList = qParser.getQuoteList();

    switch (options.action) {
      case RANDOM:
        getRandom();
        break;
      case INDEX:
        getIndex(options.param);
        break;
      case SEARCH:
        getSearch(options.searchStr, options.param);
        break;
      case ADDQUOTE: //case for adding a quote
        //addQuote();

      case HELP:
      default:
        printHelp();
    }

    System.exit(0);
  }



  public static String join(String[] strings, ArrayList<Integer> ignore_indices) {
    // tmp storage ignoring the strings at indicies `ignore_indices`
    ArrayList<String> strList = new ArrayList<String>();

    // add strings to list except the ones to ignore
    for (int i = 0; i < strings.length; i++) {
      if (ignore_indices.indexOf(i) == -1) {
        strList.add(strings[i]);
      }
    }

    // return the strings joined by a space
    return String.join(" ", strList);
  }

  public static void addQuote(Quote quote) { //addquote function
    Quote q = quote;
    CliMain.quoteList.setQuote(q); //calling the method in quotelist.

  }

  public static void getIndex(int index) {
    Quote q = CliMain.quoteList.getQuote(index);
    System.out.println(q);
  }

  public static void getSearch(String terms, int mode) {
    QuoteList returnQuote = CliMain.quoteList.search(terms, mode);
    for (int i = 0; i < returnQuote.getSize(); i++) {
      System.out.println(returnQuote.getQuote(i));
    }
  }

  public static void getRandom() {
    Quote q = CliMain.quoteList.getRandomQuote();
    System.out.println(q);
  }

  public static void printHelp() {
    System.out.println("Quotes program usage:");
    System.out.println();
    System.out.println("General arguments:");
    System.out.println("  -h/--help          - this help page");
    System.out.println("  -f/--file <path>   - specify path to quotes file");
    System.out.println("  -i/--index <index> - print quote with given index");
    System.out.println("Search arguments:");
    System.out.println("  -a/--author        - search only author");
    System.out.println("  -q/--quote         - search only quotes");
    System.out.println("  -b/--both          - search both quote and author");
    System.out.println();
    System.out.println(" Default mode (no args) will return a random quote");
    System.out.println(" When in search mode, all unmatched");
    System.out.println("   args will be part of the search");
  }
}


