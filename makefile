
all: compile
	
compile:
	javac *.java  -cp .:servlet.jar

run:
	echo TODO

clean:
	rm *class
