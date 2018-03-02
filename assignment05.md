# Assignment 5

### Contributors
- Andriy Zasypkin
- Hamza Khanane

### Type of interface
Command Line Interface (CLI)

### Location of source files
https://github.com/andy29485/swe437-assignments/

<div style="page-break-after: always;"></div>

## Assignment Write Up
Our test cases basically test for different corner cases and happy
paths. For example the leap and non-leap year test cases check for
the appropriate number of days in the month of February. We check
for the end dates in the months to as every month (except February)
has either 30 or 31 days in it. We also check that the year has
exactly 365 days and some basic cases like same day, days in the
same month, and two random days in the year.

### Faults
#### The one that our tests would catch
One of the faults which we created and was caught by our test cases
was changing line number `26` where the calculation for number of
days is done to `numdays = day2 + day1`. This lead to a failure of
one of our test cases.

#### The one that our tests would NOT catch
One fault that we came up with that was not caught was changing the
number of days in two months. We added a day to October and subtracted
one from November. Our now `daysIn` array looks like:
```java
int daysIn[] = {0, 31, 0, 31, 30, 31, 30, 31, 31, 30, 32, 29, 31};
```

