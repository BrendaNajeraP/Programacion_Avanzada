@echo off
echo Compilando y ejecutando el Sistema de Gestion de Libros...
echo.

rem Crear directorios necesarios
mkdir target\classes 2>nul
mkdir data 2>nul
mkdir lib 2>nul

rem Descargar dependencias si no existen
if not exist "lib\jackson-databind-2.15.2.jar" (
    echo Descargando dependencias...
    powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-databind/2.15.2/jackson-databind-2.15.2.jar' -OutFile 'lib\jackson-databind-2.15.2.jar'"
    powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-core/2.15.2/jackson-core-2.15.2.jar' -OutFile 'lib\jackson-core-2.15.2.jar'"
    powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-annotations/2.15.2/jackson-annotations-2.15.2.jar' -OutFile 'lib\jackson-annotations-2.15.2.jar'"
)

rem Limpiar compilación anterior
del /Q /S target\classes\* 2>nul

rem Compilar
echo Compilando archivos Java...
javac -d target/classes -cp "lib/*" -sourcepath src/main/java src/main/java/com/management/App.java

if errorlevel 1 (
    echo Error de compilacion!
    pause
    exit /b 1
)

rem Ejecutar
echo.
echo Iniciando la aplicacion...
java -cp "target/classes;lib/*" com.management.App

pause
