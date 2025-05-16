@echo off
echo Compilando y ejecutando el Sistema de Gestion de Libros...
echo.

rem Crear directorios
mkdir target\classes 2>nul
mkdir data 2>nul
mkdir lib 2>nul

rem Definir versión de Jackson
set VERSION=2.15.2

rem Intentar descargar usando PowerShell si las dependencias no existen
if not exist "lib\jackson-databind-%VERSION%.jar" (
    echo Descargando dependencias usando PowerShell...
    powershell -Command "& {
        $ProgressPreference = 'SilentlyContinue'
        Write-Host 'Descargando jackson-databind...'
        Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-databind/%VERSION%/jackson-databind-%VERSION%.jar' -OutFile 'lib\jackson-databind-%VERSION%.jar'
        Write-Host 'Descargando jackson-core...'
        Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-core/%VERSION%/jackson-core-%VERSION%.jar' -OutFile 'lib\jackson-core-%VERSION%.jar'
        Write-Host 'Descargando jackson-annotations...'
        Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-annotations/%VERSION%/jackson-annotations-%VERSION%.jar' -OutFile 'lib\jackson-annotations-%VERSION%.jar'
    }" || (
        echo Intentando descargar con curl...
        curl -k -L "https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-databind/%VERSION%/jackson-databind-%VERSION%.jar" -o "lib\jackson-databind-%VERSION%.jar"
        curl -k -L "https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-core/%VERSION%/jackson-core-%VERSION%.jar" -o "lib\jackson-core-%VERSION%.jar"
        curl -k -L "https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-annotations/%VERSION%/jackson-annotations-%VERSION%.jar" -o "lib\jackson-annotations-%VERSION%.jar"
    )
)

rem Verificar dependencias
if not exist "lib\jackson-databind-%VERSION%.jar" (
    echo Error: No se pudieron descargar las dependencias.
    echo Por favor, descargue manualmente los siguientes archivos:
    echo.
    echo 1. https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-databind/%VERSION%/jackson-databind-%VERSION%.jar
    echo 2. https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-core/%VERSION%/jackson-core-%VERSION%.jar
    echo 3. https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-annotations/%VERSION%/jackson-annotations-%VERSION%.jar
    echo.
    echo Y colóquelos en la carpeta 'lib'
    pause
    exit /b 1
)

rem Limpiar compilación anterior
del /Q /S target\classes\* 2>nul

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
