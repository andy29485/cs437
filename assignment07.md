# Assignment 7

### Contributors
- Andriy Zasypkin
- Hamza Khanane

### Type of interface
Command Line Interface (CLI)

### Location of source files
https://github.com/andy29485/swe437-assignments/tree/master/tests/assignment07

### Group Work Distribution

Both of us came with different ideas for test criteria. Implemented the ideas
into code and pair programming/coding.

<div style="page-break-after: always;"></div>

## Assignment Write Up

### Input Domain Model
Types of inputs:
- Buttons Types (criteria - each type of button tested once)
  - Random quote
  - search
  - Reset
  - Community searches
- Radio button - scope (all button combinations, but only when searching)
  - text
  - author
  - both
- Search text
  - b1: text exists in author part of quote
  - b2: text exists in quote part of the quote

### Test Designs
- Random Quotes
  - should exist (any non-empty value)
- Search
  - Author
    - b1:T,b2:T -> T
    - b1:T,b2:F -> T
    - b1:F,b2:T -> F
    - b1:F,b2:F -> F
  - Text
    - b1:T,b2:T -> T
    - b1:T,b2:F -> F
    - b1:F,b2:T -> T
    - b1:F,b2:F -> F
  - Both
    - b1:T,b2:T -> T
    - b1:T,b2:F -> T
    - b1:F,b2:T -> T
    - b1:F,b2:F -> F
- Reset
  - input should become empty after click
- Community
  - Quote results should be the same as search results

### Automated Tests
(attached on separate sheet)

### Running Tests
```bash
TODO
```
