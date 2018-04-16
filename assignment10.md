# Assignment 10

### Contributors
- Andriy Zasypkin
- Hamza Khanane

### What each person did

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
  i.   List the clauses from the predicate p.
      - a      
      - b  
      - c
      - d    
  ii.  Compute (and simplify) the conditions under which each clause
       determines predicate p.
      - a
        - !b & !c & !d
      - b
        - !a & !c & d
      - c
        - !a & !b & d
      - d
        - !a & (b | c)
  iii. Write the complete truth table for all clauses. Label your rows starting
       from 1. Use the format in the example underneath the definition of
       Combinatorial Coverage in Section 8.1. That is, row 1 should be all
       clauses true. You should include columns for the conditions under
       which each clause determines the predicate, and also a column for the
       predicate itself.
      | `a` | `b` | `c` | `d` | `a|b|c` | `a|d` | `(a|b|c) & (a|d)` |
      | --- | --- | --- | --- | ------- | ----- | ----------------- |
      |  T  |  T  |  T  |  T  |    T    |   T   |        T          |
      |  T  |  T  |  T  |  F  |    T    |   T   |        T          |
      |  T  |  T  |  F  |  T  |    T    |   T   |        T          |
      |  T  |  T  |  F  |  F  |    T    |   T   |        T          |
      |  T  |  F  |  T  |  T  |    T    |   T   |        T          |
      |  T  |  F  |  T  |  F  |    T    |   T   |        T          |
      |  T  |  F  |  F  |  T  |    T    |   T   |        T          |
      |  T  |  F  |  F  |  F  |    T    |   T   |        T          |
      |  F  |  T  |  T  |  T  |    T    |   T   |        T          |
      |  F  |  T  |  T  |  F  |    T    |   F   |        F          |
      |  F  |  T  |  F  |  T  |    T    |   T   |        T          |
      |  F  |  T  |  F  |  F  |    T    |   F   |        F          |
      |  F  |  F  |  T  |  T  |    T    |   T   |        T          |
      |  F  |  F  |  T  |  F  |    T    |   F   |        F          |
      |  F  |  F  |  F  |  T  |    F    |   T   |        F          |
      |  F  |  F  |  F  |  F  |    F    |   F   |        F          |

  iv. List all pairs of rows from your table that satisfy
      General Active Clause Coverage (GACC) with respect to each clause.
      - TODO
  v.  List all pairs of rows from your table that satisfy Correlated Active
      Clause Coverage (CACC) with respect to each clause.
      - TODO
  vi. List all pairs of rows from your table that satisfy Restricted Active
      Clause Coverage (RACC) with respect to each clause.
      - TODO
