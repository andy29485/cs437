# Assignment 4

### Contributors
- Andriy Zasypkin
- Hamza Khanane

### Type of interface
Command Line Interface (CLI)

### Location of source files
https://github.com/andy29485/swe437-quotes/

### Usage
`java CliMain [-f filename]` get random quote
`java CliMain [-f filename] -i|--index <quote number>` get quote with index
`java CliMain [-f filename] [<search terms>] [-a|--author|-q|--quote|-b|--both]`
`make test`

## Running Tests
```bash
$ make test
javac *.java  -cp .:junit-4.10.jar
java -cp ../:junit-4.10.jar quotes.QuoteListTest 
JUnit version 4.10
.....
Time: 0.059

OK (5 tests)
```

