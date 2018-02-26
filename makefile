
JARS="junit.jar:hamcrest-core.jar"
all: compile

compile: compile_quotes compile_assignment05

compile_assignment05: junit.jar hamcrest-core.jar
	javac assignment05/*.java  -cp .:assignment05:$(JARS)

compile_quotes: junit.jar hamcrest-core.jar
	javac quotes/*.java  -cp .:quotes:$(JARS)

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

junit.jar:
	wget -O junit.jar https://search.maven.org/remotecontent?filepath=junit/junit/4.12/junit-4.12.jar

hamcrest-core.jar:
	wget -O hamcrest-core.jar https://search.maven.org/remotecontent?filepath=org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar

test: test_quotes test_assignment05

test_assignment05: compile_assignment05 junit.jar hamcrest-core.jar
	java -cp .:assignment05:$(JARS) CalTest

test_quotes: compile_quotes junit.jar hamcrest-core.jar
	java -cp .:$(JARS) quotes.QuoteListTest

clean:
	rm *class

