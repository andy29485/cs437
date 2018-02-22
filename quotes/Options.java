package quotes;
import java.util.ArrayList;

/**
 * Created by HamzaKhanani on 2/8/18.
 */
public class Options {
  public CliMain.Action    action;    // type of action to perform
  public String            quotefile; // str with path to xml file w/ quotes
  public int               param;     // param to pass to action
  public ArrayList<String> otherArgs; // List of all other arguments
  public String            author;    // author text to add to new quote
  public String            text;      // quote  text to addto new quote

  public Options() {
    this(null);
  }

  public Options(String[] args) {
    this.quotefile = "quotes/quotes.xml";
    this.action    = CliMain.Action.RANDOM;
    this.param     = QuoteList.SearchBothVal;
    this.author    = "";
    this.text      = "";
    this.otherArgs = new ArrayList<String>();

    if (args != null) {
      this.parseArgs(args);
    }
  }

  public void parseArgs(String[] args) {
    String arg;

    // Parse command line options
    for (int i = 0; i < args.length; ++i) {
      arg = args[i];
      if (arg.startsWith("-f") || arg.startsWith("--file")) {
        this.quotefile = args[++i]; // set filepath to next arg
      }
      else if (arg.startsWith("-i") || arg.startsWith("--index")) {
        this.action = CliMain.Action.INDEX;
        this.param  = Integer.valueOf(args[++i]); // set index to next arg
      }
      else if (arg.startsWith("-a") || arg.startsWith("--author")) {
        this.action = CliMain.Action.SEARCH;
        this.param  = QuoteList.SearchAuthorVal;
      }
      else if (arg.startsWith("-q") || arg.startsWith("--quote")) {
        this.action = CliMain.Action.SEARCH;
        this.param  = QuoteList.SearchTextVal;
      }
      else if (arg.startsWith("-b") || arg.startsWith("--both")) {
        this.action = CliMain.Action.SEARCH;
        this.param  = QuoteList.SearchBothVal;
      }
      else if(arg.startsWith("-t") || arg.startsWith("--text")) {
        this.action = CliMain.Action.ADDQUOTE;
        this.text      = args[++i];
      }
      else if(arg.startsWith("-A") || arg.startsWith("--Author")) {
        this.action = CliMain.Action.ADDQUOTE;
        this.author    = args[++i];
      }
      else if (arg.startsWith("-h") || arg.startsWith("--help")) {
        this.action = CliMain.Action.HELP;
      }
      else { // unmatched argument, must be a search term
        this.action = CliMain.Action.SEARCH;
        this.otherArgs.add(arg);
      }
    }
  }


  public void printHelp() {
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
    System.out.println("Adding new quote arguments:");
    System.out.println("  -A/--Author        - Author of new Quote");
    System.out.println("  -t/--text          - text of new quotes");
    System.out.println();
    System.out.println(" Default mode (no args) will return a random quote");
    System.out.println(" When in search mode, all unmatched");
    System.out.println("   args will be part of the search");
  }
}
