
import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.NoSuchElementException;


public class WebQuoteTest {
  HtmlUnitDriver driver;
  String         AUTHOR = "offutt";
  String         TEXT   = "complete";
  String         BOTH   = "a";
  String         NONE   = "jaklsdjah";

  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("WebQuoteTest");
  }

  @Before
  public void setUp() {
    this.driver = new HtmlUnitDriver();
    this.driver.get("https://cs.gmu.edu:8443/offutt/servlet/quotes.quoteserve");
  }

  @Test
  public void testRandomQuote() {
    WebElement temp = this.driver.findElementByName("random");
    String firstQuote = getRandomQuote();
    temp.submit();

    // Statistically unlikely for them to be the same
    //  not the best test, but still
    assertFalse("random quotes are the same",
      firstQuote.equals(getRandomQuote())
    );
  }
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
  public void testLinks(){
    WebElement temp = searchByLinks();
    String search = temp.getText();
    temp.click();
    String quote= getQuote();

    temp = getSearchForm();
    this.driver.findElementById("searchText").sendKeys(quote);
    this.driver.findElementById("both").click();
    temp.submit();

    assertEquals("quotes are not the same", getQuote(), quote);
  }

  public void search(String text, String scope) {
    WebElement temp = getSearchForm();
    this.driver.findElementById("searchText").sendKeys(text);
    this.driver.findElementById(scope).click();
    temp.submit();
  }

  public WebElement searchByLinks(){
    String path = "/html/body/table/tbody/tr/td[3]"
                + "/table/tbody/tr[2]/td[2]/ol/li[1]/a";
    return this.driver.findElementByXPath(path);
  }

  public WebElement getResetButton() {
    return this.driver.findElementByName("reset");
  }

  public String getQuote() {
    String path = "/html/body/table/tbody/tr/td/dl";
    try {
      return this.driver.findElementByXPath(path).getText();
    }
    catch(Exception e) {
      return "";
    }
  }

  public WebElement getSearchForm(){
    String path = "/html/body/table/tbody/tr/td[1]/form";
    return this.driver.findElementByXPath(path);
  }

  public String getRandomQuote(){
    String path = "/html/body/div";
    return this.driver.findElementByXPath(path).getText();
  }
}
