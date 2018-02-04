package quotes;
import java.util.ArrayList;

public class CliMain {
  private static QuoteList quoteList;
  public enum Action {INDEX, SEARCH, RANDOM, HELP};

  //private Random randomGen;
  public static void main(String[] args) {
    // Parse arguments
    Options options = parseArgs(args);

    // Load quotes
    QuoteSaxParser qParser = new QuoteSaxParser(options.quotefile);
    CliMain.quoteList      = qParser.getQuoteList();

    switch(options.action) {
      case RANDOM:
        getRandom();
        break;
      case INDEX:
        getIndex(options.param);
        break;
      case SEARCH:
        getSearch(options.searchStr, options.param);
        break;
      case HELP:
      default:
        printHelp();
    }

    System.exit(0);
  }

  public static Options parseArgs(String[] args) {
    ArrayList<Integer> ignore = new ArrayList<Integer>();
    Options options = new Options();
    String arg;

    // Parse command line options
    for (int i=0; i<args.length; ++i) {
      arg = args[i];
      if(arg.startsWith("-f") || arg.startsWith("--file")) {
        ignore.add(i); // ignore this and next arg when joining search terms
        ignore.add(i+1);
        options.quotefile = args[++i]; // set filepath to next arg
      }
      else if(arg.startsWith("-i") || arg.startsWith("--index")) {
        ignore.add(i);
        ignore.add(i+1);
        options.action = Action.INDEX;
        options.param  = Integer.valueOf(args[++i]); // set index to next arg
      }
      else if(arg.startsWith("-a") || arg.startsWith("--author")) {
        ignore.add(i);
        options.action = Action.SEARCH;
        options.param  = QuoteList.SearchAuthorVal;
      }
      else if(arg.startsWith("-q") || arg.startsWith("--quote")) {
        ignore.add(i);
        options.action = Action.SEARCH;
        options.param  = QuoteList.SearchTextVal;
      }
      else if(arg.startsWith("-b") || arg.startsWith("--both")) {
        ignore.add(i);
        options.action = Action.SEARCH;
        options.param  = QuoteList.SearchBothVal;
      }
      else if(arg.startsWith("-h") || arg.startsWith("--help")) {
        options.action = Action.HELP;
      }
      else { // unmatched argument, must be a search term
        options.action = Action.SEARCH;
      }
    }

    // save unused arguments as the search string
    options.searchStr = join(args, ignore);

    return options;
  }

  public static String join(String[] strings, ArrayList<Integer> ignore_indices) {
    // tmp storage ignoring the strings at indicies `ignore_indices`
    ArrayList<String> strList = new ArrayList<String>();

    // add strings to list except the ones to ignore
    for(int i=0; i<strings.length; i++) {
      if(ignore_indices.indexOf(i) == -1) {
        strList.add(strings[i]);
      }
    }

    // return the strings joined by a space
    return String.join(" ", strList);
  }


  public static void getIndex(int index) {
    Quote q = CliMain.quoteList.getQuote(index);
    System.out.println(q);
  }

  public static void getSearch(String terms, int mode) {
    QuoteList returnQuote = CliMain.quoteList.search(terms, mode);
    for(int i=0; i<returnQuote.getSize(); i++) {
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

  private static class Options {
    public String quotefile; // str with file path to xml file w/ quotes
    public String searchStr; // search string extracted from arguments
    public Action action;    // type of action to perform
    public int    param;     // param to pass to action

    public Options() {
      this.quotefile = "quotes/quotes.xml";
      this.searchStr = "";
      this.action    = Action.RANDOM;
      this.param     = QuoteList.SearchBothVal;
    }
  }
}
