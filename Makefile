# Sample Makefile for the WACC Compiler lab: edit this to build your own comiler
# Locations

ANTLR_DIR	:= antlr_config
SOURCE_DIR	:= src
OUTPUT_DIR	:= bin

# Tools
ANTLR	:= antlrBuild
FIND	:= find
RM	:= rm -rf
MKDIR	:= mkdir -p
JAVA	:= java
JAVAC	:= javac

JFLAGS	:= -sourcepath $(SOURCE_DIR) -d $(OUTPUT_DIR) -cp java -cp lib/*:

# the make rules

all: rules

# runs the antlr build script then attempts to compile all .java files within src/antlr
rules:
	cd $(ANTLR_DIR) && ./$(ANTLR) 
	$(FIND) $(SOURCE_DIR) -name '*.java' > $@
	$(MKDIR) $(OUTPUT_DIR)
	$(JAVAC) $(JFLAGS) @$@
	$(RM) $@

tests: rules
	$(JAVA) -cp bin:lib/*: org.junit.runner.JUnitCore tests.BasicCompilerTest
	$(RM) $@

clean:
	$(RM) rules $(OUTPUT_DIR) $(SOURCE_DIR)/antlr

.PHONY: all rules clean


