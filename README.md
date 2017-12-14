# Java Objects Size Printer
Small Javaagent that prints sizes of Java objects.

Javaagent uses [Instrumentation.getObjectSize()](https://docs.oracle.com/javase/7/docs/api/java/lang/instrument/Instrumentation.html#getObjectSize(java.lang.Object)) method that returns an implementation-specific approximation of the amount of storage consumed by the object.

## Example
```
VertexRule    	24 bytes. Package: com.github.kornilova_l.algorithm_synthesis.grid2D.vertex_set_generator.rule
Companion     	16 bytes. Package: com.github.kornilova_l.algorithm_synthesis.grid2D.vertex_set_generator.rule
POSITION      	24 bytes. Package: com.github.kornilova_l.algorithm_synthesis.grid2D.vertex_set_generator.rule
TileSet       	32 bytes. Package: com.github.kornilova_l.algorithm_synthesis.grid2D.tiles.collections
Companion     	16 bytes. Package: com.github.kornilova_l.algorithm_synthesis.grid2D.tiles
Tile          	32 bytes. Package: com.github.kornilova_l.algorithm_synthesis.grid2D.tiles
DirectedGraph 	40 bytes. Package: com.github.kornilova_l.algorithm_synthesis.grid2D.tiles.collections
Part          	24 bytes. Package: com.github.kornilova_l.algorithm_synthesis.grid2D.tiles
Neighbourhood 	16 bytes. Package: com.github.kornilova_l.algorithm_synthesis.grid2D.tiles.collections
```

## Usage
Build jar with agent
```
# Linux
./gradlew jar

# Windows
gradlew.bat jar
```
Add javaagent to JVM
```
# following will print size of all classes
-javaagent:objects_size_printer_agent.jar

# following will print size of classes in some package 
# (including subpackages)
-javaagent:objects_size_printer_agent.jar=my.package
```

NOTE:  
This javaagent does not instrument classes that were loaded by classloaders without System Classloader in chain.