@ECHO OFF
ECHO 1.Compile/Run dev profile
ECHO 2.Compile/Run prod profile
ECHO 3.Run jar:dev profile
ECHO 4.Run jar:prod profile
ECHO 5.Build Jar
ECHO.

SET /p option=Enter the option:
IF %option% ==5 GOTO buildJar
IF %option% ==4 GOTO runJarProd
IF %option% ==3 GOTO runJarDev
IF %option% ==2 GOTO compileProd
IF %option% ==1 GOTO compileDev
GOTO inAppropriateSelection

:inAppropriateSelection
ECHO Invaid choice given
GOTO End

:compileDev
ECHO Compile/Run dev profile
@ECHO ON
mvn spring-boot:run -Dspring-boot.run.profiles=dev
@ECHO OFF
GOTO End

:compileProd
ECHO Compile/Run prod profile
@ECHO ON
mvn spring-boot:run -Dspring-boot.run.profiles=prod
@ECHO OFF
GOTO End

:runJarDev
ECHO Run jar:dev profile
@ECHO ON
java -jar ./target/examroom-0.0.1-SNAPSHOT.jar --spring.config.location=./config/application.yml,./config/application-dev.yml
@ECHO OFF
GOTO End

:runJarProd
ECHO Run jar:prod profile
@ECHO ON
call java -jar ./target/examroom-0.0.1-SNAPSHOT.jar --spring.config.location=./config/application.yml,./config/application-prod.yml
@ECHO OFF
GOTO End

:buildJar
ECHO Build Jar
@ECHO ON
mvn package -DskipTests
@ECHO OFF
GOTO End

:End