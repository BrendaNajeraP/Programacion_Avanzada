@echo off
echo Compilando y ejecutando el Sistema de Gestion de Libros...
echo.

rem Crear directorios
mkdir target\classes 2>nul
mkdir data 2>nul
mkdir lib 2>nul

rem Definir versión de Jackson
set VERSION=2.15.2

rem Descargar dependencias si no existen
if not exist "lib\jackson-databind-%VERSION%.jar" (
    echo Descargando dependencias...
    curl -# -L "https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-databind/%VERSION%/jackson-databind-%VERSION%.jar" -o "lib\jackson-databind-%VERSION%.jar"
    curl -# -L "https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-core/%VERSION%/jackson-core-%VERSION%.jar" -o "lib\jackson-core-%VERSION%.jar"
    curl -# -L "https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-annotations/%VERSION%/jackson-annotations-%VERSION%.jar" -o "lib\jackson-annotations-%VERSION%.jar"
)

rem Verificar dependencias
if not exist "lib\jackson-databind-%VERSION%.jar" (
    echo Error: Falta jackson-databind-%VERSION%.jar
    pause
    exit /b 1
)
if not exist "lib\jackson-core-%VERSION%.jar" (
    echo Error: Falta jackson-core-%VERSION%.jar
    pause
    exit /b 1
)
if not exist "lib\jackson-annotations-%VERSION%.jar" (
    echo Error: Falta jackson-annotations-%VERSION%.jar
    pause
    exit /b 1
)

rem Compilar
echo.
echo Compilando...
javac -d target/classes -cp "lib/*" -sourcepath src/main/java src/main/java/com/management/App.java

if errorlevel 1 (
    echo.
    echo Error de compilacion! Por favor revise los errores arriba.
    pause
    exit /b 1
)

rem Ejecutar
echo.
echo Iniciando aplicacion...
java -cp "target/classes;lib/*" com.management.App

pause
