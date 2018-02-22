# Assignment 5

### Contributors
- Andriy Zasypkin
- Hamza Khanane


### Location of source files
https://github.com/andy29485/swe437-assignments/

### WriteUp
Our test cases basically test for different corner inputs which the
user might put in. For example the leap and non-leap year test cases
check for the appropriate number of days in the month of February.
we  check for the end dates in the months to as every month has
either 30 or 31 days in it.
TODO add own test cases

#### Faults
##### The one that our tests would catch
One of the faults which we created and was caught by our test cases was
changing line number `26` where the calculation for number of days is done to
`numdays = day2 + day1`.
This lead to a failure of one of our test cases.

##### The one that our tests would NOT catch
One fault that we came up with that was not caught was changing the number of
days in two months. We added a day to October and subtracted one from November.
Our now `daysIn` array looks like:
```java
int daysIn[] = {0, 31, 0, 31, 30, 31, 30, 31, 31, 30, 32, 29, 31};
```
