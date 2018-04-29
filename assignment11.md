# Assignment 11

### Contributors
- Andriy Zasypkin
- Hamza Khanane

### What each person did
We both constructed both the truth tables and which rows would satisfy CACC,
discussed the values that would generate the least tests, and implemented the
tests in JUnit together.

### Location of source files
https://github.com/andy29485/swe437-assignments/

<div style="page-break-after: always;"></div>

## Assignment Write Up

1. List all predicates in the method. How many have one clause, two clauses,
   three clauses, and more than three?
   - (23) `month2 == month1` (one clause)
   - (35) `(m4 != 0) || ((m100 == 0) && (m400 != 0))` (3 clauses)
   - (44) `i <= month2-1` (one clause)
   - Two predicates have one clause and one predicate has 3 clauses.

2. For each predicate, write the truth table and choose rows from the truth
   tables that satisfy CACC. You must submit the truth tables and clearly
   mark which rows will be used. These are your abstract tests.
   - `month2 == month1`
   - Test cases used:
      -  (1, 2)

      | # | `month2 == month1` |
      | - | :----------------: |
      | 1 |          T*        |
      | 2 |          F*        |


   - `(m4 != 0) || ((m100 == 0) && (m400 != 0))`
   - A = `(m4 != 0)`
   - B = `(m100 == 0)`
   - C = `(m400 != 0)`
   - Test cases used:
      - A: (3, 7)
      - B: (5, 7)
      - C: (5, 6)

      | # | `A` | `B` | `C` | `B && C` | `P` |
      |:-:| :-: | :-: | :-: | :------: | :-: |
      | 1 |  T  |  T  |  T  |     T    |  T  |
      | 2 |  T* |  T  |  F  |     F    |  T  |
      | 3 |  T* |  F  |  T  |     F    |  T  |
      | 4 |  T* |  F  |  F  |     F    |  T  |
      | 5 |  F  |  T* |  T* |     T    |  T  |
      | 6 |  F* |  T  |  F* |     F    |  F  |
      | 7 |  F* |  F* |  T  |     F    |  F  |
      | 8 |  F* |  F* |  F* |     F    |  F  |   

   - `month2 == month1`
   - Test cases used:
      -  (1, 2)

      | # | `i <= month2-1` |
      | - | :-------------: |
      | 1 |        T*       |
      | 2 |        F*       |


3. For each abstract test, create input values that satisfy the truth
   assignments and that reach the predicate.
   - Notes:
      - Inputs will be in the format `(month1, day1, month2, day2, year)`
      - `-` means that the input does not matter.
      - Different variables mean that the values don't matter, but they
        have to be different (`X != Y`). This is needed to reach
        the second predicate

   - `month2 == month1`
      - 1: `(2, -, 2, -, -)`
      - 2: `(1, -, 2, -, -)`

   - `(m4 != 0) || ((m100 == 0) && (m400 != 0))`
      - Work:
         - Reachability: `month1 != month2`
      - 3: `(X, -, Y, -, 2011)`
      - 5: `(X, -, Y, -, 1900)`
      - 6: `(X, -, Y, -, 2000)`
      - 7: `(X, -, Y, -, 2004)`


   - `i <= month2-1`
      - Work:
         - `i = month1 + 1`
         - `p = (month1 <= month2 - 2)`
         - Reachability: `month1 != month2`
      - 1: `(1, -, 8, -, -)`
      - 2: `(1, -, 2, -, -)`


4. Implement each test in JUnit. Include comments that state which predicate
   and which truth assignment (row in the truth table) is being implemented.
   - See attached file

5. Run your tests.
```
$ make test_assignment11
javac -cp "assignment05:tests/assignment11":"junit.jar:hamcrest-core.jar:selenium.jar:htmlunit.jar:." assignment05/*.java tests/assignment11/*.java
java -cp "assignment05:tests/assignment11":"junit.jar:hamcrest-core.jar:selenium.jar:htmlunit.jar:." CalTest11
JUnit version 4.10
........
Time: 0.008

OK (8 tests)
```
