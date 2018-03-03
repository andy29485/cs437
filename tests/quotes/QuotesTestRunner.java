package quotes;

import org.junit.runner.JUnitCore;

public class QuotesTestRunner {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main(
      "quotes.QuoteTest",
      "quotes.QuoteListTest",
      "quotes.SaxParserTest",
      "quotes.SaxHandlerTest",
      "quotes.OptionsTest",
      "quotes.CliTest"
    );
  }
}

