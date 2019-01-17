### Simple console version of a drawing program written in Java


To start the self-contained executable jar, double-click the `dist/console-draw-fat.jar` file. You need to have Java JDK 8 installed.

Enter command `help` to see all the possible drawing commands.

---

Or start the program with Gradle Wrapper:

	./gradlew run
	
Or start the fat jar file in terminal:

	java -jar dist/console-draw-fat.jar

---

Build self-contained jar:

	./gradlew shadowJar

