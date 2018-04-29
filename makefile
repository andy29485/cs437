
JARS="junit.jar:hamcrest-core.jar:selenium.jar:htmlunit.jar:."
CP05="assignment05:tests/assignment05":$(JARS)
CP11="assignment05:tests/assignment11":$(JARS)
CP08="tests/assignment08":$(JARS)
CPQU="tests:.":$(JARS)
all: compile

# download jars
junit.jar:
	wget -O junit.jar https://search.maven.org/remotecontent?filepath=junit/junit/4.12/junit-4.12.jar

hamcrest-core.jar:
	wget -O hamcrest-core.jar https://search.maven.org/remotecontent?filepath=org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar

selenium.jar:
	wget -O selenium.jar https://goo.gl/Us5DnZ

htmlunit.jar:
	wget -O htmlunit.jar https://github.com/SeleniumHQ/htmlunit-driver/releases/download/2.30.0/htmlunit-driver-2.30.0-jar-with-dependencies.jar

# compile stuff
compile: compile_quotes compile_assignment05 compile_assignment11

compile_assignment05: junit.jar hamcrest-core.jar
	javac -cp $(CP05) assignment05/*.java tests/assignment05/*.java

compile_assignment08: junit.jar hamcrest-core.jar selenium.jar htmlunit.jar
	javac -cp $(CP08) tests/assignment08/*.java

compile_assignment11: junit.jar hamcrest-core.jar
	javac -cp $(CP11) assignment05/*.java tests/assignment11/*.java

compile_quotes: junit.jar hamcrest-core.jar
	javac -cp $(CPQU) quotes/*java tests/quotes/*.java

# Run tests
test: test_quotes test_assignment05

test_assignment05: compile_assignment05 junit.jar hamcrest-core.jar
	java -cp $(CP05) CalTest

test_assignment08: compile_assignment08 junit.jar hamcrest-core.jar selenium.jar htmlunit.jar
	java -cp $(CP08) WebQuoteTest

test_assignment11: compile_assignment11 junit.jar hamcrest-core.jar
	java -cp $(CP11) CalTest11

test_quotes: compile_quotes junit.jar hamcrest-core.jar
	java -cp $(CPQU) quotes.QuotesTestRunner

# MISC
clean:
	rm */*.class */*/*.class

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
