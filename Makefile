GS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	NeuralNetwork.java\
	NNRunner.java\
	Problem.java      

 
default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
	$(RM) *~
