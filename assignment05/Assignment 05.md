# Assignment 5

### Contributors
- Andriy Zasypkin
- Hamza Khanane


### Location of source files
https://github.com/andy29485/swe437-quotes/

### WriteUp
Our testcases bascially test for different corner inputs which the user might put in. For example the leap and nonleap year test cases check for the appropiate number of days in the month of February. we  check for the end dates in the months to as every month has either 30 or 31 days in it. One of the faults which we created and was caught by our test cases was changing line number 26 where the calculation for number of days is done to "numdays = day2 + day1". This lead to a failiure of one of our test cases.
```
