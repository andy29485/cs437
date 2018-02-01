# Command line interface for the SWE 437 Quote program

# Contributors (to this fork)
- Andriy Zasypkin
- Hamza Khanane

# Usage
`java CliMain [-f filename]` get random quote
`java CliMain [-f filename] -i|--index <quote number>` get quote with index
`java CliMain [-f filename] [<search terms>] [-a|--author|-q|--quote|-b|--both]`

# Documentation of Changes
1. removed the `quoteserve.java` - no methods from this file were used
2. removed deploy file, changing it to a makefile for easier compilation
3. Created `CliMain.java` file for a main method to use for the command line
  - added a command line argument parsing loop
  - added methods for getting a random quote, quote by index, and quote by terms
4. Changed the `Quote.java` toString method to print the quote appropriately
