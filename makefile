
all: compile

compile: compile_quotes compile_assignment05

compile_assignment05:
	javac assignment05/*.java  -cp .:assignment05

compile_quotes:
	javac quotes/*.java  -cp .:quotes

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

junit:
	wget -O junit.jar http://central.maven.org/maven2/junit/junit/4.12/junit-4.12.jar


test: test_quotes test_assignment05

test_assignment05: compile_assignment05 junit
	java -cp .:assignment05:junit.jar CalTest

test_quotes: compile_quotes junit
	java -cp .:junit.jar quotes.QuoteListTest

clean:
	rm *class

