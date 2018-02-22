
import org.junit.*;
import static org.junit.Assert.*;


public class CalTest {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("CalTest");
  }

  // Zero check
  
  @Test
  public void testSameMonthSameDay() {
    int days_diff = Cal.cal(1, 8, 1, 8, 2018);
    assertEquals("Same Month Same Day", days_diff, 0);
  }

  // Same month checks

  @Test
  public void testSameMonthDiffDayLarger() {
    int days_diff = Cal.cal(1, 3, 1, 12, 2018);
    assertEquals("Same Month Different Days - 2nd later", days_diff, 9);
  }

  @Test
  public void testSameMonthDiffDaySmaller() {
    int days_diff = Cal.cal(12, 18, 12, 10, 2017);
    assertEquals("Same Month Different Days - 2nd earlier", days_diff, 9);
  }

  // Diff month checks

  @Test
  public void testSameDayDiffMonthLarger() {
    int days_diff = Cal.cal(10, 31, 12, 31, 2018);
    assertEquals("Check Same Day in Diff Month - 2nd later", days_diff, 61);
  }

  @Test
  public void testSameDayDiffMonthSmaller() {
    int days_diff = Cal.cal(12, 31, 10, 31, 2018);
    assertEquals("Check Same Day in Diff Month - 2nd earlier", days_diff, 61);
  }

  // End of ... checks

  @Test
  public void testMonthEndDates() {
    int days_diff = Cal.cal(1, 31, 4, 30, 2018);
    assertEquals("Check end days at ends of months", days_diff, 89);
  }

  @Test
  public void testYearDates() {
    int days_diff = Cal.cal(1, 1, 12, 31, 2017);
    assertEquals("Entire year (non-leap)", days_diff, 365);
  }

  // Leap year checks

  @Test
  public void testFebMarchLeapYear() {
    int days_diff = Cal.cal(2, 20, 3, 1, 2012);
    assertEquals("Check Feb Leap Year (2012)", days_diff, 10);
  }

  @Test
  public void testFebMarchLeapYear2000() {
    int days_diff = Cal.cal(2, 20, 3, 1, 2000);
    assertEquals("Check End of Feb Leap Year (2000)", days_diff, 10);
  }

  @Test
  public void testForLeapYear() {
    int days_diff = Cal.cal(2, 29, 3, 31, 2016);
    assertEquals(31,x);
    assertEquals("Check End of Feb for leap year (2016)", days_diff, 31);
  }

  // NOT leap year checks

  @Test
  public void testFebMarchNotLeapYear() {
    int days_diff = Cal.cal(2, 20, 3, 1, 2018);
    assertEquals("Check End of Feb NOT Leap Year (2018)", days_diff, 9);
  }

  @Test
  public void testForNONLeapYear() {
    int days_diff = Cal.cal(2, 28, 3, 31, 2018);
    assertEquals(31,x);
    assertEquals("Check for Not leap year (2018)", days_diff, 31);
  }

  @Test
  public void testFebMarchNotLeapYear2100() {
    int days_diff = Cal.cal(2, 20, 3, 1, 2017);
    assertEquals("Check End of Feb NOT Leap Year (2100)", days_diff, 9);
  }
}
