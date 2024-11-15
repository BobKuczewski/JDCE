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

apidocs:
	mkdir -p api
	find . -type f -name "*.java" | xargs javadoc -d api
dist:
	jar --create --verbose --file jdce.jar --main-class=dce.Main `find . -name "*.class" -print` `find . -name "*.txt" -print` `find . -name "*.doc" -print`
	jar --create --verbose --file samples.jar `find . -name "*.ckt" -print` `find . -name "*.rom" -print` `find . -name "*.pld" -print`
clean:
	find . -type f -name '*.class' -exec rm {} \;
	rm -rf api
