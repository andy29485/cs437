
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
  }

  @Test
  public void testTodo() {
    assertEquals("example failed", 1, 1);
  }
}
