@echo off
setlocal enabledelayedexpansion

echo Compilando y ejecutando el Sistema de Gestion de Libros...
echo.

rem Crear directorios necesarios
if not exist target\classes mkdir target\classes
if not exist data mkdir data
if not exist lib mkdir lib

rem Descargar Jackson JARs si no existen
set "JACKSON_VERSION=2.15.2"
set "JACKSON_CORE=jackson-core-%JACKSON_VERSION%.jar"
set "JACKSON_DATABIND=jackson-databind-%JACKSON_VERSION%.jar"
set "JACKSON_ANNOTATIONS=jackson-annotations-%JACKSON_VERSION%.jar"

if not exist "lib\%JACKSON_DATABIND%" (
    echo Descargando dependencias Jackson...
    curl -L "https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-databind/%JACKSON_VERSION%/%JACKSON_DATABIND%" -o "lib\%JACKSON_DATABIND%"
    curl -L "https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-core/%JACKSON_VERSION%/%JACKSON_CORE%" -o "lib\%JACKSON_CORE%"
    curl -L "https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-annotations/%JACKSON_VERSION%/%JACKSON_ANNOTATIONS%" -o "lib\%JACKSON_ANNOTATIONS%"
)

rem Verificar que los archivos existen
if not exist "lib\%JACKSON_DATABIND%" (
    echo Error: No se pudo descargar %JACKSON_DATABIND%
    exit /b 1
)
if not exist "lib\%JACKSON_CORE%" (
    echo Error: No se pudo descargar %JACKSON_CORE%
    exit /b 1
)
if not exist "lib\%JACKSON_ANNOTATIONS%" (
    echo Error: No se pudo descargar %JACKSON_ANNOTATIONS%
    exit /b 1
)

rem Limpiar compilación anterior
del /Q /S target\classes\* 2>nul

rem Establecer classpath
set "CLASSPATH=target/classes;lib/%JACKSON_DATABIND%;lib/%JACKSON_CORE%;lib/%JACKSON_ANNOTATIONS%"

rem Compilar
echo Compilando archivos Java...
javac -d target/classes -cp "%CLASSPATH%" -sourcepath src/main/java src/main/java/com/management/App.java

if errorlevel 1 (
    echo.
    echo Error de compilacion! Por favor revise los errores.
    pause
    exit /b 1
)

rem Ejecutar la aplicación
echo.
echo Iniciando la aplicacion...
java -cp "%CLASSPATH%" com.management.App

pause
endlocal
