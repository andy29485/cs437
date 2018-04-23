# Assignment 10

### Contributors
- Andriy Zasypkin
- Hamza Khanane

### What each person did
Bounced off idea while working together on this assignment. Both of us took
turns reading the book and power points to answer the questions.

<div style="page-break-after: always;"></div>

## Assignment Write Up

1. Write the predicate (only the predicate) to represent the requirement:
“List all computer plasma monitors that either cost more than $800 or for
which the store has more than 10 items. Also list LCD monitors that cost
more than $200.”
  - A = "computer plasma monitors that either cost more than $800"
  - B = "store has more than 10 items"
  - C = "LCD monitors that cost more than $200"
  - ((A | B) | C)

2. Answer the following six questions for the predicate:
   p = (a | b | c) & (a | d)
  - Questions:
    1. List the clauses from the predicate p.
      - a      
      - b  
      - c
      - d    
    2. Compute (and simplify) the conditions under which each clause
       determines predicate p.
      - a
        - !b & !c & !d
      - b
        - !a & !c & d
      - c
        - !a & !b & d
      - d
        - !a & (b | c)
    3. Write the complete truth table for all clauses. Label your rows starting
       from 1. Use the format in the example underneath the definition of
       Combinatorial Coverage in Section 8.1. That is, row 1 should be all
       clauses true. You should include columns for the conditions under
       which each clause determines the predicate, and also a column for the
       predicate itself.
      |  # | `a` | `b` | `c` | `d` | `a|b|c` | `a|d` | `(a|b|c) & (a|d)` |
      | -- | --- | --- | --- | --- | ------- | ----- | ----------------- |
      |  1 |  T  |  T  |  T  |  T  |    T    |   T   |         T         |
      |  2 |  T* |  T  |  T  |  F  |    T    |   T   |         T         |
      |  3 |  T  |  T  |  F  |  T  |    T    |   T   |         T         |
      |  4 |  T* |  T  |  F  |  F  |    T    |   T   |         T         |
      |  5 |  T  |  F  |  T  |  T  |    T    |   T   |         T         |
      |  6 |  T* |  F  |  T  |  F  |    T    |   T   |         T         |
      |  7 |  T* |  F  |  F  |  T  |    T    |   T   |         T         |
      |  8 |  T* |  F  |  F  |  F  |    T    |   T   |         T         |
      |  9 |  F  |  T  |  T  |  T* |    T    |   T   |         T         |
      | 10 |  F* |  T  |  T  |  F* |    T    |   F   |         F         |
      | 11 |  F  |  T* |  F  |  T* |    T    |   T   |         T         |
      | 12 |  F* |  T  |  F  |  F* |    T    |   F   |         F         |
      | 13 |  F  |  F  |  T* |  T* |    T    |   T   |         T         |
      | 14 |  F* |  F  |  T  |  F* |    T    |   F   |         F         |
      | 15 |  F* |  F* |  F* |  T  |    F    |   T   |         F         |
      | 16 |  F* |  F  |  F  |  F  |    F    |   F   |         F         |

    4. List all pairs of rows from your table that satisfy
       Correlated Active Clause Coverage (CACC) with respect to each clause.
       You should be able to read this directly from your answer to part 3.
      - for `a`:
        - (2,10), (2,12), (2,14), (2,15), (2,16)
        - (4,10), (4,12), (4,14), (4,15), (4,16)
        - (6,10), (6,12), (6,14), (6,15), (6,16)
        - (7,10), (7,12), (7,14), (7,15), (7,16)
        - (8,10), (8,12), (8,14), (8,15), (8,16)
      - for `b`:
        - (11, 15)
      - for `c`:
        - (13, 15)
      - for `d`:
        - ( 9, 10), ( 9, 12), ( 9, 14)
        - (11, 10), (11, 12), (11, 14)
        - (13, 10), (13, 12), (13, 14)
  - Extra credit
    1. List all pairs of rows from your table that satisfy General Active
       Clause Coverage (GACC) with respect to each clause.
      - for `a`:
        - (2,10), (2,12), (2,14), (2,15), (2,16)
        - (4,10), (4,12), (4,14), (4,15), (4,16)
        - (6,10), (6,12), (6,14), (6,15), (6,16)
        - (7,10), (7,12), (7,14), (7,15), (7,16)
        - (8,10), (8,12), (8,14), (8,15), (8,16)
      - for `b`:
        - (11, 15)
      - for `c`:
        - (13, 15)
      - for `d`:
        - ( 9, 10), ( 9, 12), ( 9, 14)
        - (11, 10), (11, 12), (11, 14)
        - (13, 10), (13, 12), (13, 14)
    2. List all pairs of rows from your table that satisfy Restricted Active
       Clause Coverage (RACC) with respect to each clause.
      - for `a`:
        - (2,10), (4, 12), (6, 14), (7, 15), (8, 16)
      - for `b`:
        - (11, 15)
      - for `c`:
        - (13, 15)
      - for `d`:
        - (9, 10), (11, 12), (13, 14)
