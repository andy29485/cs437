# Assignment 3

### Contributors
- Andriy Zasypkin
- Hamza Khanane

### Type of interface
Command Line Interface (CLI)

### Location of source files
https://github.com/andy29485/swe437-assignments/

<div style="page-break-after: always;"></div>

## Documentation Log
1. Normalized style
2. Change method `setQuote` to `addQuote` since that is more self documenting.
  - The word set implied that it was changed not appended.
3. Added `toXml` method to the `QuoteList` and `Quote` classes
  - uses an XML library so the input is validated properly and does
    not contain illegal values
4. Added `save` and `load` methods as well
5. Moved options class to it's own file (not internal class)
6. Changed formatting
7. Added arguments for adding a new quote

## Description
  On the fron end side, quotes were verified as agruments - guarenting
both the author and the text to be strings. On the back end (writing the file)
we used an XML building library, thus guarenting that the XML would escape any
characters that would invalidate it. In this way the integrity of the quotes is
maintained (and the user is able to put just about any string as the quote).

## Maintainability Assessment
  Another thing that we found that made it hard to maintain this code was a
poorly documented method `setQuote`. The name was missleading as the method
was not setting anything but rather adding quotes to a list. But there were
many methods with good names and useful functionality which made it easy to
understand what they do.

## Running
```bash
$ java quotes.CliMain --help
Quotes program usage:
General arguments:
  -h/--help          - this help page
  -f/--file <path>   - specify path to quotes file
  -i/--index <index> - print quote with given index
Search arguments:
  -a/--author        - search only author
  -q/--quote         - search only quotes
  -b/--both          - search both quote and author
Adding new quote arguments:
  -A/--Author        - Author of new Quote
  -t/--text          - text of new quotes

 Default mode (no args) will return a random quote
 When in search mode, all unmatched
   args will be part of the search

$ java quotes.CliMain 
A right is not what someone gives you; it's what no one can take from you.
  --Ramsey Clark

$ java quotes.CliMain -q sample

$ java quotes.CliMain -A "Author name" -t "sample quote"

$ java quotes.CliMain -i 8
My life is has been one continual search for answers to one short question. The subject and the answer changes, but the question remains simply" "Why?".
  --The Anonymous Researcher

$ java quotes.CliMain -i 9
sample quote
  --Author name

$ java quotes.CliMain -q sample
sample quote
  --Author name
```

