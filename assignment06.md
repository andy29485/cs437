# Assignment 6

### Contributors
- Andriy Zasypkin
- Hamza Khanane

### Type of interface
Command Line Interface (CLI)

### Location of source files
https://github.com/andy29485/swe437-assignments/

<div style="page-break-after: always;"></div>

## Assignment Write Up

### Considerations / Decisions
1. How many keywords are allowed per quote?
  - As many as needed (only constraint is memeory)
2. How many keywords are allowed in total?
  - As many as needed (only constraint is memeory)
3. Are keywords defined by users, or pre-defined by the software?
  - User defined
4. Does every quote have to have keywords?
  - No (0 keywords/tags is valid)
5. Can a keyword also be in the quote text?
  - Yes, but that will not be searched
6. How should keyword searching interact with the other searches?
  - Can be used in combination with regular search (treated as **and**)
7. When quotes are printed, should the keywords also be printed?
  - No, since they are not part of the quote

### Tests / Changes

- User Story - Users should be able to search for quotes by tags
  - Test 01 - no quotes returned by search in list for non-existant tag search
    - Changes
      - added `searchTags` method to `QuoteList` (return empty list)
    - Refactor
      - old `QuoteList` tests to use `assertEquals`
  - Test 02 - Quotes class should return true for an existing tag
    - Changes
      - added `hasTag` method to `Quote`
    - Refactor - None
  - Test 03 - `Quote`s class should return false if tag does not exist
    - Changes
      - added `tags` property to `Quote`
      - fixed `hasTag` to check if tags are equal
      - added constructor to support new property
    - Refactor - None
  - Test 04 - `Quote`s should have multiple tags
    - Changes
      - changed `tags` property to be a list
      - fixed constructor of `Quotes`
      - fixed `hasTag` to loop over
  - Test 05 - `QuoteList` finds matching quote
    - Changes
      - `searchTags` loops over quotes and constructs list
    - Refactor - None
  - Test 06 - Options recognize keywords (tags) argument (`-k`) for no args
    - Changes
      - added `keywords` param to `Options` class
    - Refactor
      - `otherArgs` to be a generic list instead of type ArrayList
  - Test 07 - recognize multiple `-k` arguments
    - Changes
      - fixed `parseArgs`
    - Refactor - None
  - Test 08 - CliMain should search for quotes when `-k` is given
    - Changes
      - `search` added to `CliMain` which also searches tags
      - `run` ammended to send `options.keywords` to new method
    - Refactor
      - `CliMain.quoteList` is now protected
      - `CliMain`'s methods print removed and put in seprate method
      - `QuoteList` is not an iteratable class
      - if at RANDOM, `parseArgs` goes to SEARCH if tags were given
- User Story - When adding a quote, user can specify tags
  - Test 01 - tag can be added
    - Changes
      - added `addTag` method to Quote
    - Refactor - None
  - Test 02 - tag can be found after being added
    - Changes
      - fixed `addTag`
    - Refactor - None
  - Test 03 - existing tag does not get added (no error)
    - Changes
      - fixed `addTag`
    - Refactor
      - `Quote`'s `tags` are now protected
  - Test 04 - XML gets saved by Quote
    - Changes
      - modified `QuoteList`'s `toXml`
    - Refactor - None
  - Test 05
    - Changes
      - added `QuoteTagElem` param to `QuoteList`
      - reverted `QuoteList`'s `toXml`
      - modified `Quote`'s `writeXml`
    - Refactor - No longer needed
  - Test 06 - XML gets loaded
    - Changes
      - minor changes to `QuoteSaxHandler`'s
        `characters` and `startElement` methods
    - Refactor - Added help to `Options` class for the `-k` arg

### Running
```bash
$ make test
JUnit version 4.10
....................
Time: 0.037

OK (20 tests)

$ java quotes.CliMain -k tag-dne

$ java quotes.CliMain -k tag1
Text 1
  --Author 1

$ java quotes.CliMain -k tag-all
Text 1
  --Author 1
Text 2
  --Author 2
Text 3
  --Author 3
Text 4
  --Author 4

$ java quotes.CliMain -k tag-new

$ java quotes.CliMain -A "New Auth" -t "New Text" -k tag-new

$ java quotes.CliMain -k tag-new
New Text
  --New Auth
```

