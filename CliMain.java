package quotes;
import java.util.ArrayList;

public class CliMain {
  private static QuoteList quoteList;

  public enum Action {INDEX, SEARCH, RANDOM, HELP, ADDQUOTE};

  //private Random randomGen;
  public static void main(String[] args) {
    // Parse arguments
    Options options = new Options(args);

    // Load quotes
    CliMain.quoteList = new QuoteList();
    CliMain.quoteList.load(options.quotefile);

    run(options);
  }

  public static void run(Options options) {
    switch (options.action) {
      case RANDOM:
        getRandom();
        break;
      case INDEX:
        getIndex(options.param);
        break;
      case SEARCH:
        getSearch(String.join(" ", options.otherArgs), options.param);
        break;
      case ADDQUOTE: //case for adding a quote
        CliMain.quoteList.addQuote(new Quote(options.author, options.text));
        CliMain.quoteList.save(options.quotefile);
        break;

      case HELP:
      default:
        options.printHelp();
    }
  }

  public static void getIndex(int index) {
    if (index < 0 || index >= CliMain.quoteList.getSize()) {
      return;
    }
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
}
