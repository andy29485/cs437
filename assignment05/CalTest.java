
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
  int month2 = 4;
  int day2 = 30;
  int year = 2018;
  int x = c.cal(month1,day1,month2,day2,year);
  assertEquals(89,x); 
}

@Test
  public void testDiff_Day_Diff_Mon() {
  Cal c = new Cal();
  int month1=11;
  int day1=13;
  int month2 = 12;
  int day2 = 18;
  int year = 2017;
  int x = c.cal(month1,day1,month2,day2,year);
  assertEquals(35,x); 
}

@Test
  public void test_DiffDay_SameMonth() {
  Cal c = new Cal();
  int month1=12;
  int day1=18;
  int month2 = 12;
  int day2 = 31;
  int year = 2017;
  int x = c.cal(month1,day1,month2,day2,year);
  assertEquals(13,x); 
}

@Test
  public void testSameDay_DiffMonth() {
  Cal c = new Cal();
  int month1=10;
  int day1=31;
  int month2 = 12;
  int day2 = 31;
  int year = 2018;
  int x = c.cal(month1,day1,month2,day2,year);
  assertEquals(61,x); 
}





  


}
  


