# uncomment for gnu make
DIRS := $(shell find . -name Makefile -print)

# uncomment for sun make
#DIRS :sh = find . -name Makefile -print

all:
	@CLASSPATH=`pwd`; \
	export CLASSPATH; \
	for name in $(DIRS); do \
	  if [ `dirname $$name` != "." ]; then \
	    PROJECT_ROOT=`pwd`; \
	    export PROJECT_ROOT; \
	    echo "Entering `dirname $$name`"; \
	    cd `dirname $$name`; \
	    make; \
	    cd $$PROJECT_ROOT; \
	    fi; \
	  done
dist:
	jar cf jdce.jar *.class *.txt *.doc *.dat
	jar cf samples.jar *.ckt *.rom *.pld

clean:
	find . -type f -name '*.class' -exec rm {} \;
