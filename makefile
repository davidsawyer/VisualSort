compile:
	@javac -d build src/*.java
run:
	@java -cp build VisualSort
jar:
	@cd build; jar cmf MainClass.txt VisualSort.jar *.class; mv VisualSort.jar ../releases; cd ..
clean:
	@rm build/*.class; rm .DS_Store; rm src/.DS_Store; rm build/.DS_Store; rm releases/.DS_Store;
