
import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;


public class WebQuoteTest {
  WebDriver driver;

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
    assertNotEquals("random quotes are the same", firstQuote, getRandomQuote());
  }
  @Test
  public void testSearchAuthorTT(){
    WebElement temp = getSearchForm();
    temp.findElementById("searchText").sendKeys("a");
    temp.findElementById("author").click();
    temp.submit();

    assertNotEquals("search by author - author + text - not found",
                    "did not match any quotes",
                    getQuote()
    );
  }
  @Test
  public void testSearchAuthorTF(){
    WebElement temp = getSearchForm();
    temp.findElementById("searchText").sendKeys("offutt");
    temp.findElementById("author").click();
    temp.submit();

    assertNotEquals("search by author - author only - not found",
                    "did not match any quotes",
                    getQuote()
    );
  }

  @Test
  public void testSearchAuthorFT(){
    WebElement temp = getSearchForm();
    temp.findElementById("searchText").sendKeys("english");
    temp.findElementById("author").click();
    temp.submit();

    assertEquals("search by author - text only - not found",
                    "did not match any quotes",
                    getQuote()
    );
  }

  @Test
  public void testSearchAuthorFF(){
    WebElement temp = getSearchForm();
    temp.findElementById("searchText").sendKeys("jbedbwjbjb");
    temp.findElementById("author").click();
    temp.submit();

    assertEquals("search by author - none - not found",
                    "did not match any quotes",
                    getQuote()
    );
  }

  @Test
  public void testSearchTextTT(){
    WebElement temp = getSearchForm();
    temp.findElementById("searchText").sendKeys("a");
    temp.findElementById("quote").click();
    temp.submit();

    assertNotEquals("search by quote - author + text - not found",
                    "did not match any quotes",
                    getQuote()
    );
  }
  @Test
  public void testSearchTextTF(){
    WebElement temp = getSearchForm();
    temp.findElementById("searchText").sendKeys("offutt");
    temp.findElementById("quote").click();
    temp.submit();

    assertEquals("search by quote - author only - not found",
                    "did not match any quotes",
                    getQuote()
    );
  }

  @Test
  public void testSearchTextFT(){
    WebElement temp = getSearchForm();
    temp.findElementById("searchText").sendKeys("english");
    temp.findElementById("quote").click();
    temp.submit();

    assertNotEquals("search by quote - text only - not found",
                    "did not match any quotes",
                    getQuote()
    );
  }

  @Test
  public void testSearchTextFF(){
    WebElement temp = getSearchForm();
    temp.findElementById("searchText").sendKeys("jbedbwjbjb");
    temp.findElementById("quote").click();
    temp.submit();

    assertEquals("search by autquotenone - not found",
                    "did not match any quotes",
                    getQuote()
    );
  }

  @Test
  public void testSearchBothTT(){
    WebElement temp = getSearchForm();
    temp.findElementById("searchText").sendKeys("a");
    temp.findElementById("both").click();
    temp.submit();

    assertNotEquals("search by quote - author + text - not found",
                    "did not match any quotes",
                    getQuote()
    );
  }
  @Test
  public void testSearchBothTF(){
    WebElement temp = getSearchForm();
    temp.findElementById("searchText").sendKeys("offutt");
    temp.findElementById("both").click();
    temp.submit();

    assertNotEquals("search by quote - author only - not found",
                    "did not match any quotes",
                    getQuote()
    );
  }

  @Test
  public void testSearchBothFT(){
    WebElement temp = getSearchForm();
    temp.findElementById("searchText").sendKeys("english");
    temp.findElementById("both").click();
    temp.submit();

    assertNotEquals("search by quote - text only - not found",
                    "did not match any quotes",
                    getQuote()
    );
  }

  @Test
  public void testSearchBothFF(){
    WebElement temp = getSearchForm();
    temp.findElementById("searchText").sendKeys("jbedbwjbjb");
    temp.findElementById("both").click();
    temp.submit();

    assertEquals("search by autquotenone - not found",
                    "did not match any quotes",
                    getQuote()
    );
  }

  @Test
  public void testResetTT(){
    WebElement temp = getResetButton();
    temp.findElementById("searchText").sendKeys("a");
    temp.click();

    assertEquals("reset did not bar not clear",
      temp.findElementById("searchText").getText().isEmpty()
    );

  }
  @Test
  public void testResetTF(){
    WebElement temp = getResetButton();
    temp.findElementById("searchText").sendKeys("offutt");
    temp.click();

    assertEquals("reset did not bar not clear",
      temp.findElementById("searchText").getText().isEmpty()
    );

  }

  @Test
  public void testResetFT(){
    WebElement temp = getResetButton();
    temp.findElementById("searchText").sendKeys("english");
    temp.click();

    assertEquals("reset did not bar not clear",
      temp.findElementById("searchText").getText().isEmpty()
    );

  }

  @Test
  public void testResetTF(){
    WebElement temp = getResetButton();
    temp.findElementById("searchText").sendKeys("jsjbdiekjb");
    temp.click();

    assertEquals("reset did not bar not clear",
      temp.findElementById("searchText").getText().isEmpty()
    );

  }

  @Test
  public void testLinks(){
    WebElement temp = searchByLinks();
    temp.click();
    String quote= getQuote();
    String search = temp.getText();

    temp = getSearchForm();
    temp.findElementById("searchText").sendKeys(quote);
    temp.findElementById("both").click();
    temp.submit();


    assertEquals("quotes are not the same",
      getQuote(),quote
    );

  }



  public boolean searchByLinks(){
    return this.driver.findElementByXPath("body.table.tbody.tr[1].td.ol.li[0]");

  }


  public WebElement getResetButton()
  {
    return this.driver.findElementByName("reset");
  }

  public String getQuote()
  {
    return this.driver.findElementByXPath("body.table.tbody.tr.td.dl")
      .getText();
  }


  public WebElement getSearchForm(){
    return this.driver.findElementByXPath("body.table.tbody.tr.td.form");
  }
  public String getRandomQuote()
  {
    return this.driver.findElementByXPath("body.div[0]").getText();
  }


}
