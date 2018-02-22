
all: compile

compile: compile_quotes compile_assignment05

compile_assignment05:
	javac assignment05/*.java  -cp .:assignment05:junit-4.10.jar

compile_quotes:
	javac quotes/*.java  -cp .:quotes:junit-4.10.jar

install:
	echo Moving .class files to web app directory
	mv quoteserve.class /home/offutt/CS/webapps/WEB-INF/classes/quotes/
	mv Quote.class /home/offutt/CS/webapps/WEB-INF/classes/quotes/
	mv QuoteList.class /home/offutt/CS/webapps/WEB-INF/classes/quotes/
	mv QuoteSaxHandler.class /home/offutt/CS/webapps/WEB-INF/classes/quotes/
	mv QuoteSaxParser.class /home/offutt/CS/webapps/WEB-INF/classes/quotes/
	echo Run from: http://cs.gmu.edu:8080/offutt/servlet/quotes.quoteserve
	echo Did not copy quotes.xml. It belongs in /webapps/offutt/WEB-INF/data.
	echo Sometimes I edit it there, sometimes here, so make sure the files
	echo are consistent.

run:
	echo TODO

test: test_quotes test_assignment05

test_assignment05: compile_assignment05
	java -cp .:assignment05:junit-4.10.jar CalTest

test_quotes: compile_quotes
	java -cp .:junit-4.10.jar quotes.QuoteListTest 

clean:
	rm *class

