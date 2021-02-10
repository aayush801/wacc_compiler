# Locations
MAVEN := mvn

# make the compiler without running any tests
all:
	$(MAVEN) install -Dmaven.test.skip=true

# run all the compiler tests
test:
	$(MAVEN) test

# cleanup all generated files
clean:
	$(MAVEN) clean

.PHONY: all clean


