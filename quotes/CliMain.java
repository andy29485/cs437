package quotes;

import java.util.ArrayList;
import java.util.List;

public class CliMain {
  protected static QuoteList quoteList;

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
        getSearch(
                  String.join(" ", options.otherArgs),
                  options.param,
                  options.keywords
        );
        break;
      case ADDQUOTE: //case for adding a quote
        CliMain.quoteList.addQuote(
          new Quote(options.author, options.text, options.keywords)
        );
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
    CliMain.printQuote(q);
  }

  public static QuoteList search(String terms, int mode, List<String> tags) {
    QuoteList returnQuotes = CliMain.quoteList.search(terms, mode);
    for (String tag : tags) {
      returnQuotes = returnQuotes.searchTags(tag);
    }
    return returnQuotes;
  }

  public static void getSearch(String terms, int mode, List<String> tags) {
    QuoteList returnQuotes = CliMain.search(terms, mode, tags);
    CliMain.printQuotes(returnQuotes);
  }

  public static void getRandom() {
    Quote q = CliMain.quoteList.getRandomQuote();
    CliMain.printQuote(q);
  }

  public static void printQuotes(QuoteList ql) {
    for (Quote q : ql) {
      CliMain.printQuote(q);
    }
  }

  public static void printQuote(Quote q) {
    System.out.println(q);
  }
}
