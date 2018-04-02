
import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.NoSuchElementException;


public class WebQuoteTest {
  static final String   AUTHOR = "offutt";
  static final String   TEXT   = "complete";
  static final String   BOTH   = "a";
  static final String   NONE   = "jaklsdjah";
  HtmlUnitDriver driver;
  String         url;

  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("WebQuoteTest");
  }

  @Before
  public void setUp() {
    this.url = "https://cs.gmu.edu:8443/offutt/servlet/quotes.quoteserve";
    this.driver = new HtmlUnitDriver();
    this.driver.get(this.url);
  }

  @Test
  public void testRandomQuote() {
    WebElement temp = this.driver.findElementByName("random"); // find button
    String firstQuote = getRandomQuote();                      // read quote 1
    temp.submit();                                             // click button

    // check first quote agains second quote
    // Statistically unlikely for them to be the same
    //  not the best test, but still
    assertFalse("random quotes are the same",
      firstQuote.equals(getRandomQuote())
    );
  }

  // The TT refers to what parts of the quote match the search String
  // TT - both author and quote should be matched by this string
  // TF - only author should be matched by th search string
  // FT - only quote part should be matched
  // FF - this string should get no matches
  @Test
  public void testSearchAuthorTT(){
    search(BOTH, "author");
    assertFalse("search by author - author + text", getQuote().isEmpty());
  }
  @Test
  public void testSearchAuthorTF(){
    search(AUTHOR, "author");
    assertFalse("search by author - author only", getQuote().isEmpty());
  }
  @Test
  public void testSearchAuthorFT(){
    search(TEXT, "author");
    assertTrue("search by author - text only", getQuote().isEmpty());
  }
  @Test
  public void testSearchAuthorFF(){
    search(NONE, "author");
    assertTrue("search by author - none - not found", getQuote().isEmpty());
  }

  @Test
  public void testSearchTextTT(){
    search(BOTH, "quote");
    assertFalse("search by quote - author + text", getQuote().isEmpty());
  }
  @Test
  public void testSearchTextTF(){
    search(AUTHOR, "quote");
    assertTrue("search by quote - author only", getQuote().isEmpty());
  }
  @Test
  public void testSearchTextFT(){
    search(TEXT, "quote");
    assertFalse("search by quote - text only", getQuote().isEmpty());
  }
  @Test
  public void testSearchTextFF(){
    search(NONE, "quote");
    assertTrue("search by quote - none", getQuote().isEmpty());
  }

  @Test
  public void testSearchBothTT(){
    search(BOTH, "both");
    assertFalse("search by both - author + text", getQuote().isEmpty());
  }
  @Test
  public void testSearchBothTF(){
    search(AUTHOR, "both");
    assertFalse("search by both - author only", getQuote().isEmpty());
  }
  @Test
  public void testSearchBothFT(){
    search(TEXT, "both");
    assertFalse("search by both - text only", getQuote().isEmpty());
  }
  @Test
  public void testSearchBothFF(){
    search(NONE, "both");
    assertTrue("search by both - none", getQuote().isEmpty());
  }

  @Test
  public void testResetTT(){
    WebElement temp = getResetButton();
    this.driver.findElementById("searchText").sendKeys(BOTH);
    temp.click();

    assertTrue("reset did not bar not clear",
      this.driver.findElementById("searchText").getText().isEmpty()
    );
  }
  @Test
  public void testResetTF(){
    WebElement temp = getResetButton();
    this.driver.findElementById("searchText").sendKeys(AUTHOR);
    temp.click();

    assertTrue("reset did not bar not clear",
      this.driver.findElementById("searchText").getText().isEmpty()
    );
  }
  @Test
  public void testResetFT(){
    WebElement temp = getResetButton();
    this.driver.findElementById("searchText").sendKeys("english");
    temp.click();

    assertTrue("reset did not bar not clear",
      this.driver.findElementById("searchText").getText().isEmpty()
    );
  }
  @Test
  public void testResetFF(){
    WebElement temp = getResetButton();
    this.driver.findElementById("searchText").sendKeys("jsjbdiekjb");
    temp.click();

    assertTrue("reset did not bar not clear",
      this.driver.findElementById("searchText").getText().isEmpty()
    );
  }

  @Test
  public void testLink1() {
    testLinks(1);
  }
  @Test
  public void testLink2() {
    testLinks(2);
  }
  @Test
  public void testLink3() {
    testLinks(3);
  }
  @Test
  public void testLink4() {
    testLinks(4);
  }
  @Test
  public void testLink5() {
    testLinks(5);
  }

  // Since the text field is not an input for the link, that part is ignored
  //  for this test - hyperlinks != forms
  public void testLinks(int num){
    WebElement temp   = getLink(num);               // pick a link
    String     link   = temp.getAttribute("href");  // get url
    String     scope  = link.split("[&=]")[3];      // get scope
    String     search = temp.getText();             // get the search string
    temp.click();                                   // click link
    String quote = getQuote();                      // remember search results


    temp = getSearchForm();
    this.driver.findElementById("searchText").sendKeys(search);
    this.driver.findElementById(scope).click();
    temp.submit();

    assertEquals("quotes are not the same (link: "+num+")", getQuote(), quote);
  }

  // helper function - search using search bar
  //  1. type text into search bar
  //  2. click radio button with id scope
  //  3. submit form
  public void search(String text, String scope) {
    WebElement temp = getSearchForm();
    this.driver.findElementById("searchText").sendKeys(text);
    this.driver.findElementById(scope).click();
    temp.submit();
  }

  // return link element by number
  public WebElement getLink(int num){
    String path = "/html/body/table/tbody/tr/td[3]"
                + "/table/tbody/tr[2]/td[2]/ol/li["+num+"]/a";
    return this.driver.findElementByXPath(path);
  }

  // shorter way of getting the reset button
  public WebElement getResetButton() {
    return this.driver.findElementByName("reset");
  }

  // helper function, return quote text from search
  //   or empty string is quote text is missing
  public String getQuote() {
    String path = "/html/body/table/tbody/tr/td/dl";
    try {
      return this.driver.findElementByXPath(path).getText();
    }
    catch(Exception e) {
      return "";
    }
  }

  // find and return search form
  public WebElement getSearchForm(){
    String path = "/html/body/table/tbody/tr/td[1]/form";
    return this.driver.findElementByXPath(path);
  }

  // return random quote string
  public String getRandomQuote(){
    String path = "/html/body/div";
    return this.driver.findElementByXPath(path).getText();
  }
}
