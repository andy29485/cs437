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

### User Stories
Add a feature in the software when i can search for the quotes using a
keyword. For example if i type A all the quotes with the tag A come up.
(incomplete)


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
  - CLI (`-k <word>` will look for word in keywords)
  - Can be used in combination with regular search (treated as **and**)
7. When quotes are printed, should the keywords also be printed?
  - No, since they are not part of the quote

### Tests / Changes
TODO: A document that presents your user story, then your first test, then a short description of the changes you made for the test, and a short description of the refactoring. This should be repeated, with one paragraph per test, until all tests are finished.
Don’t forget to consider refactoring your tests.

### Running
TODO: demo usage

