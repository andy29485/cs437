# Command line interface for the SWE 437 Quote program

# Contributors (to this fork)
- Andriy Zasypkin
- Hamza Khanane

# Usage
`java CliMain` get random quote
`java CliMain -i|--index <quote number>` get quote with index
`java CliMain [<search terms>] [-a|--author|-q|--quote|-b|--both]`
search(both assumed)

# Documentation of Changes
1. removed the `quoteserve.java` - no methods from this file were used
2. removed deploy file, changing it to a makefile for easier compilation
3. Created `CliMain.java` file for a main method to use for the command line
