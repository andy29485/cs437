
import org.junit.*;
import static org.junit.Assert.*;


public class CalTest11 {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("CalTest11");
  }

  // Predicate: month2 == month1
  // row:       1
  @Test
  public void testSameMonthDifferentDay() {
    int days_diff = Cal.cal(2, 3, 2, 8, 2018);
    assertEquals("Same month different day", days_diff, 5);
  }

  // Predicate: month2 == month1
  // row:       2
  @Test
  public void testDifferentMonthSameDay() {
    int days_diff = Cal.cal(1, 8, 2, 8, 2018);
    assertEquals("different month same day", days_diff, 31);
  }

  // Predicate: (m4 != 0) || ((m100 == 0) && (m400 != 0))
  // row:       3
  @Test
  public void testNonLeapYearOdd() {
    int days_diff = Cal.cal(2, 8, 3, 8, 2011);
    assertEquals("Non-leap year (odd year)", days_diff, 28);
  }

  // Predicate: (m4 != 0) || ((m100 == 0) && (m400 != 0))
  // row:       5
  @Test
  public void testNonLeapYear100() {
    int days_diff = Cal.cal(2, 8, 3, 8, 1900);
    assertEquals("Non-leap year (100 not 400)", days_diff, 28);
  }

  // Predicate: (m4 != 0) || ((m100 == 0) && (m400 != 0))
  // row:       6
  @Test
  public void testLeapYear400() {
    int days_diff = Cal.cal(2, 8, 3, 8, 2000);
    assertEquals("Leap Year (400)", days_diff, 29);
  }

  // Predicate: (m4 != 0) || ((m100 == 0) && (m400 != 0))
  // row:       7
  @Test
  public void testLeapYearBasic() {
    int days_diff = Cal.cal(2, 8, 3, 8, 2004);
    assertEquals("Leap year (normal)", days_diff, 29);
  }

  // Predicate: i <= month2-1
  // row:       1
  @Test
  public void testWithInterveningMonths() {
    int days_diff = Cal.cal(1, 8, 8, 8, 2004);
    assertEquals("With Intervening Months", days_diff, 213);
  }

  // Predicate: i <= month2-1
  // row:       2
  @Test
  public void testWithoutInterveningMonths() {
    int days_diff = Cal.cal(1, 8, 2, 8, 2004);
    assertEquals("Without Intervening Months", days_diff, 31);
  }

}
