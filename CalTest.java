
import org.junit.*;
import static org.junit.Assert.*;


public class CalTest {

  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("CalTest");
  }

  @Before
  public void setup() {
    
  }




@Test
  public void testEndDates() {
  Cal c = new Cal();
  int month1=1;
  int day1=31;
  int month2 = 3;
  int day2 = 31;
  int year = 2018;
  int x = c.cal(month1,day1,month2,day2,year);
  assertEquals(59,x); 
}

  


}
  


