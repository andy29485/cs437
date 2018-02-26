# Assignment 2

### Contributors
- Andriy Zasypkin
- Hamza Khanane

### Type of interface
Command Line Interface (CLI)

### Location of source files
https://github.com/andy29485/swe437-assignments/

<div style="page-break-after: always;"></div>

## Documentation of Changes
1. removed the `quoteserve.java` - no methods from this file were used
2. removed deploy file, changing it to a makefile for easier compilation
3. Created `CliMain.java` file for a main method to use for the command line
  - added a command line argument parsing loop
    - loop reads arguments and stores temporary information in variables
    - upon completion it opens the quotes file and passes the arguments to
      the other methods
  - added methods for getting a random quote, quote by index, and quote by terms
    - used the getRandom/getQuote/getSize/search methods for the already existing
      code
    - looped through the quote array returned by search and printed each of the quotes
4. Changed the `Quote.java` toString method to print the quote appropriately

## Maintainability Statment

- What did the original programmers do that made it hard change the software?
  - There was no readme file or documentation for the code.
  - This made it hard to understand how the code worked.
- What did the original programmers do that made it easy change the software?
  - They modularized the code, everything was kept in different files/classes.
  - This made it easier to search for the methods that we needed.
- What would you do differently if you did it again?
  - We might have made a GUI interface or possibly an interactive command line
    interface in addition to our current one.
  - Randomized the seed value in the quotes list so that we would not get the same
    [random] quote each time.

